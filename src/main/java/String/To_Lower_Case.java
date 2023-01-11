package String;

/**
 * 这题不难, 对每一个判断是不是大写字母,如果是就转化成小写
 * 优化点其实就两个
 * 1. 判断是不是大写字母
 * 2. 转化成小写
 *
 * 注意的一点就是整数提升.
 */
public class To_Lower_Case {

    public String toLowerCase(String s) {

        int distance = 'A' - 'a';
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) {
                // 注意这里有个整数提升
                sb.append((char)(c - distance));
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
