package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * <code>LeetCode Problem 23<code>
 * <p>
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its
 * complexity.
 * </p>
 * 
 */

public class LeetCode_23 {

  public static void main(String argv[]) {

  }

  public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  /**
   * 这道题，我按照下面的给出了两种思路，第一种超时了，第二种虽然过了，但是我感觉也不是最佳
   * 
   * 果然看了discussion发现最佳应该是吧O(knlogk)
   * 
   * 实现方式有两种，第一种，跟我的第一个算法的思路一样，但是我每次从k个node中找出最小的时候用的是遍历
   * 
   * 时间复杂度为k，而其实可以用到一种叫做PriorityQueue的数据结构，来存储这k个节点，这样每次获得最小的结点的时间复杂度就变为了logK
   * 
   * 所以时间复杂度减少为O(nklogk)
   * 
   * 第二种实现方式是：采用分而治之的思想，不是有k个链条吗，我先实现一个两个链条合并的算法，这个算法时间负责度为O(2n)
   * 
   * 那么对于k个链表，我们可以构造一个2叉树来表示他的merge过程，那么这个2叉树肯定是logk层，在每一层都要进行数次的两两合并
   * 
   * 我们不需要知道每一层要进行几次merge，但我们知道每一层的merge完后，时间复杂度肯定是O(kn),因为总共有kn个元素
   * 
   * 那么总时间复杂度，就是log(k)层，每一层的时间复杂度是nk,所以时间复杂度为O(nklogk)
   */
  class Solution {
    /**
     * 假设有lists中有k个链表，每个链表有n个元素，那么这个方法的时间复杂度是O(n*k^2),提交会超时
     */
    public ListNode mergeKLists(ListNode[] lists) {
      if (lists == null || lists.length == 0)
        return null;
      ListNode start = new ListNode(0);
      ListNode n = start;
      boolean done = false;
      while (!done) {
        int min = Integer.MAX_VALUE;
        int index = -1;
        done = true;
        for (int i = 0; i < lists.length; i++) {
          ListNode l = lists[i];
          if (l != null) {
            done = false;
            if (l.val <= min) {
              min = l.val;
              index = i;
            }
          }
        }

        if (!done) {
          n.next = lists[index];
          lists[index] = lists[index].next;
          n = n.next;
        }

      }
      return start.next;
    }

    /**
     * 最淳朴的思想，先将所有的node放到一个集合里，时间复杂度为O(kn)
     * 
     * 接着对node集合进行排序，时间复杂度为O(knlog(kn))
     * 
     * 接着将所有的结点按顺序串起来，时间复杂度为O(kn)
     * 
     * 所以综合下来时间复杂度为O(knlog(kn))
     * 
     * 比上面的算法要快，特别是当k很大时
     * 
     * 这个算法提交并不会超时
     */
    public ListNode mergeKLists2(ListNode[] lists) {
      if (lists == null || lists.length == 0)
        return null;
      List<ListNode> col = new ArrayList<ListNode>();
      for (int i = 0; i < lists.length; i++) {
        ListNode l = lists[i];
        while (l != null) {
          col.add(l);
          l = l.next;
        }
      }

      Collections.sort(col, new Comparator<ListNode>() {

        @Override
        public int compare(ListNode o1, ListNode o2) {
          return o1.val - o2.val;
        }

      });

      ListNode start = new ListNode(0);
      ListNode n = start;

      for (int i = 0; i < col.size(); i++) {
        n.next = col.get(i);
        n = n.next;
      }

      return start.next;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
      if (l1 == null)
        return l2;
      if (l2 == null)
        return l1;

      ListNode head = null;
      ListNode former = null;
      while (l1 != null && l2 != null) {
        if (l1.val > l2.val) {
          if (former == null)
            former = l2;
          else
            former.next = l2;
          if (head == null)
            head = former;
          else
            former = former.next;
          l2 = l2.next;
        } else {
          if (former == null)
            former = l1;
          else
            former.next = l1;
          if (head == null)
            head = former;
          else
            former = former.next;
          l1 = l1.next;
        }
      }
      if (l2 != null)
        l1 = l2;
      former.next = l1;
      return head;

    }

    public ListNode mergeKLists3(List<ListNode> lists) {
      if (lists.size() == 0)
        return null;
      if (lists.size() == 1)
        return lists.get(0);
      if (lists.size() == 2)
        return mergeTwoLists(lists.get(0), lists.get(1));
      return mergeTwoLists(mergeKLists3(lists.subList(0, lists.size() / 2)),
          mergeKLists3(lists.subList(lists.size() / 2, lists.size())));
    }

    public ListNode mergeKLists4(List<ListNode> lists) {
      if (lists == null || lists.size() == 0)
        return null;

      PriorityQueue<ListNode> queue =
          new PriorityQueue<ListNode>(lists.size(), new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
              if (o1.val < o2.val)
                return -1;
              else if (o1.val == o2.val)
                return 0;
              else
                return 1;
            }
          });

      ListNode dummy = new ListNode(0);
      ListNode tail = dummy;

      for (ListNode node : lists)
        if (node != null)
          queue.add(node);

      while (!queue.isEmpty()) {
        tail.next = queue.poll();
        tail = tail.next;

        if (tail.next != null)
          queue.add(tail.next);
      }
      return dummy.next;
    }

  }
}
