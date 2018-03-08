package practice;

/**
 *
 * <code>LeetCode Problem 25<code>
 * <p>
 * <li>Given a linked list, reverse the nodes of a linked list k at a time and return its modified
 * list.
 * <li>k is a positive integer and is less than or equal to the length of the linked list. If the
 * number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * <li>You may not alter the values in the nodes, only nodes itself may be changed.
 * <li>Only constant memory is allowed.
 * <li>For example,
 * <li>Given this linked list: 1->2->3->4->5
 * <li>For k = 2, you should return: 2->1->4->3->5
 * <li>For k = 3, you should return: 3->2->1->4->5
 * </p>
 * 
 */

public class LeetCode_25 {

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
   * 这题我的解法时间复杂度O(n),空间复杂度O(1)
   * 
   * 主要思想，每次找k个节点，将这k个节点翻转后，拼到前面的已经翻转完的链表上，
   * 
   * 直到不足k个节点后，就直接将后面的节点不翻转，拼接到前面的链表
   * 
   * 我们通过设置三个指针，一个tail指针，代表前面已经处理完的链表的尾部
   * 
   * 一个cur指针，代表当前要翻转的下一段k个节点的头节点
   * 
   * 一个pre指针，代表在翻转过程中的前一个节点
   * 
   * 这样，首先，从cur节点向后走，如果链表长度不够k个了，则直接将cur连接到tail后，代表处理完了，返回即可
   * 
   * 如果够k个，那么，就从cur开始，依次翻转这个k个节点
   * 
   * 翻转的过程为：
   * 
   * 首先，将pre置为null，然后设置一个临时节点记录下cur的next
   * 
   * 接着将cur的next置为pre，然后将pre 置为 cur，将cur置为临时节点
   * 
   * 最后都翻转完后，将tail的next指向最后一个pre，然后更新tail、cur
   * 
   * 下面有两个算法，思路一致，第二算法更简洁一点
   */
  class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
      ListNode start = new ListNode(0);

      ListNode tail = start;
      ListNode cur = head;

      while (true) {
        boolean b = false;

        ListNode c = cur;
        for (int i = 0; i < k - 1; i++) {
          ListNode next = c.next;
          c = c.next;
          if (next == null) {
            b = true;
            break;
          }
        }

        if (b) {
          tail.next = cur;
          break;
        } else {
          ListNode pre = null;
          c = cur;
          for (int i = 0; i < k; i++) {
            ListNode n = c.next;
            c.next = pre;
            pre = c;
            c = n;
          }
          tail.next = pre;
          tail = cur;
          cur = c;
        }

      }

      return start.next;
    }

    public ListNode reverseKGroup2(ListNode head, int k) {
      ListNode start = new ListNode(0);

      ListNode tail = start;
      ListNode cur = head;
      ListNode pre = null;

      while (true) {
        ListNode c = cur;

        int num = 0;
        while (c != null && num < k) {
          num++;
          c = c.next;
        }

        if (num < k) {
          tail.next = cur;
          break;
        } else {
          pre = null;
          c = cur;
          for (int i = 0; i < k; i++) {
            ListNode tmp = c.next;
            c.next = pre;
            pre = c;
            c = tmp;
          }
          tail.next = pre;
          tail = cur;
          cur = c;
        }
      }

      return start.next;
    }
  }
}
