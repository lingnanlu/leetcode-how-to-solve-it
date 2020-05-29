package easy;

public class Sqrt {

    // 这样可能会溢出
//    public static int mySqrt(int x) {
//
//        int i = 1;
//        while(i * i <= x) {
//            i++;
//        }
//        return i - 1;
//    }

    // 其实就是一个2分查找, 找到一个满足条件的数
    public static int mySqrt(int x) {

        if (x == 1) return 1;

        // 注意这种写法不会溢出
        int low = 1, high = x, mid = low + (high - low) / 2;

        while(low <= high) {
            if(mid < x / mid) {     // 注意溢出
                low = mid + 1;
            } else if (mid > x / mid) {
                high = mid - 1;
            } else {
                return mid;
            }
            mid = low + (high - low) / 2;
        }

        if (mid > x / mid) mid--;
        return mid;

    }


    public static void main(String[] args) {
        System.out.println(mySqrt(8));
    }
}
