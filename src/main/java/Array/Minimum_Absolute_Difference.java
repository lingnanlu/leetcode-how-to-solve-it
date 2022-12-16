package Array;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Leetcode(
        title = "Minimum Absolute Difference",
        link = "https://leetcode.cn/problems/minimum-absolute-difference/",
        category = Category.ARRAY,
        how2SolveIt = """
                
                排成一个有序的数组, 然后先找最小的mad, 然后再遍历一遍找所有和这个相等的.
                """,
        relatedQuestions = {}
)
public class Minimum_Absolute_Difference {

    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);

        int mad = Integer.MAX_VALUE;

        for (int i = 0; i <= arr.length - 2 ; i++) {
            mad = Math.min(mad, arr[i + 1] - arr[i]);
        }

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i <= arr.length - 2 ; i++) {
            if ((arr[i + 1] - arr[i]) == mad) {
                result.add(List.of(arr[i], arr[i + 1]));
            }
        }

        return result;

    }
}
