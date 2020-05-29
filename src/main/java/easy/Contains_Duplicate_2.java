package easy;

import java.util.HashMap;
import java.util.Map;

// 找两个相同元素的距离不大于K
// 可以记录下所有相同元素的下标, 然后找有没有符合条件的
public class Contains_Duplicate_2 {

    // 这种是错误的, 这种解法有一个假设, 重复的元素只有两个, 其实是可能有多个的
    public boolean containsNearbyDuplicate(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])) {
                int distance = i - map.get(nums[i]);
                if(distance <= k) {
                    return true;
                }
            } else {
                map.put(nums[i], i);
            }
        }

        return false;
    }


    // 这种是错误的, 这种解法有一个假设, 重复的元素只有两个, 其实是可能有多个的
    public boolean containsNearbyDuplicate2(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])) {
                int distance = i - map.get(nums[i]);
                if(distance <= k) {
                    return true;
                } else {
                    map.put(nums[i], i); // 更新一下
                }
            } else {
                map.put(nums[i], i);
            }
        }

        return false;
    }

}
