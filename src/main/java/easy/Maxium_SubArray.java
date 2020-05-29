package easy;

//动态规划
public class Maxium_SubArray {

    public int maxSubArray(int[] nums) {
        int[] d = new int[nums.length];     //保存以i结尾的连续数组和的最大值

        d[0] = nums[0];
        int max = d[0];
        for (int i = 1; i < nums.length; i++) {
            //求d[i]
            d[i] = Math.max(d[i - 1] + nums[i], nums[i]);
            if (d[i] > max) {
                max = d[i];
            }
        }

        return max;
    }

    
}
