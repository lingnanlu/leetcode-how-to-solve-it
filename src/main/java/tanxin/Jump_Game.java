package tanxin;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Jump Game",
        link = "https://leetcode.com/problems/jump-game/",
        category = Category.ARRAY,
        how2SolveIt = """
                方法一:
                
                我们从开始位置开跳, 所跳的步数有多种选择
                
                跳到下一个位置之后又有多个频数可选择
                
                以上像不像多步, 每一步有多种选择的情况? 这让你想到了什么
                
                DFS
                
                但DFS可能深度与宽度很大很大, 因为0 <= nums[i] <= 10^5
                
                方法二
                
                还是先拿两个结点说事, [a1, a2], a1能不能到a2, 要看 a >= 1
                
                然后讨论三个结点 [a1, a2, a3], a2能到达a3的话,  那么 a1能到a2就好, a2到不了a3, 那么要求a1到a3
                
                四个结点 [a1, a2, a3, a4]
                
                a3能到a4, a2要求能到a3就好
                
                a3到不了a4, a2要求能到a4就好
                
                a2能到a4, a1要求能到a2就好
                
                a2到不了a4, a1要求能到a4就好
                
                发现这么一个规律
                
                1. 从后往前一个一个元素考察的.
                2. 能到需要量化, 具体转化成某种数值.
                
                根据以上写的规律, 写出代码即可
                
           
                方法三
                
                我们换一个角度来看这个问题, 即先解决一个类似的问题, 比如说, 给定
                
                [a1, a2, a3]
                
                看看能走到最远是多少.
                
                从a1开始, 走到一个最远位置.
                如果a2小于最远位置, 我们再考察a2, 得到另一个最远位置, 如果比当前的最远还远就更新.
                否则, 考察范围内的另一个元素.
                
                看起来这种思路也挺简单, 试一试
                       
                """,
        relatedQuestions = {}
)
public class Jump_Game {
    static class First {
        public boolean canJump(int[] nums) {
            return false;
        }
    }

    static class Second {
        public boolean canJump(int[] nums) {

            if (nums.length < 2) return true;

            // i指向下一个要考察的位置
            int i = nums.length - 2;

            // 当前要考察的位置走到终点所需要的最少步数.
            int min = 1;
            // 从后往前看, 一直看到nums[0], 看是不是满足最小的.
            while (i != 0) {
                if (nums[i] >= min) {
                    // i指向前一个结点, 这里min依然是一, 这并不是说前一个结点一步走到最终终点需要1步, 而是说, 前一个结点要走到终点
                    // 只需要最少一步, 即走到后一个结点就行了, 因为后一个结点确保能走到终点
                    min = 1;
                } else {
                    // 如果该结点到不了终点
                    // 那么前面那个结点就要多步一步了.
                    min += 1;
                }
                i--;
            }

            // 此时到了nums[0], 由不变式知, min就是nums[0]要到终点所需要的最少步数
            return nums[0] >= min;
        }
    }

    static class Third {
        public boolean canJump(int[] nums) {

            //[0, dest]是要考察的范围
            int dest = 0;
            for (int i = 0; i <= dest; i++) {
                int newDest = i + nums[i];
                if (newDest >= nums.length - 1) {
                    return true;
                } else {
                    dest = Math.max(newDest, dest);
                }
            }

            return false;
        }
    }

    public static void main(String[] args) {
        Second test = new Second();
        int[] nums = {3, 0, 0, 0};
        test.canJump(nums);
    }

}
