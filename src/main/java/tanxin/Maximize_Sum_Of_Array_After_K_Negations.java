package tanxin;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

@Leetcode(
        title = "Maximize Sum Of Array After K Negations",
        link = "https://leetcode.com/problems/maximize-sum-of-array-after-k-negations/",
        category = Category.ARRAY,
        how2SolveIt = """
                有负选负, 从小选到大, 
                
                无负看0, 有0就消耗完
                
                无0再选正, 注意, 这里的正要包含之前的负
                
                方法一:
                
                因为要从小取最大, 所以可以使用优先级队列
                
                方法二:
                
                方法一使用了库方法, 不好分析时间复杂度, 看看能不能不使用高级数据结构
                
                还是要排序, 然后从小到大修改, 到0就结束, 如果没有0, 到正数时, 就选择最小的正数来回翻转
                
                本题的作法其实是模拟
                
                方法三:
                
                按照绝对值大小进行排序, 就不用区分正数和0了, 这个有一点trick, 关键是你能想到按照绝对值大小排序.
                """,
        relatedQuestions = {}
)
public class Maximize_Sum_Of_Array_After_K_Negations {

    static class First {
        public int largestSumAfterKNegations(int[] nums, int k) {
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

            for (int n : nums) {
                priorityQueue.add(n);
            }

            // 消耗k -> 0
            while(k != 0) {
                int min = priorityQueue.poll();
                if (min != 0) {
                    priorityQueue.add(-min);
                    k--;
                } else {
                    // 为0的话, 就不再消耗了.
                    break;
                }
            }

            int sum = 0;
            while (!priorityQueue.isEmpty()) {
                sum += priorityQueue.poll();
            }

            return sum;
        }
    }

    static class Second {

        public int largestSumAfterKNegations(int[] nums, int k) {

            Arrays.sort(nums);

            int i = 0;
            while (k != 0 && i != nums.length) {
                if (nums[i] < 0) {
                    nums[i] = -nums[i];
                    k--;
                    i++;
                } else if (nums[i] == 0) {
                    k = 0;
                    break;
                } else {
                    // 此时大于0, 就要找最小的正数来消耗k
                    if (i > 0) { // 前面有元素, 说明前面是负数变过来的.
                        if (nums[i - 1] < nums[i]) {
                            if (k % 2 == 1) {
                                nums[i - 1] = -nums[i - 1];
                            }
                        } else {
                            if (k % 2 == 1) {
                                nums[i] = -nums[i];
                            }
                        }
                    } else {
                        if (k % 2 == 1) {
                            nums[i] = -nums[i];
                        }
                    }
                    k = 0;
                    break;
                }
            }

            // 说明全是负数
            if (k != 0) {
                if (k % 2 == 1) {
                    nums[nums.length - 1] = -nums[nums.length - 1];
                }
            }

            int sum = 0;
            for (int n : nums) {
                sum += n;
            }

            return sum;

        }

    }

}
