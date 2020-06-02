# Single Number

[https://leetcode.com/problems/single-number/](https://leetcode.com/problems/single-number/)

**问题描述**

Given a **non-empty** array of integers, every element appears twice except for one. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:

```text
Input: [2,2,1]
Output: 1
```

Example 2:

```text
Input: [4,1,2,1,2]
Output: 4
```

**解题思路**


### step 1

    asdfasdf

### step 2

    asdfqwer

### step 3

    asdfasdf



**代码**

```java
public class Single_Number {


    public int singleNumber(int[] nums) {

        int result = 0;

        for (int n : nums) {
            result = result ^ n;
        }

        return result;
    }
}
```

