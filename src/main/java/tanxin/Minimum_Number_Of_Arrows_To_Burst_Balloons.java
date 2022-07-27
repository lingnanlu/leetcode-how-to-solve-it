package tanxin;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.*;
import java.util.stream.Collectors;

@Leetcode(
        title = "Minimum Number of Arrows to Burst Balloons",
        link = "https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/",
        category = Category.ARRAY,
        how2SolveIt = """
                先剥离题目中无关的概念, 看本题说了什么, 重新描述一遍
                
                已知一个数组, 每一个元素代表数轴上的一个区间
                
                如果两个区间重叠了, 那么, 就当它们属于一组, 求这些区间可分为几组
                
                每个区间只属于一组.
                
                方法一:
                
                其实这就是一个将元素分组的问题, 可以依次考察数组中的每一个区间, 然后再依次判断其属于哪一组
                
                这里最重要的就是判断某一元素是属于哪一组的, 如何判断呢? 画个图, 举几个例子, 找找规律
                
                比如
                
                [4, 10]属于一组
                
                [1, 7] 和它有交叉, 所以和[2, 10]是同一组
                
                那
                
                [8, 9]也属于这一组么?
                
                因为[8,9]与[4, 10], [1, 7]没有重叠的部分, 所以一属于, 
                
                那[5, 8]呢, 有重叠的部分.
                
                由以上讨论可知, 有没有和已知组内元素有重叠的部分, 说明了其属于还是不属于这一组.
                
                而随着元素加入组内, 重叠部分也在更新, 所以这题就有思路了.
                
        
                
                方法二:
                
                时间复杂度看起来不高, 主要是两次循环, 对于排完序的数据, 看看在判断分组过程中, 有没有重复计算.
                
                比如对于point[i]判断其已经不属于group[j]了, 那么, point[i + 1]肯定也是不属于group[j]的, 那么, j前面的group就不需要再判断了.
                
                因此, 可以优化一下.
                
                这个方法的时间复杂度变成了O(n).
                
                方法三:
                
                能不能省略group呢? 其实感觉也是可以的. 因为每次只需要最后一个group, 前面的都不需要, 所以是可以省略这个列表的.
                
                启示:
                
                1. 如果不按起始位置排序的话, 能求一个解, 但求不出最优解, 排序完了就可以得出最优解, 如何证明呢?
                2. 仔细分析已有的解, 还是可以优化时间复杂度的.
                3. 一个题和顺序没有关系的话, 往往排个序就可以看出解题方案, 有规律的数据更容易洞察一些东西.
               
                """,
        relatedQuestions = {}
)
public class Minimum_Number_Of_Arrows_To_Burst_Balloons {

    static class First {
        public int findMinArrowShots(int[][] points) {
            List<Group> groups = new ArrayList<>();

            for (int[] point : points) {

                int j = 0;
                while (j != groups.size()) {
                    if (groups.get(j).belong(point)) {
                        groups.get(j).update(point);
                        break;
                    } else {
                        j++;
                    }
                }

                // 不属于任何一组
                if (j == groups.size()) {
                    groups.add(new Group(point));
                }
            }

            groups.forEach(System.out::println);

            return groups.size();
        }

        static class Group {
            int[] overlap;      // 重叠部分, 要来判断是否属于该组.
            List<int[]> elements; //  组内元素, 其实本题中没有必要

            public Group(int[] interval) {
                overlap = new int[2];
                overlap[0] = interval[0];
                overlap[1] = interval[1];
                elements = new ArrayList<>();
                elements.add(interval);
            }

            // 判断某一区间是否属于该组
            public boolean belong(int[] interval) {
                return interval[0] <= overlap[1] && interval[1] >= overlap[0];
            }

            // 将某一区间加入该组
            public void update(int[] interval) {

                elements.add(interval);
                overlap[0] = Math.max(overlap[0], interval[0]);
                overlap[1] = Math.min(overlap[1], interval[1]);
            }

            @Override
            public String toString() {
                return "Group{" +
                        "overlap=" + Arrays.toString(overlap) +
                        ", elements={" + elements.stream().map(Arrays::toString).collect(Collectors.joining(",")) +
                        '}';
            }
        }
    }


    static class Second {

        public int findMinArrowShots(int[][] points) {

            List<Group> groups = new ArrayList<>();
            Arrays.sort(points, Comparator.comparingInt(o -> o[0]));

            for (int[] point : points) {
                if (groups.size() == 0) {
                    groups.add(new Group(point));
                } else {
                    // 使用最后一个组进行匹配
                    Group last = groups.get(groups.size() - 1);
                    if (last.belong(point)) {
                        last.update(point);
                    } else {
                        groups.add(new Group(point));
                    }
                }
            }

            return groups.size();
        }

        static class Group {
            int[] overlap;      // 重叠部分, 要来判断是否属于该组.

            public Group(int[] interval) {
                overlap = new int[2];
                overlap[0] = interval[0];
                overlap[1] = interval[1];
            }

            // 判断某一区间是否属于该组
            public boolean belong(int[] interval) {
                return interval[0] <= overlap[1] && interval[1] >= overlap[0];
            }

            // 将某一区间加入该组
            public void update(int[] interval) {
                overlap[0] = Math.max(overlap[0], interval[0]);
                overlap[1] = Math.min(overlap[1], interval[1]);
            }


        }
    }

    public static void main(String[] args) {
        First first = new First();

        int[][] input = {
                {3, 9},
                {7, 12},
                {3, 8},
                {6, 8},
                {9, 10},
                {2, 9},
                {0, 9},
                {3, 9},
                {0, 6},
                {2, 8}
        };


        first.findMinArrowShots(input);
    }


}
