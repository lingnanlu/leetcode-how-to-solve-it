package question.array;

// 你不能把所有的都相乘, 然后再做除法, 想想如果有0的情况
// 这个看答案也是非常trick, 还是记住吧


import run.elder.Category;
import run.elder.Leetcode;
import run.elder.Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Leetcode(
        title = "Product of Array Except Self",
        link = "https://leetcode.com/problems/product-of-array-except-self/",
        category = Category.ARRAY,
        how2SolveIt = """
                
                方法一、
                
                最直观的作法是，就是按照字面上的意思，每次计算其它数的乘积，但不包括该数。
                
                但这种法有个缺点，就是重复计算了。
                
                那么，怎么消除重复计算呢？其实就是把中间过程保存下来，然后重复利用就行。
                
                所以下一步的关键问题就是，
                
                1. 哪些值被重复计算了
                2. 这些值如何保存
                
                以上两个问题，拿一个例子，自己模拟一下即可。
                
                方法二、 使用常量空间
                
                现在，要求使用常量空间，所以，我们在以上进行修改。
                
                当然所使用的额外空间和数组中元素个数有关，如果使用常量空间的话，说明我们所保存的重复计算的值有些是不必要的。
                
                现在，有两个额外的O(n)空间，我们一一消除
                
                通过分析在计算result过程中，left数组的各个元素，发现，并不需要全部保存。只是需要一个不断变化的整数就行。
                
                而至于right，发现并不能消除，那么能不能使用result来保存right,然后再由right和之前不断变化的整数来进行计算，得到最终的result呢？
                
                方法三、使用除法
                
                如果是除法的话，一定要注意0的情况，仔细考虑各种特殊情况，这个要求细心，就不展开了。
                
                """,
        relatedQuestions = {}
)
public class Product_Of_Array_Except_Self {

    @Solution(name = "根据字面意思，使用额外空间消除重复")
    static class ProductOfOther {

        public int[] productExceptSelf(int[] nums) {

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
    }


    @Solution(name = "使用常量空间")
    static class ProductOfOtherUsingConstantSpace {

        public int[] productExceptSelf(int[] nums) {

            int[] result = new int[nums.length];

            //先利用result保存right
            result[nums.length - 1] = 1;
            for(int i = nums.length - 2; i >= 0; i--) {
                result[i] = nums[i + 1] * result[i + 1];
            }

            int left = 1;       // 使用一个不断变化的整数来代替left数组。

            for (int i = 0; i < nums.length; i++) {
                result[i] = left * result[i];
                left = left * nums[i];
            }
            return result;
        }
    }

    @Solution(name = "使用除法")
    static class UsingDivision {
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
    }
}
