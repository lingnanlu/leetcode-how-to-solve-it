package easy;

import java.util.HashSet;

/**
 * 找最长的连续数列
 *
 * 可以先排序， 这是O(nlogn)
 *
 * 如果要求O(n), 可能需要hash表
 */
public class Longest_Consecutive_Sequence {

    public int longestConsecutive(int[] nums) {
        final HashSet<Integer> myset = new HashSet<>();

        //对于重复的去重没关系
        for(int i: nums) myset.add(i);

        int longest = 0;

        //从每一个开始， 向两边找
        for(int i: nums) {
            int ilongest = 1; //以i向左右找的长度
            for(int j = i - 1; myset.contains(j); j--) {
                myset.remove(j); // 注意可以remove掉， 对于连续的， 从一个开始就可以找到所有， 再从其它元素开找， 无意义
                ilongest++;
            }

            for(int j = i + 1; myset.contains(j); j++) {
                myset.remove(j);
                ilongest++;
            }

            if(ilongest > longest) {
                longest = ilongest;
            }
        }

        return longest;
    }
}
