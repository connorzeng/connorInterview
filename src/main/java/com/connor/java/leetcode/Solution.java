package com.connor.java.leetcode;

import com.connor.java.common.ListNode;
import org.w3c.dom.NodeList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import static com.connor.java.sort.Sort.doQuickSort;
import static com.connor.java.sort.Sort.swap;

public class Solution {

    /**
     * 这里是错误的示范
     *
     * @param param
     * @return
     */
    public static int lengthOfLongestSubstringBak(String param) {

        if (param.length() <= 1) {
            return param.length();
        }

        // 记录最长的字符长度
        int maxLength = 0;
        // 记录重复字符
        Set<Character> rp = new HashSet<>();

        // 进行遍历
        int f = 0;
        char[] params = param.toCharArray();
        while (f < params.length) {
            if (rp.contains(params[f])) {
                rp.clear();
                rp.add(params[f]);
            } else {
                rp.add(params[f]);
                if (rp.size() > maxLength) {
                    maxLength = rp.size();
                }
            }
            f++;
        }
        return maxLength;
    }

    public static int lengthOfLongestSubstring(String param) {

        if (param.length() <= 1) {
            return param.length();
        }

        // 记录最长的字符长度
        int maxLength = 0;

        // 进行遍历
        int f = 1;
        int l = 0;
        char[] params = param.toCharArray();
        while (f < params.length) {
            int index = l;
            while (index < f) {
                if (params[f] == params[index]) {
                    l = index + 1;
                    break;
                }
                index++;
            }
            if ((f - l) + 1 > maxLength) {
                maxLength = (f - l) + 1;
            }
            f++;
        }
        return maxLength;
    }

    public static ListNode reverseListBak(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = head.next;
        ListNode pre = head;
        head.next = null;

        while (cur.next != null) {
            ListNode next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;

        }

        cur.next = pre;
        return cur;
    }

    public static ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode temp = cur.next;
            // 指针
            cur.next = pre;

            // forward
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    // ---------
    private int size;
    private int cap;

    private Node head;
    private Node tail;

    static class Node {
        public Node pre;
        public Node next;
        public int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public int findKthLargestBak(int[] nums, int k) {
        cap = k;

        for (int i = 0; i < nums.length; i++) {
            addToList(nums[i]);
            delTail();
        }

        return tail.value;
    }

    private void delTail() {
        if (size > cap) {
            size--;

            Node n = tail.pre;
            if (n == null) {
                tail = head;
                return;
            }
            tail = tail.pre;
            tail.next = null;
        }

    }

    private void addToList(int num) {
        size++;
        if (head == null) {
            head = new Node(num);
            tail = head;
            return;
        }

        if (num >= head.value) {
            Node n = new Node(num);
            n.next = head;
            head.pre = n;
            head = n;
            return;
        }
        if (num <= tail.value) {
            Node n = new Node(num);
            n.pre = tail;
            tail.next = n;
            tail = n;
            return;
        }

        Node cur = head.next;
        while (cur != null) {
            Node next = cur.next;
            Node pre = cur.pre;
            if (num >= cur.value) {
                Node n = new Node(num);
                pre.next = n;
                n.pre = pre;
                n.next = cur;
                cur.pre = n;
                break;
            }
            cur = next;
        }
    }

    public int findKthLargest(int[] nums, int k) {
        int i = doQuickSortK(nums, 0, nums.length - 1, k - 1);
        return nums[i];
    }

