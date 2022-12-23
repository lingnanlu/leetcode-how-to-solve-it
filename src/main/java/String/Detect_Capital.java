package String;

/**
 * 这题不难, 按照题意从左到右检测就好
 *
 * 这种合法与非法的情况, 可以
 * 直接检测是否合法.
 * 如果直接检测是否合法比较麻烦.就检测是否非法.
 *
 * 这里非法有一种情况.
 *
 * 有小写, 且非首字母有大写
 *
 * 启示:
 *
 * 当只有两种情况时, 如果正面不好判断, 可以判断反面.
 */
public class Detect_Capital {

    public boolean detectCapitalUse(String word) {

        // 遍历word一遍, 以下两个都为真时, 就是非法的.
        boolean hasSmall = false;
        boolean hasCapNotFirst = false;

        if (word.charAt(0) >= 'a' && word.charAt(0) <= 'z') {
            hasSmall = true;
        }

        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) >= 'a' && word.charAt(i) <= 'z') {
                hasSmall = true;
            }

            if (word.charAt(i) >= 'A' && word.charAt(i) <= 'Z') {
                hasCapNotFirst = true;
            }
        }

        return !hasSmall || !hasCapNotFirst;




    }
}
