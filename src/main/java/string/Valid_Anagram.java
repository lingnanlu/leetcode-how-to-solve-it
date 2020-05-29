package string;

import java.util.Arrays;

/**
 * 统计每个字符出现的次数有两种方法
 *
 * 1. 使用HashMap
 * 2. 因为字符的种类是有限的， 字符又可以转化成整形下标， 所以可以使用数组
 * 注意， 这里使用空间固定的数组也是空间复杂度为O(1)
 */
public class Valid_Anagram {

    public boolean isAnagram(String s, String t) {

        int[] sCount = count(s);
        int[] tCount = count(t);

        return Arrays.equals(sCount, tCount);
    }

    public int[] count(String s) {
        int[] result = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            result[index]++;
        }

        return result;
    }


}
