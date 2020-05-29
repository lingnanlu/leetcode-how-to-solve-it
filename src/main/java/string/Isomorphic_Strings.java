package string;

public class Isomorphic_Strings {

    // 这种方法是错误的， 因为两个字符不能映射到同一个字符， 其实这里的映射是双向的, 即一一对应， 是单射
//    public boolean isIsomorphic(String s, String t) {
//
//        Map<Character, Character> charMap = new HashMap<>();
//
//        for (int i = 0; i < s.length(); i++) {
//            if(charMap.get(s.charAt(i)) == null) {
//                charMap.put(s.charAt(i), t.charAt(i));
//            } else {
//                char c = charMap.get(s.charAt(i));
//                if(c == t.charAt(i)){
//                    continue;
//                } else {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

    // 利用函数的思想
    public boolean isIsomorphic(String s, String t) {

        int[] s2t = new int[256];   // f(a) = b
        int[] t2s = new int[256];   // g(b) = a

        // 建立f(a) = b
        for (int i = 0; i < s.length(); i++) {
            if(s2t[s.charAt(i)] == 0) {
                s2t[s.charAt(i)] = t.charAt(i);
            } else {
                if(s2t[s.charAt(i)] != t.charAt(i)) {
                    return false;
                }
            }

        }

        // 建立g(b) = a
        for (int i = 0; i < t.length(); i++) {
            if(t2s[t.charAt(i)] == 0) {
                t2s[t.charAt(i)] = s.charAt(i);
            } else {
                if(t2s[t.charAt(i)] != s.charAt(i)) {
                    return false;
                }
            }
        }

        // 验证对于所有的映射是不是 f(g(b)) = b
        for (int i = 0; i < s.length(); i++) {
            if (s2t[t2s[t.charAt(i)]] != t.charAt(i)) return false;
        }

        return true;
     }

}
