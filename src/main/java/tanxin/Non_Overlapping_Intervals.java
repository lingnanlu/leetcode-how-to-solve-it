package tanxin;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.Arrays;
import java.util.Comparator;

@Leetcode(
        title = "Non-overlapping Intervals",
        link = "https://leetcode.com/problems/lemonade-change/",
        category = Category.ARRAY,
        how2SolveIt = """
                
                方法一:
                
                该题和顺序无关, 不知先排个序. 然后观查
                
                发现一个规律, 它们是这样排列的.
                
                如
                
                第一个: 然后就是几个和它有重叠的.
                
                下一个: 然后又是几个和它有重叠的.
                
                再下一个: 然后又是几个和它有重叠的.
                
                而这第一个, 下一个, 再下一个, 其实就是分组.
                
                那么, 我们就要把每组的元素个数变成一, 每组中减去的元素总和, 就是最少要删除的了.
                
                注意这里的排序, 当起始元素相同时, 终止元素大的排后面.
                
                这是因为, 终止元素越大, 区间越长, 可能产生的overlap就越多, 所以我们就要优先排除它们,
                
                所以要使用小区间作为匹配的对象.
                
                
                方法二:
                
                这题还是没有AC, 想想为什么?
                
                还是在一组互相有overlap的元素中删除元素时, 要选择删除哪些出了问题..
                
                再画一画, 举例多种情况.
                
                现在的删除逻辑是,
                
                -----
                  ------
                    ------
                    
                删除后两个, 没问题, 因为右边越长的越容易干扰到后面, 但, 如果是如下这样的呢?
                
                -------------
                  --------
                    ------------
                           ------- 第四个, 这种情况下会删除
                其实应该删除第一和第三个.
                
                那是不是应该按照右边排序? (这种开头是往后的
                
                  --------
                -------------
                    ------------
                           ------- 第四个, 这种情况下不会删除
                
                比如上面说的第四个, 按照右边排序, 就不会删除
                
                等等, 如果第四个是如下这样的呢.
                
                -----------
                    ----------
                       ---------
                          ------- 第四个会删除
                              
                       ------
                    ----------
                ---------------
                           ------- 第四个, 这种情况下不会删除              
                
                好像也没问题, 看来, 按照右边排序就好.  
                
                其实可以感觉到, 结尾位置会影响删除overlap, 而开头并不怎么能影响.   
                
                启示:
                
                想到一个解不要激动, 要尽量看下在多种情况下是否有问题, 不要怕举例子, 简单的列举几种情况就好, 这考察的是耐心的细心 
                    
                """,
        relatedQuestions = {}
)
public class Non_Overlapping_Intervals {

    static class First {
        public int eraseOverlapIntervals(int[][] intervals) {

            if (intervals.length == 0) return 0;
            Arrays.sort(intervals, (o1, o2) -> {
                if (o1[0] > o2[0]) {
                    return 1;
                } else if (o1[0] < o2[0]) {
                    return -1;
                } else {
                    return o1[1] - o2[1];
                }
            });

            // 把interval[0]当成第一组, 开始迭代
            int currentMatchGroup = 0;
            int result = 0;
            for (int i = 1; i < intervals.length; i++) {

                // 判断当前要考察的元素和currentMatchGroup是否是同一组(即是否有overlap)
                if(intervals[i][0] < intervals[currentMatchGroup][1]) {
                    result++;
                } else {
                    // 没有重叠, 作为下一次要匹配的组
                    currentMatchGroup = i;
                }
            }

            return result;


        }
    }

    // 按照结尾排序
    static class Second {

        public int eraseOverlapIntervals(int[][] intervals) {

            if (intervals.length == 0) return 0;
            Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));

            // 把interval[0]当成第一组, 开始迭代
            int currentMatchGroup = 0;
            int result = 0;
            for (int i = 1; i < intervals.length; i++) {

                // 判断当前要考察的元素和currentMatchGroup是否是同一组(即是否有overlap)
                if(intervals[i][0] < intervals[currentMatchGroup][1]) {
                    result++;
                } else {
                    // 没有重叠, 作为下一次要匹配的组
                    currentMatchGroup = i;
                }
            }

            return result;


        }

    }


    public static void main(String[] args) {

        int[][] inputs = {
                {-52, 31},
                {-73, -26},
                {82, 97},
                {-65, -11},
                {-62, -49},
                {95, 99},
                {58, 95},
                {-31, 49},
                {66, 98},
                {-63, 2},
                {30, 47},
                {-40, 26}
        };

        Arrays.sort(inputs, (o1, o2) -> {
            if (o1[0] > o2[0]) {
                return 1;
            } else if (o1[0] < o2[0]) {
                return -1;
            } else {
                return o1[1] - o2[1];
            }
        });

        for (int[] e : inputs) {
            System.out.println(Arrays.toString(e));
        }

    }
}
