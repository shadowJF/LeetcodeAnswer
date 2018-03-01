package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * <code>LeetCode Problem 15<code>
 * <p>
 * <li>Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find
 * all unique triplets in the array which gives the sum of zero.
 * <li>Note: The solution set must not contain duplicate triplets.
 * <li>For example, given array S = [-1, 0, 1, 2, -1, -4],
 * <li>A solution set is
 * <li>[
 * <li>[-1, 0, 1],
 * <li>[-1, -1, 2]
 * <li>]
 * </p>
 * 
 */

public class LeetCode_15 {

  public static void main(String argv[]) {
    int[] nums = {-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6};
    System.out.println(new LeetCode_15().new Solution().threeSum(nums));
  }

  /**
   * 这道题，我的解法想法很简单
   * 
   * 首先对数组排序，快排，时间复杂度为nlogn
   * 
   * 接着，遍历一遍数组，把大于0的数放到一个数组里，把所有的0放到一个数组，把所有的负数取相反数后按从小到大放到一个数组里
   * 
   * 接着分情况讨论，假设有3个以上的0，那么可以把3个0的情况加到结果里
   * 
   * 如果有一个以上的0，那么三元组里可以有一个0，一个正数，一个负数，那么就对每个正数，去负数数组里找相等的负数，时间复杂度为O(n^2)
   * 
   * 如果用二分法查找，时间复杂度为nlogn
   * 
   * 最复杂的是第三种情况，要么一个正数两个负数，要么一个负数两个正数，因为我把负数取相反数然后按从小到大排序了，所以这两种情况实际上一致
   * 
   * 就是从第一个数组里找一个数，在第二个数组里找两个数相加等于第一个数
   * 
   * 那么遍历第一个数组，对每一个数，从第二个数组里从头开始遍历，找到一个就放到一个set中，如果第一个数减去该数在set中已存在，则找到了，将set中的那个数
   * 
   * 去掉，同时为了防止取到重复的，每当找到后，要将该数放到另一个set中，如果在该set中的是不能加到第一个set中的
   * 
   * 这样的时间复杂度也是O(n^2)
   * 
   * 而看了网友的解法，就很简单了 = =
   * 
   * 数组直接排序后，遍历数组，取每个元素作为三元组的第一个数，接着设置两个游标，一个指向下一个数，一个指向数组尾部的数
   * 
   * 判断两个游标所指的数相加是否等于第一个数，如果相等，则将两个游标向中间移动，直到游标所指的数与之前所指的数不同，或者游标相遇
   * 
   * 如果两个游标所致的数相加大于第一个数，则尾部游标向前移动一个，再看
   * 
   * 如果两个游标所指的数相加小于第一个数，则头部游标向后移动一个，再看
   * 
   * 时间复杂度也是O(n^2)
   */
  class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
      List<List<Integer>> result = new ArrayList<List<Integer>>();
      List<Integer> positive = new ArrayList<Integer>();
      List<Integer> negative = new ArrayList<Integer>();
      List<Integer> zero = new ArrayList<Integer>();
      Arrays.sort(nums);
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] < 0) {
          negative.add(nums[i]);
        } else if (nums[i] > 0) {
          positive.add(nums[i]);
        } else {
          zero.add(nums[i]);
        }
      }

      negative.clear();

      for (int i = nums.length - 1; i >= 0; i--) {
        if (nums[i] < 0) {
          negative.add(-nums[i]);
        }
      }


      if (zero.size() >= 3) {
        List<Integer> threeZero = new ArrayList<Integer>();
        threeZero.add(0);
        threeZero.add(0);
        threeZero.add(0);
        result.add(threeZero);
      }

      if (zero.size() >= 1) {
        Integer last = 0;
        for (int i = 0; i < positive.size(); i++) {
          Integer p = positive.get(i);
          if (p.equals(last)) {
            last = p;
            continue;
          }
          for (int j = 0; j < negative.size(); j++) {
            Integer n = negative.get(j);
            if (p.equals(n)) {
              result.add(this.getList(0, p, -n));
              break;
            }
          }
          last = p;
        }
      }

      this.getList(result, positive, negative, true);
      this.getList(result, negative, positive, false);

      return result;


    }

    private void getList(List<List<Integer>> result, List<Integer> one, List<Integer> two,
        boolean firstPositive) {
      Set<Integer> set = new HashSet<Integer>();
      Set<Integer> pairSet = new HashSet<Integer>();

      Integer last = 0;
      for (int i = 0; i < one.size(); i++) {
        Integer f = one.get(i);
        if (!last.equals(f)) {
          for (int j = 0; j < two.size(); j++) {
            Integer s = two.get(j);
            if (s >= f)
              break;
            if (set.contains(f - s)) {
              if (firstPositive)
                result.add(this.getList(f, -s, s - f));
              else
                result.add(this.getList(-f, s, f - s));
              pairSet.add(s);
              set.remove(f - s);
            } else {
              if (!pairSet.contains(s))
                set.add(s);
            }
          }
        }
        set.clear();
        pairSet.clear();
        last = f;
      }
    }

    private List<Integer> getList(Integer a, Integer b, Integer c) {
      List<Integer> t = new ArrayList<Integer>();
      t.add(a);
      t.add(b);
      t.add(c);
      return t;
    }


    public List<List<Integer>> threeSum2(int[] num) {
      Arrays.sort(num);
      List<List<Integer>> res = new LinkedList<>();
      for (int i = 0; i < num.length - 2; i++) {
        if (i == 0 || (i > 0 && num[i] != num[i - 1])) {
          int lo = i + 1, hi = num.length - 1, sum = 0 - num[i];
          while (lo < hi) {
            if (num[lo] + num[hi] == sum) {
              res.add(Arrays.asList(num[i], num[lo], num[hi]));
              while (lo < hi && num[lo] == num[lo + 1])
                lo++;
              while (lo < hi && num[hi] == num[hi - 1])
                hi--;
              lo++;
              hi--;
            } else if (num[lo] + num[hi] < sum)
              lo++;
            else
              hi--;
          }
        }
      }
      return res;
    }

  }


}
