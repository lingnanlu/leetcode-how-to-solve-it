package Tree;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Trim a Binary Search Tree",
        link = "https://leetcode.com/problems/trim-a-binary-search-tree/",
        category = Category.TREE,
        how2SolveIt = """
                方法一:
                
                看样子可以转换成删除问题, 我们可以
                1. 先中序遍历得到一个有序数组
                2. 将有序数组和边界比较, 得到要删除的元素
                3. 对于每个要删除的元素, 调用一遍删除的逻辑
                
                方法二: 
                
                方法一是一个元素一个元素的删除, 可不可以整个删除, 一次删除多个. 
                
                比如说我们要删除 < A的结点.
                
                假如A结点就是树中结点, 那么, 小于它的结点和它的关系如何呢? 一定都在它的左边.
                
                画个图, 明白一下, 发现还是很复杂
                
                这个复杂性在于在遍历过程中不裁剪, 而是直接找到比如说第一个比low小的, 再去裁剪.
                
                其实在遍历过程中, 就可以裁剪了.边遍历, 边裁剪.
                
                这个迭代其实包含了两个子问题
                
                1. 找第一个比val小的元素
                2. 找第一个比val大的元素.
                
                所以说, 这个迭代其实不简单.
                
                方法三:
                
                既然删除很麻烦, 那么, 能不能换个思路, 不删除, 但保留未删除的元素, 这样, 也容易满足其结构要求.
                
                我们很容易知道要保留的元素的值. 
                
                现在就是如何遍历原来的树, 摘取保留的值并保持子孙关系
                
                我们试试中序遍历, 发现, 中序遍历的话, 正好和递归差不多了, 那么, 能不能使用递归啊.
                
                感觉也行. 试一试
                
                注意:
                
               题目的结构要求是, 如果原来 A是B的祖先, 在删除某一元素后, A要依然是B的祖先.
                
               如果A是B的旁亲, 删除某一元素后, A是B的祖先了, 这样也没问题
               
               方法四:
               
               依然是DFS, 方法三是O(n), 发现, 如果root.val < low, 那左边全部都小, 不用管了, 所以可以裁枝.
               
               
             
           
               启示:
               
               1. 如果一个题使用迭代思维太复杂, 可以尝试使用递归来试试
               2. 对于DFS, 如果是O(n), 也可以想想看能不能裁枝.
               3. 搜索BST其实就类似于二叉搜索, 要学习利用BST的搜索来定位到一些特殊的值, 如第一个比val小或大的元素.
               
                """,
        relatedQuestions = {}
)
public class Trim_A_Binary_Search_Tree {

    static class Third {
        public TreeNode trimBST(TreeNode root, int low, int high) {

            if (root == null) return null;
            // 1. 左子树trim
            TreeNode trimLeft = trimBST(root.left, low, high);

            // 2. 右子树trim
            TreeNode trimRight = trimBST(root.right, low, high);

            if (low <= root.val && root.val <= high) {
                root.left = trimLeft;
                root.right = trimRight;
                return root;
            } else {
                // 中间节点也要被删除, 要相办法链接左右子树, 并且保证descent关系.

                // 找左子树最大的那个
                TreeNode trimLeftMax = trimLeft;
                while (trimLeftMax != null && trimLeftMax.right != null) {
                    trimLeftMax = trimLeftMax.right;
                }

                if (trimLeftMax != null) {
                    trimLeftMax.right = trimRight;
                    return trimLeft;
                } else {
                    // 此时trimLeft其实为null
                    return trimRight;
                }
            }
        }
    }

    static class Forth {

        public TreeNode trimBST(TreeNode root, int low, int high) {

            if (root == null) return null;
            // 1. 左子树trim

            if (root.val < low) return trimBST(root.right, low, high);
            if (root.val > high) return trimBST(root.left, low, high);

            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);

           return root;
        }

    }

    static class Fifth {

        public TreeNode trimBST(TreeNode root, int low, int high) {
            //1. 先将root移动到low和high之间
            if (root == null) return null;

            TreeNode cur = root;
            while(cur != null && (cur.val < low || cur.val > high)) {
                if (cur.val < low) {
                    cur = cur.right;
                } else {
                    cur = cur.left;
                }
            }
            // 此时cur.val在low和high之间
            //2. 剪裁左子树时, 边遍历, 边裁剪.

            // 此时newRoot在[low, high]之间了.

            if (cur == null) return null;

            TreeNode newRoot = cur;
            TreeNode parent = newRoot;

            cur = newRoot.left;

            // 这里有一个隐含的子问题, 就是: 在一个BST中, 如何找到第一个比val小的元素.
            // 这里比如何找到第一个比val小的元素更难, 在找的过程中, 要剪枝.

            // cur会一直找, 直到为null
            // 找的过程时, 遇到比low大的, 就向左, 比low小的就向右. 直到为null
            // 而且找的过程是边找, 边剪枝
            while (cur != null) {
                // 一直向左走. 走到第一个比low小的.
                while (cur != null && cur.val >= low) {
                    parent = cur;
                    cur = cur.left;
                }

                if (cur == null) {
                    // 说明最左端元素(最小的元素)都比low大, 不用剪枝了.
                    break;
                } else {
                    // 此时cur < low, cur的左边也一定比low小, 所以往右找 , 并剪枝.
                    // 剪枝
                    parent.left = cur.right;
                    cur = parent.left;
                }
            }

            parent = newRoot;
            cur = newRoot.right;

            while (cur != null) {

                while (cur != null && cur.val <= high) {
                    parent = cur;
                    cur = cur.right;
                }

                if (cur == null) {
                    break;
                } else {
                    parent.right = cur.left;
                    cur = parent.right;
                }
            }

            return newRoot;

        }
    }

}
