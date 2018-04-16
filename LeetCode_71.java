package practice;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * <code>LeetCode Problem 71<code>
 * <p>
 * <li>Given an absolute path for a file (Unix-style), simplify it.
 * <li>For example,
 * <li>path = "/home/", => "/home"
 * <li>path = "/a/./b/../../c/", => "/c"
 * <li>click to show corner cases.
 * <li>Corner Cases:
 * <li>Did you consider the case where path = "/../"?
 * <li>In this case, you should return "/".
 * <li>Another corner case is the path might contain multiple slashes '/' together, such as
 * "/home//foo/".
 * <li>In this case, you should ignore redundant slashes and return "/home/foo".
 * </p>
 * 
 */

public class LeetCode_71 {

  public static void main(String argv[]) {
    // // String[] tmp = {"Listen", "to", "many,", "speak", "to", "a", "few."};
    System.out.println(new LeetCode_71().new Solution().simplifyPath("/home/foo/./bar/"));
  }

  /**
   * 这道题，我最开始想的就是，利用正则，得到每两个/之间的字符串
   * 
   * 然后按照栈的方式，将这些字符串push到栈中。
   * 
   * 如果字符串是. 或者是空，则不push
   * 
   * 如果字符串是.. ，则pop栈顶元素
   * 
   * 如果字符串是其他，则push到栈里
   * 
   * 最后，将栈里的元素一个个取出来，拼接成路径即可
   */
  class Solution {
    public String simplifyPath(String path) {
      List<String> paths = new ArrayList<String>();
      path += "/";

      StringBuilder tmp = new StringBuilder();
      for (int i = 0; i < path.length(); i++) {
        if (path.charAt(i) == '/') {
          String s = tmp.toString();
          if (s.equals("") || s.equals(".")) {
            tmp = new StringBuilder();
            continue;
          } else if (s.equals("..")) {
            if (paths.size() > 0)
              paths.remove(paths.size() - 1);
          } else
            paths.add(s);
          tmp = new StringBuilder();
        } else {
          tmp.append(path.charAt(i));
        }
      }

      StringBuilder sb = new StringBuilder("/");
      for (String s : paths)
        sb.append(s).append("/");
      if (sb.length() > 1)
        sb.deleteCharAt(sb.length() - 1);
      return sb.toString();
    }
  }

}
