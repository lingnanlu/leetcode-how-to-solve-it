package Array.binary;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Single Element in a Sorted Array",
        link = "https://leetcode.com/problems/single-element-in-a-sorted-array/",
        category = Category.ARRAY,
        how2SolveIt = """
                已知
                1. 有序
                2. 只有一个落单, 其它两两
                3. 说明元素个数为奇数个
                
                求
                落单元素是什么.  
                
                利用二分的关键, 就是, 能每次排除一半的元素, 对于这题来说, 怎么排除呢?
                
                想想, 不能利用大小了, 你不知道目标值, 但可以利用奇偶性. 
                
                包含它的那一半元素个数一定是奇, 剩余部分一定是偶, 这样就可以排除了.
                
                现在的难点就是如何利用中间元素来划分区间.
                因为看中间元素时, 要往前看和往后看. 这给人的启发是不是可以三个指针?
                
                这题给人的启示是, 不一定要用一个指针, 有时多个指针更容易理解.
                """,
        relatedQuestions = {
        }
)
public class Single_Element_In_A_Sorted_Array {

    public int singleNonDuplicate(int[] nums) {

        // 先排除一些特殊情况, 因为这些特殊情况导致循环比较难写
        //1. 只有一个元素
        if(nums.length == 1) {
            return nums[0];
        }

        // 2. 开头为落单
        if (nums[0] != nums[1]) {
            return nums[0];
        }

        // 3. 结尾为落单
        int last = nums.length - 1;
        if (nums[last] != nums[last - 1]) {
            return nums[last];
        }


        int i = 0, j = nums.length - 1;

        int middle = (i + j) / 2;
        int preMiddle = middle - 1;
        int afterMiddle = middle + 1;

        //
        //
        /**
         * 排除了特殊情况后, 落单元素一定在非开头和结尾处, 循环中就可以不考虑特殊情况了.
         * preMiddle 和 afterMiddle不会越界, 因为每次要搜索的区间一定是奇数个元素
         * 因为一定能找到, 所以这里是true
         * 这里没有办法写终止条件
         */
        while(true) {
            if (nums[preMiddle] != nums[middle] && nums[middle] != nums[afterMiddle]) {
                return nums[middle];
            } else if (nums[preMiddle] == nums[middle]) {
                // 前半部分为偶
                if ((preMiddle - i) % 2 == 0) {
                    i = afterMiddle;
                } else {
                    j = preMiddle - 1;
                }
            } else {
                if ((middle - i) % 2 == 0) {
                    i = afterMiddle + 1;
                } else {
                    j = preMiddle;
                }
            }

            middle = (i + j) / 2;
            preMiddle = middle - 1;
            afterMiddle = middle + 1;
        }
    }

    /**
     * 判断p所指向的元素是否是落单
     */
//    private boolean isSingle(int[] nums, int p) {
//
//        // 只有一个元素
//        if (nums.length == 1) {
//            return true;
//        }
//
//        // 开头
//        if (p == 0) {
//            return nums[p] != nums[p + 1];
//        }
//
//        // 结尾
//        if (p == nums.length - 1) {
//            return nums[p] != nums[p - 1];
//        }
//
//        // p在中间
//        return nums[p] != nums[p + 1] && nums[p] != nums[p - 1];
//
//    }
}
