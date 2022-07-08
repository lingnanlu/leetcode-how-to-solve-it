package Tree;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.LinkedList;
import java.util.Queue;

@Leetcode(
        title = "Populating Next Right Pointers in Each Node",
        link = "https://leetcode.com/problems/populating-next-right-pointers-in-each-node/",
        category = Category.TREE,
        how2SolveIt = """
                层次遍历的变化
                
                这里强调的是完全二叉树, 那么, 完全二叉树可以用数组表示, 暗示了可以利用坐标计算的方式来找到next的结点.
                """,
        relatedQuestions = {}
)
public class Populating_Next_Right_Pointers_In_Each_Node {

    public Node connect(Node root) {

        if (root != null) {

            Queue<Node> queue = new LinkedList<>();

            // queue保存的是当前将要遍历的层的结点
            queue.add(root);

            // 依然是每一次处理一层的情况
            while (queue.size() != 0) {
                // queue保存的是当前将要遍历的层的结点, count就是该层结点数
                int count = queue.size();
                while (count != 0) {
                    Node first = queue.poll();
                    Node second = queue.peek();

                    // 说明是最后一个了, 就直接指向null
                    if (count == 1) {
                        first.next = null;
                    } else {
                        first.next = second;
                    }

                    if (first.left != null) {
                        queue.add(first.left);
                    }

                    if (first.right != null) {
                        queue.add(first.right);
                    }
                    count--;
                }
            }
        }

        return root;


    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
}
