package easy;


public class Roman_To_Integer {

    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
    }

    public static int romanToInt(String s) {

        int result = 0;

        int length = s.length();

        int i = 0;
        while (i <= (length - 2)) {

            //每次向前看两位
            char first = s.charAt(i);
            char second = s.charAt(i + 1);

            int temp = getResult(first, second);

            /**
             * 先看两位能不能组成一个整体, 能组成一个整体, 就当一个整体来计算, 然后前移两位
             * 不能组成一个整体, 就当一位来计算, 然后前移一位
             */
            if (temp != 0) {
                result += temp;
                i += 2;
            } else {
                result += getResult(first);
                i += 1;
            }
        }

        if (i == (length - 1)) {
            result += getResult(s.charAt(i));
        }

        return result;
    }


    private static int getResult(char first, char second) {

        if(first == 'I' && second == 'V') {
            return 4;
        } else if (first == 'I' && second == 'X') {
            return 9;
        } else if (first == 'X' && second == 'L') {
            return 40;
        } else if (first == 'X' && second == 'C') {
            return 90;
        } else if (first == 'C' && second == 'D') {
            return 400;
        } else if (first == 'C' && second == 'M') {
            return 900;
        } else {
            return 0;
        }
    }

    private static int getResult(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

}
