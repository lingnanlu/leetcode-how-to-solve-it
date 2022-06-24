package Array;



import run.elder.Category;
import run.elder.Leetcode;
import run.elder.Solution;

import javax.annotation.processing.SupportedOptions;
import java.util.*;

@Leetcode(
        title = "3Sum",
        link = "https://leetcode.com/problems/3sum/",
        category = Category.ARRAY,
        how2SolveIt = """
                Q. 问题是什么？
                
                A. 找出所有a + b + c = 0, 要求不重复
                
                Q. 你能把未知分开描述么？比如，列一个列表
                
                A. 
                    1. 找出a + b + c = 0
                    2. 找出所有
                    3. 要求不重复
                    
                Q. 好的，现在，你要求的未知需要同时满足以上三者，你现在还没有什么思路，但，你可以先只满足部分要求，你有没有遇到过类似的问题？
                
                A. 有的啊，刚才那个two sum，就是求 a + b = c
                
                Q. 你能说出两者的异同么？
                
                A. 
                
                    1. two sum只是找出一个解。而这里是要找出所有的，而且不重复。
                    2. two sum求的是index,而这里是要找的数组中元素的值. 
                    3. two sum是 a + b = c, 而这里的 a + b + c = 0...
                
                Q. 好, 现在这个问题有3个部分, 你可不可以每一个部分想出一个相关思路, 不考虑其它部分, 而且既然有了一个相似的问题, 你看看在找思路过程中, 
                能不能利用two sum的一些方法或思路?
                
                A. 嗯, 我想想
                
                1. two sum求的是index,而这里是要找的数组中元素的值: 这个我觉得更简单一些, 因为原来的还要考虑index和value, 这里只考虑value就行
                2. two sum是 a + b = c, 而这里的 a + b + c = 0: 这个把C移动过去就和two sum是一样的, 我想可以使用夹逼思路
                3. 找出所有的解且不重复: 之前使用夹逼只找到一组解, 这里要找到所有的解, 应该可以改造一下
                
                我觉得最大的难点就是改造夹逼, 使其返回所有解, 这里我想先使用简化一个问题, 找到a + b = 0的所有解并且没有重复.
                
                A. 嗯, 解决了a + b = 0之后, 我就可以解决a + b + c = 0的问题了
                """,
        relatedQuestions = {}
)
public class Three_Sum {

    @Solution(name = "使用夹逼技巧，找到a + b = 0的所有解, 并且没有重复")
    static class JiaBiFindAll {

        public List<List<Integer>> twoSum(int[] nums) {

            List<List<Integer>> solutions = new ArrayList<>();
            Arrays.sort(nums);

            int i = 0, j = nums.length - 1;
            while(i != j) {

                if(nums[i] + nums[j] == 0) {

                    List<Integer> solution = Arrays.asList(nums[i], nums[j]);
                    solutions.add(solution);

                    /**
                     * 找到一组解后, 这里是i++, 还是j--呢?
                     *
                     * 任选一下就行,  因为 a + b = 0, 如果一个不变, 另一个也是不变的. 所以必会重复, 所以, 这里让一个有变就行.
                     */
                    j++;
                    while(i != j && nums[j] == nums[j - 1]) j++; // 跳过和nums[j]相等的, 如果需要重复, 再添加进去

                } else if (nums[i] + nums[j] > 0) {
                    j--;
                } else {
                    i++;
                }
            }

            //没找到。
            return solutions;
        }
    }


    @Solution(name = "使用夹逼技巧，找到a + b + c= 0的所有解，没有重复")
    static class ThreeSumSolution {

        public List<List<Integer>> threeSum(int[] nums) {

            List<List<Integer>> solutions = new ArrayList<>();
            Arrays.sort(nums);

            int i = 0;
            while (i <= nums.length - 3) {
                int j = i + 1, k = nums.length - 1;

                while (j != k) {

                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> solution = Arrays.asList(nums[i], nums[j], nums[k]);
                        solutions.add(solution);

                        /**
                         * 找到一组解后, 这里是i++, 还是j--呢?
                         *
                         * 任选一下就行,  因为 a + b = 0, 如果一个不变, 另一个也是不变的. 所以必会重复, 所以, 这里让一个有变就行.
                         */
                        j++;
                        while(j != k && nums[j] == nums[j - 1]) j++; // 跳过和nums[j]相等的, 如果需要重复, 再添加进去

                    } else if (nums[i] + nums[j] + nums[k] > 0) {
                        k--;
                    } else {
                        j++;
                    }
                }

                /**
                 * 这里其实有一个证明
                 *
                 * List<int[]> result 是 nums(i, length)内所有a + b = 0的solution
                 * 那么
                 * result也包含了nums(i+1, length)内的所有a + b = 0的solution
                 *
                 */
                i++;
                while(i <= nums.length - 3 && nums[i] == nums[i - 1]) i++;      // 跳过所有相同的i

            }

            return solutions;
        }
    }


}
