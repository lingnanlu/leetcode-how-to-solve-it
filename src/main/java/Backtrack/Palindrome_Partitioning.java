package Backtrack;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.List;

@Leetcode(
        title = "Palindrome Partitioning",
        link = "https://leetcode.com/problems/palindrome-partitioning/",
        category = Category.ARRAY,
        how2SolveIt = """
                这题没那么直观, 但可以先切出第一个子串, 如果能切出第一个子串的话, 剩余的又有多种情况
                
                就这样一直切下去, 直到不能再切为止
                
                发现没, 这又是一个多步, 每一步又有多种可能的题解过程, 所以还是树.
                
                对于每一个结点, 就是一个子串, 只有是回文才能继续分割下去, 如果到了叶子结点, 还是回文, 那么, 就是一种分割方案了.
                """,
        relatedQuestions = {}
)
public class Palindrome_Partitioning {


    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();

        for (int i = 1; i <= s.length() ; i++) {
            walk(s, 0, i, path, result);
        }

        return result;
    }


    /**
     * @param s
     * @param from 当前子串的[from, to)
     * @param to 当前子串的[from, to)
     * @param path 保存路径
     * @param result 保存结果
     */
    private void walk(String s, int from, int to, List<String> path, List<List<String>> result) {

        // 不是回文, 什么也不做
        if (!palindrome(s, from, to)) {
            return;
        }

        path.add(s.substring(from, to));
        // 当将子串是最后一个了
        if (to == s.length()) {
            result.add(new ArrayList<>(path));
        } else {
            for (int i = to + 1; i <= s.length(); i++) {
                walk(s, to, i, path, result);
            }
        }

        path.remove(path.size() - 1);
    }


    private boolean palindrome(String s, int from, int to) {
        int i = from, j = to - 1;
        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }
}
