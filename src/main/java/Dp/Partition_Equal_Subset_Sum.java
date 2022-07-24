package Dp;

import run.elder.Category;
import run.elder.Leetcode;

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
                
                [1, 5, 11, 5]
                
                我们试着探索一下, 现在既然要划分成两个数组, 那么, 其中的每一个元素肯定属于某一个数组, 假设这两个数组是
                
                A, B
                我们先选择一个元素放到A中, 选择哪一个呢? 不如选最特别的那个吧, 这里我们选择1
                
                A = [1]
                B = []
                
                R = [5, 5, 11]
                
                此时, 看一下A, B, R的和, 有什么特点
                
                sum(A) = 1
                sum(B) = 0
                sum(R) = 21
                
                此时, 好像没什么思路了, 如果把R全放B里面肯定不行, 那么, 再从R中取一个吧.
                
                取哪一个呢? 
                
                
                方法四:
                
                
                
                
                
                """,
        relatedQuestions = {}
)
public class Partition_Equal_Subset_Sum {

    static class First {
        public boolean canPartition(int[] nums) {
            return false;
        }
    }


}
