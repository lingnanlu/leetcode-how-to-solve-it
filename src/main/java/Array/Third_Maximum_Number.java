package Array;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.Arrays;

@Leetcode(
        title = "Third Maximum Number",
        link = "https://leetcode.com/problems/third-maximum-number/",
        category = Category.ARRAY,
        how2SolveIt = """
               返回第三大的唯一的数.
               
               方法一:
               
               要求第三大, 又要求唯一, 那么, 首先想到的是, 先排序, 再去重
               
               方法二:
               
               方法一又是排序, 又是去重的, 能不能不排序, 不去重
               之前类似的问题, 比如找数组中最大元素, 就没有排序和去重, 在一遍遍历过程中就行.
               能不能利用同样的方式? 使用一个变量记录已经找到的第三大元素?
               
               方法三:
               找最大, 找第几大, 这让我想到堆这种数据结构.
               
                """,
        relatedQuestions = {}
)
public class Third_Maximum_Number {

    static class First {
        public int thirdMax(int[] nums) {
            // 排序
            Arrays.sort(nums);

            // 原地去重
            // i用来遍历原数组, 指向下一个要遍历的元素
            // j指向新数组的下一个空位
            int i = 0, j = 0;

            while (i != nums.length) {

                if (j == 0) {
                    nums[j] = nums[i];
                    i++;
                    j++;
                } else {
                    // 找到下一个与nums[j - 1]不同的元素
                    while (i != nums.length && nums[i] == nums[j - 1]) {
                        i++;
                    }

                    if (i == nums.length) break;
                    else {
                        nums[j] = nums[i];
                        j++;
                        i++;
                    }
                }

            }

            // 此时已去重
            if(j >= 3) return nums[j - 3];
            else return nums[j - 1];
        }
    }

    static class Second {

        public int thirdMax(int[] nums) {

            //int thirdMax = ? 这里最关键的一点就是, 如何初始化这个?
            // 人工模拟一下思路就行了
//            int thridMax =

            return 0;
        }

    }
}
