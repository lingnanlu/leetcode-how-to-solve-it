package easy;

import java.util.Arrays;

/**
 * 你能不能解决一个更简单的问题，2个怎样？
 * 你能利用更简单问题的方法么？ 或者利用结果？
 */
public class Three_Sum_Closeset {

    // 先排序， 再左右夹逼， 其实是把所有情况都试了一遍
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int result = 0; //这不是默认值， 肯定能找到
        int minGap = Integer.MAX_VALUE;

        for(int i = 0; i < nums.length - 2; i++) {
            int j = i + 1, k = nums.length - 1;
            while(j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                int gap = Math.abs(sum - target);

                if(gap < minGap) {
                    result = sum;
                    minGap = gap;
                }

                if(sum > target) {
                    k--;
                } else if(sum < target) {
                    j++;
                } else {
                    return sum;
                }
            }
        }

        return result;
    }


    private int oneCloset(int[] nums, int target) {
        Arrays.sort(nums);

        if (nums[0] >= target) return nums[0];
        if (nums[nums.length - 1] <= target) return nums[nums.length - 1];

        int i = 0;
        while (i < nums.length - 1) {
            if (nums[i] <= target && target <= nums[i + 1]) {
                break;
            } else {
                i++;
            }
        }

        int a = target - nums[i];
        int b = nums[i + 1] - target;
        return a > b ? nums[i + 1] : nums[i];
    }
}
