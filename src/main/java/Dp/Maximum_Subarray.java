package Dp;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Maximum Subarray",
        link = "https://leetcode.com/problems/maximum-subarray/",
        category = Category.ARRAY,
        how2SolveIt = """
                又是要求O(n)
                又是求最xxx
                所以还是Dp
                
                1. 初始化一个元素, 没问题
                2. 由一个元素来递推第二个元素, 没问题, 由第二个元素来递推第三个, 也没问题
                3. 一直下去, 直到所有元素..
                
                启示:
               
                对于DP中的推导, 可以不一上来就证明一般化, 可以由一生二, 二生三的过程中看看
                
                1. 能不能行的通
                2. 能行的通的话, p => p + 1中的某些情况可能并不存在, 所以不需要推导.   
                
                
                注: 
                
                这题试了很多次依然不能AC, 其实不是代码的问题, 是自己思路的问题.
                这题超出了自己的认知
                
                https://www.wikiwand.com/zh/%E6%9C%80%E5%A4%A7%E5%AD%90%E6%95%B0%E5%88%97%E9%97%AE%E9%A2%98
                
                也正常, 不要在这上面花费太多时间, 等状态好了, 或者哪一天有思路了再回来看看 
                
                比如对如下 [8,-19,5,-4,20] 这个case
                
                不能ac的原因是自己设计的状态转移方程漏掉了 [5, -4, 20]这个解, 说明由
                
                dp[0...n] => dp[0...n+1]是有问题的
                
                解释一下为什么会有问题, 因为这个推导分三种情况 
                
                1. 连接nums[n+1]
                2. 不连接nums[n+1]
                3. 跳转到nums[n]
                
                其实是漏掉了一些情况
                
                4. 从dp[0...n]的另一个子序列连接到nums[n+1]
                
                那么, 我们补充上第四种情况不就得了.  
                
                事实证明, 的确正确了~~~ 
                
                方法二
                
                再仔细分析一下方法一, 发现, 有了ends之后, 就可以简化了.
                
                如
                
                max[0...n+1] = Math.max(max[0...n], ends[n+1])
                
                这是因为 max[0...n+1]只有两种情况, 一种是以n+1结尾的, 一种不是以n+1结尾的, 以n+1为结尾的已经算出来了. 是一个已知的值
                
                这样, 上面的状态转移方程就可以写出代码了, 因为初始值是知道的.
                
                
                启示:
                
                1. 状态转移时不要漏掉情况, 漏掉情况就是漏掉了某些解
                2. 状态转移不是那么好设计, 不过出发点依然是直观检验的.
                3. 状态转移可能包含另一个状态转移, 不要害怕, 都是从直观出发得出来的结果. 这里面就包含了一个以i为结尾的最大子序列的子状态.
                
                这题还是靠自己想出来的, 不错.
                """,
        relatedQuestions = {}
)
public class Maximum_Subarray {

    static class First {
        public int maxSubArray(int[] nums) {

            if (nums.length == 0) {
                return 0;
            }

            // 1. 初始化 [0, 0] 之间的状态
            int p = 0;   // 表示当前状态
            int max = nums[0]; // 当前状态下的连续子数组的和
            int spaceSum = 0; // last和下一个p之间元素的和
            // 2. 由当前状态推导到下一状态, 最终把当前状态变为 [0, nums.length - 1]
            while (p != (nums.length - 1)) {  // 如果不是最后一个状态{
                p++;  // 指向下一个, 然后更新各个状态

                // 其实对于nums[p], 有三种情况

                // 1. 连接它
                // 2. 跳转到它
                // 3. 什么也不做

                // 第一种情况, 接上它要比原来的大,而且接上它要比它本身大
                if (spaceSum + nums[p] >= 0 && spaceSum + max >= 0) {
                    max += spaceSum + nums[p];
                    spaceSum = 0;
                } else  if (max + spaceSum <= 0 && nums[p] >= max) { // 它本身比接上它要大, 而且它本身也要比原来的大
                    max = nums[p];
                    spaceSum = 0;
                } else {
                    // 剩下就是第三种情况
                    spaceSum += nums[p];
                }

                // 第二种情况

            }
            // 此时p == nums.length - 1
            // 由不变式可知, max就是要的结果
            return max;
        }
    }

    static class Second {
        public int maxSubArray(int[] nums) {

            if (nums.length == 0) {
                return 0;
            }

            // 先提前计算出以n为结尾的最大子序列, 作为第四种情况中的值
            int[] ends = new int[nums.length];

            ends[0] = nums[0];
            for (int i = 1; i < ends.length; i++) {
                ends[i] = Math.max(ends[i - 1] + nums[i], nums[i]);
            }

            // 1. 初始化 [0, 0] 之间的状态
            int p = 0;   // 表示当前状态
            int max = nums[0]; // 当前状态下的连续子数组的和
            int spaceSum = 0; // last和下一个p之间元素的和
            // 2. 由当前状态推导到下一状态, 最终把当前状态变为 [0, nums.length - 1]
            while (p != (nums.length - 1)) {  // 如果不是最后一个状态{
                p++;  // 指向下一个, 然后更新各个状态

                // 其实对于nums[p], 有三种情况

                // 1. 连接它
                // 2. 跳转到它
                // 3. 以前面一个值为结尾的连接它
                // 4. 什么也不做

                int first = max + spaceSum + nums[p];
                int second = nums[p];
                int third = ends[p - 1] + nums[p];


                // 连接它是最大的, 且比原来大
                if ((first >= second && first >= third) && first >= max) {
                    max += spaceSum + nums[p];
                    spaceSum = 0;
                } else if ((second >= first && second >= third) && second >= max) { // 跳转它是最大的, 且比原来大
                    max = nums[p];
                    spaceSum = 0;
                } else if ((third >= first && third >= second) && third >= max) {
                    max = third;
                    spaceSum = 0;
                } else {
                    spaceSum += nums[p];
                }

            }
            // 此时p == nums.length - 1
            // 由不变式可知, max就是要的结果
            return max;
        }
    }

    static class Third {
        public int maxSubArray(int[] nums) {

            if (nums.length == 0) {
                return 0;
            }

            // 先提前计算出以n为结尾的最大子序列, 作为第四种情况中的值
            int[] ends = new int[nums.length];

            ends[0] = nums[0];
            for (int i = 1; i < ends.length; i++) {
                ends[i] = Math.max(ends[i - 1] + nums[i], nums[i]);
            }

            int p = 0;   // 表示当前状态
            int max = nums[0]; // 当前状态下的连续子数组的和
            while (p != (nums.length - 1)) {  // 如果不是最后一个状态{
                p++;
                max = Math.max(max, ends[p]);
            }
            // 此时p == nums.length - 1
            // 由不变式可知, max就是要的结果
            return max;
        }
    }


    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};

//        Maximum_Subarray test = new Maximum_Subarray();
//
//        int result = test.maxSubArray(nums);
//        System.out.println(result);
    }
}
