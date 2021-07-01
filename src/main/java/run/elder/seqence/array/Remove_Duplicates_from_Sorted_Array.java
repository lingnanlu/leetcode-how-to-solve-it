package run.elder.seqence.array;


import run.elder.Category;
import run.elder.Leetcode;
import run.elder.Level;
import run.elder.Solution;

@Leetcode(
        title = "Remove Duplicates from Sorted Array",
        link = "https://leetcode.com/problems/remove-duplicates-from-sorted-array/",
        category = Category.ARRAY,
        level = Level.EASY,
        how2SolveIt = """
                
                Q. 已知是什么? 未知是什么, 或者说要求的是什么?

                A. 已知是一个升序数组, 要求的是将该数组中的重复元素去掉, 剩下的元素没有重复的.

                Q. 限制是什么？

                A.
                
                1. 空间上要O(1),in-place,不能使用额外的空间
                2. 时间上没要求
                3. 结果放在数组中的前k个位置

                Q. 如果你现在想不出来一个解，如果想不出来, 你能不能放松一下限制？你觉得哪个限制比较禁锢你的思路? 可不可以去掉一个限制?

                A. 额, 主要是对空间有要求, 我想去掉空间限制, 比如可以使用额外空间.

                Q. 现在我们放松的限制, 可以稍微变化一个问题了, 这个问题和原问题类似, 即: 创建一个新的数组，但是不能包括重复元素。这个问题你会解么?
                
                A. 我可以把数组中的元素一个个拷贝到新的数组中, 拷贝过程中判断新数组是否包含这个元素, 如果包含就不拷贝了.
                
                Q. 试着写出你的解.
                
                Q. 好了, 现在你写出了一个清晰的, 无错误的解, 看看有没有什么可以优化的地方, 你觉得哪里有些复杂?
                
                A. 判断新数组中是否包含了要插入的元素. 每次都要从头判断, 这个导致时间复杂度为0(n^2).
                
                Q. 嗯, 可不可以改进一下呢? 比如, 你在看作这个解时, 有没有利用所有的已知呢? 还有什么已知条件你没利用上呢?
                
                A. 原数组是有序的, 这个我的第一次答案中没有用到.
                
                Q. 那么, 你可不可以利用这个条件来优化一下呢? 如果是有序的, 怎么判断是否重复了? 可以画图作为辅助.
                
                A. 我只要判断前一个元素和要插入的是不是一样就行.
                
                Q. 试着写出来.
                
                Q. 现在算法的时间复杂度是多少
                
                A. O(n)
                
                Q. 好了, 现在我们有了一个类似的问题, 而且已经得到一个满意的解了, 现在你可以利用解决这个问题的方法么? 比如说, 把原数组看做是2个数组, 可以么?
                
                A. 我可以试试, 为了不混乱, 我要给原数组起一个别名, 这样, 我看起来是操作两个数组, 但实际上还是操作的一个. 
                
                A. 啊哈! 可以的, 而且我画图画了一个, 没毛病! 就是可以起个别名, 当成两个数组!

                """,
        relatedQuestions = {
                "Remove Duplicates from Sorted Array II"
        }
)
public class Remove_Duplicates_from_Sorted_Array {
    @Solution(
            name = """
            一个类似的问题, 创建一个不包含重复元素的新的数组.
            """,
            detail =
            """
            使用一个新的数组, 将原数组的数据依次拷贝, 拷贝过程中判断一下新数组是否已包含该元素
            """
    )
    static class First {

        public int[] removeDuplicates(int[] nums) {

            // 新数组, 和原来一样大
            int[] newNums = new int[nums.length];


            /**
             * 注意这里使用循环不变式, 以下两个断言一直为真
             * i 指向原数组中下一个要copy的元素
             * j 指向新数组中下一个空位
             */
            int i = 0;
            int j = 0;
            while(i != nums.length) {
                if (!contains(newNums, j, nums[i])) {
                    newNums[j] = nums[i];
                    i++;
                    j++;
                } else {
                    i++;
                }
            }

            // 因为是循环不变式, 到这里时
            // i == nums.length 而i的含义是指向下一个要copy的元素, 可以确定, nums中的元素都copy完了
            // j 指向新数组中的下一个空位, 所以, j之前的都是有元素的, 而且j正好表示新数组的size.
            // 这就是循环不变式的好处.
            return newNums;
        }