    private int doQuickSortK(int[] nums, int low, int high, int k) {
        if (low >= high) {
            return low;
        }
        int i = low;
        int j = high;
        int p = nums[low]; //pivot随意设置

        while (i < j) {
            // 右边找到比p大的
            while (i < j && p >= nums[j]) {
                j--;
            }
            // 左边找到比p小的
            while (i < j && p <= nums[i]) {
                i++;
            }
            swap(nums, i, j);
        }

        nums[low] = nums[i];
        nums[i] = p; // 将基准移动到中间。

        if (k > i) {
            return doQuickSortK(nums, i + 1, high, k);
        } else if (k == i) {
            return k;
        } else {
            return doQuickSortK(nums, low, i - 1, k);
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode dummy = new ListNode(0);
        ListNode start = dummy;
        ListNode end = dummy;
        dummy.next = head;

        boolean isEnd = false;
        while (true) {
            //
            for (int i = 0; i < k; i++) {
                end = end.next;
                if (end == null) {
                    isEnd = true;
                    break;
                }
            }

            if (isEnd) {
                break;
            }

            // 需要反转的节点
            ListNode r = start.next;
            ListNode nextP = end.next;

            // 边界断开
            end.next = null;
            start.next = null;

            // 反装
            ListNode reverse = reverseList(r);

            // 左右边界接上来.
            start.next = reverse;
            r.next = nextP;

            // 新的边界
            start = r;
            end = r;
        }

        return dummy.next;
    }

    private ListNode getKNode(ListNode tail, int k) {
        int group = 0;
        while (tail.next != null) {
            tail = tail.next;
            group++;
            if (group == k) {
                // 断开K组的连接,返回下组的start;
                ListNode start = tail.next;
                tail.next = null;
                return start;
            }
        }
        return null;
    }

    /*输入：amount = 5, coins = [1, 2, 5]
    输出：4
    解释：有四种方式可以凑成总金额：
            5=5
            5=2+2+1
            5=2+1+1+1
            5=1+1+1+1+1*/
    // 零钱兑换


    // 0-1背包
    // 给你一个可装载重量为 W 的背包和 N 个物品，每个物品有重量和价值两个属性。
    // 其中第 i 个物品的重量为 wt[i]，价值为 val[i]，现在让你用这个背包装物品，最多能装的价值是多少
    //    N = 3, W = 4
    //    wt = [2, 1, 3]
    //    val = [4, 2, 3]
    // 典型背包问题。动态规划：状态和选择。
    public static void pack(int[] weight, int[] value, int bagSize) {
        // 创建dp数组
        int goods = weight.length;  // 获取物品的数量
        int[][] dp = new int[goods][bagSize + 1];

        // 初始化dp数组
        // 创建数组后，其中默认的值就是0
        for (int j = weight[0]; j <= bagSize; j++) {
            dp[0][j] = value[0];
        }

        // 填充dp数组
        for (int i = 1; i < weight.length; i++) {
            for (int j = 1; j <= bagSize; j++) {
                if (j < weight[i]) {
                    /**
                     * 当前背包的容量都没有当前物品i大的时候，是不放物品i的
                     * 那么前i-1个物品能放下的最大价值就是当前情况的最大价值
                     */
                    dp[i][j] = dp[i - 1][j];
                } else {
                    /**
                     * 当前背包的容量可以放下物品i
                     * 那么此时分两种情况：
                     *    1、不放物品i
                     *    2、放物品i
                     * 比较这两种情况下，哪种背包中物品的最大价值最大
                     */
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                }
            }
        }

        // 打印dp数组
        for (int i = 0; i < goods; i++) {
            for (int j = 0; j <= bagSize; j++) {
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println("\n");
        }
    }

    public int packS(int w, int n, int[] wt, int[] val) {
        return 0;
    }

//    public static void main(String[] args) {
//        int res = fib(5);
//        System.out.println(res);
//    }

    // fib, 动态规划来实现
    public static int fib(int n) {
        // 1 1 2 3 5
        if (n <= 0) {
            return -1;
        }
        if (n <= 2) {
            return 1;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n - 1];
    }

    public static int climbStairs(int n) {
        if (n <= 1) {
            return 1;
        }

        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;

        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }

    public static int trap(int[] height) {

        if (height.length <= 2) {
            return 0;
        }

        Stack<Integer> s = new Stack<>();
        s.push(0); // 下标0
        int res = 0;

        for (int i = 1; i < height.length; i++) {
            int top = height[s.peek()];
            int cur = height[i];
            if (top > cur) {
                s.push(i);
            } else if (top == cur) {
                s.pop();
                s.push(i);
            } else {
                while (!s.isEmpty() && s.peek() > cur) {
                    // pop
                    int lowValIndex = s.pop();

                    // 计算有多少水,往左边找到柱子,栈底一定大,判断不为空
                    if (!s.isEmpty()) {
                        int h = Math.min(height[s.peek()], cur) - height[lowValIndex];
                        int w = lowValIndex - s.peek();
                        int hold = h * w;
                        res += hold;
                    }
                }
            }
        }

        return res;
    }

    //    输入: nums = [-1,0,3,5,9,12], target = 9
//    输出: 4
//    解释: 9 出现在 nums 中并且下标为 4
    public static int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        if (nums[0] > target || nums[nums.length - 1] < target) {
            return -1;
        }

        int resIndex = doSearch(0, nums.length - 1, nums, target);
        return resIndex;
    }

    private static int doSearch(int left, int right, int[] nums, int target) {

        if (left > right) {
            return -1;
        }

        int center = (right + left) / 2;

        if (nums[center] == target) {
            return center;
        }
        if (nums[center] < target) {
            return doSearch(center + 1, right, nums, target);
        } else {
            return doSearch(left, center, nums, target);
        }
    }


    //    示例 1: 给定 nums = [3,2,2,3], val = 3, 函数应该返回新的长度 2,
//    并且 nums 中的前两个元素均为 2。 你不需要考虑数组中超出新长度后面的元素。
    public static int removeElement(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }
        // 双指针, 快指针遍历数组,慢指针指向被消除的位置
        int slowIndex = 0;
        for (int fast = 0; fast < nums.length; fast++) {

            if (nums[fast] != val) {
                // 数组长度++
                nums[slowIndex] = nums[fast];
                slowIndex++;
            }

        }

        return slowIndex;
    }

