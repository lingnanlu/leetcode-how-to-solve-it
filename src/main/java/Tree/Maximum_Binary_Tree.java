package Tree;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Maximum Binary Tree",
        link = "https://leetcode.com/problems/maximum-binary-tree/",
        category = Category.TREE,
        how2SolveIt = """
                
                方法一:
                
                就是从数组来构建二叉树, 一模一样. 
                
                但这种方法, 虽然构建过程中, 对每个节点都访问一次, 但耗时主要消耗在找最大值上面
                
                可以仔细考虑一个找最大值的过程, 不妨考虑两种特殊情况
                
                1. 递增顺序.
                
                这个就是O(n^2), 每一次都要遍历整个数组
                
                2. 每一次划分后, 最大值在中间
                
                这个时间复杂度怎么估计呢?
                
                可以想像, 整个过程就是构建一个树, 因为最大值是在中间, 所以是一个平衡二叉树.
                
                对每个结点, 都要顺序遍历完该结点中的所有值, 而每一层的结点中的值的个数总和是n
                
                层高是logn, 所以最终复杂度是O(nlogn)
                
                
                方法二:
                
                方法一最好就是O(nlogn), 但这里我们又看到了熟悉的数组, 那么, 哎, 是不是可以遍历一遍数组就可以了?
                这样不就是O(n)了么, 咱们试一试哈.
                
                这里最难的就是把生成的节点来连接起来, 这种连接涉及到两方面, 假如有A, B两个节点
                
                1. A和B哪个是父结点?
                2. 如果A是B的子结点, 那么, 是左子结点, 还是右子结点?
                
                而我们很容易猜到, 这两种关系与A, B值的大小, 以及, 在数组中的左右关系来确定的.
                
                举例来试试, 假设, 数组只有两个元素, 分析以下几种情况生成的结点关系
                
                [1, 2] => 2是1的父结点, 1是2的左子孩子
                
                [2, 1] => 2是1的父结点, 1是2的右子孩子
                
                看来, 大小关系决定了父子关系, 左右关系决定了左子孩子还是右子孩子.
                
                
                以上是2个元素, 我们扩展到三个元素试试
                
                [1, 2, 3]
                [3, 2, 1]
                
                同样符合
                
                但以上都是递增或递减, 试试不一样的
                
                [1, 5, 3]
                
                也行, 
                
                
                再试试题目中给出的例子
                
                [3,2,1,6,0,5]
                
                发现, 生成规则要变一下了, 
                
                [3, 2, 1]还没问题, 到了6时, 它要比之前最大的还要大, 所以是之前最大的结点的父, 而之前最大的是它的左.
                
                
                总结上面, 感觉到, 在整个遍历过程中, 似乎要有两个指针
                
                1. 指向已遍历的最大的
                2. 指向当前遍历的
                3. 指向当前遍历的前一个元素的.
                
                其实还有一种思考的方式, 就是常用的归纳法, 比如说, 我们假设
                
                [3, 2, 1, 6, 0]这向个元素都生成好了, 那么, 又来了一个元素, 怎么插入呢?
                
                比较直观的就是, 要记录, 之前的
                
                1. 最大
                2. 前一个
                
                (不要记录前两个, 这有点奇怪, 因为记录前两个, 为什么不记录前三个?)
                还有一种要记录的可能是之前最小
                
                总之记录的点是特殊的点的可能性最大.
                
                所以试试.
                
                最后,发现不需要前一个结点, 最关键的是, 想明白当比最大小时, 如何定位到插入位置.
               
               
                启示:
                
                1. 不要递归完了就完事了, 可以简单分析一个时间复杂度
                2. 看到数组, 想想有没有O(n)的方法
                3. 注意使用归纳法
                4. 注意举例子, 并画图
                """,
        relatedQuestions = {}
)
public class Maximum_Binary_Tree {

    static class First {
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            return build(nums, 0, nums.length);
        }

        private TreeNode build(int[] nums, int start, int end) {

            if ((end - start) <= 0) return null;

            int max = start;
            for (int i = start; i < end; i++) {
                if (nums[i] > nums[max]) {
                    max = i;
                }
            }

            TreeNode node = new TreeNode(nums[max]);

            int leftTreeStart = start;
            int leftTreeEnd = max;
            node.left = build(nums, leftTreeStart, leftTreeEnd);

            int rightTreeStart = max + 1;
            int rightTreeEnd = end;
            node.right = build(nums, rightTreeStart, rightTreeEnd);

            return node;
        }
    }

    static class Second {
        public TreeNode constructMaximumBinaryTree(int[] nums) {

            if (nums.length == 0) return null;

            // 遍历到目前为止, 创建的最大结点, 其实就是root.
            TreeNode maxNode = new TreeNode(nums[0]);

            // 遍历剩余的元素
            for (int i = 1; i < nums.length; i++) {
                TreeNode curNode = new TreeNode(nums[i]);
                // 与maxNode以及preNode进行比较
                if (curNode.val > maxNode.val) {
                    // 比最大还大, 又是右边
                    curNode.left = maxNode;
                    maxNode = curNode;
                } else {
                    // 我们知道, 它肯定在maxNode的右边, 那么插入位置是什么呢?
                    // 我们一直往右找, 找到最后一个比它大的元素, 作为它的右子结点就行. 原来的右子树作为它的左子树.
                    // 为什么是上面那个位置呢? 是由定义可知, 画画图
                    TreeNode first = maxNode;
                    TreeNode second = maxNode.right;

                    while (second != null && first.val > curNode.val && second.val > curNode.val) {
                        first = second;
                        second = second.right;
                    }

                    // first就是最后一个比它大的结点了
                    curNode.left = first.right;
                    first.right = curNode;
                }
            }

            return maxNode;
        }
    }

}
