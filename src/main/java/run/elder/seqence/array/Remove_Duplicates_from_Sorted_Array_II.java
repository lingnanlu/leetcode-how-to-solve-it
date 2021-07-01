package run.elder.seqence.array;


import run.elder.Category;
import run.elder.Leetcode;
import run.elder.Solution;

@Leetcode(
        title = "Remove Duplicates from Sorted Array II",
        link = "https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/",
        category = Category.ARRAY,
        how2SolveIt = """
              
                Q. 你之前做过一个类似的问题**Remove Duplicates from Sorted Array**，现在这个和原来的有什么区别? 
                
                A. 原来只是要求最多出现一次, 这个要求最多出现两次
                
                Q. 现在有一个问题和你现在要解决的类似, 你能利用它的方法么? 

                A. 应该不难，只要在复制动作发生的时候做一些处理就行，试一试

                Q. 回顾你的解法，你能不能把你的方法用到其它类似的问题上来

                A. 这两个题其实可以使用同一个框架来解决，不同的是，要满足复制的条件不同，所以，可以抽象出一个canCopy条件，检查当前状态是否可以进行手复制。
                """,
        relatedQuestions = {}
)
public class Remove_Duplicates_from_Sorted_Array_II {

    @Solution(name = "更优雅，可以一眼看出思路，很清晰")
    static class Good {

        public int removeDuplicates(int[] nums) {

            // 起个别名
            int[] newNums = nums;

            // 注意这里也是使用的循环不变式
            int i = 0;  // 指向原数组中下一个要copy的元素
            int j = 0;  // 指向新数组中下一个空位

            while(i != nums.length) {

                //要改变的地方就是这里，更一般的理解就是，要满足一个复制条件。
                if(canCopy(nums, j, nums[i])) {
                    nums[j] = nums[i];
                    // 这里，i，j的含义发生变化了，所以要移动两者，让其恢复到本来的含义。
                    i++;
                    j++;
                } else {
                    // 不进行复制, 所以i要指向下一个元素
                    i++;
                }
            }

            //[0,j)为newNums中的元素，newNums.length == j,所以j就是新数组的长度。
            return j;
        }

        private boolean canCopy(int[] nums, int size, int n) {

            if(size == 0 || size == 1) return true;

            if (nums[size - 1] != n) {   // 前一个是否相等
                return true;
            } else if(nums[size - 2] != n) { // 往前第二个是否相等
                return true;
            } else {                     // 已经有两个相等的了, 不再复制
                return false;
            }
        }
    }

}
