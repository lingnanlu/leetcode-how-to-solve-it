package question.array;

import elder.Category;
import elder.Leetcode;

import java.util.Arrays;

@Leetcode(
        title = "Next Permutation",
        category = Category.ARRAY,
        howToSolveIt = """
                
                q 这个题的要求是什么
                
                a 求已知排列的下一个比它大的排列，要求原地和O(1)的空间复杂度
                
                q OK，那么，你遇到过类似问题么,比如说类似的这种已知一个东西，求下一个比它大的。
                
                a 没。。。
                
                q 举个例子，比如给出1， 3， 5， 2， 4 求下一个比它大的排列，你会从哪里开始考虑。
                
                a 从低位到高位开始。。。
                
                q 你之前有没有遇到过这种从低位到高位开始的类似的题？而且下一个数要比当前的数大
                
                a 嗯，好像是加法进位的问题。
                
                q 好，我们就试着从进位的角度来看待这个问题，比如说，就把它当做是排列上的加一操作。
                
                q 当成进位来看待的话，其实就是从以下角度来思考
                
                1. 低位的加1操作对这里指的是什么
                2. 什么时候应该向高位看
                3. 进位就是什么操作
                
                我们一个一个来，我们要得到它的下一个排列，就要从低位开始考虑，我们最开始应该看几位
                
                a 两位，一位组不成排列。
                
                q 那么这两位的加一操作指的是什么
                
                a 就是寻找一个比这两位更大排列。
                
                q 对于两个数来说，如何得到其更大的排列？
                
                a 比如当前给出来的是 1，2 ，然后将两者交换一个就行 2，1
                
                如果给出来的是2，1， 那么就要考虑高位了。
                
                嗯，看第一个数是不是比第二个数大，如果大就要往前考虑一位。
                
                q. 往前考虑一位的话，现在我们是三位了。那么，怎么得到三位的下一个排列呢？
                
                a 如果这三位排列是最大的，需要再往前考虑一位，如果不是的话就要在这三位中调整了。
                
                q. 举个例子，在三位中进行调整的例子，现在，我们的最低两位已经是最大了。
                
                比如说，2， 3， 1
                
                a 因为最低两位是最大的，所以不可能只变化最低两位，需要从最低两位中拿出一个数来换掉最高位的，而这个数应该是比最高位刚刚大一点就好，
                这里是3， 所以变成3， 2， 1
                
                q 仅仅是换掉么，最高位确定了，剩下的呢? 
                
                a 剩下的应该足够小，所以剩下的要重新从小到大排个序
                
                q 好，现在我们再向前看一位，假如最低三位是最大的了，那么，你现在要调整4位。。
                
                a 其实和之前调整三位是一样的。
                
                q 所以，我们这里有一个规则
                
                如果不是最大的，就调整这几位就行。
                
                如果是最大的，就向前考虑一位。
                
                那么，如何知道是最大的？
                
                a 就是一个逆序
                
                """


)
public class Next_Permutation {

    public static void main(String[] args) {
        Next_Permutation next_permutation = new Next_Permutation();

        next_permutation.nextPermutation(new int[] {1, 3, 2});
    }
    public void nextPermutation(int[] nums) {

        // 小于等于1位
        if (nums.length <= 1) return;

        // 只有两位，交换即可
        if (nums[nums.length - 2] < nums[nums.length - 1]) {
            int temp = nums[nums.length - 2];
            nums[nums.length - 2] = nums[nums.length - 1];
            nums[nums.length - 1] = temp;
        } else {

            // 3位或3位以上，从最低两位不断调整。
            // 先将低位置的调整成最大的，如果低位置的是最大的，就向前看一位

            int i = nums.length - 3;

            // 一直向前看，跳过最大的低位置
            while(i >= 0 && nums[i] >= nums[i + 1]) {
                i--;
            }

            if(i < 0) {
                // 说明整个序列是倒序的，这时反向排序就行
                Arrays.sort(nums);
            } else {
                // 找第一个比num[i]大的元素
                int j = nums.length - 1;
                while(j > i && nums[j] <= nums[i]) j--;

                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;

                Arrays.sort(nums, i + 1, nums.length);

            }
        }

    }
}
