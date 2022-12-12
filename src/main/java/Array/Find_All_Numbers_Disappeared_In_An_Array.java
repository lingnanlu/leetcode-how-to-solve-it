package Array;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.List;

@Leetcode(
        title = "Find All Numbers Disappeared in an Array",
        link = "https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/description/",
        category = Category.ARRAY,
        how2SolveIt = """
                方法一:
                
                最直观的, 使用一个数组来标记出现过的元素.
               
               方法二:
               
               方法一中使用了额外空间, 但能不能不使用额外空间?
               
               但这题似乎依然要使用一个标记数组, 那么, 能想到的方式就是把原数组当成标记数组来用.
               
               比如
               [4,3,2,7,8,2,3,1]
               
               如果使用额外的标记数组, 其实是把对应值为下标对应的值设置为0
               
               [1, 1, 1, 1, 1, 1, 1, 1] => [0, 0, 0, 0, 1, 1, 0, 0]
               
               但如果把原数组当成标记数组, 会有一个问题
               
              [4,3,2,7,8,2,3,1] => [4, 3, 2, 0, 8, 2, 3, 1] 这里7就没了.
              那么, 怎么保留7的信息, 但又能标记呢? 使用负数
              
               [4,3,2,7,8,2,3,1] => [4, 3, 2, -7, 8, 2, 3, 1] 
               
               -说明这个位置的下标对应的数字出现过, 但还保留了原来的信息
              
              启示:
              这题的第二种想法其实并不难, 依然是对第一种方法的优化, 这告诉自己, 不要一开始就想搞一件大事情, 先从简单的来
              
                """,
        relatedQuestions = {}
)
public class Find_All_Numbers_Disappeared_In_An_Array {

    static class First {
        public List<Integer> findDisappearedNumbers(int[] nums) {
            int[] mark = new int[nums.length];

            for (int n : nums) {
                mark[n - 1] = 1;
            }

            List<Integer> result = new ArrayList<>();

            for (int i = 0; i < mark.length; i++) {
                if (mark[i] == 0) {
                    result.add(i + 1);
                }
            }

            return result;

        }
    }

    static class Second {

        public List<Integer> findDisappearedNumbers(int[] nums) {

            for (int n : nums) {
                int m = Math.abs(n);
                if (nums[m - 1] > 0) {
                    nums[m - 1] = -nums[m - 1];
                }
            }

            List<Integer> result = new ArrayList<>();

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0) {
                    result.add(i + 1);
                }
            }

            return result;

        }

    }
}
