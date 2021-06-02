#Contains Duplicate

https://leetcode.com/problems/contains-duplicate

### 问题描述

<p>Given an question.array of integers, find if the question.array contains any duplicates.</p>

<p>Your function should return true if any value appears at least twice in the question.array, and it should return false if every element is distinct.</p>

<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> [1,2,3,1]
<strong>Output:</strong> true</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input: </strong>[1,2,3,4]
<strong>Output:</strong> false</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input: </strong>[1,1,1,3,3,4,3,2,4,2]
<strong>Output:</strong> true</pre>

### 解题思路


1. 最直观的依然是使用一个Set来保存出现过的元素。
2. 另一种方式是排序，然后出现连接相同的就行。

其实关于数组有一种通用的尝试的方式，就是

如果乱序不好解决，那么，先排个序，改变一个数据结构，再看看有没有更好的方式，


### 相关题目

1. Majority Element

### 代码

```java
public class Contains_Duplicate {

    public boolean containsDuplicate(int[] nums) {

        if (nums.length == 0) return false;
        // 使用set来检查一个元素有没有出现过
        Set<Integer> s = new HashSet<>(nums.length);

        s.add(nums[0]);

        for(int i = 1; i < nums.length; i++) {
            if(s.contains(nums[i])) {
                return true;
            } else {
                s.add(nums[i]);
            }
        }

        return false;
    }

}
```
