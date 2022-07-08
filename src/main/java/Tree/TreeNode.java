package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }

      public List<String> printInList() {
          List<String> result = new ArrayList<>();
          Queue<TreeNode> queue = new LinkedList<>();

          // queue保存的是当前将要遍历的层的结点
          queue.add(this);

          while (true) {

              int count = queue.size();

              List<TreeNode> currentLevelNodes = new ArrayList<>();

              boolean lastLevel = true;
              while (count != 0) {
                  TreeNode node = queue.poll();

                  if (node != null) {
                      lastLevel = false;
                  }
                  currentLevelNodes.add(node);
                  count--;
              }

              if (lastLevel) {
                  break;
              }

              for (TreeNode node : currentLevelNodes) {
                  if (node == null) {
                      result.add("null");
                      queue.add(null);
                      queue.add(null);
                  } else {
                      result.add(node.val + "");
                      queue.add(node.left);
                      queue.add(node.right);
                  }
              }
          }

          return result;


      }
 }