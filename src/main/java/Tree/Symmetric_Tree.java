package Tree;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Leetcode(
        title = "Symmetric Tree",
        link = "https://leetcode.com/problems/symmetric-tree/",
        category = Category.TREE,
        how2SolveIt = """
                
                方法一: 
                观察一下这种树的特点.
                
                如果一个树是镜像的, 左右子树是镜像的.
                
                两个树是镜像的, 可递归到其子树是镜像的,  一个树的左子树和另一个树的右子树是对称的, 一个树的右子树与一个树的左子树是对称的.
                
                由此可写出递归的方法
                
                注意, 这个DFS有点特别, 是因为其DFS了两颗树, 而不是一颗
                
                方法二:
                
                如果是递归的迭代方法, 这个比较复杂, 因为是DFS了两颗
                
                方法三:
                
                层序遍历也可, 就是每一层满足
                1. 个数上是2的指数
                2. 值是对称的.
                
                只要某一层不满足, 那么就不是镜像的.
                
                这个方法也行, 只是对于某些case, 超时了
                
                方法四:
                
                依然是层序遍历, 但三中使用的是一个队列, 因为递归时, 其实比较的是两个树, 那么, 我们可以使用两个队列
                
                注意, 使用层序遍历, 有时要有明确的分层, 有时又不需要明确的分层, 只要保证遍历的顺序是层序就行.
                
                方法三中是需要明确拿出一层的元素, 然后比较的, 方法四并不需要明确分层
                
                方法五:
                
                依然是方法四的改进, 只使用一个队列, 只是把这个队列当成两个队列来使用就行(不如两个队列清晰)
                
                启示:
                1. 分层遍历不一定要有明确层的划分
                2. 分层遍历中的队列不一定是保存某一层中的所有, 可能只是一个临时的容器, 因为本质上, 对于一个结点, 在分层遍历过程中, 只要
                处理完了, 就不必再保存了, 对于方法四就是这种情况.
                3. 一个队列可以当两个来使用, 如方法五
                """,
        relatedQuestions = {}
)
public class Symmetric_Tree {

    static class First {
        public boolean isSymmetric(TreeNode root) {

            if (root == null) return true;

            return isSymmetric(root.left, root.right);
        }

        // 判断两个树是不是镜像的.
        private boolean isSymmetric(TreeNode tree1, TreeNode tree2) {

            if (tree1 == null && tree2 == null) {
                return true;
            }

            // 有一方为null, 另一方不为null
            if (tree1 == null || tree2 == null) {
                return false;
            }

            // 两方都不是nuLl
            if (tree1.val != tree2.val) {
                return false;
            }

            // 判断子树是不是镜像的
            return isSymmetric(tree1.left, tree2.right) && isSymmetric(tree1.right, tree2.left);
        }
    }

    static class Third {
        public boolean isSymmetric(TreeNode root) {

            if (root != null) {

                Queue<TreeNode> queue = new LinkedList<>();

                queue.add(root);

                // 依然是每一次处理一层的情况
                while (true) {
                    // list保存的是当前处理层的结点值. java中的ArrayList允许add null, 这里把NULL也算上
                    List<TreeNode> list = new ArrayList<>();
                    // queue保存的是当前将要遍历的层的结点, count就是该层结点数, 这里把NULL把算上
                    int count = queue.size();

                    // 先把该层中的结点都取出来, 放到list中, 好比较
                    while (count != 0) {
                        TreeNode node = queue.poll();
                        list.add(node);
                        count--;
                    }

                    // 比较list中结点是否对称
                    int i = 0;
                    int j = list.size() - 1;
                    while (i < j) {
                        TreeNode left = list.get(i);
                        TreeNode right = list.get(j);

                        if (left == null && right == null) {
                            i++;
                            j--;
                        } else if (left == null || right == null) {
                            return false;
                        } else {
                            if (left.val != right.val) {
                                return false;
                            } else {
                                i++;
                                j--;
                            }
                        }
                    }


                    // 从该层结点生成下一层结点
                    boolean lastLevel = true;
                    for (TreeNode node : list) {
                        if (node != null) {
                            lastLevel = false;
                            queue.add(node.left);
                            queue.add(node.right);
                        } else {
                            queue.add(null);
                            queue.add(null);
                        }
                    }

                    // 如果是最后一层, 就不再往下
                    if (lastLevel) {
                        break;
                    }
                }
            }

            return true;


        }

        static class Fourth {
            public boolean isSymmetric(TreeNode root) {

                if (root != null) {
                    //
                    Queue<TreeNode> queue1 = new LinkedList<>();
                    Queue<TreeNode> queue2 = new LinkedList<>();

                    queue1.add(root.left);
                    queue2.add(root.right);

                    while (!queue1.isEmpty() && !queue2.isEmpty()) {
                        TreeNode node1 = queue1.poll();
                        TreeNode node2 = queue2.poll();

                        if (node1 == null && node2 != null) {
                            return false;
                        } else if (node1 != null && node2 == null) {
                            return false;
                        } else if (node1 != null && node2 != null) {
                            if (node1.val != node2.val) {
                                return false;
                            }
                        }

                        // 此时两者相同, 要不值一样, 要不就都是null
                        if (node1 != null && node2 != null) {
                            queue1.add(node1.left);
                            queue1.add(node1.right);

                            queue2.add(node2.right);
                            queue2.add(node2.left);
                        }
                    }

                    return queue1.isEmpty() && queue2.isEmpty();
                }

                return true;

            }


        }
    }

    public static void main(String[] args) {
        Construct_Binary_Tree helper = new Construct_Binary_Tree();

        int[] treeInArr = {1,2,2,3,4,4,3};
        TreeNode tree = helper.buildTree(treeInArr);

        Third third = new Third();
        boolean result = third.isSymmetric(tree);
        System.out.println(result);
    }

}
