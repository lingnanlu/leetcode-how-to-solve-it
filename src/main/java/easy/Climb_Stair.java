package easy;

public class Climb_Stair {

    public static void main(String[] args) {
        Climb_Stair cs = new Climb_Stair();

        System.out.println(cs.climbStairs(10));
    }
//    public int climbStairs(int n) {
//
//        if (n == 0) {
//            return 0;
//        }
//
//        if (n == 1) {
//            return 1;
//        }
//
//        if (n == 2) {
//            return 2;
//        }
//
//        //仔细想想, 有的算了很多遍, 重复了, 所以, 可以把中间结果保存下来, 优化代码.
//        return climbStairs(n - 1) + climbStairs(n - 2);
//    }


    public int climbStairs(int n) {

        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;

        int[] cache = new int[n];

        cache[0] = 0;
        cache[1] = 1;
        cache[2] = 2;

        for (int i = 3; i < cache.length; i++) {
            cache[i] = cache[i - 1] + cache[i - 2];
        }
        return cache[n];
    }
}
