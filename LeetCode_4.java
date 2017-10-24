package practice;

/**
 *
 * <code>LeetCode Problem 4<code>
 * <p>
 * <li>There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).</li>
 * <li>Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * The median is 2.0</li>
 * <li>Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * The median is (2 + 3)/2 = 2.5</li>
 * </p>
 * 
 */

public class LeetCode_4 {
  public static void main(String[] args) {
    int[] nums1 =
        {115, 201, 509, 700, 738, 895, 1024, 1047, 1248, 1264, 1272, 1453, 1492, 1540, 1552, 1590,
            1611, 1692, 1703, 1769, 1911, 1952, 1966, 2021, 2144, 2357, 2374, 2458, 2474, 2499,
            2502, 2540, 2735, 2805, 2921, 2944, 3175, 3194, 3438, 3475, 3808, 3862, 3899, 4084,
            4161, 4268, 4480, 4528, 4592, 4645, 4725, 4774, 4872, 4987, 5100, 5184, 5358, 5377,
            5460, 5561, 5569, 5580, 5750, 5779, 5805, 5971, 6019, 6085, 6135, 6153, 6210, 6377,
            6472, 6612, 6631, 6640, 6664, 6871, 6929, 6957, 7092, 7158, 7190, 7297, 7396, 7551,
            7594, 7854, 7929, 7933, 8051, 8311, 8337, 8358, 8376, 8391, 8406, 8421, 8551, 8563,
            8579, 8594, 8609, 8749, 8889, 8957, 9000, 9013, 9046, 9117, 9148, 9251, 9336, 9532,
            9624, 9891, 9916, 10027, 10057, 10063, 10217, 10236, 10279, 10626, 10711, 10809, 10883,
            11108, 11176, 11190, 11340, 11383, 11513, 11584, 11669, 11693, 11880, 11984, 12029,
            12052, 12054, 12080, 12149, 12247, 12297, 12326, 12400, 12453, 12463, 12736, 12851,
            12963, 12999, 13185, 13233, 13287, 13552, 13605, 13686, 13701, 13718, 13725, 13959,
            14001, 14132, 14409, 14432, 14440, 14537, 14622, 14701, 14756, 14839, 14853, 14983,
            15063, 15117, 15283, 15370, 15588, 15909, 16157, 16259, 16418, 16448, 16641, 16653,
            16671, 16710, 16895, 16915, 16990, 16995, 17019, 17109, 17171, 17181, 17250, 17513,
            17568, 17620, 17672, 17722, 17827, 17936, 18007, 18037, 18064, 18081, 18222, 18695,
            18784, 18957, 19048, 19116, 19117, 19127, 19198, 19304, 19309, 19387, 19516, 19572,
            19642, 19788, 19900, 19922, 20000, 20046, 20186, 20726, 20776, 20920, 20998, 21200,
            21207, 21213, 21225, 21431, 21434, 21543, 21578, 21591, 21602, 21648, 21700, 21785,
            21840, 22014, 22017, 22036, 22078, 22136, 22247, 22250, 22361, 22438, 22483, 22493,
            22540, 22674, 22696, 22746, 22751, 22801, 22913, 22919, 22941, 22991, 23114, 23160,
            23210, 23274, 23356, 23427, 23642, 23712, 23781, 23794, 23799, 23804, 23824, 23931,
            24003, 24218, 24236, 24449, 24455, 24498, 24610, 24670, 24681, 24873, 24879, 24964,
            25026, 25076, 25095, 25148, 25171, 25198, 25259, 25276, 25283, 25661, 25870, 25883,
            25929, 26068, 26113, 26152, 26212, 26356, 26374, 26630, 26710, 26724, 26985, 27087,
            27090, 27109, 27275, 27285, 27338, 27353, 27518, 27618, 27684, 27822, 27879, 27981,
            28031, 28056, 28099, 28147, 28229, 28266, 28285, 28386, 28412, 28464, 28643, 28678,
            28718, 28735, 28848, 28936, 29170, 29175, 29220, 29251, 29386, 29405, 29496, 29562,
            29654, 29711, 29895, 30086, 30116, 30168, 30193, 30212, 30231, 30244, 30368, 30408,
            30558, 30722, 30779, 30790, 30806, 30817, 30900, 30919, 31090, 31106, 31293, 31357,
            31373, 31632, 31676, 31744, 31797, 31903, 32149, 32209, 32392, 32539, 32620, 32644,
            32744};
    int[] nums2 =
        {10, 269, 374, 413, 440, 612, 652, 864, 876, 1070, 1110, 1439, 1683, 2010, 2024, 2087,
            2136, 2285, 2705, 3018, 3112, 3279, 3412, 3521, 3644, 3664, 3760, 3860, 3927, 4022,
            4374, 4408, 4520, 4580, 4864, 5014, 5195, 5200, 5473, 5481, 5767, 5874, 6382, 6390,
            6472, 6480, 6548, 6562, 6624, 7021, 7062, 7086, 7089, 7190, 7256, 7551, 7954, 8180,
            8249, 8264, 8367, 8399, 8407, 8429, 8652, 8700, 8738, 9074, 9136, 9214, 9874, 10008,
            10028, 10060, 10225, 10237, 10334, 10354, 10464, 10879, 11096, 11188, 11483, 11619,
            11632, 11698, 11810, 11820, 11871, 11946, 12281, 12434, 12464, 12600, 12954, 13075,
            13163, 13360, 13365, 13429, 13654, 13679, 13818, 14029, 14067, 14188, 14343, 14436,
            14502, 14575, 14582, 14750, 14774, 14868, 15056, 15514, 15581, 15648, 16072, 16299,
            16348, 16499, 16576, 16596, 16752, 16795, 17106, 17163, 17206, 17647, 17716, 17958,
            18132, 18664, 18756, 18766, 18938, 18962, 19062, 19097, 19115, 19232, 19336, 19341,
            19412, 19558, 19661, 19683, 19872, 20134, 20215, 20245, 20476, 20958, 20968, 21040,
            21097, 21100, 21180, 21221, 21313, 21410, 21456, 21613, 21649, 21651, 21708, 21938,
            22160, 22244, 22249, 22286, 22304, 22378, 22412, 22572, 22580, 22681, 22890, 22987,
            23185, 23593, 23654, 24014, 24457, 24952, 25009, 25128, 25178, 25349, 25397, 25430,
            25435, 26134, 26215, 26251, 26263, 26307, 26391, 26507, 26613, 27053, 27059, 27075,
            27114, 27139, 27201, 27325, 27371, 27610, 27700, 28025, 28157, 28568, 28609, 28665,
            28681, 28866, 28987, 29466, 29521, 29600, 29664, 29794, 29901, 30118, 30145, 30263,
            30364, 30516, 30922, 30983, 31371, 31448, 31507, 31687, 32115, 32130, 32308, 32643};
    Solution s = getSolution();
    System.out.println(s.findMedianSortedArrays(nums1, nums2));
  }

