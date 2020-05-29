package easy;

import java.util.*;

// 注意， 此题是不能包含重复的， 但不要求和原来顺序不同，
// 如果乱序的话， 可能是重复的， 先排个序
public class Three_Sum {

    // a + b + c = 0 => a + b = -c
    public static List<List<Integer>> threeSum(int[] nums) {

        Set<List<Integer>> results = new HashSet<>();
        Arrays.sort(nums);

        for(int i = 0; i < nums.length - 2; i++) {

            if (nums[i] > 0) continue; // 因为是排序了的

            int target = -nums[i];

            int j = i + 1;
            int k = nums.length - 1;
            while(j < k) {
                if(nums[j] + nums[k]  == target) {
                    results.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                } else if (nums[j] + nums[k] > target){
                    k--;
                } else {
                    j++;
                }
            }

        }

        return new ArrayList<>(results);
    }

    public static void main(String[] args) {
        int[] nums = {3, 0, -2, -1, 1, 2};
        threeSum(nums);
    }
}
