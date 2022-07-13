package Tree;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.LinkedList;
import java.util.Queue;

@Leetcode(
        title = "Lowest Common Ancestor of a Binary Tree",
        link = "https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/",
        category = Category.TREE,
        how2SolveIt = """
                
                这题要求最低的公共祖先, 如果没有思路的话, 能不能简化一下问题?
                
                比如说, 只求公共祖先?
                
                其实最直观的, 就是使用DFS, 依次考察每一个节点, 看其是不是p, q的祖先. 如果是, 就是公共祖先.
                
                但这只是找到公共祖先, 并一定是最低的, 所以还要往下找, 
                
                还有, 这些公共祖先之间, 也一定是父子关系, 不可能是兄弟关系.
                
                所以, 使用DFS, 一直找, 找到一个之后, 再找, 直到没有, 就是公共祖先了.
                
                
                方法一: 
                
                使用DFS, 考察每个节点是不是其公共祖先, 如果是, 最低的公共祖先也一定在它的子孙里.
                
                方法注意commonAncestor的使用
                
                方法二:
                
                方法一超时了, 那么就看看方法一中, 有没有效率低的地方.
                
                isAncestor是一个比较重的操作, 在DFS过程中, 先调用了isAncestor, 如果A是祖先, B也更低的祖先, 其实对于A, B
                都会执行一遍isAncestor.
                
                但, 如果我们知道B是祖先, 那么, A必然是祖先, 所以对于A就不用调用isAncestor了.
                
                而A是B的祖节点, 所以想到, 使用后序遍历可能更好.
                     
                
                启示:
                
                1. 在DFS过程中, 利用了一个外部状态(没在栈里)来记录DFS过程中的状态.
                
                方法三:
                
                方法二还是有问题.
                
                当左子树没有commonAncestor, 右子树没有commonAncestor时, 要对该结点进行BFS, 但如果依然没有, 则对于其父结点, 如果另一个
                兄弟结点也没有commonAcncestor, 那么对父结点也会进行isAncestor的操作.
                
                方法一是对存在时, 重复执行isAncestor, 方法二是对不存在时, 重复执行isAncestor.
                
                既然我们遍历了子树, 这里只利用了, 是否存在ancestor, 那么, 能不能在遍历过程中给出更多信息, 这样, 可以减少isAncestor的操作?
                
                比如, 如果没有ancestor, 那是否包含p, q呢?
                
                可以试试
                
                其实这里的方法, 就是回溯, 复用之前的结果, 不再重复计算.
                
                后序遍历就是天然的回溯过程.
                
                """,
        relatedQuestions = {}
)
public class Lowest_Common_Ancestor_Of_A_Binary_Tree {

    static class First {

        TreeNode commonAncestor;
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            walk(root, p, q);
            return commonAncestor;
        }


        private void walk(TreeNode node, TreeNode p, TreeNode q) {
            // 如果该结点是公共祖先, 那么,
            if (isAncestor(node, p, q)) {
                commonAncestor = node;

                if (node.left != null) {
                    walk(node.left, p, q);
                }

                // 如果左子树中有公共祖先, 那么, commonAncestor就会被修改, 就不用往右走了.
                // 否则可能在右子树中
                if (commonAncestor != node) {
                    return;
                }

                if (node.right != null) {
                    walk(node.right, p, q);
                }
            }
        }

