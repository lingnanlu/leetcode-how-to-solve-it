package Array;

/**
 * 把偶数移动到奇数前
 *
 * 原地移动
 */
public class Sort_Array_By_Parity {

    public int[] sortArrayByParity(int[] nums) {

        //

        /**
         * [0, p)之间的都是偶数
         * [p, i)之间都是奇数
         *
         * 最后i = nums.length, 由不变式可知, p是分隔点
         * p指向下一个空位
         */
        int p = 0;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] % 2 == 0) {
                int temp = nums[p];
                nums[p] = nums[i];
                nums[i] = temp;
                p++;
            }

        }

        return nums;
    }
}
