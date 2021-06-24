# Move Zeroes

[https://leetcode.com/problems/move-zeroes](https://leetcode.com/problems/move-zeroes)

## 问题描述

Given an question.array `nums`, write a function to move all `0`'s to the end of it while maintaining the relative order of the non-zero elements.

**Example:**

```text

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
```

**Note**:

You must do this **in-place** without making a copy of the question.array.

Minimize the total number of operations.

## 解题思路

其实这种题和Remove Element, Remove Duplicates From Sorted Array一样，就是要求原地对数组进行一些操作。

如果你原地不好解决，可以先使用一个新数组，看如何操作的，然后再原地就行了。

一般这种题就是O\(n\)的复杂度

## 相关题目

## 代码

```java
public class Move_Zeroes {

    public void moveZeroes(int[] nums) {
        int begin = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) {
                nums[begin] = nums[i];
                begin++;
            }
        }

        for (int i = begin; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
```

