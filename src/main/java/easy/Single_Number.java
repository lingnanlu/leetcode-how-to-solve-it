package easy;

public class Single_Number {

    public int singleNumber(int[] nums) {

        int result = 0;
        for (int n : nums) {
            result = result ^ n;
        }

        return result;
    }
}
