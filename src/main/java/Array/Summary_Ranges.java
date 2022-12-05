package Array;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Leetcode(
        title = "Summary Ranges",
        link = "https://leetcode.com/problems/summary-ranges/",
        category = Category.ARRAY,
        how2SolveIt = """
                这题描述挺多，其实就是将整数数组用另一种方式表示而已。
                
                直观的看就是依次检查，找到连续的。
                
                代码模拟题而已
                
                这题考察的不是智力，而是你能否将你的思路表述清楚
                """,
        relatedQuestions = {}
)
public class Summary_Ranges {

    static class First {
        public List<String> summaryRanges(int[] nums) {

            /**
             * 大概思路：
             * 从前向后扫描，找到一个连续区间就把它加入到结果集当中。
             */

            // 用来记录找到的连续区间
            List<int[]> subNums = new ArrayList<>();

            // [i, j) 用来定位找到的连续区间.
            int i = 0, j = 0;

            while (i != nums.length) {
                /**
                 * 1. 找到一个区间
                 * 2. 加入到结果集中
                 * 3. 调整 i,j用来寻找下一个区间
                 */

                ++j; // 错开一个元素, 初始时,i,j 指向同一个元素
                while (j != nums.length && nums[j] == nums[j - 1] + 1) {
                    ++j;
                }

                // 此时[i, j)定位到了一个连续子区间

                subNums.add(Arrays.copyOfRange(nums, i, j));

                i = j;

            }

            // 此时subNums保存着所有的连续子数组,将它转化成字符串形式的就行

            List<String> result = new ArrayList<>();

            for (int[] sub : subNums) {

                if (sub.length == 1) {
                    result.add(sub[0] + "");
                } else {
                    result.add(sub[0] + "->" + sub[sub.length - 1]);

                }
            }

            return result;

        }
    }

    static class Second {
        // 方法一中一些数据可以不保存
        public List<String> summaryRanges(int[] nums) {

            List<String> result = new ArrayList<>();

            // [i, j) 用来定位找到的连续区间.
            int i = 0, j = 0;

            while (i != nums.length) {
                /**
                 * 1. 找到一个区间
                 * 2. 加入到结果集中
                 * 3. 调整 i,j用来寻找下一个区间
                 */

                ++j; // 错开一个元素, 初始时,i,j 指向同一个元素
                while (j != nums.length && nums[j] == nums[j - 1] + 1) {
                    ++j;
                }

                // 此时[i, j)定位到了一个连续子区间
                if (i + 1 == j) {
                    result.add(nums[i] + "");
                } else {
                    result.add(nums[i] + "->" + nums[j - 1]);
                }

                i = j;

            }
            return result;

        }
    }


}
