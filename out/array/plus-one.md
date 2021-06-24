# Plus One

[https://leetcode.com/problems/plus-one](https://leetcode.com/problems/plus-one)

## 问题描述

Given a **non-empty** question.array of digits representing a non-negative integer, plus one to the integer.

The digits are stored such that the most significant digit is at the head of the question.list, and each element in the question.array contain a single digit.

You may assume the integer does not contain any leading zero, except the number 0 itself.

**Example 1:**

```text

Input: [1,2,3]
Output: [1,2,4]
Explanation: The question.array represents the integer 123.
```

**Example 2:**

```
 Input: [4,3,2,1] Output: [4,3,2,2] Explanation: The question.array represents the integer 4321. 
```

## 解题思路

没什么可说的，纯粹模拟加法操作

## 相关题目

## 代码

```java
public class Plus_One {

    public int[] plusOne(int[] digits) {
        int carry = 1;

        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i] = digits[i] + carry;      //对于最低位来说, 相当于后一位有一个进位
            if (digits[i] == 10) {
                digits[i] = 0;
                carry = 1;
            } else {
                carry = 0;
            }
        }

        if(carry == 1) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            for (int i = 1; i < result.length; i++) {
                result[i] = 0;
            }
            return result;
        } else {
            return digits;
        }
    }

}
```

