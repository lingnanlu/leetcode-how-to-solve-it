package Array.TwoPoint;

import run.elder.Category;
import run.elder.Leetcode;
import run.elder.Solution;

@Leetcode(
        title = "Remove Element",
        link = "https://leetcode.com/problems/remove-element/",
        category = Category.ARRAY,
        how2SolveIt = """
                这一题其实和Remove Duplicates from Sorted Array类似, 那一个是要移除重复元素, 而这一个是要移除值为val的元素.
                可以使用和Remove Duplicates from Sorted Array相同的方法来做.
                """,
        relatedQuestions = {
                "Remove Duplicates from Sorted Array",
                "Remove Duplicates from Sorted Array II"
        }
)
public class Remove_Element {


    @Solution(
            name = """
            将原数组看成两个
            """,
            detail =
                    """
                    将原数组看成两个, 为了不混乱, 起一个别名. 复制的时候判断一下是否应该复制.
                    """
    )
    static class New {

        public int removeElement(int[] nums, int val) {

            int[] newNums = nums;

            // 注意这里也是使用的循环不变式
            int i = 0;  // 指向原数组中下一个要copy的元素
            int j = 0;  // 指向新数组中下一个空位
            while (i != nums.length) {
                if (nums[i] != val) {
                    newNums[j] = nums[i];
                    i++;
                    j++;
                } else {
                    i++;
                }
            }

            // 因为是循环不变式, 到这里时
            // i == nums.length 而i的含义是指向下一个要copy的元素, 可以确定, nums中的元素都copy完了
            // j 指向新数组中的下一个空位, 所以, j之前的都是有元素的, 而且j正好表示新数组的size.
            // 这就是循环不变式的好处.
            return j;
        }

    }

    @Solution(name = "原答案, 不算清晰")
    static class Old {

        public int removeElement(int[] nums, int val) {

            int begin = 0;

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != val) {
                    nums[begin] = nums[i];
                    begin++;
                }
            }

            return begin;
        }
    }


}
