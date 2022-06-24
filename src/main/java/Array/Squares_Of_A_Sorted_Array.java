package Array;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Squares of a Sorted Array",
        link = "https://leetcode.com/problems/squares-of-a-sorted-array/",
        category = Category.ARRAY,
        how2SolveIt = """
                要想O(n)就得保证遍历的顺序和生成的顺序一致.
                
                遍历的顺序就是想办法取绝对值之后, 从小到大的顺序
                """,
        relatedQuestions = {}
)
public class Squares_Of_A_Sorted_Array {

    public int[] sortedSquares(int[] nums) {

        /**
         * 1. nums先全部取绝对值
         * 2. 从两边向中边, 由大到小开始遍历, 最后一定相遇
         *
         * 这个其实可以不要, 比较时, 直接取绝对值比就好
         */

        int[] absNums = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                absNums[i] = -nums[i];
            } else {
                absNums[i] = nums[i];
            }
        }


        int[] squareNums = new int[absNums.length];

        // i , j是要比较的下面两个元素, 且都未被处理
        // p是要填充的下一个元素位置
        int p = squareNums.length - 1;
        int i = 0, j = absNums.length - 1;
        while (i != j) {
            if (absNums[i] > absNums[j]) {
                squareNums[p] = absNums[i] * absNums[i];
                i++;
                p--;
            } else {
                squareNums[p] = absNums[j] * absNums[j];
                j--;
                p--;
            }
        }

        squareNums[p] = absNums[i] * absNums[i];
        return squareNums;
    }
}
