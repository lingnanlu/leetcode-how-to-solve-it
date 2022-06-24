package Array;


import run.elder.Category;
import run.elder.Leetcode;
import run.elder.Solution;

@Leetcode(
        title = "Search Insert Position",
        link = "https://leetcode.com/problems/search-insert-position/",
        category = Category.ARRAY,
        how2SolveIt = """
                
                这题的条件
                1. 有序数组
                2. target
                2. 返回index, 或差入位置
                
                让人想起二分查找, 这题是二分查找的更进一步.
                
                这里要明白, i, j, middle的含义了.
                
                i, j其实一直是搜索的区间, 但当找不到时, i, j是什么呢?
                
                首先, 你要明白, i, j是如何变化的, 仔细观察, 发现, i, j 最终会
                
                i = j, 
                
                然后, 
                
                i > j , i = j + 1;
                
                
                
                """,
        relatedQuestions = {

        }
)
public class Search_Insert_Position {


    @Solution(detail = """
            i, j 的含义不太直观, 但middle的含义很直观, 可以利用middle
            """
    )
    static class Second {
        public int searchInsert(int[] nums, int target) {

            // 要考察[i, j]之间的是否有target或者插入位置
            int i = 0, j = nums.length - 1;

            // 这里的初始化没什么意义, 看题目描述, nums一定是一个非空数组
            int middleIndex = 0;
            // 说明考察的还是一个区间, 如果是区间, 那么还可以继续, 如果不是区间, 说明就找不到了
            // 此时应该明确i, j的含义
            while(i <= j) {
                middleIndex = (i + j) / 2; // 这里小心溢出
                int middle = nums[middleIndex];
                if(middle == target) {
                    return middleIndex;
                } else if (middle > target) {
                    j = middle - 1;
                } else {
                    i = middle + 1;
                }
            }

            /**
             * 由不断缩小过程可知
             * 此时, middleIndex之前的都比target小, middleIndex之后的都比target大
             */

            if (nums[middleIndex] > target) {
                return middleIndex;
            } else {
                return middleIndex + 1;
            }
        }
    }

    @Solution(detail = """
            循环不变式, 彻底搞明白i, j的含义
            
            关键两点
            1. i, j含义
            2. 搜寻区间是如何缩小的, 每一次排除, 不要多排, 不要少排
            
            注意搜寻区间的缩小一定要确保中止, 否则可能无限死循环.
            """
    )
    static class First {
        public int searchInsert(int[] nums, int target) {


            // 要考察[i, j]之间的是否有target或者插入位置
            int i = 0, j = nums.length - 1;

            // 这里的初始化没什么意义, 看题目描述, nums一定是一个非空数组
            int middleIndex = 0;
            // 说明考察的还是一个区间, 如果是区间, 那么还可以继续, 如果不是区间, 说明就找不到了

            /**
             * 不变式一
             * i左边的(不包含i), 都比target小
             * j右边的(不包含j), 都比target大
             *
             * 可以假设0之前的是一个很小的值, nums.length - 1之后的是一个很大的, 这样满足不变式.
             *
             * 不变式二
             *
             * i, j之间有元素
             *
             * 第一次迭代, 都会缩小区间.
             */
            while(i <= j) {
                middleIndex = (i + j) / 2; // 这里小心溢出
                int middle = nums[middleIndex];
                if(middle == target) {
                    return middleIndex;
                } else if (middle > target) {
                    j = middle - 1;
                } else {
                    i = middle + 1;
                }
            }


            /**
             * 此时不变式一依然为真, 因为循环过程保证了真
             * 而由循环过程可以知道, 最后
             *
             * i = j + 1;
             *
             * 由不变式一可知
             *
             * nums[j] < target // j在i左边
             * nums[i] > target // i在j右边
             *
             * 所以插入位置就是i
             */
            return i;
        }
    }

}
