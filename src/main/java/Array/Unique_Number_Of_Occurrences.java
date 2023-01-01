package Array;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Leetcode(
        title = "Unique Number of Occurrences",
        link = "https://leetcode.cn/problems/unique-number-of-occurrences/",
        category = Category.ARRAY,
        how2SolveIt = """
                
                其实就两步
                第一步统计出现次数
                第二步看该出现次数是否重复过: 这个可以利用集合性质
                
                """,
        relatedQuestions = {}
)
public class Unique_Number_Of_Occurrences {

    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> count = new HashMap<>();

        for (int n : arr) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        Set<Integer> set = new HashSet<>();

        for (int n : count.values()) {
            if (set.contains(n)) {
                return false;
            } else {
                set.add(n);
            }
        }

        return true;
    }


}
