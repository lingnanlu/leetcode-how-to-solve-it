package easy;

import java.util.HashSet;
import java.util.Set;


public class Contains_Duplicate {

    public boolean containsDuplicate(int[] nums) {

        if (nums.length == 0) return false;
        // 使用set来检查一个元素有没有出现过
        Set<Integer> s = new HashSet<>(nums.length);

        s.add(nums[0]);

        for(int i = 1; i < nums.length; i++) {
            if(s.contains(nums[i])) {
                return true;
            } else {
                s.add(nums[i]);
            }
        }

        return false;
    }

}
