package question.array;

import run.elder.Category;
import run.elder.Leetcode;
import run.elder.Solution;

import java.util.HashSet;
import java.util.Set;


@Leetcode(
        title = "Contains Duplicate",
        link = "https://leetcode.com/problems/contains-duplicate/",
        category = Category.ARRAY,
        how2SolveIt = """
                
                1. 最直观的依然是使用一个Set来保存出现过的元素。
                2. 另一种方式是排序，然后出现连接相同的就行。
                
                其实关于数组有一种通用的尝试的方式，就是
                
                如果乱序不好解决，那么，先排个序，改变一个数据结构，再看看有没有更好的方式，
                
                """,
        relatedQuestions = {"Majority Element"}
)
public class Contains_Duplicate {

    @Solution
    public boolean containsDuplicate(int[] nums) {

        if (nums.length == 0) return false;
        // 使用set来检查一个元素有没有出现过
        Set<Integer> s = new HashSet<>(nums.length);

        s.add(nums[0]);

        for(int i = 1; i < nums.length; i++) {
            if(s.contains(nums[i])) {
                return true;
            } else {
                s.add(nums[i]);
            }
        }

        return false;
    }

}
