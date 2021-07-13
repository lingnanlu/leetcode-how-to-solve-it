package run.elder.seqence.array;

import run.elder.Category;
import run.elder.Leetcode;
import run.elder.Solution;

import java.util.Arrays;

@Leetcode(
        title = "Two Sum II Input array is sorted",
        category = Category.ARRAY,
        link = "https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/",
        how2SolveIt = """
                这一题和Two Sum很像, 唯一的不同就是是排序了的.
                
                如果是排序的, 自然也可以使用二分法.
                
                但既然是排序的, 是不是可以利用这个有序的性质呢? 
                
                画一画图, 举个例子.
                
                这题说难不难, 说简单不简单, 其实你要明白为什么两头夹逼就可以起作用, 其实是需要数学证明的.
                
                """,
        relatedQuestions = {

        }
)
public class Two_Sum_Sorted_Array {

    @Solution(name = "夹逼方法")
    static class First {

        public int[] twoSum(int[] numbers, int target) {
            int i = 0, j = numbers.length - 1;

            while (i != j) {
                int sum = numbers[i] + numbers[j];
                if(sum == target) {
                    return new int[]{i + 1, j + 1};
                } else if (sum < target) {
                    i++;
                } else {
                    j--;
                }
            }

            return new int[]{-1, -1};
        }

    }
}
