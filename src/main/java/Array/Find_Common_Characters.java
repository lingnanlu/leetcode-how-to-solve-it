package Array;

import java.util.*;

/**
 * 其实就是求交集, 但这里要求重复的字符也算. 如"bella"和"label"
 * 怎么表示有重复元素的集合呢?
 * Java中并没有, 所以, 这里需要自定义这样一种结构并想办法请求交集.
 *
 * 这题写的就是恶心, 唉没办法, 其它人也是这样的.
 *
 */
public class Find_Common_Characters {
    public List<String> commonChars(String[] words) {

        if (words.length == 0) return new ArrayList<>();

        // 用来记录交集
        int[] commonCharCount = new int[26];
        int[] temp = new int[26];

        // 用第一个字符串初始化commonCharCount
        for (char c : words[0].toCharArray()) {
            commonCharCount[c - 'a']++;
        }

        for (int i = 1; i < words.length; i++) {

            for (char c : words[i].toCharArray()) {
                temp[c - 'a']++;
            }


            /**
             * 求commonCharCount与temp的交集, 其实就是对于共有的字符,
             * 将其数量设置成较少的
             */
            for (int j = 0; j < 26; j++) {
                if (commonCharCount[j] != 0) {
                    if (temp[j] != 0) {
                        commonCharCount[j] = Math.min(commonCharCount[j], temp[j]);
                    } else {
                        commonCharCount[j] = 0;
                    }
                }
            }

            // 清空temp
            Arrays.fill(temp, 0);

        }

        List<String> result = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            if (commonCharCount[i] != 0) {
                int count = commonCharCount[i];

                while (count != 0) {
                    result.add(String.valueOf((char)('a' + i)));
                    count--;
                }
            }
        }

        return result;

    }

}
