package dfs;

import java.util.ArrayList;
import java.util.List;

// 是一个n * 2 层的树
public class Generate_Parentheses {

    public List<String> generateParenthesis(int n) {

        StringBuilder path = new StringBuilder();
        List<String> result = new ArrayList<>();

        visit('(', 1, n, 0, 0, path, result);

        return result;
    }

    /**
     *
     * 其实，算法保证了在访问一个结点时，该结点一定是合法的，不合法的其实已经被之前过滤了
     *
     * 还是要想象一个人去访问一个个的结点，这样更加形象化
     *
     * @param c          其实就是合法的
     * @param layer      将要访问的字符所在的层数
     * @param n          括号对数，最大层数 = n * 2
     * @param leftCount  到目前为止左括号的数量
     * @param rightCount 到目前为止右括号的数量
     * @param path       保存一个正确的结果
     * @param result     保存所有结果
     */
    private void visit(char c,
                       int layer,
                       int n,
                       int leftCount,
                       int rightCount,
                       StringBuilder path,
                       List<String> result) {

        // 因为在上一层决定要访问下一层时，已经去除了所有不可能的情况，所以c肯定是合法的
        path.append(c);

        if (layer == n * 2) {
            result.add(new String(path));
        } else {

            if (c == '(') {
                leftCount++;
            } else {
                rightCount++;
            }

            // 注意以下的两个if判断，有点小巧妙
            if (leftCount < n) {
                visit('(', layer + 1, n, leftCount, rightCount, path, result);
            }

            if (rightCount < leftCount) {
                visit(')', layer + 1, n, leftCount, rightCount, path, result);
            }

        }

        path.deleteCharAt(path.length() - 1);

    }
}
