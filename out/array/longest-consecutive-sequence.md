# Longest Consecutive Sequence

[https://leetcode.com/problems/longest-consecutive-sequence](https://leetcode.com/problems/longest-consecutive-sequence)

## 问题描述

Given an unsorted question.array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O\(_n_\) complexity.

**Example:**

```text

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
```

## 解题思路

q 已知是什么，未知是什么

a 已知是一个无序数组，未知是求这个数组中元素所有组成的最长子序列。

q 你能不能改变一个未知，或者改变已知，让这个题更好解一些

a 如果是有序的话，我估计会简单许多，当然，未是是要求最长，所以需要找出所有子序列，然后找出最长即可

q 实验你的想法

a 现在，你能利用这个新问题的解法么？

a 只要把数组排序就行，但这样其实时间复杂度不是O\(n\)了。

q 好的，现在，我们重新审视一下这个题，你看，假如给的数组是\[100, 4, 200, 1, 3, 2\]，比如说，你先拿到了100，那么，如果 这个数组中有它的后序，那么就是101，现在的问题就是，你怎么确定101是否在一个数组中？你见过类似的问题么？就是快速确定一个数 是不是在一个数组中。

a 好像之前的two sum就是类似这样的，如a + b = c， 判断c - a 是否在数组中

q 你能利用它的方法么？

a 嗯，如果使用hashmap的话，我可以，先拿一个数 a，然后以O\(1\)的复杂度来知道 a + 1是否在数组中，如果不再的话，再拿下一个。

q 试试你的方案

q 你有没有感觉到有些重复计算，比如说\[100, 2, 200, 1, 4, 3\], 2的时候，你得出2， 3， 4， 而对于1，你得出1， 2， 3， 4.

其实2， 3，4之前已经算过了，可不可以在找2的时候，往前找找，而且把所有相关的都删除，这样，就不会再对，1，4， 3进行计算了。

a 是的。可以这样。

## 相关题目

1. two sum

## 代码

```java
public class Longest_Consecutive_Sequence {

    @Solution("假设是有序数组")
    static class Orded {

        // 使用两个指针不断的找到一个连续的子序列，只到遍历完为止，
        public int longestConsecutive(int[] nums) {

            // 1. 排序
            Arrays.question.sort(nums);

            // 2. 去重

            int longestLength = 0;
            for (int i = 0; i < nums.length;) {

                // i 指向连续子序列的开头，j指向子序列末尾的后一个，当循环结束时，这个断言为真
                // 以下是指一个连续子序列
                int j = i + 1;
                while(j < nums.length && nums[j] == nums[j - 1] + 1) j++;
                int length = j - i;
                longestLength = Math.max(longestLength,length);
                //
                i = j;

            }

            return longestLength;
        }

    }

    static class UsingHashSet {

        public int longestConsecutive(int[] nums) {
            Set<Integer> set = new HashSet<>();

            // 这里不关心元素的地址和是否重复，只要知道是否存在即可
            for (int i : nums) {
                set.add(i);
            }

            int longestLength = 0;
            for (int i = 0; i < nums.length; i++) {

                // 从i开始，利用set找所有连续的数字。
                int length = 1;
                for(int j = nums[i] + 1; set.contains(j); j++) {
                    set.remove(j);
                    length++;
                }

                for(int j = nums[i] - 1; set.contains(j); j--) {
                    set.remove(j);
                    length++;
                }

                longestLength = Math.max(longestLength, length);
            }

            return longestLength;
        }
    }

    public int longestConsecutive(int[] nums) {
        final HashSet<Integer> myset = new HashSet<>();

        //对于重复的去重没关系
        for (int i : nums) myset.add(i);

        int longest = 0;

        //从每一个开始， 向两边找
        for (int i : nums) {
            int ilongest = 1; //以i向左右找的长度
            for (int j = i - 1; myset.contains(j); j--) {
                myset.remove(j); // 注意可以remove掉， 对于连续的， 从一个开始就可以找到所有， 再从其它元素开找， 无意义
                ilongest++;
            }

            for (int j = i + 1; myset.contains(j); j++) {
                myset.remove(j);
                ilongest++;
            }

            if (ilongest > longest) {
                longest = ilongest;
            }
        }

        return longest;
    }

    public static void main(String[] args) {
        Orded orded = new Orded();
        System.out.println(orded.longestConsecutive(new int[]{1, 2, 3, 4, 100, 101, 102, 200, 201}));
    }
}
```

