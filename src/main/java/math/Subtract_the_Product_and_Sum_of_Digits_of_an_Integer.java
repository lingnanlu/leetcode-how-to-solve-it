package math;

/**
 * 这题没什么意思,就是把乘积和和算出来
 * 就当练习不变式.
 */
public class Subtract_the_Product_and_Sum_of_Digits_of_an_Integer {

    public int subtractProductAndSum(int n) {
        int sum = 0;
        int product = 1;

        // 用来取最低位
        int low;
        // n表示取了最低位之后,剩下的位的值
        while (n != 0) {
            low = n % 10;
            n = n / 10;
            product = product * low;
            sum = sum + low;
        }

        return product - sum;
    }

}
