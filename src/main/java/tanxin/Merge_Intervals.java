package tanxin;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Leetcode(
        title = "Merge Intervals",
        link = "https://leetcode.com/problems/merge-intervals/",
        category = Category.ARRAY,
        how2SolveIt = """
                这题比较简单, 就是排序之后, 依次merge.
                就是这个变量起名字, 不知哪个名字比较合适
                """,
        relatedQuestions = {}
)
public class Merge_Intervals {
    public int[][] merge(int[][] intervals) {

        if (intervals.length == 0) return intervals;
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);

        // 指向一个要合并进的区间
        int[] nextNonOverlapInterval = intervals[0];

        List<int[]> result = new ArrayList<>();
        result.add(nextNonOverlapInterval);

        // 依次遍历i, 看能否合并进nextNonOverlapInterval
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= nextNonOverlapInterval[1]) {
                nextNonOverlapInterval[1] = Math.max(intervals[i][1], nextNonOverlapInterval[1]);
            } else {
                nextNonOverlapInterval = intervals[i];
                result.add(nextNonOverlapInterval);
            }
        }

        int[][] result2 = new int[result.size()][];

        return result.toArray(result2);


    }
}