  public static Solution getSolution() {
    return new LeetCode_4().new Solution();
  }

  /**
   * 
   * 这道题真的蛮难的，我花了差不多一天的时间，基本上靠自己写了出来，哈哈，最后有一点确实是看了Solution后才写出来，但是我发现我的解法和solution的思路基本一致。
   * 只是最后找到分界点后怎么进一步处理，我卡壳了
   * 
   * 好了，接下来，详细表述下我的解题思路：
   * 
   * 首先，我们需要明确，何谓median，主要的歧义在于，是数值的中间部分，还是数量的中间部分，这道题来说，是指数量的中间，也就是找到合并后的数组的最中间的一个数或者两个数
   * 使得左边的数和右边的数的数量相等。
   * 
   * ok，搞清楚题目的意思后，再看题，最简单的，将两个数组合并成一个数组，然后取中间的数就行了，时间复杂度为O(m+n),显然不可能这么简单，毕竟是标为hard的题目
   * 
   * 时间复杂度要求为log(m+n),好吧，一看到log就知道，肯定跟二分法挨边了，那么具体怎么个二分法呢？
   * 
   * 试着想想，要求左右两边的数据量相同，那么我对两个数组，先分别取到最中间的那部分数据（一个数或者两个数），这时候，两个数组左边和右边的数据量是相同的吧
   * 
   * 我们将找到的中间数据称为划分点，那么A和B数组就首先都有了各自的一个划分点，并且划分点左右的数据量是相等的。
   * 
   * 这两个划分点的数据有大小之分（如果是两个数，就取平均值），我们将小的假设为A数组，大的假设为B数组
   * 
   * 而我们需要移动这两个划分点，使得至始至终，A和B划分点左边的数据量都和右边的数据量相等，
   * 
   * 所以如果A的划分点向右移动一步，那么B的划分点就要向左移动一步，使得两边的数据量一致
   * 
   * 但是，怎么移动呢？
   * 
   * 首先，我们需要确定：A和B的划分点的最终位置只能是在A和B的初始划分点之间，这是因为，如果最终划分点，定在了A的最初划分点之前，那么A和B的划分点都是向左移动了，这样左右的数据量不可能一致
   * 
   * 如果定在了B的最初划分点之后，那么A和B的划分点都是向右移动了，这样左右数据量也不一致
   * 
   * 所以，我们需要将A的划分点，向右移动，以此来接近B的划分点，直到A的划分点与B的划分点接近（A的划分点始终要小于B的划分点）
   * 
   * 而移动A的同时，我们也不断向左移动B的划分点，直到A的划分点不能再向右移动了为止（A的划分点再向右移动就超过了B的划分点的时候）
   * 
   * 那么这样我们就可以用二分法，来确定A的划分点向右移动的步数
   * 
   * 因为最开始，A的划分点在中间，那么可以再向后半段的一半移动，B也向前移动同样的步数，如果发现A的新的划分点已经大于B的划分点了，A就缩短步数，只向后半段的四分之一移动，以此类推
   * 
   * 如果没有跨过B的划分点，则再继续二分，向后移动A的划分点
   * 
   * 最终直到找到接近的A和B的划分点
   * 
   * 再然后怎么处理，这里就是我卡壳的地方了，最后看了Solution后，恍然大悟
   * 
   * 找到A和B的划分点后，我们的数据就分为了两部分，A划分点左边数据+A的划分点数据+B划分点左边的数据 另一部分为，A划分点右边的数据+B划分点数据+B划分点右边的数据
   * 
   * 理论上，第一部分就是左边数据，第二部分就是右边数据，我们只要取左边部分的最大值和右边部分的最小值，就能得到中位数了
   * 
   * 但是因为存在中位数是两个还是一个的问题，所以这里情况比较复杂，我们分情况讨论
   * 
   * 当A的划分点和B的划分点都是奇数时：
   * 
   * 我们知道A的划分点是小于B的划分点的，但是我们不知道A的划分点和B的划分点之前那个数的大小，因此，左边部分的最大值就应该是max(A的划分点,B的划分点左边的数)
   * 
   * 当A的划分点是偶数个，而B的划分点是奇数时：
   * 
   * 那么左边部分多出来一个数，中位数肯定就出在左边部分，左边部分的最大值，就是max(A的划分点的第二个数，B的划分点左边的数)，但是，这里A的划分点的第二个数不一定就是A的划分点的第二个数，
   * 有可能是B的划分点的数，因为虽然我们知道B的划分点大于A的划分点，但是A的划分点是两个数，所以是B的划分点大于这两个数的平均值，但是有可能B的划分点小于A的划分点第二个数，
   * 这时候是要将B的划分点的数与A的划分点的第二个数进行对调的
   * 
   * 同理，当A的划分点是奇数个，而B的划分点是偶数个时，同上处理
   * 
   * 最复杂是A的划分点和B的划分点都是偶数个时：
   * 
   * 我们先要获得A的划分点的第二个值，通过比较A的划分点第二个数与B的划分点第一个数，取最小的，然后再和A的划分点第一个数相比，取最大的，作为A的划分点第二个数，然后取max(A的划分点第二个数
   * ，B的划分点左边的数)作为左边部分的最大值
   * 
   * 然后要获得B的划分点的第一个值，通过比较A的划分点第二个数与B的划分点第一个数，取最大的，然后再和B的划分点第二个数相比，取最小的，作为B的划分点第一个数，然后取min(B的划分点第一个数，
   * A的划分点右边的数)作为右边部分的最小值
   * 
   * 这样，就完成了。。。
   * 
   * 我觉得我可能过不久再看，我也看不懂了。。。就这样吧。。。
   * 
   */
  public class Solution {
    private class Entry {
      public double value;
      public int index;
      public boolean even;
      public int[] nums;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
      int len1 = nums1.length;
      int len2 = nums2.length;
      // 特殊处理，有一个数组为空，或者某个数组的最大值也小于另一个数组的最小值时
      if (len1 == 0 || len2 == 0)
        return specialHandle(nums1, nums2);
      if (nums1[len1 - 1] <= nums2[0])
        return specialHandle(nums1, nums2);
      if (nums2[len2 - 1] <= nums1[0])
        return specialHandle(nums2, nums1);

