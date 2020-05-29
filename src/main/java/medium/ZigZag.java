package medium;

/**
 * 要是找各个下标的规律, 比较难
 * 发现都是0121的循环.
 * 所以换个思路, 迭代结果
 * 而不是迭代输入, 而输入的指针用来限制
 *
 * 这种思路是一种通用的方法
 *
 */
public class ZigZag {

    public String convert(String s, int numRows) {

        StringBuilder[] arrays = new StringBuilder[numRows];

        for (int i = 0; i < numRows; i++) {
            arrays[i] = new StringBuilder();
        }

        int length = s.length();

        int i = 0;
        while (i < length) {

            for (int j = 0; j < numRows && i < length; j++) {
                arrays[j].append(s.charAt(i));
                i++;
            }

            for (int j = numRows - 2; j > 0 && i < length; j--) {
                arrays[j].append(s.charAt(i));
                i++;
            }
        }



        StringBuilder result = new StringBuilder();
        for (StringBuilder sb : arrays) {
            result.append(sb);
        }

        return result.toString();
    }

}
