package Array;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 这题直观的解法不难, 难在实现
 *
 * O(nums1.length + nums2.length)这个方案
 *
 * 直观的做法是对于nums1中的每一个元素, 都要将nums2遍历一遍
 *
 * 那么, 为什么要遍历一遍呢? 其实就是要找这样一个元素, 它和nums中的要遍历的元素相同, 然后返回比它大的.
 *
 * 如果能直接定位到nums1中的元素, 并返回比它大的, 不就行了?
 *
 * 想到用一个hash表来来示.
 *
 * 这里的最难的地方就是把[1,3,4,2]
 *
 * 经过一遍遍历, 变成
 *
 * 1 - 3
 * 3 - 4
 * 4 - -1
 * 2 - -1
 *
 * 再举一个其它的例子
 *
 * [5, 3, 4, 7, 1]
 *
 * 5 - 7
 * 3 - 4
 * 4 - 7
 * 7 - -1
 * 1 - -1
 *
 * 试着模拟一下
 *
 * [5, 3]
 * [5, 3, 4]可以, 把3, 4放到map中, 3好像没用了. 把3删除
 *
 * [5, 4, 7]可以, 4, 7放到map中, 4好像没有用了, 把4删除
 *
 * [5, 7], 可以, 把5,7放到map中, 5好像没有用了, 把5删除
 *
 * [7, 1]
 *
 * 以上方式似乎要有个东西保存这个过程, 看样子是一个栈.
 *
 * 再以[1,3,4,2]为例, 同样使用以上过程
 *
 * [1, 3] => 1, 3
 *
 * [3, 4] => 3, 4
 *
 * [4, 2]
 *
 * 经过这两个例子, 好像找到了规律.
 *
 * 启示:
 *
 * 本题第一步就是想到将nums2转化成某种形式, 但如何通过一遍遍历将之转化呢?
 * 第二个关键就是举两个例子, 然后找到方法.
 * 还是要注意举例的作用.
 *
 */
public class Next_Greater_Element_I {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();

        stack.push(nums2[0]);

        for (int i = 1; i < nums2.length; i++) {
            if (nums2[i] < stack.peek()) {
                stack.push(nums2[i]);
            } else {
                // nums2[i] > stack.peek();
                map.put(stack.pop(), nums2[i]);
                while (!stack.isEmpty() && stack.peek() < nums2[i]) {
                    map.put(stack.pop(), nums2[i]);
                }
                stack.push(nums2[i]);
            }
        }

        int[] result = new int[nums1.length];
        // 此时map中保存的就是值和第一个比它大的
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.getOrDefault(nums1[i], -1);
        }

        return result;
    }

}
