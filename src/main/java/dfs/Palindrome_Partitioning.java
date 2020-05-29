package dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 网上很多解法都提到了DFS，回shuo法，那么他们是如何想到这到题是要用DFS来解呢？
 *
 * 我猜有两种可能
 *
 * 1. 他们之前做过大量类似的，然后一眼看出来了
 * 2. 某个神秘灵感突现？一拍脑门，显然？
 *
 * 本文试着使用探索的态度与启发的方法来让一个缺乏经验的人也能想到此问题。
 *
 * 以aab为例
 *
 * 找出其有所可能的划分？
 *
 * 你怎么找
 *
 * a|ab
 *
 * 如果能找出所有ab的子划分就好了？
 *
 * 嗯，一个子问题，和原来一样？
 *
 * 有没有想到树？
 *
 * 一颗大树，分支也是一颗小树
 *
 * 用结点来表示。
 *
 *
 */
public class Palindrome_Partitioning {
    public List<List<String>> partition(String s) {

        List<List<String>> allPartitions = new ArrayList<>();

        if (s == null || s.isEmpty()) return allPartitions;

        List<String> partition = new ArrayList<>();

        visit(0, s, allPartitions, partition);

        return allPartitions;
    }

    /**
     * 其实不必找到所有可能的划分，如果某一个不是回文，就不必再深入了
     * @param start 表示在第start元素之前的隔板
     * @param s
     * @param allPartitions 保存所有可能的划分
     * @param partition 一种划分
     */
    private  void visit(int start, String s, List<List<String>> allPartitions, List<String> partition) {

        // 叶子结点，表示一种划分了
        if (start == s.length()) {
            allPartitions.add(new ArrayList<>(partition));
        } else {
            // 不是叶子结点，剩余部分还能划分
            for (int i = start + 1; i <= s.length(); i++) {


                // 访问每个子结点之前，将这个子结点代表的子串添加进去
                // 添加之前，判断是不是回文，不是就不再访问该分支

                String substr = s.substring(start, i);

                if (isPalindrome(substr)) {
                    partition.add(substr);
                    visit(i, s, allPartitions, partition);

                    // 访问完了之后，已以把这种情况下的所有添加到了allPartions
                    partition.remove(partition.size() - 1);
                }

            }
        }

    }

    private boolean isPalindrome(String s) {
        if (s == null || s.isEmpty()) return false;

        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != s.charAt(n - i - 1)) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        List<List<String>> allPartitions = new ArrayList<>();
        List<String> partition = new ArrayList<>();

        Palindrome_Partitioning pp = new Palindrome_Partitioning();
        pp.visit(0, "aab", allPartitions, partition);

        System.out.println(allPartitions);
    }


    /**
     * 寻找所有可能的划分
     *
     * @param start 表示在第start元素之前的隔板
     * @param s
     * @param allPartitions 保存所有可能的划分
     * @param partition 一种划分
     */
    private static void visit1(int start, String s, List<List<String>> allPartitions, List<String> partition) {

        // 叶子结点，表示一种划分了
        if (start == s.length()) {
            allPartitions.add(new ArrayList<>(partition));
        } else {
            // 不是叶子结点，剩余部分还能划分
            for (int i = start + 1; i <= s.length(); i++) {

                // 访问每个子结点之前，将这个子结点代表的子串添加进去
                partition.add(s.substring(start , i));
                visit1(i, s, allPartitions, partition);

                // 访问完了之后，已以把这种情况下的所有添加到了allPartions
                partition.remove(partition.size() - 1);
            }
        }

    }
}
