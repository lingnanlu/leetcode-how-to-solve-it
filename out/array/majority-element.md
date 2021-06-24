# Majority Element

[https://leetcode.com/problems/majority-element](https://leetcode.com/problems/majority-element)

## 问题描述

Given an question.array of size n, find the majority element. The majority element is the element that appears **more than** `⌊ n/2 ⌋` times.

You may assume that the question.array is non-empty and the majority element always exist in the question.array.

**Example 1:**

```text

Input: [3,2,3]
Output: 3
```

**Example 2:**

```text

Input: [2,2,1,1,1,2,2]
Output: 2
```

## 解题思路

1. 最简单直观的就是统计各个元素的数量，但这个需要额外空间
2. 排序后中间就是，但这个时间复杂度是O\(nlogn\)
3. 有没有一种不需要额外空间，但又是O\(n\)的方法呢？

其实有的，有一个科学家给出了答案

[http://www.cs.utexas.edu/~moore/best-ideas/mjrty/](http://www.cs.utexas.edu/~moore/best-ideas/mjrty/)

你之所以靠自己想不出来，很正常，你要是能想出来就是天才了。

> If the poster could really be that smart to figure out this algorithm by him/her-self, this would be a top top conference paper and bring him/her to a professor position. Absolutely no need to struggle coding here.

Should cite the source:

Boyer-Moore Majority Vote Algorithm

[http://www.cs.utexas.edu/~moore/best-ideas/mjrty/](http://www.cs.utexas.edu/~moore/best-ideas/mjrty/)

所以，不必为这感到难过。

## 相关题目

## 代码

```java
public class Majority_Element {
    public int majorityElement(int[] nums) {
        int major=nums[0], count = 1;
        for(int i=1; i<nums.length;i++){
            if(count==0){
                count++;
                major=nums[i];
            }else if(major==nums[i]){
                count++;
            }else count--;

        }
        return major;
    }
}
```

