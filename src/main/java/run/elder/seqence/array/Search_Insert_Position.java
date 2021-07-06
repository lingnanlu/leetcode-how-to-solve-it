package run.elder.seqence.array;


import run.elder.Category;
import run.elder.Leetcode;
import run.elder.Solution;

@Leetcode(
        title = "Search Insert Position",
        link = "https://leetcode.com/problems/search-insert-position/",
        category = Category.ARRAY,
        how2SolveIt = """
                这题其实考察二分查找时, 如果找不到, 那么, 每个变量的含义是什么.
                
                其实就是要清楚每个变量的含义是什么, 一定要清楚.
                """,
        relatedQuestions = {

        }
)
public class Search_Insert_Position {

    @Solution(detail = """
            其实查找就是不断的考察一部分, 排除一部分, 你每一次排除的越多, 你的算法就越好
            """
    )
    static class First {
        public int searchInsert(int[] nums, int target) {

            // 要考察[i, j]之间的是否有target或者插入位置
            int i = 0, j = nums.length - 1;

            // 说明考察的还是一个区间, 如果是区间, 那么还可以继续, 如果不是区间, 说明就找不到了
            // 此时应该明确i, j的含义
            while(i <= j) {
                int middleIndex = (i + j) / 2; // 这里小心溢出
                int middle = nums[middleIndex];
                if(middle == target) {
                    return middleIndex;
                } else if (middle > target) {
                    j--;
                } else {
                    i++;
                }
            }


            // i, j的含义其实不好想明白, 此时应该举个例子, 直观的感受一下.
            // 最终i = j + 1;
            return i;

        }
    }

}
