package tanxin;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Jump Game II",
        link = "https://leetcode.com/problems/jump-game-ii/",
        category = Category.ARRAY,
        how2SolveIt = """
                方法一:
                
                DFS依然可解
                
                方法二:
                
                还是利用Jump Game中的方法三, 怎样才是最少的步数呢?
                
                就是尽快的到达终点, 那么, 怎么能尽快, 
                
                比如a1一跳, 有个范围, 
                
                那么, 下一跳选哪个呢? 一定要先能最大化扩展这个范围的, 不扩不行, 扩小了肯定不行.
                
                虽然是两层循环, 但每个元素只处理了一次, 所以还是O(n)
                """,
        relatedQuestions = {}
)
public class Jump_Game_II {


    public int jump(int[] nums) {

        // 如果只有一个元素, 相当于不用起跳就到达终点了
        if (nums.length == 0) return 0;

        // 记录跳的次数
        int count = 0;
        //[0, dest]是要考察的范围
        int dest = 0;

        int i = 0;
        while (i <= dest) {
            // 从[i, dest]中选择一个最远的, 做为下一跳即dest.
            int furthest = dest;
            for (int j = i; j <= dest ; j++) {
                int newDest = j + nums[j];
                furthest = Math.max(furthest, newDest);
            }

            // 题目中说了, 一定会到达终点, 所以一定会前进的.

            // furthest是[i, dest]中能跳的最远
            // 进行一次起跳
            i = dest + 1;
            dest = furthest;
            // 记录一次跳跃
            count++;

            // 跳了之后, 发现超过终点了, 就结束
            if (dest >= nums.length - 1) {
                return count;
            }
        }

        // 这里应该走不到
        return 0;

    }

}
