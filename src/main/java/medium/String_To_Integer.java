package medium;

public class String_To_Integer {

    public static void main(String[] args) {

        int r = myAtoi("9223372036854775808");
        System.out.println(r);
    }
    public static int myAtoi(String str) {

        long result = 0;

        if(str == null) return 0;

        if (str.trim().length() == 0) return 0;

        str = str.trim();

        boolean positive = true;
        char sign = str.charAt(0);
        if(sign == '-') {
            positive = false;
            str = str.substring(1);
        } else if(sign == '+') {
            str = str.substring(1);
        }

        //考虑计算过程中也可能溢出了
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(c >= '0' && c <= '9') {
                result = result * 10 + c - '0';

                //str有可能很长很多, 超出long范围, 所以要在计算过程中进行溢出判断
                if(positive) {
                    if(result > Integer.MAX_VALUE) {
                        return Integer.MAX_VALUE;
                    }
                } else {
                    if(-result < Integer.MIN_VALUE) {
                        return Integer.MIN_VALUE;
                    }
                }
            } else {
                break;
            }
        }

        result = positive ? result : result * -1;

        return (int) result;
    }
}
