package practice;

/**
 *
 * <code>LeetCode Problem 24<code>
 * <p>
 * <li>Given a linked list, swap every two adjacent nodes and return its head. For example, Given
 * <li>1->2->3->4, you should return the list as 2->1->4->3. Your algorithm should use only constant
 * <li>space. You may not modify the values in the list, only nodes itself can be changed.
 * </p>
 * 
 */

public class LeetCode_24 {

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
   * 这道题就是先设置一个指针，判断这个指针后两个节点是否为空，如果都不为空
   * 
   * 那么需要交换他们的位置
   * 
   * 交换位置时，需要注意，有三步
   * 
   * 第一步：将第一个节点的next指向第二个节点的next
   * 
   * 第二部：将第二个节点的next指向第一个节点
   * 
   * 第三步：将指针所指节点的next指向第二个节点
   */
  class Solution {
    public ListNode swapPairs(ListNode head) {
      ListNode start = new ListNode(0);
      ListNode n = start;
      n.next = head;

      while (n.next != null && n.next.next != null) {
        ListNode second = n.next.next;
        n.next.next = n.next.next.next;
        second.next = n.next;
        n.next = second;
        n = second.next;
      }

      return start.next;

    }
  }
}