        /**
         * 其实就看node树中, 包不包含两这个节点
         * 使用递归不太直观, 使用BFS吧
         */
        private boolean isAncestor(TreeNode node, TreeNode p, TreeNode q) {

            Queue<TreeNode> queue = new LinkedList<>();

            boolean pHasFound = false;
            boolean qHasFound = false;

            queue.add(node);

            while (!queue.isEmpty()) {
                TreeNode n = queue.poll();
                if (n == p) {
                    pHasFound = true;
                }

                if (n == q) {
                    qHasFound = true;
                }

                if (n.left != null) {
                    queue.add(n.left);
                }

                if (n.right != null) {
                    queue.add(n.right);
                }
            }

            return pHasFound && qHasFound;
        }
    }

    static class Second {

        TreeNode commonAncestor;
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            walk(root, p, q);
            return commonAncestor;
        }


        private void walk(TreeNode node, TreeNode p, TreeNode q) {

            // 先往左走

            if (node.left != null) {
                walk(node.left, p, q);
                if (commonAncestor != null) {
                    // 说明左子树找到了最低公共祖先, commonAncestor就是找的最终值, 不再寻找, 一直返回就行
                    return;
                }
            }

            // commonAncestor != null 说明左子树没有找到, 往右找
            if (node.right != null) {
                walk(node.right, p, q);
                if (commonAncestor != null) {
                    // 说明右子树找到了最低公共祖先, commonAncestor就是找的最终值, 不再寻找, 一直返回就行
                    return;
                }
            }

            // 即不在左子树, 又不在右子树
            if (isAncestor(node, p, q)) {
                commonAncestor = node;
            }
        }

        /**
         * 其实就看node树中, 包不包含两这个节点
         * 使用递归不太直观, 使用BFS吧
         */
        private boolean isAncestor(TreeNode node, TreeNode p, TreeNode q) {

            Queue<TreeNode> queue = new LinkedList<>();

            boolean pHasFound = false;
            boolean qHasFound = false;

            queue.add(node);

            while (!queue.isEmpty()) {
                TreeNode n = queue.poll();
                if (n == p) {
                    pHasFound = true;
                }

                if (n == q) {
                    qHasFound = true;
                }

                if (n.left != null) {
                    queue.add(n.left);
                }

                if (n.right != null) {
                    queue.add(n.right);
                }
            }

            return pHasFound && qHasFound;
        }
    }

    static class Third {

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            Result result = walk(root, p, q);
            return result.commonAncestor;
        }


        private Result walk(TreeNode node, TreeNode p, TreeNode q) {
            // 先往左走
            Result leftResult = null;
            if (node.left != null) {
                leftResult = walk(node.left, p, q);
                if (leftResult.commonAncestor != null) {
                    // 说明左子树找到了最低公共祖先, commonAncestor就是找的最终值, 不再寻找, 一直返回就行
                    // 此时 containsP和containsQ应该都是true
                    return leftResult;  //
                }
            }

            // commonAncestor != null 说明左子树没有找到, 往右找
            Result rightResult = null;
            if (node.right != null) {
                rightResult = walk(node.right, p, q);
                if (rightResult.commonAncestor != null) {
                    // 说明右子树找到了最低公共祖先, commonAncestor就是找的最终值, 不再寻找, 一直返回就行
                    return rightResult;
                }
            }


            // 此时左右子树没有公共祖先

            // 从左右子树的结果中推断出是否包含p和q
            boolean containsP = (leftResult == null ? false : leftResult.containsP) ||
                    (rightResult == null ? false : rightResult.containsP)
                    || node.val == p.val;

            boolean containsQ = (leftResult == null ? false : leftResult.containsQ) ||
                    (rightResult == null ? false : rightResult.containsQ)
                    || node.val == q.val;


            System.out.println(node.val + ":" + containsP + ":" + containsQ);
            if (containsP && containsQ) {
                return new Result(node, true, true);
            } else {
                return new Result(null, containsP, containsQ);
            }

        }

        /**
         * 保存dfs一个node的结果
         * commonAncestor = 该树中是否有commonAncestor
         * containsP = 该树中是否有p
         * containsQ = 该树中是否有q
         */
        static class Result {
            TreeNode commonAncestor;
            boolean containsP;
            boolean containsQ;

            public Result(TreeNode commonAncestor, boolean containsP, boolean containsQ) {
                this.commonAncestor = commonAncestor;
                this.containsP = containsP;
                this.containsQ = containsQ;
            }
        }


    }

    public static void main(String[] args) {

        Construct_Binary_Tree constructor = new Construct_Binary_Tree();

        String[] treeInArr = {"3","5","1","6","2","0","8","null","null","7","4"};
        TreeNode tree = constructor.buildTree(treeInArr);

        tree.prettyPrint();

        Third third = new Third();
        TreeNode commonAncestor = third.lowestCommonAncestor(tree, new TreeNode(7), new TreeNode(4));
        System.out.println(commonAncestor.val);



    }


}
