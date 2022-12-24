package Array;

public class Longest_Continuous_Increasing_Subsequence {

    public int findLengthOfLCIS(int[] nums) {

        // 最长连续子序列的长度
        int max = 0;

        // [i, j)之间是连续子序列, i是开头
        int i = 0, j = 1;

        // 记录当前连续子序列的长度.
        int current = 1;
        while (i != nums.length) {
            // 不断移动j, 找到最后一个
            while (j != nums.length && nums[j] > nums[j - 1]) {
                j++;
                current++;
            }

            // 此时j是下一个连续子序列的开头
            max = Math.max(max, current);
            i = j;
            j = i + 1;
            current = 1;
        }

        return max;
    }

}
