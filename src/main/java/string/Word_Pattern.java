package string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Word_Pattern {

    // 依然利用函数的思想， 一一映射
    // leetcode中的
    public boolean wordPattern(String pattern, String str) {

        String[] strArray = str.split(" ");
        if(pattern.length() != strArray.length) return false;

        String[] char2str = new String[256];
        Arrays.fill(char2str, "");
        Map<String, Character> str2char = new HashMap<>();

        // f(char) = str
        for (int i = 0; i < pattern.length(); i++) {

            char key = pattern.charAt(i);
            String value = char2str[key];

            if (value.equals("")) {
                char2str[key] = strArray[i];
            } else {
                if(!value.equals(strArray[i])) {
                    return false;
                }
            }

        }

        // g(str) = char
        for (int i = 0; i < strArray.length; i++) {

            String key = strArray[i];
            Character value = str2char.get(key);

            if(value == null) {
                str2char.put(key, pattern.charAt(i));
            } else {
                if(value != pattern.charAt(i)) {
                    return false;
                }
            }
        }

        // f(g(str)) = str
        for (int i = 0; i < pattern.length(); i++) {
            if(!char2str[str2char.get(strArray[i])].equals(strArray[i])) return false;
        }

        return true;

    }


    // 这是leetcode中的一种方法， 其实不直观
    public boolean wordPattern2(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length()) return false;
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char key = pattern.charAt(i);
            String word = words[i];
            if (map.containsKey(key) && !map.get(key).equals(word)) return false;
            if (!map.containsKey(key) && map.containsValue(word)) return false;
            map.put(key, word);
        }
        return true;
    }

    // 这是leetcode中的另一种方法， 也不直观
    public boolean wordPattern3(String pattern, String str) {
        String[] strs = str.split(" ");

        if(pattern.length() != strs.length) return false;

        HashMap<Character, String> hm1 = new HashMap<Character, String>();
        HashMap<String, Character> hm2 = new HashMap<String, Character>();
        for(int i=0; i<pattern.length(); ++i) {
            if(hm1.containsKey(pattern.charAt(i))) {
                if(!hm1.get(pattern.charAt(i)).equals(strs[i])) return false;
            }
            else {
                if(hm2.containsKey(strs[i])) return false;
                else {
                    hm1.put(pattern.charAt(i), strs[i]);
                    hm2.put(strs[i], pattern.charAt(i));
                }
            }
        }

        return true;
    }

}
