package easy;

public class Remove_Element {

    public int removeElement(int[] nums, int val) {
        int result_len = 0;
        int cur = 0;        //结果数组指针

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[cur] = nums[i];
                cur++;
                result_len++;
            }
        }

        return result_len;
    }
}
