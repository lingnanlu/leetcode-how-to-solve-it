package Tree;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Leetcode(
        title = "N-ary Tree Preorder Traversal",
        link = "https://leetcode.com/problems/n-ary-tree-preorder-traversal/",
        category = Category.TREE,
        how2SolveIt = """
                
                方法一: 递归
                
                方法二: 迭代
                
                对于栈来说, 其实入栈的顺序就是DFS的顺序, 至少什么时候出栈, 就看是不是对它进行visit了, 如果已经visit,并且安排好了它的孩子
                的入栈顺序符合DFS, 就可以出栈了.
                """,
        relatedQuestions = {}
)
public class Nary_Tree_Preorder_Traversal {

    static class First {
        public List<Integer> preorder(Node root) {
            List<Integer> result = new ArrayList<>();
            walk(root, result);
            return result;
        }

        private void walk(Node node, List<Integer> order) {
            if (node == null) return;

            visit(node, order);

            for (Node child : node.children) {
                walk(child, order);
            }
        }

        private void visit(Node node, List<Integer> order) {
            order.add(node.val);
        }
    }

    static class Second {

        public List<Integer> preorder(Node root) {
            List<Integer> list = new ArrayList<>();
            if (root == null) return list;

            Stack<Node> stack = new Stack<>();
            stack.add(root);

            while (!stack.empty()) {
                root = stack.pop();
                list.add(root.val);
                for (int i = root.children.size() - 1; i >= 0; i--)
                    stack.add(root.children.get(i));
            }

            return list;
        }
    }


    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}