      // 找到A和B的初始划分点
      Entry median1 = findMedian(nums1, 0, len1 - 1);
      Entry median2 = findMedian(nums2, 0, len2 - 1);

      // 令A为初始划分点小的，B为初始划分点大的
      if (median1.value > median2.value) {
        Entry tmp = median1;
        median1 = median2;
        median2 = tmp;
      }

      // 如果A和B的初始划分点值相等，那么就直接返回
      if (median1.value == median2.value)
        return median1.value;

      // 确定A向后推移划分点的边界
      int endIndex = median1.nums.length - 1;

      while (!found(median1, median2)) {
        // 二分法，将A向右平移一半
        Entry rightMedian = getRightMedian(median1, endIndex);
        // 如果跨过了，说明不能再向右推移，所以就认为找到了
        if (rightMedian == null)
          break;

        // 计算A的划分点向右平移了多少步
        int step = getStep(median1, rightMedian);
        // 同时将B向左平移这么多步
        Entry leftEntry = this.getLeftEntry(median2, step);
        // 如果B左边没有这么多平移空间，那么将A的平移边界缩小，再继续
        if (leftEntry == null) {
          if (rightMedian.even)
            endIndex = rightMedian.index;
          else
            endIndex = rightMedian.index - 1;
          continue;
        }
        // 如果平移之后的A和B的划分点，满足，A的划分点小于B的划分点的值，则更新A和B的划分点，再继续循环
        if (rightMedian.value < leftEntry.value) {
          median1 = rightMedian;
          median2 = leftEntry;
          // 如果平移之后的A和B的划分点，满足，A的划分点大于B的划分点的值，则A移动的过多了，所以缩小A的右移边界
        } else if (rightMedian.value > leftEntry.value) {
          if (rightMedian.even)
            endIndex = rightMedian.index;
          else
            endIndex = rightMedian.index - 1;
          // 如果A的划分点等于B的划分点的值，则也相当于找到了，更新新的划分点，并退出循环
        } else {
          median1 = rightMedian;
          median2 = leftEntry;
          break;
        }
      }

