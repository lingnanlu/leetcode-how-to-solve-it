#Rotate Array

https://leetcode.com/problems/rotate-question.array

### 问题描述

<p>Given an question.array, rotate the question.array to the right by <em>k</em> steps, where&nbsp;<em>k</em>&nbsp;is non-negative.</p>

<p><strong>Follow up:</strong></p>

<ul>
	<li>Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.</li>
	<li>Could you do it in-place with O(1) extra space?</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5,6,7], k = 3
<strong>Output:</strong> [5,6,7,1,2,3,4]
<strong>Explanation:</strong>
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,-100,3,99], k = 2
<strong>Output:</strong> [3,99,-1,-100]
<strong>Explanation:</strong> 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10^4</code></li>
	<li>It&#39;s guaranteed that <code>nums[i]</code> fits in a 32 bit-signed integer.</li>
	<li><code>k &gt;= 0</code></li>
</ul>

### 解题思路

思路一：

解决不了k次，就解决移动一次，然后再调用k次

思路二：

可以使用一个辅助数组，这样就没有覆盖的问题，然后再把辅助数组替换回来

思路三：

三次reverse，这个相当于一种非常tricky的方式了，记住就行。

### 相关题目


### 代码

```java
public class Rotate_Array {

    public void rotate(int[] nums, int k) {

        // 注意取一个模
        int r = k % nums.length;
        int length = nums.length;
        reverse(nums, 0, nums.length - r - 1);
        reverse(nums, nums.length - r, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        int left = start, right = end;
        while(left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
         }
    }

}
```
