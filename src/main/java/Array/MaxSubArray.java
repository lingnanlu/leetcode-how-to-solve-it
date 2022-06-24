package Array;

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
                
                Q. 嗯, 很不错, 利用中间计算结果, 你已经把时间复杂度降低了一个数量级, 但你还能再优化一下么? 你能利用上面的方法和结果么?
                
                A. ...
                
                Q. 好吧, 看来上面两种方法并不能直接使用, 咱们先不管上面两种方法, 重新审一下题, 你看, 这里是要求一个连续数组, 这里比较的也是两个数组, 
                但如果开头和结尾都发生变化, 那么似乎比较起来很麻烦, 无从下手.
                这样吧, 咱们简化一下, 你能不能固定一头? 把问题简化一下?
                
                A. 嗯, 假如把开头限制为第一个元素, 那么问题就会成为"给定一个数组, 求一个连续子数组, 开头固定为每一个元素"
                
                Q. 现在也就是只要确定结尾喽? 
                
                
                A. 嗯.
                
                Q. 好, 现在我们假定选择了a[0], 那么, 你有没有办法很快确定结尾是哪个元素. 可以试着在[-2,1,-3,4,-1,2,1,-5,4]中尝试一下.
                
                A. 如果选择了a[0], 那么, 可以计算一下以每个元素为结尾的sum数组, 结果如下
                
                [-2, -1, -4, 0, -1, 1, 2, -3, 1]
                
                Q. 嗯, 我们发现, a[6]是作为结尾的, 并且a[0] + a[1] + ...a[6] = 2, 对吧, 现在我们确定了结尾. 是吧?
                
                A. 是的.
                
                Q. 不过, 这个结尾是我们确定了开头的情况下确定的结尾, 它是不是最终要求的和的结尾呢? 当然, 我们当然希望是了, 这样我们就确定了一头, 只需考虑另一头就行
                
                但它到底是不是呢? 你能不能证明一下? 假如不是, 而是另一个为结尾, 会怎么样?
                
                A. 假如最终的数组是a[i, j] , j != 6.
                
                如果 i <= 6
                
                a[i, j] > a[i, 6].
                
                a[0, i] + a[i, j] > a[0, i] + a[i, j] => a[0, j] > a[0, 6] 这个与之前的矛盾, 我们之前已经推导出了如果以a[0]开头
                那么, a[6]就是最大的. 所以不可能.
                
                如果 i > 6
                
                a[i, j] > a[6, i]
         
                
                a[6, j] < 0
                
                a[6, i] < a[i, j]
                
                
                
                
               
                
                
                
                
                
                
                
                
                
                
                也就是要知道开头和结尾, 但如果开头和结尾都不一样, 那么两个数组比较起来就很麻烦了.
                咱们先保持一个不变, 然后另一个变怎么样? 比如说结尾一样, 先确定开头试试,你如何确定开头呢? 如果想不明白, 可以举个例子, 比如题目中这个数组
                [-2,1,-3,4,-1,2,1,-5,4].
                
                咱们先不考虑找最优解, 先看假如两个子数组结尾一样, 为了讨论方便, 结尾在无限远, 这样, 结尾和开头就不重合了, 开头不一样, 这样吧, 一个是[0, 3], 一个是[1, 3]
                也就是区别是a[0], a[1]
                你是从a[0], 还是a[1]开始呢?
                
                A. 嗯, 如果a[0] + a[1] > a[1], 那么, 就从a[0]开始, 否则, 就从a[1]开始. 这里应该从a[1]开始.
                
                Q. 好, 如果结尾一样的话, 是不是a[0]开始的都会小于a[1]开始的?
                
                A. 嗯, 是这样的.
                
                Q. 好, 那么, 我们就可以不考虑a[0], 继续a[1], a[2], 那么, 这次怎么办?
                
                A. a[1] + a[2] > a[2], 所以如果要从a[1], a[2], 选的话, 应该从a[1]开始.
                
                Q. 好的, 没问题, 但, 如果再考虑a[3]呢? a[1], 还是a[3]开始, 记住结尾在无限远
                
                A. 如果 a[1] + a[2] + a[3] > a[3], 那么, 就从a[1]开始, 否则从a[3]开始. 这里a[1] + a[2] + a[3] < a[3], 所以从a[3]开始
                
                Q. 然后呢, 你能自己往这推么.
                
                A. 嗯, 其实现在排除了a[0], a[1], a[2], 可以就考虑这几个了, 现在从a[3]开始, 又和a[0], a[1]是一样的.
                
                即a[3] + a[4] > a[4], 所以还是从a[3]开始
                
                a[3] + a[4] + a[5] > a[5] 还是从a[3]开始 
                
                a[3] + a[4] + a[5] + a[6] > a[6] 还是从a[3]开始
                
                a[3] + a[4] + a[5] + a[6] + a[7] > a[7] 还是从a[3]开始
                
                a[3] + a[4] + a[5] + a[6] + a[7] + a[8] > a[8]还是从a[3] 开始.
                
                
                Q. 以上说明了什么? 
                
                A. 如果要找开始的话, 就从a[3]开始.
                
                Q. 好, 现在你已经找到了开始, 结尾应该没问题了吧?
                
                A. 嗯, 从a[3]开始, 一个个累加起来, 就可以找到结尾了.
                
                而且找开始的过程看起来是一遍遍历, 而找到之后, 使用第二遍遍历就行, 嗯, 时间复杂度是O(n)
                
                Q. 写下你的想法.
                
                
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


    @Solution(name = "暴力穷举, 缓存计算结果",
            detail = """ 
            在计算子数组的和时, 发现可以利用之前的结果, 所以缓存
            这样将时间复杂度降了一个数量级.
            """)
    static class Second {
        public int maxSubArray(int[] nums) {

            int maxSum = Integer.MIN_VALUE;

            for (int i = 0; i <= nums.length - 1; i++) {

                int j = i + 1;

                /**
                 * 注意这里的循环不变式
                 * sum = sum([i, j));
                 * maxSum 为迄今为止, 所遍历的[i, j)连续数组中, 最大的.
                 */
                int sum = nums[i];
                maxSum = Math.max(maxSum, sum);

                /**
                 * 注意这里没有=, 它和解法一不一样
                 * 跳出循环后, j = nums.length
                 * 但此时循环不变式为真
                 */
                while(j < nums.length) {
                    j++;

                    // 以下又使循环不变式为真
                    sum += nums[j - 1];
                    maxSum = Math.max(maxSum, sum);
                }

            }
            return maxSum;
        }
    }


    @Solution(name = "先找开始, 再找结尾, 两遍遍历",
            detail = """ 
          
            """)
    static class Third {
        public int maxSubArray(int[] nums) {

            // 通过一遍遍历找开始, 模拟之前人工计算过程
            int start = 0;
            int i = 1;
            while (i < nums.length) {
                
                // 累加[start, i]
                int sum = 0;
                for (int j = start; j <= i; j++) {
                    sum += nums[j];
                }

                if(sum < nums[i]) {
                    start = i;
                    i++;
                } else {
                    //start 不变
                    i++;
                }
            }

            System.out.println(start);


            // 到这里找到start了, 然后开始计算最大值
            /**
             * max = max([start, j))
             */
            int max = nums[start];
            int j = start + 1;
            int accu = nums[start];
            while(j < nums.length) {
                j++;
                accu += nums[j - 1];
                max = Math.max(accu, max);
            }

            return max;

        }
    }

    public static void main(String[] args) {
        int result = new Third().maxSubArray(new int[]{-1, -2});
        System.out.println(result);
    }
}
