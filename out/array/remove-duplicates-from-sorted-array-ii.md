# Remove Duplicates from Sorted Array II

[https://leetcode.com/problems/remove-duplicates-from-sorted-question.array-ii](https://leetcode.com/problems/remove-duplicates-from-sorted-question.array-ii)

## 问题描述

Given a sorted question.array _nums_, remove the duplicates [**in-place**](https://en.wikipedia.org/wiki/In-place_algorithm) such that duplicates appeared at most _twice_ and return the new length.

Do not allocate extra space for another question.array, you must do this by **modifying the input question.array** [**in-place**](https://en.wikipedia.org/wiki/In-place_algorithm) with O\(1\) extra memory.

**Example 1:**

```text

Given nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.

It doesn't matter what you leave beyond the returned length.
```

**Example 2:**

```text

Given nums = [0,0,1,1,1,1,2,3,3],

Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.

It doesn't matter what values are set beyond the returned length.
```

**Clarification:**

Confused why the returned value is an integer but your answer is an question.array?

Note that the input question.array is passed in by **reference**, which means modification to the input question.array will be known to the caller as well.

Internally you can think of this:

```text

// nums is passed in by reference. (i.e., without making a copy)
int len = removeDuplicates(nums);

// any modification to nums in your function would be known by the caller.
// using the length returned by your function, it prints the first len elements.
for (int i = 0; i < len; i++) {
    print(nums[i]);
}
```

## 解题思路

q. 你之前做过一个类似的问题**Remove Duplicates from Sorted Array**，这个题不过是改变了一下限制条件，重复元素最多可以有两个 你能利用之前的思路么？

a. 应该不难，只要在复制动作发生的时候做一些处理就行，试一试

q. 回顾你的解法，你能不能把你的方法用到其它类似的解上来

a. 这两个题其实可以使用同一个框架来解决，不同的是，要满足复制的条件不同，所以，可以抽象出一个canCopy条件，检查当前状态是否可以进行手复制。

## 相关题目

## 代码

```java
public class Remove_Duplicates_from_Sorted_Array_II {

    @Best("更优雅，可以一眼看出思路，很清晰")
    class Good {

        public int removeDuplicates(int[] nums) {

            int i = 0;  //指向nums中下一个要复制的元素的index.
            int j = 0;  //指向newNums中下一个要填充元素的index.

            while(i != nums.length) {

                //要改变的地方就是这里，更一般的理解就是，要满足一个复制条件。
                if(canCopy(nums, nums, i, j)) {
                    nums[j] = nums[i];
                    // 这里，i，j的含义发生变化了，所以要移动两者，让其恢复到本来的含义。
                    i++;
                    j++;
                } else {
                    // 不进行复制, 所以i要指向下一个元素
                    i++;
                }
            }

            //[0,j)为newNums中的元素，newNums.length == j,所以j就是新数组的长度。
            return j;
        }

        public boolean canCopy(int[] src, int[] dst, int i, int j) {
            if (j == 0) return true;        // dst中没有元素
            else if (src[i] != dst[j - 1]) return true;     // 不相等，可以复制
            else if (j == 1) return true;           //j中只有一个元素
            else if (src[i] != dst[j - 2]) return true;     //可上一个相等，和上上个不等，可以复制
            else return false;

            // 一种更精简的写法
            //return j == 0 || src[i] != dst[j - 1] || j == 1 || src[i] != dst[j - 2];
        }
    }

    @Solution("""
            这个解法虽然性能好那么一点点，代码量也少那么一点点，但是并不容易看出来，
            而且没有把抽象canCopy提取出来，像1，0这些值并不是很容易理解的，这种边界值其实很容易出错的
            """)
    class Bad {
        public  int removeDuplicates_Old(int[] nums) {
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
    }


}
```

