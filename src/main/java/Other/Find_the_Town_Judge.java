package Other;

import java.util.ArrayList;
import java.util.List;

/**
 * 其实就是在1-n中, 找这样一个数
 *
 * 1. 所有其它的人指向它
 * 2. 它不指向任何人.
 *
 * 现在已知的是指向关系. 求以上未知.
 *
 * 怎么做呢? 其实本质上是找满足条件的数.
 *
 * 最笨的方法, 对于1-n中的每一个数, 挨个尝试检查, 看是不是. 但这是最笨的了.
 *
 * 那么, 怎么快呢?
 *
 * 现在要找的数, 要满足两个条件, 似乎不好搞, 那么我们先简化一下, 只保留一个条件.
 *
 * 1. 找不指向任何人的数.
 *
 * 这个好办. 通过一次遍历, 就能找出所有有指向的数, 也能得到所有没指向的数.
 *
 * 现在再单独来看
 *
 * 1. 所有其它的数指向它的数.
 *
 * 对于一个数, 怎么快速判断其它的数指向它呢? 首先, 不需要对每一个对判断, 因为之前的一步帮助我们缩小了后选集.
 *
 * 只要对于这个后选集中的数, 判断是否满足所有其它的数指向它就好了.
 *
 * 而这需要对整个数组进行遍历, 这里并不需要记录一个数有没有指向它. 因为由已知, 我们知道如果其它数都指向它, 那么统计的边的个数一定是n - 1.
 *
 * 方法二:
 *
 * 其实这是一个图相关的题, 本质是出度和入度, 就是找一个出度为0, 入度为n - 1的结节.
 * 所以遍历一遍, 统计出度和入度.
 *
 * 启示
 * 是一个图的题, 自己不熟悉, 没想到, 正常.
 * 方法一反而是对自己有启发的
 * 当要找满足多个条件的值时, 可以先省略一些条件, 简化问题, 这样每处理一个条件, 相当于把候选集缩小很多.
 */
public class Find_the_Town_Judge {

    static class First {
        public int findJudge(int n, int[][] trust) {

            // mark[i] = 1 表示该数指向某一个数, mark[i] = 0, 表示该数不指向一个数
            int[] mark = new int[n + 1];

            for (int i = 0; i < trust.length; i++) {
                mark[trust[i][0]] = 1;
            }

            // 收集mark中为0的数
            List<Integer> tos = new ArrayList<>();

            for (int i = 1; i < mark.length; i++) {
                if (mark[i] == 0) {
                    tos.add(i);
                }
            }

            // 对每一个可能值
            for (int to : tos) {
                int count = 0;
                for (int[] ints : trust) {
                    if (ints[1] == to) {
                        count++;
                    }
                }

                if (count == n - 1) {
                    return to;
                }
            }

            return -1;
        }
    }

    static class Second {

        public int findJudge(int n, int[][] trust) {
            int[] in = new int[n + 1];
            int[] out = new int[n + 1];

            for (int i = 0; i < trust.length; i++) {
                out[trust[i][0]]++;
                in[trust[i][1]]++;
            }

            // 找出度为0, 入度为n - 1的点
            for (int i = 1; i < n + 1; i++) {
                if (in[i] == n - 1 && out[i] == 0) {
                    return i;
                }
            }

            return -1;
        }

    }

}
