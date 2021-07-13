package run.elder.seqence.array;

import run.elder.Category;
import run.elder.Leetcode;
import run.elder.Solution;

@Leetcode(
        title = "Maximum Subarray",
        link = "https://leetcode.com/problems/maximum-subarray/",
        category = Category.ARRAY,
        how2SolveIt = """
                Q. 这题你遇到过没? 这里的未知是什么? 你见过没?
                                
                A. 这里的未知是求数组中的一个连续子数组, 然后该连续子数组的和是所有子数组中最大的.
                                
                Q. 你有没有什么思路? 或者先想一个最简单的思路
                                
                A. 最简单的就是暴力穷举所有子数组, 然后找出最简单的.
                                
                Q. 你能再审视一下第一种解法的步骤么, 仔细考虑每一步, 看看有没有优化的地方.
                
                A. ....(不知从何处看起)
                
                Q. 比如说, 你看你这操作中, 最耗时的是什么? 
                
                A. 嗯, 最里面的累加操作.
                
                Q. 一般情况下, 对于这种耗时呢, 有一种通用的思路, 就是缓存一些结果, 减少计算, 你看能不能缓存一些结果呢? 或者有没有重复计算.
                
                A. 感觉还是有重复计算的, 比如计算过sum(i, j) = [i, j) , 那么, sum(i, j + 1) = sum(i, j) + nums[j], 而不用从头计算了.
                所以, 我可以保存一下中间计算结果.          
                """,
        relatedQuestions = {

        }
)
public class MaxSubArray {

    @Solution(name = "暴力穷举",
    detail = """
            虽然这个方法看起来看简单, 其实不然.
            为什么呢? 
            还是边界的问题, 这里的i <= nums.length - 1和j <= nums.length很关键, 很微妙. 很考验你是否清楚.
            而不是脑子过一遍暴力穷举就完事了.
            
            比如说这个
            
            i <= nums.length - 1, i指的是什么呢? i指的是区间的开始, 是要包括的, 所以, i应该指向数组中的每一个元素.
            而不能是 i < nums.length - 1, 这样就忽略了最后一个元素.
            
            j 是什么呢? j是区间的关闭, 是开放的. 所以, j最终要指向nums.length, 这样就包含了最后一个元素. 
            
            所以, i, j 设置的没问题, 那么, 就不需要为元素个数为1个的数组进行特殊处理了.
            
            这个暴力方法可以作为面试题之一, 考察面试者的思路是否清晰, 即使是在一个简单的解法下.
            
            
            """)
    static class First {
        public int maxSubArray(int[] nums) {
            int maxSum = Integer.MIN_VALUE;
            for (int i = 0; i <= nums.length - 1; i++) {
                for (int j = i + 1; j <= nums.length ; j++) {
                    int sum = 0;
                    // 计算sum([i, j))
                    for (int k = i; k < j; k++) {
                        sum += nums[k];
                    }

                    maxSum = Math.max(maxSum, sum);

                }
            }
            return maxSum;
        }
    }
}
