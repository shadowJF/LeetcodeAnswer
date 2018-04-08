package practice;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * <code>LeetCode Problem 57<code>
 * <p>
 * <li>Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if
 * necessary).
 * <li>You may assume that the intervals were initially sorted according to their start times.
 * <li>Example 1:
 * <li>Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 * <li>Example 2:
 * <li>Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 * <li>This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 * </p>
 * 
 */

public class LeetCode_57 {

  public static void main(String argv[]) {
    System.out.println(new LeetCode_57().new Solution().insert(null, null));
  }



  public class Interval {
    int start;
    int end;

    Interval() {
      start = 0;
      end = 0;
    }

    Interval(int s, int e) {
      start = s;
      end = e;
    }
  }

  /**
   * 这题我是用寻找插入进去的区间的start和end来入手
   * 
   * 初始化start 和 end 都为null，代表还没找到
   * 
   * 接着遍历区间数组
   * 
   * 首先，如果start为null，说明，start还没找到
   * 
   * 需要判断，当前interval的start和待插入的区间的start的大小
   * 
   * 如果当前区间的start大于新区间的start，那么说明，插进去后的start肯定为新区间的start
   * 
   * 如果小于新区建的start，那么需要判断新区间的start是否小于等于当前区间的end
   * 
   * 如果是，则start为当前区间的start
   * 
   * 否则，start还是为空
   * 
   * 接下来，如果start不为空了
   * 
   * 我们需要判断end的位置
   * 
   * 如果新区间的end小于当前区间的start，name插入后的区间的end就确定了，就是新区建的end
   * 
   * 否则，判断新区间的end是否小于等于当前区间的end
   * 
   * 若是，则end 为当前区间的end
   * 
   * 否则，end还是null，无法确定
   * 
   * 最后，当start和end都确定了 之后，我们要判断是否end小于当前区间的start 若是，则说明当前区间并不在覆盖范围内，所以要加到结果集
   * 
   * 最后循环出来后，再次判断start和end 的状态
   * 
   * 若都为空，则新区间未和任何已有区间覆盖，直接加到结果集
   * 
   * 若start不为空，而end为空
   * 
   * 说明新区间覆盖了start之后的所有区间，那么直接new一个新的区间加到结果集
   */
  class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
      List<Interval> result = new ArrayList<Interval>();
      Integer start = null, end = null;
      for (int i = 0; i < intervals.size(); i++) {
        if (start == null) {
          if (intervals.get(i).start > newInterval.start)
            start = newInterval.start;
          else {
            if (newInterval.start <= intervals.get(i).end)
              start = intervals.get(i).start;
            else
              result.add(intervals.get(i));
          }
        }

        if (start != null && end == null) {
          if (newInterval.end < intervals.get(i).start) {
            end = newInterval.end;
            result.add(new Interval(start, end));
          } else if (newInterval.end <= intervals.get(i).end) {
            end = intervals.get(i).end;
            result.add(new Interval(start, end));
          }
        }

        if (start != null && end != null) {
          if (end < intervals.get(i).start)
            result.add(intervals.get(i));
        }

      }

      if (start == null && end == null) {
        result.add(newInterval);
      } else if (start != null && end == null) {
        result.add(new Interval(start, newInterval.end));
      }

      return result;
    }
  }
}