    //    输入：nums = [-7,-3,2,3,11]
//    输出：[4,9,9,49,121]
    public static int[] sortedSquares(int[] nums) {
        if (nums.length == 0) {
            return nums;
        }

        if (nums.length == 1) {
            return new int[]{nums[0] * nums[0]};
        }

        int[] res = new int[nums.length];

        // 双指针
        int left = 0;
        int right = nums.length - 1;
        int startIndex = 0;
        while (left <= right) {
            int leftS = nums[left] * nums[left];
            int rightS = nums[right] * nums[right];
            if (leftS > rightS) {
                left++;
                res[startIndex] = leftS;
            } else {
                right--;
                res[startIndex] = rightS;
            }
            startIndex++;
        }
        return res;
    }

//    public static void main(String[] args) {
////        int res = search(new int[]{-1,0,3,5,9,12}, 12);
////        System.out.println(res);
//        //removeElement(new int[]{3,2,2,3}, 3);
////        sortedSquares(new int[]{-7,-3,2,3,11});
////        System.out.println(minSubArrayLen(9, new int[]{2,3,1,2,4,3}));
//        change(5, new int[]{1,2,5});
//    }

    //    给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，
//    并返回其长度。如果不存在符合条件的子数组，返回 0。
//    示例：
//    输入：s = 7, nums = [2,3,1,2,4,3]
//    输出：2
//    解释：子数组 [4,3] 是该条件下的长度最小的子数组。
// 滑动窗口
    public static int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int left = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= s) {
                minLength = Math.min(right - left + 1, minLength);
                // 左边缩一下
                sum -= nums[left];
                left++;
            }
        }
        if (minLength != Integer.MAX_VALUE) {
            return minLength;
        }
        return 0;
    }

    /**
     * 最大组合数
     * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
     * <p>
     * 示例 1:
     * <p>
     * 输入: amount = 5, coins = [1, 2, 5]
     * 输出: 4
     * 解释: 有四种方式可以凑成总金额:
     * <p>
     * 5=5
     * 5=2+2+1
     * 5=2+1+1+1
     * 5=1+1+1+1+1
     */
    public static int changebak(int amount, int[] coins) {
        //递推表达式
        int[] dp = new int[amount + 1];
        //初始化dp数组，表示金额为0时只有一种情况，也就是什么都不装
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        for (int i = 0; i < dp.length; i++) {
            System.out.print(dp[i] + "\t");
        }

        return dp[amount];
    }
    public static int change(int amount, int[] coins) {

        // 滚动数组
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        for (int i = 0; i < dp.length; i++) {
            System.out.print(dp[i] + "\t");
        }
        return dp[amount];
    }

    public static void main(String[] args) {
//        int res = search(new int[]{-1,0,3,5,9,12}, 12);
//        System.out.println(res);
        //removeElement(new int[]{3,2,2,3}, 3);
//        sortedSquares(new int[]{-7,-3,2,3,11});
//        System.out.println(minSubArrayLen(9, new int[]{2,3,1,2,4,3}));
//        System.out.println(coinChange(new int[]{2, 5}, 5));

        //System.out.println(maxBagValue(new int[]{1, 3, 4}, new int[]{15, 20, 30}, 4));
        System.out.println(maxProfit(new int[]{7,1,5,3,6,4}));
    }

    /**
     * 最少个数
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
     * 如果没有任何一种硬币组合能组成总金额，返回 -1。
     * <p>
     * 你可以认为每种硬币的数量是无限的。
     * <p>
     * 示例 1：
     * <p>
     * 输入：coins = [1, 2, 5], amount = 11
     * 输出：3
     * 解释：11 = 5 + 5 + 1
     */
    public static int coinChange(int[] coins, int amount) {
        int max = Integer.MAX_VALUE;
        int[] dp = new int[amount + 1];
        // 初始化
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                if (dp[j - coins[i]] != max) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        return dp[amount] == max ? -1 : dp[amount];
    }

    // 背包能装下的,最大的价值.
    // int[] weight = {1, 3, 4};
    // int[] value = {15, 20, 30};
    // int bagWight = 4;
    public static int maxBagValue(int[] weight, int[] value, int bagWeight) {

        // 定义DP数组
        int[] dp = new int[bagWeight + 1];

        // 情况一:  可以放无数次
//        for (int i = 0; i < weight.length; i++) {
//            for (int j = weight[i]; j <= bagWeight; j++) {
//                dp[j] = Math.max(dp[j], (dp[j - weight[i]] + value[i]));
//            }
//        }

        // 情况二:  只能放一次
        for (int i = 0; i < weight.length; i++) {
            for (int j = bagWeight; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], (dp[j - weight[i]] + value[i]));
            }
        }

        //打印dp数组
        for (int j = 0; j <= bagWeight; j++){
            System.out.print(dp[j] + " ");
        }
        return dp[bagWeight];
    }


    //买卖股票的最佳时机 II
    // 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
    //
    //返回 你能获得的 最大 利润
    // 输入：prices = [7,1,5,3,6,4]
    //输出：7
    //解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
    //     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
    //     总利润为 4 + 3 = 7 。。
    public static int maxProfit(int[] prices) {
        if (prices.length <= 1){
            return 0;
        }
        int max = 0;

        // 贪心法
        for (int i = 1; i < prices.length; i++) {
            max += Math.max(prices[i] - prices[i-1], 0);
        }

        return max;
    }
    // 买卖股票的最佳时机
    // 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
    //
    // 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
    public int maxProfit1(int[] prices) {
        if (prices.length <= 1){
            return 0;
        }
        // 找到一个最小的购入点
        int low = Integer.MAX_VALUE;
        // res不断更新，直到数组循环完毕
        int res = 0;
        for(int i = 0; i < prices.length; i++){
            low = Math.min(prices[i], low);
            res = Math.max(prices[i] - low, res);
        }
        return res;
    }



    //给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
    //
    //示例:
    //
    // 输入: [-2,1,-3,4,-1,2,1,-5,4]
    // 输出: 6
    // 解释:  连续子数组  [4,-1,2,1] 的和最大，为  6。
    // 从代码角度上来讲：遍历 nums，从头开始用 count 累积，如果 count 一旦加上 nums[i]变为负数，
    // 那么就应该从 nums[i+1]开始从 0 累积 count 了，因为已经变为负数的 count，只会拖累总和。

    public int maxSubArray(int[] nums) {
         if (nums.length == 0){
             return 0;
         }
         if (nums.length == 1){
             return nums[0];
         }

         int max = Integer.MIN_VALUE;
         int count = 0;
         for (int i = 0; i < nums.length; i++) {
             // count 累加
             count += nums[i];

             // 记录最大值
             max = Math.max(max, count);
             // count 重置
             if (count < 0){
                 count = 0;
             }
         }
         return max;
    }

}