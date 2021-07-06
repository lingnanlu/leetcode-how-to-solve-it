# Two Sum II Input array is sorted
**link** : https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/

**level** : EASY
## 一 What the question is ?
<p>Given an array of integers <code>numbers</code> that is already <strong><em>sorted in non-decreasing order</em></strong>, find two numbers such that they add up to a specific <code>target</code> number.</p>

<p>Return<em> the indices of the two numbers (<strong>1-indexed</strong>) as an integer array </em><code>answer</code><em> of size </em><code>2</code><em>, where </em><code>1 &lt;= answer[0] &lt; answer[1] &lt;= numbers.length</code>.</p>

<p>The tests are generated such that there is <strong>exactly one solution</strong>. You <strong>may not</strong> use the same element twice.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> numbers = [2,7,11,15], target = 9
<strong>Output:</strong> [1,2]
<strong>Explanation:</strong> The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> numbers = [2,3,4], target = 6
<strong>Output:</strong> [1,3]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> numbers = [-1,0], target = -1
<strong>Output:</strong> [1,2]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= numbers.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-1000 &lt;= numbers[i] &lt;= 1000</code></li>
	<li><code>numbers</code> is sorted in <strong>non-decreasing order</strong>.</li>
	<li><code>-1000 &lt;= target &lt;= 1000</code></li>
	<li>The tests are generated such that there is <strong>exactly one solution</strong>.</li>
</ul>

## 二 How to solve it ?
这一题和Two Sum很像, 唯一的不同就是是排序了的.

如果是排序的, 自然也可以使用二分法.

但既然是排序的, 是不是可以利用这个有序的性质呢?

画一画图, 举个例子.

这题说难不难, 说简单不简单, 其实你要明白为什么两头夹逼就可以起作用, 其实是需要数学证明的.


## 三 Related questions

## 四 Solutions 
### 1 夹逼方法



```java
public int[] twoSum(int[] numbers, int target) {
    int i = 0, j = numbers.length - 1;

    while (i != j) {
        int sum = numbers[i] + numbers[j];
        if(sum == target) {
            return new int[]{i + 1, j + 1};
        } else if (sum < target) {
            i++;
        } else {
            j--;
        }
    }

    return new int[]{-1, -1};
}

```
