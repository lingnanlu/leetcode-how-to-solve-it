#Contains Duplicate II

https://leetcode.com/problems/contains-duplicate-ii

### 问题描述

<p>Given an array of integers and an integer <i>k</i>, find out whether there are two distinct indices <i>i</i> and <i>j</i> in the array such that <b>nums[i] = nums[j]</b> and the <b>absolute</b> difference between <i>i</i> and <i>j</i> is at most <i>k</i>.</p>

<div>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input: </strong>nums = <span id="example-input-1-1">[1,2,3,1]</span>, k = <span id="example-input-1-2">3</span>
<strong>Output: </strong><span id="example-output-1">true</span>
</pre>

<div>
<p><strong>Example 2:</strong></p>

<pre>
<strong>Input: </strong>nums = <span id="example-input-2-1">[1,0,1,1]</span>, k = <span id="example-input-2-2">1</span>
<strong>Output: </strong><span id="example-output-2">true</span>
</pre>

<div>
<p><strong>Example 3:</strong></p>

<pre>
<strong>Input: </strong>nums = <span id="example-input-3-1">[1,2,3,1,2,3]</span>, k = <span id="example-input-3-2">2</span>
<strong>Output: </strong><span id="example-output-3">false</span>
</pre>
</div>
</div>
</div>

### 解题思路


1. 利用Contains Duplicate的方法，只是之前使用Set,而这里要保存index,所以使用Map
2. 也可以先排序，但因为排了序后，丢失了位置信息，所以要先改变一个结构，再排序。但这个排序需要保持相同元素在原序列中的相对顺序。
即排序是要稳定的。

以上都是利用了Contains Duplicate的思想

### 相关题目

1. Contain Duplicate

### 代码

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
