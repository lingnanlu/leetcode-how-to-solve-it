package easy;

public class Palindrome_Number {

    public static void main(String[] args) {


        System.out.println(isPalindrome(121));
    }
//    public static boolean isPalindrome(int x) {
//        if(x < 0) return false;
//
//
//        long reversed = 0;
//        int temp = x;
//        while (temp != 0) {
//            reversed = reversed * 10 + temp % 10;
//            temp = temp / 10;
//        }
//
//        return reversed == x ? true : false;
//    }


    /**
     * 想办法得到最高位和最低位, 然后比较数字
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        if(x < 0) return false;

        int div = getHighest(x);

        while (x != 0) {
            int h = x / div;    //得到最高位数字
            int l = x % 10;     //得到最低位数字
            if(h != l) return false;

            x = (x % div) / 10;



            div /= 100;
        }

        return true;
    }


    /**
     *
     *
     * x是正数, 如果x=123 得到100, 如果x=3412, 得到1000
     * @param x
     * @return
     */
    public static int getHighest(int x) {
        int result = 1;

        //比如x = 123, x向右移3位, result向左移3位,
        while (x != 0) {
            x = x / 10; //x向右移
            result = result * 10;   //result向左移
        }

        return result / 10;
    }
}
