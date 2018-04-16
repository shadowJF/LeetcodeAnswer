package practice;

/**
 *
 * <code>LeetCode Problem 61<code>
 * <p>
 * <li>Given a list, rotate the list to the right by k places, where k is non-negative.
 * <li>Example:
 * <li>Given 1->2->3->4->5->NULL and k = 2,
 * <li>return 4->5->1->2->3->NULL.
 * </p>
 * 
 */

public class LeetCode_61 {

  public static void main(String argv[]) {
    // System.out.println(new LeetCode_61().new Solution().rotateRight(3, 2));
  }



  public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  /**
   * 这道题，我看到的时候一下就想到了之前的用一块一慢两个指针的方法
   * 
   * 首先，这道题就是要找到倒数第k的节点，让他成为新的头，并让尾节点链到原来的头结点
   * 
   * 这样，用一块一慢的指针很容易就做到
   * 
   * 但是，没想到，k居然有可能大于链表的长度，这时需要先计算一遍链表的长度，然后再用k去除以链表长度，取余，得到新的k
   */
  class Solution {
    public ListNode rotateRight(ListNode head, int k) {
      if (head == null || head.next == null)
        return head;

      ListNode tmp = head;
      int len = 1;
      while (tmp.next != null) {
        len++;
        tmp = tmp.next;
      }
      k = k % len; // k会出现大于等于len的情况，需要取余数

      if (k == 0)
        return head;

      ListNode fast = head, slow = head;
      while (fast.next != null) {
        fast = fast.next;
        if (k-- <= 0)
          slow = slow.next;
      }

      ListNode newHead = slow.next;
      fast.next = head;
      slow.next = null;
      return newHead;
    }
  }

}
