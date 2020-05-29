package easy;

import java.util.*;

public class Four_Sum {

    public List<List<Integer>> fourSum(int[] nums, int target) {

        Set<List<Integer>> result = new HashSet<>();
        Arrays.sort(nums);


        for(int i = 0; i < nums.length - 3; i++) {
       //     if (nums[i] > target) break; 注意这行对负数不成立
            for(int j = i + 1; j < nums.length - 2; j++) {

                // 关键是最内层的， 找到了相同的后， 怎样去移动指针

                int m = j + 1, n = nums.length -1;
                while (m < n) {
                    int sum = nums[i] + nums[j] + nums[m] + nums[n];

                    if(sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[m], nums[n]));

                        //随变移动一位就行
                        m++;
                    } else if(sum > target) {
                        n--;
                    } else {
                        m++;
                    }
                }

            }
        }

        return new ArrayList<>(result);
    }

    public static void main(String[] args) {
        Four_Sum test = new Four_Sum();
        int[] nums = {1, -2, -5, -4, -3, 3, 3, 5};
        test.fourSum(nums, -11);
    }
}
