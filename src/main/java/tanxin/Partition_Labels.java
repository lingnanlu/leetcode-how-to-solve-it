package tanxin;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.*;

@Leetcode(
        title = "Partition Labels",
        link = "https://leetcode.com/problems/partition-labels/",
        category = Category.ARRAY,
        how2SolveIt = """
                方法一:
                
                就模拟人工寻找片段的步骤, 比如
                
                [a.........a]
                在这个区间内有b, 再从最后一个a后开始找有没有b之类的.
                
                但这种模拟的办法感觉描述起来比较啰嗦, 故而代码写起来也比较啰嗦, 细节较多.
                
                方法二:
                
                既然是要求每个字母只出现在一个片段当中, 而方法一中其实是找开始的位置和最后出现的位置.
                
                如果把问题变化一下, 比如说,得到s中每个字母出现的第一次和最后一次位置, 看看问题如何变化
                
                已知变成了一个数组, 每一个数组元素是二元值, 如对
                
                ababcbacadefegdehijhklij
                
                则已知转化成
                
                a:[0, 8]
                b:[1, 5]
                c:[4, 7]
                
                d:[9, 14]
                e:[10, 15]
                f:[11, 11]
                g:[13, 13]
                
                h:[16, 19]
                i:[17, 22]
                j:[18, 23]
                k:[20, 20]
                l:[21, 21]
                
                abc是在同一片段的
                defg是同一片段
                hijkl是同一片段
                
                那么, 把它们归为同一片段的几个区间有什么特点么
                
                发现它们是重叠的, 不同片段之间的不重叠
                
                而重叠之后的最小和最大坐标, 就构成了每个片段的长度, 
                
                于是得出方法二
                
                此方法为O(n)
                
                
                方法三
                
                https://programmercarl.com/0763.%E5%88%92%E5%88%86%E5%AD%97%E6%AF%8D%E5%8C%BA%E9%97%B4.html#%E6%80%9D%E8%B7%AF
                
                这个思路有些巧妙啊~~~
                
                不过本质上感觉和方法二是一样的.
                
                1. 都是两次遍历
                2. 都是更新最远区间
                
                只是方法三中省略了很多要保存的状态.
   
                启示:
                
                1. 将已知换一种形式来描述, 相当与转化了一下问题, 有可能就有新的看法和思路.
                
                
                
                """,
        relatedQuestions = {}
)
public class Partition_Labels {

    static class First {
        public List<Integer> partitionLabels(String s) {
            return null;
        }
    }

    static class Second {
        public List<Integer> partitionLabels(String s) {

            // 1. 将之转化成区间, 不存在的字母对应的为null;
            int[][] positions = new int[26][];

            for (int i = 0; i < s.length(); i++) {
                if (positions[s.charAt(i) - 'a'] != null) {
                    // 更新结尾
                    positions[s.charAt(i) - 'a'][1] = i;
                } else {
                    // 第一次出现
                    positions[s.charAt(i) - 'a'] = new int[]{i, i};
                }
            }


            List<Integer> result = new ArrayList<>();
            // 2. 由以上遍历顺序可知, positions中的已经排好序了, 而且不存在的字母对应的为null, 为了操作方便过虑null

            List<int[]> validPositions = new ArrayList<>();
            for (int[] position : positions) {
                if (position != null) {
                    validPositions.add(position);
                }
            }

            // 接下来开始遍历, 找到同一片段的区间, 计算出长度.
            if (validPositions.size() == 0) return new ArrayList<>();

            validPositions.sort(Comparator.comparingInt(o -> o[0]));
            // 注意以下不变式, 不是所有不变式都要有初始值, java中不能表现一个变量没有值, 这里使用不赋值.
            // 用来记录找到的同一片段的区间
            int i, j = 0;      // i是上一次的开始, j表示第一个和前一个元素没有重叠的区间.
            int start, end; // 用来记录同一片段的开始和结尾, 用来计算长度

            while (true) {
                // 每一次循环, 找到一个片段, i从上次的j开始
                i = j;

                start = validPositions.get(i)[0];
                end = validPositions.get(i)[1];

                j = i + 1;

                while(j != validPositions.size()) {
                    // 判断是否有重叠
                    if (validPositions.get(j)[0] > end) {
                        // 没有重叠, 说明此时start, end 代表了最小和最大.
                        result.add(end - start + 1);
                        break;  // break之后, j满足不变式
                    } else {
                        // 有重叠, 更新end, j
                        if (validPositions.get(j)[1] > end) {
                            end = validPositions.get(j)[1];
                        }
                        j++;
                    }
                }

                // 此时j可能为validPositions.size(), 也可以看成与前一个元素没有重叠
                // 要显示保存这次找到的
                if (j == validPositions.size()) {
                    result.add(end - start + 1);
                    break;
                }

            }

            return result;

        }
    }


    public static void main(String[] args) {
        String s = "eccbbbbdec";

        Second second = new Second();

        second.partitionLabels(s);
    }

}
