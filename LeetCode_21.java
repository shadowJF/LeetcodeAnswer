package practice;

/**
 *
 * <code>LeetCode Problem 21<code>
 * <p>
 * <li>Merge two sorted linked lists and return it as a new list. The new list should be made by
 * splicing together the nodes of the first two lists.
 * <li>Example:
 * <li>Input: 1->2->4, 1->3->4
 * <li>Output: 1->1->2->3->4->4
 * </p>
 * 
 */

public class LeetCode_21 {

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
   * 这道题确实也简单，就是从头开始遍历两个链表，比较两个链表的当前值，将较小的加到新链表后面
   */
  class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
      ListNode start = new ListNode(0);
      ListNode n = start;
      while (l1 != null || l2 != null) {
        if (l1 == null) {
          n.next = l2;
          break;
        }
        if (l2 == null) {
          n.next = l1;
          break;
        }

        if (l1.val <= l2.val) {
          n.next = l1;
          l1 = l1.next;
          n = n.next;
        } else {
          n.next = l2;
          l2 = l2.next;
          n = n.next;
        }
      }
      return start.next;
    }
  }
}
