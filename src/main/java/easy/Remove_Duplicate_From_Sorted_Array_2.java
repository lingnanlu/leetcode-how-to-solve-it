package easy;

public class Remove_Duplicate_From_Sorted_Array_2 {

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int count = 1;
        int result_index = 0;
        int i = 1;
        while(i < nums.length) {
            if(nums[i] == nums[result_index]) {
                if(count == 1) {
                    nums[++result_index] = nums[i];
                    count++;
                } else if (count == 2) {
                    // do nothing
                }
            } else {
                nums[++result_index] = nums[i];
                count = 1;
            }

            i++;
        }

        return result_index + 1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        System.out.println(removeDuplicates(nums));
    }
}
