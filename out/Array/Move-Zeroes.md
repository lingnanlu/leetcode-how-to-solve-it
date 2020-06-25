#Move Zeroes

https://leetcode.com/problems/move-zeroes

### 问题描述

<p>Given an array <code>nums</code>, write a function to move all <code>0</code>&#39;s to the end of it while maintaining the relative order of the non-zero elements.</p>

<p><b>Example:</b></p>

<pre>
<b>Input:</b> <code>[0,1,0,3,12]</code>
<b>Output:</b> <code>[1,3,12,0,0]</code></pre>

<p><b>Note</b>:</p>

<ol>
	<li>You must do this <b>in-place</b> without making a copy of the array.</li>
	<li>Minimize the total number of operations.</li>
</ol>
### 解题思路

其实这种题和Remove Element, Remove Duplicates From Sorted Array一样，就是要求原地对数组进行一些操作。

如果你原地不好解决，可以先使用一个新数组，看如何操作的，然后再原地就行了。

一般这种题就是O(n)的复杂度

### 相关题目


### 代码

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
