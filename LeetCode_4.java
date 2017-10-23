package practice;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * <code>LeetCode Problem 4<code>
 * <p>
 * <li>There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).</li>
 * <li>Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * The median is 2.0</li>
 * <li>Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * The median is (2 + 3)/2 = 2.5</li>
 * </p>
 * 
 */

public class LeetCode_4 {

  public static void main(String[] args) {
    int[] nums1 = {5, 7, 8};
    int[] nums2 = {1, 2, 3, 4, 6};
    Solution s = getSolution();
    System.out.println(s.findMedianSortedArrays(nums1, nums2));
  }

  public static Solution getSolution() {
    return new LeetCode_4().new Solution();
  }

  public class Solution {
    private class Entry {
      public double value;
      public int index;
      public boolean even;
      public int[] nums;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
      int len1 = nums1.length;
      int len2 = nums2.length;
      if (len1 == 0 || len2 == 0)
        return specialHandle(nums1, nums2);
      if (nums1[len1 - 1] <= nums2[0])
        return specialHandle(nums1, nums2);
      if (nums2[len2 - 1] <= nums1[0])
        return specialHandle(nums2, nums1);

      if (len1 > len2) {
        int[] tmp = nums1;
        nums1 = nums2;
        nums2 = tmp;
      }

      int max1IndexIn2 = getMax1IndexIn2(nums1, nums2);

      Entry median1 = findMedian(nums1, 0, len1 - 1);
      Entry median2 = findMedian(nums2, 0, len2 - 1);

      if (median1.value > median2.value) {
        Entry tmp = median1;
        median1 = median2;
        median2 = tmp;
      }

      int endIndex = median1.nums.length - 1;

      while (!found(median1, median2)) {
        Entry rightMedian = getRightMedian(median1, endIndex);
        if (rightMedian == null)
          break;
        int step = getStep(median1, rightMedian);
        Entry leftEntry = this.getLeftEntry(median2, step);
        if (leftEntry == null)
          break;
        if (rightMedian.value < leftEntry.value) {
          median1 = rightMedian;
          median2 = leftEntry;
        } else if (rightMedian.value > leftEntry.value) {
          endIndex = rightMedian.index - 1;
        } else {
          median1 = rightMedian;
          median2 = leftEntry;
          break;
        }
      }

      return median(median1, median2);

    }

    private double median(Entry e1, Entry e2) {
      ArrayList<Integer> tmp = new ArrayList<Integer>();
      tmp.add(e1.nums[e1.index]);
      tmp.add(e2.nums[e2.index]);
      if (e1.even)
        tmp.add(e1.nums[e1.index + 1]);
      if (e2.even)
        tmp.add(e2.nums[e2.index + 1]);

      Collections.sort(tmp);

      if (tmp.size() == 2) {
        return (tmp.get(0) + tmp.get(1)) / 2.0;
      }

      if (tmp.size() == 3) {
        return tmp.get(1);
      }

      if (tmp.size() == 4) {
        return (tmp.get(2) + tmp.get(1)) / 2.0;
      }

      return 0;// 不存在
    }

    private int getStep(Entry e1, Entry e2) {
      return e2.index - e1.index;
    }

    private Entry getRightMedian(Entry e, int endIndex) {
      int tmp = endIndex - e.index;
      if (e.even) {
        if (tmp <= 1)
          return null;
        else {
          Entry e2 = new Entry();
          e2.even = true;
          e2.nums = e.nums;
          e2.index = e.index + tmp / 2;
          e2.value = (e.nums[e2.index] + e.nums[e2.index + 1]) / 2.0;
          return e2;
        }
      } else {
        if (tmp <= 0) {
          return null;
        } else {
          Entry e2 = new Entry();
          e2.even = false;
          e2.nums = e.nums;
          e2.index = e.index + (tmp + 1) / 2;
          e2.value = e.nums[e2.index];
          return e2;
        }
      }
    }

    private boolean found(Entry e1, Entry e2) {
      Entry e1Right = getRightEntry(e1, 1);
      Entry e2Left = getLeftEntry(e2, 1);
      if (e1Right == null || e2Left == null) {
        return true;
      } else {
        if (e1Right.value > e2.value || e2Left.value < e1.value)
          return true;
        else
          return false;
      }
    }

    private Entry getRightEntry(Entry e, int step) {
      if (e.even) {
        if (e.index + 1 + step >= e.nums.length)
          return null;
        else {
          Entry e2 = new Entry();
          e2.even = true;
          e2.index = e.index + step;
          e2.nums = e.nums;
          e2.value = (e.nums[e2.index] + e.nums[e2.index + 1]) / 2.0;
          return e2;
        }
      } else {
        if (e.index + step >= e.nums.length)
          return null;
        else {
          Entry e2 = new Entry();
          e2.even = false;
          e2.index = e.index + step;
          e2.nums = e.nums;
          e2.value = e.nums[e2.index];
          return e2;
        }
      }
    }

    private Entry getLeftEntry(Entry e, int step) {
      if (e.even) {
        if (e.index - step < 0)
          return null;
        else {
          Entry e2 = new Entry();
          e2.even = true;
          e2.index = e.index - step;
          e2.nums = e.nums;
          e2.value = (e.nums[e2.index] + e.nums[e2.index + 1]) / 2.0;
          return e2;
        }
      } else {
        if (e.index - step < 0)
          return null;
        else {
          Entry e2 = new Entry();
          e2.even = false;
          e2.index = e.index - step;
          e2.nums = e.nums;
          e2.value = e.nums[e2.index];
          return e2;
        }
      }
    }

    private Entry findMedian(int[] num, int startIndex, int endIndex) {
      Entry e = new Entry();
      e.nums = num;
      int len = endIndex - startIndex + 1;
      if (len % 2 == 0) {
        e.index = startIndex + (len - 2) / 2;
        e.even = true;
        e.value = (num[e.index] + num[e.index + 1]) / 2.0;
      } else {
        e.index = startIndex + (len - 1) / 2;
        e.even = false;
        e.value = num[e.index];
      }
      return e;
    }

    /**
     * 
     * 如果数组1的最后一个数还比数组2的第一个数小，则直接算两个数组中间的那个数的位置即可
     */
    private double specialHandle(int[] nums1, int[] nums2) {
      int len1 = nums1.length;
      int len2 = nums2.length;
      int x = len1 - len2;
      // 如果数组1比数组2长，则中位数肯定在数组1中寻找
      if (x > 0) {
        // 如果数组长度为偶数
        if (x % 2 == 0) {
          int index = len2 + (x - 2) / 2;
          return (nums1[index] + nums1[index + 1]) / 2.0;
        } else {
          int index = len2 + (x - 1) / 2;
          return nums1[index];
        }
      } else if (x < 0) {
        if (x % 2 == 0) {
          int index = ((-x) - 2) / 2;
          return (nums2[index] + nums2[index + 1]) / 2.0;
        } else {
          int index = ((-x) - 1) / 2;
          return (nums2[index]);
        }
      } else {
        return (nums1[len1 - 1] + nums2[0]) / 2.0;
      }
    }
  }

}
