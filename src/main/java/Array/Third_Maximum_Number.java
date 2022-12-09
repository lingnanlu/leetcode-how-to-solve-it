package Array;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

@Leetcode(
        title = "Third Maximum Number",
        link = "https://leetcode.com/problems/third-maximum-number/",
        category = Category.ARRAY,
        how2SolveIt = """
               返回第三大的唯一的数.
               
               方法一:
               
               要求第三大, 又要求唯一, 那么, 首先想到的是, 先排序, 再去重, 这种方法改变了原数组.
               
               方法二:
               
               方法一又是排序, 又是去重的, 能不能不排序, 不去重
               之前类似的问题, 比如找数组中最大元素, 就没有排序和去重, 在一遍遍历过程中就行.
               能不能利用同样的方式? 
               
               我觉得行, 使用三个变量分别记录第一大, 第二大和第三大的, 然后遍历剩余元素.
               
               这个方法虽然代码量大, 边界条件多, 但自己写的清晰, 也是一遍过
               
               方法三:
               找最大, 找第几大, 这让我想到堆这种数据结构.
               
               其实二就是一种优先级队列, 只是这个队列只有3个元素, 所以就使用三个变量来代替了.
               
               方法四:
               
               这个是网上的做法, 相当于方法二的简化, 但并不直观, 因为不好直观的说出每个变量的含义.
               
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


            //i指向第一个元素, , k指向下一个与i, j都不一样的.
            int i = 0;

            //j指向下一个与之不同的
            int j = 1;
            while (j != nums.length && nums[j] == nums[i]) {
                j++;
            }

            // 说明没有与i指向的元素不同的元素
            if (j == nums.length) {
                return nums[i];
            }

            // 此时j指向第一个与i不同的元素

            int k = j + 1;
            while (k != nums.length && (nums[k] == nums[i] || nums[k] == nums[j])) {
                k++;
            }

            if (k == nums.length) {
                // 说明只有nums[i], nums[j]两种元素
                return Math.max(nums[i], nums[j]);
            }

            // 此时, i, j, k分别指向3种不同的元素.

            // 从i, j, k 3种元素中排出大小.
            int first = Math.max(Math.max(nums[i], nums[j]), nums[k]);
            int third = Math.min(Math.min(nums[i], nums[j]), nums[k]);

            int second = 0;
            if (nums[i] != first && nums[i] != third) {
                second = nums[i];
            } else if (nums[j] != first && nums[j] != third) {
                second = nums[j];
            } else {
                second = nums[k];
            }

            // 现在first是最大, second第二大, third是第三大
            // 迭代剩余的元素
            int p = k + 1;
            while (p != nums.length) {

                // 跳过重复的
                if (nums[p] == first || nums[p] == second || nums[p] == third) {
                    p++;
                    continue;
                }

                if (nums[p] > first) {
                    third = second;
                    second = first;
                    first = nums[p];
                } else if (nums[p] > second) {
                    third = second;
                    second = nums[p];
                } else if (nums[p] > third){
                    third = nums[p];
                }
                p++;
            }

            return third;
        }

    }

    static class Fourth {
        public int thirdMax(int[] nums) {

            // 这里的难点是, 直观的说出每个变量的含义.
            Integer max1 = null;
            Integer max2 = null;
            Integer max3 = null;
            for (Integer n : nums) {
                if (n.equals(max1) || n.equals(max2) || n.equals(max3)) continue;
                if (max1 == null || n > max1) {
                    max3 = max2;
                    max2 = max1;
                    max1 = n;
                } else if (max2 == null || n > max2) {
                    max3 = max2;
                    max2 = n;
                } else if (max3 == null || n > max3) {
                    max3 = n;
                }
            }
            return max3 == null ? max1 : max3;
        }
    }

}
