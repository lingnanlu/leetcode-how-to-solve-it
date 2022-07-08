package Tree;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Leetcode(
        title = "N-ary Tree Level Order Traversal",
        link = "https://leetcode.com/problems/n-ary-tree-level-order-traversal/",
        category = Category.TREE,
        how2SolveIt = """
                注: 这题没看明白N树是怎么根据输入构建出来的.
                
                其实思路和二叉树是一样的.
                """,
        relatedQuestions = {}
)
public class Nary_Tree_Level_Order_Traversal {
    public List<List<Integer>> levelOrder(Node root) {

        List<List<Integer>> result = new ArrayList<>();

        if (root != null) {

            Queue<Node> queue = new LinkedList<>();

            // queue保存的是当前将要遍历的层的结点
            queue.add(root);

            // 依然是每一次处理一层的情况
            while (queue.size() != 0) {
                List<Integer> list = new ArrayList<>();
                // queue保存的是当前将要遍历的层的结点, count就是该层结点数
                int count = queue.size();
                while (count != 0) {
                    Node node = queue.poll();
                    list.add(node.val);
                    for (Node child : node.children) {
                        queue.add(child);
                    }
                    count--;
                }
                result.add(list);
            }
        }

        return result;

    }
}
