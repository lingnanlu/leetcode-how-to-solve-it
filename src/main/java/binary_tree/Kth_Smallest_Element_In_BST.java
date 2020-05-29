package binary_tree;

// 其实这个题有一个特殊点， 就是在DFS过程中， 要保存多个信息
// 这些信息的保存有几种方式
// 1. 做为walk的参数， 如一个list
// 2. 使用一个对象成员， 但这种方式是不好的。
// 3. walk返回更丰富的结构体， 如下所示。
public class Kth_Smallest_Element_In_BST {

    public int kthSmallest(TreeNode root, int k) {

        int c = count(root.left);
        if (c + 1 == k) {
            return root.val;
        } else if (c + 1 < k) {
            return kthSmallest(root.right, k - c - 1);
        } else {
            return kthSmallest(root.left, k);
        }
    }

    private int count(TreeNode root) {

        if (root == null) return 0;

        return count(root.left) + count(root.right) + 1;

    }



    public int kthSmallest2(TreeNode root, int k) {

        Result result = walk(root, 0, k);
        return result.val;

    }

    /**
     * @param node 要进入的node
     * @param count 进入node前已经访问了多少个node
     * @return 访问完成后，可以得到如下信息
     *  1. 该子树下的node个数
     *  2. 是否找到
     *  3. 结果是什么
     */
    private Result walk(TreeNode node, int count, int k) {

        if (node == null) return new Result(0);

        Result left = walk(node.left, count, k);

        // 找到后就层层向上传递就行
        if (left.find) {
            return left;
        } else {
            Result result = new Result(0);

            count += left.count + 1;

            if (count == k) {
                result.val = node.val;
                result.find = true;
                return result;
            } else {
                Result right = walk(node.right, count, k);
                if (right.find) return right;
                else {
                    result.count = left.count + right.count + 1;
                    return result;
                }
            }
        }

    }

    private class Result {
        int val;                    // 最终结果值， 当find=true时有效
        int count = 0;              // 记录结点个数
        boolean find = false;       // 是否找到


        public Result(int val, int count, boolean find) {
            this.val = val;
            this.count = count;
            this.find = find;
        }

        public Result(int count) {
            this.count = count;
        }
    }

}
