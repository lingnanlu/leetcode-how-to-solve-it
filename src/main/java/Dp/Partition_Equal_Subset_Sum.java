package Dp;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.Arrays;

@Leetcode(
        title = "Partition Equal Subset Sum",
        link = "https://leetcode.com/problems/partition-equal-subset-sum/",
        category = Category.ARRAY,
        how2SolveIt = """
                
                方法一:
                
                其实把所有的子数组找出来, 然后一一判断是否有满足一个划分
                
                现在的问题就是, 如何找所有子数组呢? 其实假设一个空的数组, 然后, 在剩余数组中的每个元素可选可不选, 两种情况.
                
                这就是一个DFS问题.
                
                当然, DFS的一种优化就是剪枝, 我们注意到了元素全是正数, 利用这个条件, 就可以剪枝了
                
                方法二:
                
                这是一个证明题, 能不能利用逻辑转化来做? 转化成一个容易证明的东西.
                
                就算不能完全转化, 也可以得到某些启示, 如
                
                如果存在, 那么, 整个数组的和一定为偶数
                
                如果和不为偶数, 那么, 一定不存在
                
                (和为偶数, 不一定存在)
                
                方法三:
                
                使用DFS是超时的, 我们再看一下这个问题, n个元素, 目标是target.
                
                我们又想到了迭代的方法, 既然不好求n, 我们试试找n - 1个元素和n以及target的关系.
                
                f(n)(target) = true,表示前n个元素中可以取若干元素组成target.
                
                它和f(n-1)有什么关系呢, 仔细一想, 如下
                
                f(n)(target) = f(n-1)(target) | f(n-1)(target - sum[n])
                
                嗯, 现在有了递推公式, 可以写代码了
                
                方法四: 降低空间复杂度
                
                方法三中, 我们使用了一个二维数组, 占用了一定的空间, 再仔细看一下递推公式与二维数组填充过程.
                
                f(n)(target) = f(n-1)(target) | f(n-1)(target - sum[n])
                
                发现下一行的填充只依赖与上一行以及上一行左边的元素, 所以, 能不能就用一个一维数组, 重复利用呢?
                
                注意, 在填充下一行时, 这里使用的是从后往前的填充, 这是为什么呢? 因为要填充的值依赖与左边的值.
                
                而从前往后填充会修改左边的值.
                
                举一个例子, 比如, 有如下数组
                
                [1, 2, 3, 4, 5]
                
                将之变成
                
                [1, 3, 5, 7, 9], 即每一个值加上左边的值.
                
                如果只用一个数组, 从左往右遍历的话, 左边的值会改变.
                
                所以, 这里可以从右往左遍历.
                
                当然, 如果想不到这个技巧, 可以使用两个数组, 一个保存旧值, 一个保存新值, 交替进行.
                
                """,
        relatedQuestions = {}
)
public class Partition_Equal_Subset_Sum {

    static class First {

        public boolean canPartition(int[] nums) {
            if (nums.length == 1) return false;

            int target = 0;
            for (int num : nums) {
                target += num;
            }

            if (target % 2 != 0) return false;

            return walk(nums, 0, true, 0, target / 2) ||
                    walk(nums, 0, false,0, target /2);
        }

        /**
         * @param nums
         * @param i 走到第i个元素
         * @param sum 当前累积和 (未加上第i个元素)
         * @param seleted 选择或不选择这个元素
         * @param target 目标值
         * @return
         */
        private boolean walk(int[] nums, int i, boolean seleted, int sum, int target) {

            if (i == nums.length) return false;

            if (seleted) {
                sum += nums[i];
                if (sum == target) return true;
                else if (sum > target) return false;
            }

            return walk(nums, i + 1, true, sum, target) || walk(nums, i + 1, false, sum, target);
        }
    }

    static class Third {
        public boolean canPartition(int[] nums) {

            if (nums.length == 1) return false;

            int target = 0;
            for (int num : nums) {
                target += num;
            }

            if (target % 2 != 0) return false;
            
            target = target / 2;
            
            // 这里最关键的是搞明白二维矩阵每一维坐标的含义
            boolean[][] dp = new boolean[nums.length][target + 1];
            
            // dp[i][target]表示, nums[0...i]中是否满足target
            
            // 因为递推公式是f(n)(target) = f(n-1)(target) | f(n-1)(target - sum[n])
            // 依赖与上一行与左边列的值, 所以可以初始化第一行与第一列

            // 初始化第一行dp[1]
            for (int i = 0; i <= target ; i++) {
                dp[0][i] = nums[0] == i;
            }
            
            // 由递推公式可以看出, 不用初始化第一列
            // 下面就是填充二维数组, 一行一行的填充
            for (int i = 1; i < nums.length; i++) {
                for (int j = 0; j <= target ; j++) {
                    if (nums[i] > j) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                    }
                }
            }

            return dp[nums.length - 1][target];
            
            
        }
    }

    static class Forth {

        public boolean canPartition(int[] nums) {

            if (nums.length == 1) return false;

            int target = 0;
            for (int num : nums) {
                target += num;
            }

            if (target % 2 != 0) return false;

            target = target / 2;

            boolean[] dp = new boolean[target + 1];


            for (int i = 0; i <= target ; i++) {
                dp[i] = nums[0] == i;
            }

            // 此时dp保存的是第0行元素的填充结果
            for (int i = 1; i < nums.length; i++) {
                // 不变式, i表示将要填充的行, dp[i - 1]表示上一行的结果.
                for (int j = target; j >= 0 ; j--) {
                    if (nums[i] <= j) {
                        dp[j] = dp[j] || dp[j - nums[i]];
                    }
                }
            }

            return dp[target];

        }

    }


}
