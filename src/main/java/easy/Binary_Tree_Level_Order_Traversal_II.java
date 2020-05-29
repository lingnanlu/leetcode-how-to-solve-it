package easy;

import java.util.*;

public class Binary_Tree_Level_Order_Traversal_II {

    public static void main(String[] args) {
        Binary_Tree_Level_Order_Traversal_II test = new Binary_Tree_Level_Order_Traversal_II();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(test.levelOrderBottom(root));
    }
    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        Stack<List<Integer>> stack = new Stack<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();      //保存着将要处理的层的结点

        queue.add(root);
        while(!queue.isEmpty()) {
            int count = queue.size();                  //开始处理下一层
            List<Integer> data = new ArrayList<Integer>();

            //还剩余count个没有处理完
            while (count > 0) {
                TreeNode node = queue.poll();
                data.add(node.val);
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
                count--;
            }

            stack.push(data);

        }

        List<List<Integer>> result = new ArrayList<List<Integer>>();

        while(!stack.isEmpty()) {
            result.add(stack.pop());
        }


        return result;

    }

}
