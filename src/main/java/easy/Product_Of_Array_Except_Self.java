package easy;

// 你不能把所有的都相乘, 然后再做除法, 想想如果有0的情况
// 这个看答案也是非常trick, 还是记住吧

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 前提是数组长度n > 1
public class Product_Of_Array_Except_Self {

    // 如果能用除法呢? 你怎么处理0
    // 其实只有一个0, 和大于1个0这两种情况需要处理
    // 只有一个0的话, 只有一元素不为0, 其它都是0
    // 有大于一个0的话, 就都是0了
    public int[] productExceptSelf(int[] nums) {

        List<Integer> zeroIndexs = new ArrayList<>();
        int nonZeroProduct = 1;

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                zeroIndexs.add(i);
            } else {
                nonZeroProduct *= nums[i];
            }
        }

        int[] result = new int[nums.length];
        Arrays.fill(result, 0);

        // 只有一个元素为0, 否则都是0
        if (zeroIndexs.size() == 1) {
            int zeroIndex = zeroIndexs.get(0);
            result[zeroIndex] = nonZeroProduct;
        } else if (zeroIndexs.size() == 0) {
            for (int i = 0; i < result.length; i++) {
                result[i] = nonZeroProduct / nums[i];
            }
        } else {
           // 都是0了
        }

        return result;

    }


    // 考虑到O(n), 那么就是对数组进行常数遍的遍历, 如果一个结果要遍历整个数组才能拿到, 那么考虑复用部分结果
    // 其实这里有一个复用的方式.
    public int[] productExceptSelf2(int[] nums) {

       int[] result = new int[nums.length];
       int[] left = new int[nums.length];
       int[] right = new int[nums.length];

       left[0] = 1;
       for(int i = 1; i < left.length; i++) {
           left[i] = left[i - 1] * nums[i - 1];
       }

       right[nums.length - 1] = 1;
       for(int i = nums.length - 2; i >= 0; i--) {
           right[i] = right[i + 1] * nums[i + 1];
       }

       for (int i = 0; i < nums.length; i++) {
           result[i] = left[i] * right[i];
       }

       return result;
    }

    // 对2进行改进, 考虑到常量空间, 复用一个数组作为结果, 消除另一个数组
    public int[] productExceptSelf3(int[] nums) {

        int[] result = new int[nums.length];
        int[] left = new int[nums.length];

        left[0] = 1;
        for(int i = 1; i < left.length; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }

        int right = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] = left[i] * right;
            right = right * nums[i];
        }

        return result;
    }
}
