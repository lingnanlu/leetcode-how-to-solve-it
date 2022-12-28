package String;

import java.util.Arrays;

/**
 * 其实就是依次检查words中的单词的每一个字母, 是不是都出现在chars当中
 * 注意chars中的字母只能使用一次.
 *
 * 那么, 可以先处理一个简化的问题, 即
 *
 * 一个单词 word能否由chars拼写.
 *
 * 方法一中, 每次都要复制一份charCount, 能不能不复制呢? 这一点让我有点不爽.
 *
 */
public class Find_Words_That_Can_Be_Formed_by_Characters {

    static class First {
        public int countCharacters(String[] words, String chars) {

            int[] charCount = new int[26];

            for (char c : chars.toCharArray()) {
                charCount[c - 'a']++;
            }

            int result = 0;
            for (String word : words) {
                if (wordInChars(word, Arrays.copyOf(charCount, 26))) {
                    result += word.length();
                }
            }

            return result;
        }

        private boolean wordInChars(String word, int[] charCount) {


            for (char c : word.toCharArray()) {
                if (charCount[c - 'a'] == 0) {
                    return false;
                } else {
                    charCount[c - 'a']--;
                }
            }

            return true;
        }
    }

    static class Second {

        public int countCharacters(String[] words, String chars) {

            int[] charCount = new int[26];

            int[] wordCount = new int[26];
            for (char c : chars.toCharArray()) {
                charCount[c - 'a']++;
            }

            int result = 0;
            for (String word : words) {
                // 统计word中的单词数
                for (char c : word.toCharArray()) {
                    wordCount[c - 'a']++;
                }

                boolean wordInChars = true;
                for (int i = 0; i < 26; i++) {
                    if (wordCount[i] > charCount[i]) {
                        wordInChars = false;
                        break;
                    }
                }

                if (wordInChars) {
                    result += word.length();
                }

                Arrays.fill(wordCount, 0);
            }

            return result;
        }


    }

}
