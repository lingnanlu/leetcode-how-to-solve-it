package math;

/**
 * 没什么难度的题
 */
public class Nth_Tribonacci_Number {

    public int tribonacci(int n) {

        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 1;

        // a, b, c表示要计算的值的前3个值
        // result表示下一个要计算的下标对应的值.
        int a = 0;
        int b = 1;
        int c = 1;
        int result = 0;

        // i 表示下一个要计算的下标.
        int i = 3;
        while (i <= n) {

            result = a + b + c;

            i++;
            a = b;
            b = c;
            c = result;
        }

        return result;
    }
}
