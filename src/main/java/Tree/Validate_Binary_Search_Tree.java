package Tree;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.List;

@Leetcode(
        title = "Validate Binary Search Tree",
        link = "https://leetcode.com/problems/validate-binary-search-tree/",
        category = Category.TREE,
        how2SolveIt = """
                方法一: 
                
                这题是一个证明题, 还记得
                
                1. 如果P, 则Q, 非Q, 则非P么.
                2. 如果P, 则Q, 如果Q, 那么P么
                
                以上这个逻辑有问题, 只能证明非P, 不能证明P
                
                如果想利用Q证明P, 则应该 P , Q等价
                
                这里的代码之所有生效, 是因为
                
                中序遍历为递增数列的二叉树就是二叉搜索树
                
                如果是一个BST, 那么, 中序遍历一定是递增数组, 而递增数组好判断.
                
                启示:
                
                该方法给人的启示是, 将一个P转换成容易证明的Q
                另外要警惕, 如果P, 则Q, 如果Q, 那么P的逻辑错误.
                
                方法二:
                
                方法一其实是把所有结点遍历了.
                
                我们人工判断时, 其实很容易知道一个树是不是BST, 不用遍历完了, 把人工判断时的逻辑写成代码.
                
                注意BST并不等价于 " root.left.val < root.val < root.right.val
                
                这里的根本还是要寻找等价条件
                
                等价条件如下
                
                1. 左右都是一个BST
                2. 左边最大的小于root.val
                3. 右边最小的大于root.val
              
                方法三:
                
                方法二中, 从左子树返回时, 还要再遍历一次找最大(或最小), 最终是要将最大或最小与root比较, 
                
                那么, 能不能记录下最大或最小
                
                现在isValidBST已经返回一个boolean, 我们不能利用返回值了
                
                但还有两种方式能让isValidBST返回多个值
                
                1. 定义一个结构体, 作为参数
                2. 利用全局变量.
                3. 定义一个结构体, 作为返回值
                
                注意这两种是不同的
                
                1. 定义一个结构体, 作为参数: 这种其实是在递归过程中修改同一个东西, 不涉及到栈, 你得自己处理回溯
                2. 利用全局变量和1一样
                3. 定义一个结构体, 作为返回值: 这种就利用了递归过程中的栈, 所以不需要作回溯.
                
                方法四:
                
                这种方法看起来trick, 其实是对dfs的理解还是不够深入.
                
                这里, p指向的结点顺序就是中序遍历的顺序
                当walk到某一个结点时, p就是指的其中序遍历的前的一结点.
                所以本质上和转化成数组之后, 两两相邻比较是一样的.
                所以在判断右子树时, 就没有哪p和右子树的第一个遍历结点比较了.
                
                启示:
                
                这种把递归当成迭代的思想确实有趣, 递归的访问点决定了迭代的顺序, 有意思.
                
                """,
        relatedQuestions = {}
)
public class Validate_Binary_Search_Tree {

    static class First {
        public boolean isValidBST(TreeNode root) {
            List<Integer> inorder = new ArrayList<>();
            walk(root, inorder);

            for (int i = 0; i < inorder.size() - 1; i++) {
                int first = inorder.get(i);
                int second = inorder.get(i + 1);
                if (first >= second) {
                    return false;
                }
            }

            return true;
        }

        private void walk(TreeNode node, List<Integer> inorder) {

            if (node == null) return;
            walk(node.left, inorder);
            inorder.add(node.val);
            walk(node.right, inorder);
        }
    }

    static class Second {
        public boolean isValidBST(TreeNode root) {

            if (root == null) return true;

            boolean leftIsBST = isValidBST(root.left);
            boolean rightIsBST = isValidBST(root.right);

            if (leftIsBST && rightIsBST) {
                // 找左边最大的
                TreeNode p = root.left;

                if (p != null) {
                    // 当p不是最右结点时
                    while(p.right != null) {
                        p = p.right;
                    }

                    // P指向左子树最右, 即左子树最大的
                    if (p.val >= root.val) {
                        return false;
                    }
                }

                // 找右边最小的
                p = root.right;
                if (p != null) {
                    while (p.left != null) {
                        p = p.left;
                    }

                    if (p.val <= root.val) {
                        return false;
                    }
                }

                return true;

            } else {
                return false;
            }


        }

    }

    static class Third {

        public boolean isValidBST(TreeNode root) {
            if (root == null) return true;
            Result result = walk(root);
            return result.isBST;
        }

        private Result walk(TreeNode node) {

            // 不要走到空节点, 空节点的最小和最大无意义
            // if (node == null) return new Result(true, 0, 0);


            // 走到叶子结点
            if (node.left == null && node.right == null) {
                return new Result(true, node.val, node.val);
            }

            // 走到没有左子树但有右子树的结点
            if (node.left == null) {
                Result rightResult = walk(node.right);
                if (rightResult.isBST && node.val < rightResult.min) {
                    return new Result(true, node.val, rightResult.max);
                } else {
                    return new Result(false, 0, 0);
                }
            }

            // 走到没有右子树但有左子树的结点
            if (node.right == null) {
                Result leftResult = walk(node.left);
                if (leftResult.isBST && node.val > leftResult.max) {
                    return new Result(true, leftResult.min, node.val);
                } else {
                    return new Result(false, 0, 0);
                }
            }

            // 左右子树都有
            Result leftResult = walk(node.left);
            Result rightResult = walk(node.right);

            boolean isBST = leftResult.isBST && rightResult.isBST && (leftResult.max < node.val && node.val < rightResult.min);
            return new Result(isBST, leftResult.min, rightResult.max);
        }

        /**
         * 保存dfs一个node的结果
         */
        static class Result {
            boolean isBST;      // 是不是bst
            int min;            // 最小值
            int max;            // 最大值

            public Result(boolean isBST, int min, int max) {
                this.isBST = isBST;
                this.min = min;
                this.max = max;
            }
        }

    }

    static class Forth {
        TreeNode p; // p指向要迭代的节点的前一个结点
        public boolean isValidBST(TreeNode root) {
            if (root == null) return true;

            boolean left = isValidBST(root.left);
            if (!left) {
                return false;
            }
            // 此时p指向中序遍历时, 最右结点, 也就是左子树最大值
            if (p != null && root.val <= p.val) {
                return false;
            }

            p = root;


            // 此时p指向的是右子树中序遍历的第一个节点的前一个节点
            // 注意, 这里为什么没有拿p和右子树中序遍历的第一个节点的前一个节点比较
            // 因为右子树中序遍历时, 第一个遍历的节点就是最左边的节点, 即最小的节点, 而此时p指向的刚好是它的前一个节点
            // 由上面的代码可以看出, 确实是将p和该结点进行比较了.
            return isValidBST(root.right);
        }

    }

    public static void main(String[] args) {
        Construct_Binary_Tree constructor = new Construct_Binary_Tree();

        String[] treeInArr = {"5","1","2"};
        TreeNode tree = constructor.buildTree(treeInArr);

        tree.prettyPrint();

        Forth forth = new Forth();
        System.out.println(forth.isValidBST(tree));
    }

}
