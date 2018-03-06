package practice;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * <code>LeetCode Problem 19<code>
 * <p>
 * <li>Given a linked list, remove the nth node from the end of list and return its head.
 * <li>For example,
 * <li>Given linked list: 1->2->3->4->5, and n = 2.
 * <li>After removing the second node from the end, the linked list becomes 1->2->3->5.
 * <li>Note:
 * <li>Given n will always be valid.
 * <li>Try to do this in one pass.
 * </p>
 * 
 */

public class LeetCode_19 {

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
   * 又到了经典的链表题目了
   * 
   * 这一题，我最开始的想法是，既然只能循环一遍列表，那我将每一个结点都放到一个map里，key为他的位置
   * 
   * 最后根据n确定待删除结点，然后将他删除
   * 
   * 虽然时间复杂度满足了，但是空间复杂度确为O(n)
   * 
   * 后来看了下别人的解法，确实很棒，果然又是一块一慢，两个指针。。（好多链表题目都是这样解）
   * 
   * 新增一个start结点连到最开头，然后fast从start先开始走n+1步，slow还是在start
   * 
   * 然后slow和fast一起走，知道fast走到尾，这时显然，fast比slow快了n+1步，所以待删除结点就是slow的下一个
   */
  class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
      Map<Integer, ListNode> map = new HashMap<Integer, ListNode>();
      if (n == 0)
        return head;

      ListNode node = head;
      Integer index = 0;

      while (node != null) {
        index++;
        map.put(index, node);
        node = node.next;
      }

      int tarIndex = index - n + 1;

      if (tarIndex == 1) {
        return map.get(2);
      } else {
        map.get(tarIndex - 1).next = map.get(tarIndex).next;
        return head;
      }

    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {

      ListNode start = new ListNode(0);
      ListNode slow = start, fast = start;
      slow.next = head;

      // Move fast in front so that the gap between slow and fast becomes n
      for (int i = 1; i <= n + 1; i++) {
        fast = fast.next;
      }
      // Move fast to the end, maintaining the gap
      while (fast != null) {
        slow = slow.next;
        fast = fast.next;
      }
      // Skip the desired node
      slow.next = slow.next.next;
      return start.next;
    }
  }
}
