package String;

import java.util.ArrayList;
import java.util.List;

/**
 * 其实就是判断一个单词中的字母是否可以由某一行键盘中的字母组成.
 *
 * 大概的方法就是, 对于每一个字母, 判断它属于第几行, 然后再看这一行与之前的是否是同一行.
 *
 * 这里的关键, 最基本的操作就是判断一个字母属于第几行.
 *
 */
public class Keyboard_Row {

    // 字母到行的映射, 这个最关键, 能用简单的数据结构就不用复杂的.
    int[] charToPos = new int[]{2, 3, 3, 2, 1, 2, 2, 2, 1, 2, 2, 2, 3, 3, 1, 1, 1, 1, 2, 1, 1, 3, 1, 3, 1, 3};

    public String[] findWords(String[] words) {

        List<String> result = new ArrayList<>();

        for (String word : words) {
            // 判断一个单词的所有字母是否在同一行中.
            int lastExistLineNum = -1; // 上一个字母所在的行号

            int i = 0;
            while (i != word.length()) {
                char lower = Character.toLowerCase(word.charAt(i));
                int lineNum = charToPos[lower - 'a'];
                if (lastExistLineNum == -1) {
                    lastExistLineNum = lineNum;
                } else {
                    if (lastExistLineNum != lineNum) {
                        break;
                    }
                }
            }

            // 说明在同一行中
            if (i == word.length()) {
                result.add(word);
            }
        }

        return result.toArray(new String[0]);
    }


}
