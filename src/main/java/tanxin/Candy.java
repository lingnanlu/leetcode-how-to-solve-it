package tanxin;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Leetcode(
        title = "Candy",
        link = "https://leetcode.com/problems/candy/",
        category = Category.ARRAY,
        how2SolveIt = """
                方法一:
                
                直观暴力方法, 先每个人发一个, 然后两两比较, 发一轮
                
                发完一轮之后, 可能又不符合条件了, 那么就再发一轮.
                
                感觉上,经过多轮调整之后, 直到某一轮不再需要调整, 就可以终止了.
                
                方法二:
                
                仔细审查这个问题, 可以举个例子, 比如
                
                [5, 4, 3, 2, 1]
                
                给0发的糖要看1, 1要看2, 2要看3, 3要看4, 给4发的糖为1
                
                这有点像迭代, 比如f(n)与f(n - 1)有某种关系.
                
                那么, 是不是最后一个就一定是初始值呢? 我们换一个顺序
                
                [1, 2, 3, 4, 5]
                
                这次第一个又成为初始值了.
                
                那么, 我们把最小的放中间看看
                
                [5, 4, 1, 2, 3]
                
                这里, 1所在的位置就是初始值了.
                
                以上给人的启示是, 要把比两边元素小的元素的位置找出来, 这很重要, 它们的值可以做为初始值, 不再改变
                
                那么剩余的值怎么安排呢.
                
                还是举例子
                
                [5, 4, 1, 2, 6, 5, 4, 3, 0, 9]
                
                1和0是波谷, 中间的元素是什么形状呢? 是一个上坡跟着一个下坡
                
                中间的孩子怎么分配糖果呢? 其实是根据坡长来的, 哪个坡长, 就先分配哪一个, 这里先分配[6, 5, 4, 3], 然后再考虑短坡.
                
                于是我们就有了分配的线性顺序
                
                1. 先分配波谷的
                2. 再分配坡长的
                3. 再分配剩余的
                
                整个过程中, 谷底和山峰的位置是最重要的.要记录下来.
                
                但这个方法还是很难写代码, 因为有重复元素的存在, 确定山峰和山谷, 很难
                
                方法三:
                
                这里要求分发的时候, 我们要考虑两边的孩子, 如果我们变化一下问题, 只考虑一边呢? 比如只考虑左, 或只考虑右.
                
                只考虑左边的, 我们可以得到一个分发结果
                只考虑右, 我们可以得到另一个结果.
                
                比如 
                
                1, 5, 4, 3
                
                只考虑左
                
                [1, 2, 1, 1]
                顺考虑右
                
                [1, 3, 2, 1]
                那么我们怎么综合起来呢? 
                
                一种比较直观的做法是, 同位置选择最大.
                
                但, 这种作法是不是就符合了题目要求呢?
                
                1. 分发的最少
                2. 比两边要大.
                
                先证明2吧, 假设
                
                先择同位置最大的后, 存在
                
                得分比某一边要高, 但糖果比某一边要少.
                
                我们要证明的是, 这个假设不为假的.
                
                怎么证明其为假呢? 就是要证明它与已知事实矛盾.
                
                
                就当比左边要少吧.
                
                假设
                
                A, B两个孩子
                
                A得分比B小, 
                
                向左看和向右看后, 分别得到
                
                A1, A2, B1, B2
                
                Max(A1, A2) > Max(B1, B2)可能成立么, 
                
                其实就四种情况 
                
                A1, B1, 因为是向左看的, 所以 A1 < B1
                
                A2, B2, 因为是向右看的, 所以 A2 < B2
                
                因为A1 < B1, A2 < B2
                
                所以 Max(A1, A2) < Max(B1, B2)
                
                现在证明分发的是最少的.
                
                因为向左看和向右看时, 就是按照最少原则分发的, 所以感觉取max之后, 也是最少的, 这个就不形式证明了.
                
                启示:
                
                1. 你要证明你的结论, 不能感觉
                2. 当前感觉有时也很重要
                3. 可以简化问题的方式来找思路
                
                
                
                """,
        relatedQuestions = {}
)
public class Candy {

    static class First {

        public int candy(int[] ratings) {
            // 记录发的糖果数
            int[] candies = new int[ratings.length];

            // 先发第一轮, 每个人为一
            Arrays.fill(candies, 1);

            // 表示上一轮有没有调整过, 如果没有调整过, 就不进行下一轮了.
            boolean updated = true;
            // 根据评分, 进行多轮调整
            while(updated) {
                // 假设本轮不需要调整
                updated = false;
                for (int i = 0; i <= ratings.length - 2; i++) {
                    if (ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                        candies[i] = candies[i + 1] + 1;
                        updated = true;
                    } else if (ratings[i + 1] > ratings[i]  && candies[i + 1] <= candies[i]){
                        candies[i + 1] = candies[i] + 1;
                        updated = true;
                    }
                }
            }

            return Arrays.stream(candies).sum();
        }

    }

    static class Second {


        public int candy(int[] ratings) {

            int result = 0;

            int p = 0;
            /**
             * low1指向第一个上坡的起点, 如果开头单调下降的, 也可以
             */
            int low1 = 0;
            int high;
            int low2;
            while (p != ratings.length) {
                // 1. 找一个上坡
                // 2. 找一下下坡
                // 3. 比较两者谁长, 谁长就依谁, 计算糖果数
            }

            return 0;

        }

    }

    static class Third {


        public int candy(int[] ratings) {

            int[] left = new int[ratings.length];
            int[] right = new int[ratings.length];

            Arrays.fill(left, 1);
            Arrays.fill(right, 1);

            for (int i = 1; i <= ratings.length - 1; i++) {
                if (ratings[i] > ratings[i - 1]) {
                    left[i] = left[i - 1] + 1;
                }
            }

            for (int i = ratings.length - 2; i >= 0; i--) {
                if (ratings[i] > ratings[i + 1]) {
                    right[i] = right[i + 1] + 1;
                }
            }

            int[] result = new int[ratings.length];
            for (int i = 0; i < ratings.length; i++) {
                result[i] = Math.max(left[i], right[i]);
            }

            return Arrays.stream(result).sum();
        }

    }
}