      // 找到之后，就开始分四种情况讨论，获取左边部分最大值，和右边部分最小值
      return median(median1, median2);

    }

    private double median(Entry e1, Entry e2) {
      // 左右划分点都是单个数的情况
      if (!e1.even && !e2.even) {
        // 获取B左边的一个数
        Entry leftofe2 = this.getLeftOne(e2);
        // 获取A右边的一个数
        Entry rightofe1 = this.getRightOne(e1);
        int left;
        int right;
        // 如果B左边数不为空，则左边部分的最大值，为max(A划分点，B左边数)
        if (leftofe2 != null) {
          left = (int) Math.max(e1.value, leftofe2.value);
          // 如果B左边数为空，则左边部分最大值就是A划分点的值
        } else {
          left = (int) e1.value;
        }
        // 如果A右边数不为空，则右边部分的最小值，为min(B划分点，A右边数)
        if (rightofe1 != null) {
          right = (int) Math.min(rightofe1.value, e2.value);
          // 如果A右边数为空，则右边部分最小值就是B划分点的值
        } else {
          right = (int) e2.value;
        }
        return (left + right) / 2.0;
      }

      // 如果A和B的划分点都是两个数的情况
      if (e1.even && e2.even) {
        // 获取B左边的一个数
        Entry leftofe2 = this.getLeftOne(e2);
        // 获取A右边的一个数
        Entry rightofe1 = this.getRightOne(e1);
        int left;
        int right;

        // A划分点第二个数的确定
        int v1 = Math.max(e1.nums[e1.index], Math.min(e2.nums[e2.index], e1.nums[e1.index + 1]));
        // B划分点第一个数的确定
        int v2 =
            Math.min(e2.nums[e2.index + 1], Math.max(e2.nums[e2.index], e1.nums[e1.index + 1]));

        if (leftofe2 != null) {
          left = (int) Math.max(v1, leftofe2.value);
        } else {
          left = (int) v1;
        }

        if (rightofe1 != null) {
          right = (int) Math.min(rightofe1.value, v2);
        } else {
          right = (int) v2;
        }
        return (left + right) / 2.0;
      }

      if (e1.even && !e2.even) {
        Entry leftofe2 = this.getLeftOne(e2);

        int v = (int) Math.min(e1.nums[e1.index + 1], e2.value);

        if (leftofe2 != null) {
          return Math.max(leftofe2.value, v);
        } else {
          return v;
        }
      }

      if (!e1.even && e2.even) {
        Entry rightofe1 = this.getRightOne(e1);

        int v = (int) Math.max(e1.value, e2.nums[e2.index]);

        if (rightofe1 != null) {
          return Math.min(v, rightofe1.value);
        } else {
          return v;
        }
      }

      return 0;
    }

    private int getStep(Entry e1, Entry e2) {
      return e2.index - e1.index;
    }

