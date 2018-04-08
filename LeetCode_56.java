package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * <code>LeetCode Problem 56<code>
 * <p>
 * <li>Given a collection of intervals, merge all overlapping intervals.
 * <li>For example,
 * <li>Given [1,3],[2,6],[8,10],[15,18],
 * <li>return [1,6],[8,10],[15,18].
 * </p>
 * 
 */

public class LeetCode_56 {

  public static void main(String argv[]) {
    System.out.println(new LeetCode_56().new Solution().merge(null));
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

  class Solution {
    public List<Interval> merge(List<Interval> intervals) {
      Collections.sort(intervals, new Comparator<Interval>() {
        @Override
        public int compare(Interval o1, Interval o2) {
          return o1.start - o2.start;
        }
      });

      List<Interval> result = new ArrayList<Interval>();
      if (intervals.size() == 0)
        return result;

      int start = intervals.get(0).start, end = intervals.get(0).end;

      for (int i = 0; i < intervals.size(); i++) {
        if (intervals.get(i).start > end) {
          result.add(new Interval(start, end));
          start = intervals.get(i).start;
          end = intervals.get(i).end;
        } else {
          if (intervals.get(i).end > end) {
            end = intervals.get(i).end;
          }
        }
      }
      result.add(new Interval(start, end));
      return result;
    }
  }
}
