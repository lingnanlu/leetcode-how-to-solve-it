package Array;


import run.elder.Category;
import run.elder.Leetcode;

import java.util.Arrays;

@Leetcode(
        title = "Intersection of Two Arrays II",
        link = "https://leetcode.com/problems/intersection-of-two-arrays-ii/",
        category = Category.ARRAY,
        how2SolveIt = """
                其实就是不去重的版本, 可以拿之前的结果来修改一下, 因为不去重, 所以不使用set, 就是修改方法三了
                """,
        relatedQuestions = {}
)
public class Intersection_Of_Two_Arrays_II {

    public int[] intersect(int[] nums1, int[] nums2) {

        int[] result = new int[Math.min(nums1.length, nums2.length)];
        int p = 0; // result中下一个空位.

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        // i, j用来依次遍历两个数组
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                result[p++] = nums1[i];
                i++;
                j++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                i++;
            }
        }

        int[] finalResult = new int[p];
        for (int k = 0; k < p; k++) {
            finalResult[k] = result[k];
        }
        return finalResult;


    }

}
