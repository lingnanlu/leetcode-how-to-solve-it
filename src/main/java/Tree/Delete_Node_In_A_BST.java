package Tree;


import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Delete Node in a BST",
        link = "https://leetcode.com/problems/delete-node-in-a-bst/",
        category = Category.TREE,
        how2SolveIt = """
                
                思路一:
                
                
                依然是画个图, 发现删除一个结点, 除了要找到该结点本身以外, 还需要其父结点, 因为要修改父结点的孩子.
                
                另一个问题就是删除了该结点后,该结点的左右子树怎么办?
                
                其实可以这样
                
                1. 如果有左子树, 找到左子树最右边的结点, 来代替它
                2. 如果没有左子树, 但有右子树, 就用右子树的根来代替它
                3. 如果左右子树都没有, 那么就直接删除
                
                这种摘除的方法是行不通的, 因为找到前继结点之后, 摘除前继结点要删除它, 但删除它本来就是我们要求的问题, 这样就会一直递归下去.
                
                
                思路二:
                
                其实删除不一定是真的物理删除, 我们可以使用覆盖的办法, 方法一是物理删除, 方法二我们找前继结点或后继结点, 然后把值赋给它
                再把前继或后继删除即可
                
                如何又另一个值来补它的空档, 那么另一个值又由谁来补呢?
                
                其实这种把另一个结点来弥补要删除的依然有问题
                
                思路三:
                
                不管是真删除也好, 还是假删除也好, 其实都是删除一个结点会引发删除另一个结点, 把另一个结点放到被删除结点的位置.
                而另一个结点的删除, 又会引发另另一个结点的删除, 这是一个连锁反应.
                
                看来这个连锁反应是避免不了的, 那么, 连锁反应总要有个结束吧? 什么时候会结束呢? 
                
                我们看这个连锁到底是什么
                
                找到要删除的结点 -> 
                
                {
                1. 如果要删除的结点是叶子结点 => 直接删除
                2. 如果要删除的结点没有左子树或右子树 => 直接使用右子树或左子树
                3. 如果要删除的结点有右子树和左子树 => 
                    1. 找到前继结点
                    2. 将前继结点的值赋值给要删除的结点
                    3. 删除前继结点 (这一步就是递归删除了)
                }
                
                其实最复杂的就是第三种情况了, 那么, 第三种这种连锁反应什么时候是个头呢? 其实, 就是递归删除到前继结点是1,2就可以了. 而
                由二叉BST可知, 前继不可能是双子结点. 所以这个连锁也就进行一次.
                
                启示:
                
                1. 本题并不难, 主要就是考虑的情况有些多, 要细致和画图
                2. 比较两个结点是否等要使用值比较, 而不是引用比较
                """,
        relatedQuestions = {}
)
public class Delete_Node_In_A_BST {

    static class First {

        public TreeNode deleteNode(TreeNode root, int key) {

            TreeNode parent = null;
            TreeNode cur = root;
            while (cur != null) {
               if (cur.val > key) {
                    parent = cur;
                    cur = cur.left;
               } else if (cur.val < key) {
                   parent = cur;
                   cur = cur.right;
               } else {
                   break;
               }
            }

            // 此时cur指向要删除的元素
            if (cur == null) {
                // 要删除的元素不存在
                return root;
            } else {
                // 要删除的元素存在, 找前继结点
                TreeNode rightMostParent = cur;
                TreeNode rightMost = cur.left;
                while (rightMost != null && rightMost.right != null) {
                    rightMostParent = rightMost;
                    rightMost = rightMost.right;
                }

                if (rightMost == null) {
                    // 没有前继结点, 右子树补上.
                    if (parent == null) {
                        // 父结点没有, 说明删除的是根
                        return cur.right;
                    } else {
                        // 有父结点
                        if (parent.left != null && parent.left.val == cur.val) {
                            parent.left = cur.right;
                        } else {
                            parent.right = cur.right;
                        }
                    }
                } else {
                    // 有前继结点
                    // 将前继结点从其父结点上摘下来 (不能摘下来.
                    // 然后作为新的根结点
                    if (rightMostParent == cur) {
                        cur.left = null;
                        rightMost.right = cur.right;
                    } else {
                        // 把rightMost摘下来
                        rightMostParent.right = null;
                        rightMost.left = cur.left;
                        rightMost.right = cur.right;
                    }

                    if (parent == null) {
                        return rightMost;
                    } else {
                        if (parent.left != null && parent.left.val == cur.val) {
                            parent.left = rightMost;
                        } else {
                            parent.right = rightMost;
                        }
                    }
                }


            }

            return root;
        }
    }