        // 判断nums中是否包含n
        private boolean contains(int[] nums, int size, int n) {
            for (int i = 0; i < size; i++) {
                if (nums[i] == n) {
                    return true;
                }
            }
            return false;
        }
    }

    @Solution(
            name = """
            一个类似的问题, 创建一个不包含重复元素的新的数组. 优化时间复杂度
            """,
            detail =
                    """
                    使用一个新的数组, 将原数组的数据依次拷贝, 拷贝过程中判断一下新数组是否已包含该元素, 优化了判断新数组中是否包含元素的方式.
                    """
    )
    static class Two {

        public int[] removeDuplicates(int[] nums) {

            // 新数组, 和原来一样大
            int[] newNums = new int[nums.length];

            /**
             * 注意这里使用循环不变式, 以下两个断言一直为真
             * i 指向原数组中下一个要copy的元素
             * j 指向新数组中下一个空位
             */
            int i = 0;
            int j = 0;
            while(i != nums.length) {
                if (!contains(newNums, j, nums[i])) {
                    newNums[j] = nums[i];
                    i++;
                    j++;
                } else {
                    i++;
                }
            }

            // 因为是循环不变式, 到这里时
            // i == nums.length 而i的含义是指向下一个要copy的元素, 可以确定, nums中的元素都copy完了
            // j 指向新数组中的下一个空位, 所以, j之前的都是有元素的, 而且j正好表示新数组的size.
            // 这就是循环不变式的好处.
            return newNums;
        }

        /**
         * 判断nums中是否包含n
         * @param nums
         * @param size 数组大小
         * @param n
         * @return
         */
        private boolean contains(int[] nums, int size, int n) {
            if (size == 0) return false;
            if (nums[size - 1] == n) return true;
            else return false;
        }
    }

    @Solution(
            name = """
            将原数组看成两个
            """,
            detail =
                    """
                    将原数组看成两个, 为了不混乱, 起一个别名, 然后使用之前的方法.
                    """
    )
    static class Third {

        public int removeDuplicates(int[] nums) {

            // 起个别名
            int[] newNums = nums;

            // 注意这里也是使用的循环不变式
            int i = 0;  // 指向原数组中下一个要copy的元素
            int j = 0;  // 指向新数组中下一个空位
            while (i != nums.length) {
                if (!contains(newNums, j, nums[i])) {
                    newNums[j] = nums[i];
                    i++;
                    j++;
                } else {
                    i++;
                }
            }

            // 因为是循环不变式, 到这里时
            // i == nums.length 而i的含义是指向下一个要copy的元素, 可以确定, nums中的元素都copy完了
            // j 指向新数组中的下一个空位, 所以, j之前的都是有元素的, 而且j正好表示新数组的size.
            // 这就是循环不变式的好处.
            return j;
        }

        /**
         * 判断nums中是否包含n
         * @param nums
         * @param size 数组大小
         * @param n
         * @return
         */
        private boolean contains(int[] nums, int size, int n) {
            if (size == 0) return false;
            if (nums[size - 1] == n) return true;
            else return false;
        }
    }

    @Solution(name = "思路不清晰的答案")
    static class Old {
        public int removeDuplicates(int[] nums) {

            if (nums.length == 0) {
                return 0;
            }

            int cur = 0;            //指向结果
            int result_len = 1;     //结果已经有一个元素了

            //从第2个元素开始迭代nums
            int i = 1;
            while (i < nums.length) {
                //找到与cur所指向的元素不同的元素
                while (i < nums.length && nums[i] == nums[cur]) i++;

                //此时, i所指向的元素与cur的不同
                if (i < nums.length) {
                    nums[++cur] = nums[i];
                    result_len++;
                    i++;
                } else {
                    break;
                }
            }

            return result_len;
        }
    }

}
