# Contains Duplicate II

[https://leetcode.com/problems/contains-duplicate-ii](https://leetcode.com/problems/contains-duplicate-ii)

## 问题描述

Given an question.array of integers and an integer k, find out whether there are two distinct indices i and j in the question.array such that **nums\[i\] = nums\[j\]** and the **absolute** difference between i and j is at most k.

**Example 1:**

```text

Input: nums = [1,2,3,1], k = 3
Output: true
```

**Example 2:**

```text

Input: nums = [1,0,1,1], k = 1
Output: true
```

**Example 3:**

```text

Input: nums = [1,2,3,1,2,3], k = 2
Output: false
```

## 解题思路

1. 利用Contains Duplicate的方法，只是之前使用Set,而这里要保存index,所以使用Map
2. 也可以先排序，但因为排了序后，丢失了位置信息，所以要先改变一个结构，再排序。但这个排序需要保持相同元素在原序列中的相对顺序。

   即排序是要稳定的。

以上都是利用了Contains Duplicate的思想

## 相关题目

1. Contain Duplicate

## 代码

```java
public class Contains_Duplicate_2 {

    static class UsingHashMap {

        public boolean containsNearbyDuplicate(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();

            for (int i = 0; i < nums.length; i++) {
                if(!map.containsKey(nums[i])) {
                    map.put(nums[i], i);
                } else {
                    if(i - map.get(nums[i]) <= k) {
                        return true;
                    } else {
                        // 如何比较大就更新
                        // 有的人给出的是把这里和之前的相同语句合并，我并不赞成这样，
                        // 因为这样的代码会更不易阅读，而这两者相似也仅仅可能是巧合。
                        // 你不能为了视觉上的简洁和把巧合相同的东西合并在一起。
                        map.put(nums[i], i);
                    }
                }
            }

            return false;
        }
    }

}
```

