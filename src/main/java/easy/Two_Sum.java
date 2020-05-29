package easy;

import java.util.HashMap;

// // 注意， hashmap的方式 nums中不能有重复元素， 否则，只能排序然后夹逼
public class Two_Sum {

    public int[] twoSum(int[] nums, int target) {

        int[] result = new int[2];


        final HashMap<Integer, Integer> num2Index = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {
            num2Index.put(nums[i], i);
        }

        for(int i = 0; i < nums.length; i++) {

            int remain = target - nums[i];
            if(num2Index.containsKey(remain) && num2Index.get(remain) != i) {   //注意这里， 可能有6 - 3 = 3的情况
                result[0] = i;
                result[1] = num2Index.get(remain);
                return result;
            }
        }

        return result;
    }
}
