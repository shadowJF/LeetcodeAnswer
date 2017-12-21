package practice;

/**
 *
 * <code>LeetCode Problem 9<code>
 * <p>
 * <li>Determine whether an integer is a palindrome. Do this without extra space.</li>
 * </p>
 * 
 */

public class LeetCode_9 {

  public static void main(String argv[]) {
    System.out.println(new LeetCode_9().new Solution().isPalindrome(-1458541));
  }



  /**
   * 这道题也属于比较简单的类型，首先第一想法就是把数字变成字符串，然后从头从尾依次取一个字符，判断是否相等
   * 
   * 但是人家说了不能用额外的空间，转化字符串肯定要用到额外的空间，虽然我们自己不用写转化的代码
   * 
   * 那么为什么一定要转化为字符串呢，我们直接利用int型，依次取最高位和最低位的数字，判断是否相等不就行了
   * 
   * 有些人说，不是字符串，我没法知道数字的长度，没法取最高位，
   * 
   * 但是int型最长也就2的31次方，也就是10位数字，我们直接从10位数字开始假设，去取最高位的数字，如果是0，说明该数字没有到10位，那么假设数字是9位
   * 
   * 直到取到最高位有值为止。
   * 
   * 最高位有值了，我们只需要再将数字模10，取最低位数字，然后判断是否相等即可。
   */
  class Solution {
    public boolean isPalindrome(int x) {
      if (x < 0) {
        return false;
      }

      boolean first = true;

      for (int i = 9; i >= 0;) {
        int head = x / pow(10, i);
        if (first && head == 0) {
          i--;
          continue;
        } else {
          first = false;
          int tail = x % 10;
          if (head == tail) {
            x = x % pow(10, i);
            if (x == 0)
              return true;
            x = x / 10;
            if (x == 0)
              return true;
            i = i - 2;
          } else {
            return false;
          }
        }
      }

      return true;

    }

    private int pow(int c, int n) {
      int result = 1;
      for (int i = 1; i <= n; i++) {
        result *= c;
      }
      return result;
    }

  }

}
