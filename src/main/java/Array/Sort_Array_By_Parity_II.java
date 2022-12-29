package Array;

/**
 *
 * 方法一:
 *
 * 使用一个新数组
 *
 * 方法二:
 * 尝试原地排序, 就是把原数组变成奇偶依次出现的数组.
 * 那么我们就遍历nums, 把所有的奇数都放到其最终位置上
 * 那么, 偶数自然也在最终位置.
 *
 * 这题原地的难点在于, 确定各个变量之间的准确含义.
 *
 * 方法三:
 *
 * 还是原地, 依次检查偶数位上的值, 然后使用一个指针指向奇数位, 如果遇到一个不满足条件的, 就从奇数指针指向的位置中的下一个偶数值进行对换.
 *
 * 这里的关键是, 偶数指针只检查偶数位, 奇数指针只检查奇数位
 *
 * 两者没有重叠, 最后偶数指针检查过的位都是满足条件的, 这样, 自然剩余的也是满足条件的.
 *
 * 启示:
 *
 * 这题原地时的难度主要是在于会有重叠, 所以使用两个指针, 一个处理偶数, 一个处理奇数, 这样就没有重叠了, 不变式写起来也简单了
 * 许多. 原地就要注意防止重复处理已经处理过的数.
 *
 *
 *
 */
public class Sort_Array_By_Parity_II {

    static class First {

        public int[] sortArrayByParityII(int[] nums) {
            int[] result = new int[nums.length];

            int p = 0, q = 1;
            for (int n : nums) {
                if (n % 2 == 0) {
                    result[p] = n;
                    p += 2;
                } else {
                    result[q] = n;
                    q += 2;
                }
            }

            return result;
        }

    }

    static class Second {
        public int[] sortArrayByParityII(int[] nums) {
            /**
             * p指向偶数的下一个空位, 即始终指向一个偶数位, 但是其值为奇数.
             * 即p指向的位置第一个: 是一个偶数位, 但值为奇数的.
             * 也就是说在[0, p)之间, 偶数位上的数都是偶数 (非偶数位上的即可能是偶数, 也可能是奇数)
             */
            int p = 0;
            while (p != nums.length) {
                if (p % 2 == 0 && nums[p] % 2 != 0) {
                    break;
                } else {
                    p++;
                }
            }

            if (p == nums.length) {
                // 由不变式[0, p)之间, 偶数位上的数都是偶数, 由题意限制, 可得, 奇数位上的也都是奇数.所以原数组本身就是满足条件的.
                return nums;
            }

            /**
             * 此时, p指向的位置应该是把下一个要放偶数值的地方.
             * 依次处理nums[i], 每处理一个, 都将其放到最终位置上
             */
            for (int i = 0; i < nums.length; i++) {
                // 如果是偶数, 且正好在其应该在的位置上, 就不动
                if (nums[i] % 2 == 0 && i % 2 == 0) {
                    // do nothing
                } else if (nums[i] % 2 == 0 && i % 2 == 1){
                    // 说明是偶数, 但不在其应该在的位置上, 放到p所指的位置上
                    int temp = nums[p]; // 由不变式, temp一定是奇数
                    nums[p] = nums[i];
                    nums[i] = temp;

                    // 继续让p指向下一个偶数位, 但是其值为奇数
                    p++;
                    while (p != nums.length) {
                        if (p % 2 == 0 && nums[p] % 2 != 0) {
                            break;
                        } else {
                            p++;
                        }
                    }

                    // 说明所有的偶数位都是偶数了.
                    if (p == nums.length) {
                        return nums;
                    }
                } else if (nums[i] % 2 == 1 && i % 2 == 0) {
                    // 偶数位出现奇数, 这不可能
                } else {
                    // nums[i] % 2 == 1 && i % 2 == 1
                    // 奇数位在奇数, 什么也不做.
                }
            }

            return nums;
        }
    }

    static class Third {
        public int[] sortArrayByParityII(int[] nums) {

            // p指向下一个要检查的偶数位, q指向下一个奇数位
            int p = 0, q = 1;

            // 依次检查偶数位, 让其满足限制, 这样, 剩余的奇数位也就都是奇数了.
            while (p < nums.length) {
                if (nums[p] % 2 == 1) {
                    //从奇数位中找一个偶数值来换
                    while (q < nums.length && nums[q] % 2 != 0) {
                        q += 2;
                    }

                    if (q >= nums.length) {
                        // 没有可以换的奇数了, 说明整个数组已经满足条件
                        return nums;
                    }

                    int temp = nums[p];
                    nums[p] = nums[q];
                    nums[q]  = temp;
                    p += 2;
                    q += 2;
                } else {
                    p += 2;
                }
            }

            return nums;

        }


    }

}
