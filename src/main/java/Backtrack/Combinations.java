package Backtrack;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.List;

@Leetcode(
        title = "Combinations",
        link = "https://leetcode.com/problems/combinations/",
        category = Category.ARRAY,
        how2SolveIt = """
                
                方法一:
                
                举个例子 
                
                [1, 2, 3, 4]
                
                从这里面要选两个数, 即两步, 第一步第一个数, 第二步第二个数.
                
                两步, 让你想到树的深度
                
                每一步都有几种选择.
                
                所以, 就是一个多叉树的DFS, 画一个这个树, 想想
                
                1. walk
                2. visit
                3. 树在怎么在walk过程中生成的.
                
                方法二:
                
                如果k是固定的, 可以使用k层for循环, 如k==3, 可以使用两层, 但k并不固定
                
                启示:
                
                1. 如果不明白, 就画图
                2. 结合图, 想想walk和visit的含义
                3. 注意这个隐式树是什么样子的, 画个图.
                """,
        relatedQuestions = {}
)
public class Combinations {

    public List<List<Integer>> combine(int n, int k) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        for (int i = 1; i <= (n + 1) - k ; i++) {
            walk(i, n, k, result, path);

            // 这里对path不做操作是因为每个node返回时, 会还原, 就像没来过一样.
        }

        return result;
    }

    /**
     * @param i 走到的结点值
     * @param n 输入n
     * @param k 剩余选取个数
     * @param result 保存结果
     */
    private void walk(int i, int n, int k, List<List<Integer>> result, List<Integer> path) {

        path.add(i);
        // 当走到这个结点时, 剩余个数刚好是1, 那就这个结点就是所选择的最后一个值.
        if (k == 1) {
            result.add(new ArrayList<>(path));
        } else {
            // j < ?
            // 候选集是 [i + 1, n]
            // 还要从这里取 k - 1个值.
            // 那么,剪枝到 n + 1 - (k - 1) = n - k + 2
            for (int j = i + 1; j <= (n - k + 2) ; j++) {
                walk(j, n, k - 1, result, path);
            }
        }

        // 从这个结点离开, 要从路径中删除, 注意这个删除的时机
        // 进入的时候添加到路径中, 离开的时候从路径删除
        path.remove(path.size() - 1);

    }
}
