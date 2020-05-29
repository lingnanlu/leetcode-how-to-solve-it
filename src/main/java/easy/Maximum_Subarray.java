package easy;

public class Maximum_Subarray {
    public static int maxSubArray(int[] nums) {

        // result中存放的是以nums[i]为结尾的最大子数组值
        int[] result =new int[nums.length];

        if(nums.length == 0) {
            return 0;
        } else {

            int max = result[0];
            result[0] = nums[0];

            for (int i = 1; i != nums.length; i++) {
                result[i] = Math.max(nums[i], result[i - 1] + nums[i]);
                max = Math.max(max, result[i]);
            }

            return max;
        }

    }

    public static void main(String[] args) {
        int[] nums = {1};
        maxSubArray(nums);
    }
}
