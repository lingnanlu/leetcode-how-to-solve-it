package String;

/**
 * 没啥, 就是跳过非字母
 */
public class Reverse_Only_Letters {

    public String reverseOnlyLetters(String s) {

        char[] array = s.toCharArray();
        int p = 0, q = s.length() - 1;

        while (p < q) {
            // 让p指向从左开始的第一个字母
            while (p != s.length() && !Character.isLetter(array[p])) {
                p++;
            }

            // 此时p要么指向末尾, 要么指向第一个字母
            if (p == s.length()) {
                break;
            }

            // 让q指向从右开始的第一个字母
            while (q >= 0 && !Character.isLetter(array[q])) {
                q--;
            }

            if (q < 0) {
                break;
            }

            if (p < q) {
                // 交换
                char temp = array[p];
                array[p] = array[q];
                array[q] = temp;
                p++;
                q--;
            } else {
                break;
            }
        }

        return new String(array);


    }
}