    static class Third {

        public TreeNode deleteNode(TreeNode root, int key) {
            // 1. 找到要删除的结点
            // 2. 如果是叶子结点 => 直接删除
            // 3. 如果是单子结点 => 直接删除
            // 4. 如果双子结点
            //    1. 找到前继
            //    2. 值替换
            //    3. 直接删除前继结点

            TreeNode parent = null;
            TreeNode cur = root;
            while (cur != null) {
                if (cur.val > key) {
                    parent = cur;
                    cur = cur.left;
                } else if (cur.val < key) {
                    parent = cur;
                    cur = cur.right;
                } else {
                    break;
                }
            }

            // 此时cur指向要删除的元素
            if (cur == null) {
                // 要删除的元素不存在
                return root;
            } else {

                // 要删除的元素存在.
                // 1. 如果是叶子结点. 直接删除
                if (cur.left == null && cur.right == null) {
                    if (parent == null) return null;
                    else {
                        // 要删除元素是父结点的左结点
                        if (parent.left != null && (parent.left.val == cur.val)) {
                            parent.left = null;
                        } else {
                            parent.right = null;
                        }
                    }
                } else if (cur.left == null) {      // 只有右子树
                    if (parent == null) return cur.right;
                    else {
                        if (parent.left != null && (parent.left.val == cur.val)) {
                            parent.left = cur.right;
                        } else {
                            parent.right = cur.right;
                        }
                    }
                } else if (cur.right == null) {    // 只有左子树
                    if (parent == null) return cur.left;
                    else {
                        if (parent.left != null && (parent.left.val == cur.val)) {
                            parent.left = cur.left;
                        } else {
                            parent.right = cur.left;
                        }
                        return root;
                    }
                } else {
                    // 左右子树都有, 找前继结点, 前继结点是其左子树中, 最右的结点
                    TreeNode rightMostParent = cur;
                    TreeNode rightMost = cur.left;
                    while (rightMost.right != null) {
                        rightMostParent = rightMost;
                        rightMost = rightMost.right;
                    }

                    // 此时rightMost指向前继结点, rightMostParent是它的父结点.

                    cur.val = rightMost.val;

                    // 删除前继结点, 前继结点一定没有右子树.
                    if (rightMostParent.val == cur.val) {
                        rightMostParent.left = rightMost.left;
                    } else {
                        rightMostParent.right = rightMost.left;
                    }

                }

            }

            return root;

        }

    }

    public static void main(String[] args) {
        Construct_Binary_Tree constructor = new Construct_Binary_Tree();


        String treeInStr = "1,0,15,null,null,4,35,3,8,25,49,2,null,5,12,22,27,47,null,null,null,null,7,11,13,19,24,26,31,40,48,6,null,9,null,null,14,17,21,23,null,null,null,30,33,39,42,null,null,null,null,null,10,null,null,16,18,20,null,null,null,28,null,32,34,36,null,41,44,null,null,null,null,null,null,null,null,null,29,null,null,null,null,null,37,null,null,43,46,null,null,null,38,null,null,45";
        TreeNode tree = constructor.buildTreeLeetCode(treeInStr);

        tree.prettyPrint();

       // First first = new First();
       // first.deleteNode(tree, 7);
    }

}