    private Entry getRightMedian(Entry e, int endIndex) {
      int tmp = endIndex - e.index;
      if (e.even) {
        if (tmp <= 1)
          return null;
        else {
          Entry e2 = new Entry();
          e2.even = true;
          e2.nums = e.nums;
          e2.index = e.index + tmp / 2;
          e2.value = (e.nums[e2.index] + e.nums[e2.index + 1]) / 2.0;
          return e2;
        }
      } else {
        if (tmp <= 0) {
          return null;
        } else {
          Entry e2 = new Entry();
          e2.even = false;
          e2.nums = e.nums;
          e2.index = e.index + (tmp + 1) / 2;
          e2.value = e.nums[e2.index];
          return e2;
        }
      }
    }

    // 如果A右边和B左边都没有新的划分点了，那么就找到了划分点的最终位置
    private boolean found(Entry e1, Entry e2) {
      Entry e1Right = getRightEntry(e1, 1);
      Entry e2Left = getLeftEntry(e2, 1);
      if (e1Right == null && e2Left == null) {
        return true;
      } else {

        return false;
      }
    }

    private Entry getRightEntry(Entry e, int step) {
      if (e.even) {
        if (e.index + 1 + step >= e.nums.length)
          return null;
        else {
          Entry e2 = new Entry();
          e2.even = true;
          e2.index = e.index + step;
          e2.nums = e.nums;
          e2.value = (e.nums[e2.index] + e.nums[e2.index + 1]) / 2.0;
          return e2;
        }
      } else {
        if (e.index + step >= e.nums.length)
          return null;
        else {
          Entry e2 = new Entry();
          e2.even = false;
          e2.index = e.index + step;
          e2.nums = e.nums;
          e2.value = e.nums[e2.index];
          return e2;
        }
      }
    }

    private Entry getLeftEntry(Entry e, int step) {
      if (e.even) {
        if (e.index - step < 0)
          return null;
        else {
          Entry e2 = new Entry();
          e2.even = true;
          e2.index = e.index - step;
          e2.nums = e.nums;
          e2.value = (e.nums[e2.index] + e.nums[e2.index + 1]) / 2.0;
          return e2;
        }
      } else {
        if (e.index - step < 0)
          return null;
        else {
          Entry e2 = new Entry();
          e2.even = false;
          e2.index = e.index - step;
          e2.nums = e.nums;
          e2.value = e.nums[e2.index];
          return e2;
        }
      }
    }

    private Entry getLeftOne(Entry e) {
      Entry e2 = new Entry();
      e2.even = false;
      e2.index = e.index - 1;
      if (e2.index < 0)
        return null;
      e2.value = e.nums[e2.index];
      e2.nums = e.nums;
      return e2;
    }

    private Entry getRightOne(Entry e) {
      Entry e2 = new Entry();
      e2.even = false;
      if (e.even)
        e2.index = e.index + 2;
      else
        e2.index = e.index + 1;
      if (e2.index >= e.nums.length)
        return null;
      e2.value = e.nums[e2.index];
      e2.nums = e.nums;
      return e2;
    }

    private Entry findMedian(int[] num, int startIndex, int endIndex) {
      Entry e = new Entry();
      e.nums = num;
      int len = endIndex - startIndex + 1;
      if (len % 2 == 0) {
        e.index = startIndex + (len - 2) / 2;
        e.even = true;
        e.value = (num[e.index] + num[e.index + 1]) / 2.0;
      } else {
        e.index = startIndex + (len - 1) / 2;
        e.even = false;
        e.value = num[e.index];
      }
      return e;
    }

    /**
     * 
     * 如果数组1的最后一个数还比数组2的第一个数小，则直接算两个数组中间的那个数的位置即可
     */
    private double specialHandle(int[] nums1, int[] nums2) {
      int len1 = nums1.length;
      int len2 = nums2.length;
      int x = len1 - len2;
      // 如果数组1比数组2长，则中位数肯定在数组1中寻找
      if (x > 0) {
        // 如果数组长度为偶数
        if (x % 2 == 0) {
          int index = len2 + (x - 2) / 2;
          return (nums1[index] + nums1[index + 1]) / 2.0;
        } else {
          int index = len2 + (x - 1) / 2;
          return nums1[index];
        }
      } else if (x < 0) {
        if (x % 2 == 0) {
          int index = ((-x) - 2) / 2;
          return (nums2[index] + nums2[index + 1]) / 2.0;
        } else {
          int index = ((-x) - 1) / 2;
          return (nums2[index]);
        }
      } else {
        return (nums1[len1 - 1] + nums2[0]) / 2.0;
      }
    }
  }

}
