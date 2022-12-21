package String;

/**
 * 这题没什么可说的, 试着一遍过, 注意已知条件.
 * 不使用自带的任何库函数, 纯粹当成字符操作.
 * 考察自己的思路是否清晰
 */
public class Reverse_Words_in_A_String_III {

    public String reverseWords(String s) {

        StringBuilder sb = new StringBuilder();

        /**
         * 依次找到s中的一个单词, 然后把该单词进行反转
         */
        int i = 0; //  i是单词开头
        while (i != s.length()) {

            int j = i + 1;
            // 将j移动到结尾
            while (j != s.length() && s.charAt(j) != ' ') {
                j++;
            }

            // 此时[i, j)定位到一个单词
            // p用来倒序复制一个单词
            int p = j - 1;
            while (p >= i) {
                sb.append(s.charAt(p));
                p--;
            }

            //此时复制了一个单词, 添加一个空格
            sb.append(' ');

            // 将i指向下一个单词开头, 使不变式为真
            i = j;
            while (i != s.length() && s.charAt(i) == ' ') {
                i++;
            }

        }

        // 此时多一个空格, 删除最后一个
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

}
