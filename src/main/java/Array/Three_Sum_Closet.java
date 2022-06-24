package Array;

import run.elder.Category;
import run.elder.Leetcode;
import run.elder.Solution;

import java.util.Arrays;


@Leetcode(
        title = "3Sum Closest",
        link = "https://leetcode.com/problems/3sum-closest/",
        category = Category.ARRAY,
        how2SolveIt = """
                
                Q. 你之前遇到过类似问题么?
                
                A. 没有
                
                Q. 可不可以简化一下问题, 你看这里是3Sum, 可不可以变成2Sum, 2Sum你会解么?
                
                A. 2Sum就是找a + b 接近 target, 我感觉最简单暴力的, 就是穷举完所有的, 然后找到最接近的, 这个时间复杂度是O(n^2)
                
                Q. 你看, 这里有a + b, 有target. 你真的没见过类似的题?
                
                A. 等等, 好像有一个, a + b = target, 不过, 那个是相等, 这个是接近.
                
                Q. 你能不能利用之前 a + b = target的方法, 你知道, 你利用那个夹逼的方法, 使的O(n^2), 变成了O(n)
                
                A. 我觉得可以试一试.
                
                q 你之前遇到过类似的问题么
                
                a a + b + c = target
                
                q 两者有什么不同？
                
                a 之前是相等，这里的最接近
                
                q 能不能用数学语言描述这种最接近
                
                a 就是  |a + b + c - target| 越小越好。
                
                q 你能利用a + b + c = b 的思路么，
                
                a 我想可以，就是左右夹逼，然后判断gap，找到最小的gap就行。
                """,
        relatedQuestions = {}
)
public class Three_Sum_Closet {

    @Solution(name = "2Sum 夹逼方法",
    detail = """
            其实这个依然需要数学上的逻辑证明, 不能感觉对就对.
            为什么通过这种夹逼的方式就可以得到解呢?
            这种左右夹逼得到的和是什么规律呢? 
            这种左右夹逼得到的和的个数肯定小于穷举的, 为什么这种就是合适的呢? 抛弃了哪些呢? 万一抛弃的那些正好有解怎么办?
            所以说, 还是要证明一下的.
            这个夹逼其实不好证明, 先记住吧, 求和可以使用这个方法, 先记住.这里假设该方法是有效的. 
            """)
    static class TwoSum {
        public int twoSumClosest(int[] nums, int target) {

            Arrays.sort(nums);

            int minGap = Integer.MAX_VALUE;
            int minSum = Integer.MAX_VALUE;
            int i = 0, j = nums.length - 1;
            while (i != j) {
                int sum = nums[i] + nums[j];
                int gap = Math.abs((nums[i] + nums[j]) - sum);
                if(gap == 0) {
                    return sum;
                } else {
                    if (gap < minGap) {
                        minGap = gap;
                        minSum = sum;
                    }

                    if (sum < target) {
                        i++;
                    } else {
                        j--;
                    }
                }
            }

            return minSum;
        }
    }


    @Solution(name = "夹逼方法")
    static class Right {
        public int threeSumClosest(int[] nums, int target) {

            Arrays.sort(nums);

            int closetGap = Integer.MAX_VALUE;
            int closetSum = 0;


            for (int i = 0; i <= nums.length - 3; i++) {

                for(int j = i + 1, k = nums.length - 1; j < k;) {

                    int sum = nums[i] + nums[j] + nums[k];
                    int gap = Math.abs(sum - target);

                    if (gap < closetGap) {
                        closetGap = gap;
                        closetSum = sum;
                    }

                    if (sum < target) j++;
                    else k--;
                }

            }

            return closetSum;
        }

    }

    @Solution
    static class Wrong {
        // 这里的做法是错误的，数轴上的从一个方向逼近就是一种错误的想法，并不是从一个方向进行逼近，而是一会从左，一会从右。
        public int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums);

            int minGap = Integer.MAX_VALUE;                 // 记录全局的最小gap
            int minSum = 0;                                 // 记录全局最小gap的值。
            boolean find = false;                           // 如果找到a + b + c = target，则设置find = true，不再迭代下去

            for (int i = 0; i <= nums.length - 3; i++) {

                /**
                 * 你可以想像，一个数轴，target就是这个数轴上的点，而a + b则是从一个从向不断趋进于该点的另一个点，而gap无非就是
                 * 一个不断缩小然后不断扩大的过程。
                 *
                 * 以上想法是错误的，不是一个不断逼近的过程。是左右往复的过程。
                 *
                 * 比如在趋进过程中，当前点的gap比上一个要远，即上一个就是最小的gap了。
                 *
                 * lastGap ： 本轮上一次的gap
                 * lastSum ： 本轮上一次的sum
                 *
                 * 当跳出循环时，lastGap和lastSum保留的就是本轮最小的sum和gap了。
                 */
                int lastGap = Integer.MAX_VALUE;            // 本轮上一次的gap
                int lastSum = 0;                            // 本轮最小gap对应的sum
                for(int j = i + 1, k = nums.length - 1; j < k;) {

                    int sum = nums[i] + nums[j] + nums[k];

                    //当前gap
                    int gap = Math.abs(sum - target);

                    // 则对于本轮来说不需要再逼进下去了。
                    // lastGap 就是本轮的最小Gap

                    if(gap == 0) {
                        find = true;
                        break;
                    } if(gap > lastGap) {
                        break;
                    } else {         // 继续本轮的夹逼。
                        lastSum = sum;
                        lastGap = gap;

                        if(sum > target){
                            k--;
                        } else {
                            j++;
                        }
                    }
                }

                // 如何找到和target一样的，就不用再继续下去了。
                if (find == true) {
                    minGap = 0;
                    minSum = target;
                    break;
                } else if (lastGap < minGap) {
                    minGap = lastGap;
                    minSum = lastSum;
                }

            }

            return minSum;
        }

    }
}
