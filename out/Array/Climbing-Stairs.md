#Climbing Stairs

https://leetcode.com/problems/climbing-stairs

### 问题描述

<p>You are climbing a stair case. It takes <em>n</em> steps to reach to the top.</p>

<p>Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?</p>

<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 45</code></li>
</ul>

### 解题思路

这种可以举几个例子，找出规律，然后发现是数学归纳法

这种一般两种思路

一是递归

二是迭代

注意迭代时，可能不需要保存历史数据，这样节约空间

### 相关题目


### 代码

```java
public class Climb_Stair {

    @Solution("简单的递归")
    static class Recursive {
        public int climbStairs(int n) {

            if (n == 0) {
                return 0;
            }

            if (n == 1) {
                return 1;
            }

            if (n == 2) {
                return 2;
            }

            //仔细想想, 有的算了很多遍, 重复了, 所以, 可以把中间结果保存下来, 优化代码.
            return climbStairs(n - 1) + climbStairs(n - 2);
        }
    }


    @Solution("迭代")
    static class Iterate {
        public int climbStairs(int n) {

            if (n == 0) return 0;
            if (n == 1) return 1;
            if (n == 2) return 2;

            int[] cache = new int[n];

            cache[0] = 0;
            cache[1] = 1;
            cache[2] = 2;

            for (int i = 3; i < cache.length; i++) {
                cache[i] = cache[i - 1] + cache[i - 2];
            }
            return cache[n];
        }
    }

    @Solution("迭代，因为只用到前两次的数据，所以可以不保留历史数据")
    static class IterateWithoutExtraSpace {
        public int climbStairs(int n) {

            if (n == 0) return 0;
            if (n == 1) return 1;
            if (n == 2) return 2;

            int one = 1;
            int two = 2;

            int result = 0;

            for (int i = 3; i <= n ; i++) {
                result = one + two;
                one = two;
                two = result;
            }

            return result;
        }
    }

}
```