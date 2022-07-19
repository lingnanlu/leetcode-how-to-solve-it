package Todo;

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


    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};

        Maximum_Subarray test = new Maximum_Subarray();

        int result = test.maxSubArray(nums);
        System.out.println(result);
    }
}
