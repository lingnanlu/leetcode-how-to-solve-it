#3Sum Closest

https://leetcode.com/problems/3sum-closest

### 问题描述

<p>Given an array <code>nums</code> of <em>n</em> integers and an integer <code>target</code>, find three integers in <code>nums</code>&nbsp;such that the sum is closest to&nbsp;<code>target</code>. Return the sum of the three integers. You may assume that each input would have exactly one solution.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,2,1,-4], target = 1
<strong>Output:</strong> 2
<strong>Explanation:</strong> The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10^3</code></li>
	<li><code>-10^3&nbsp;&lt;= nums[i]&nbsp;&lt;= 10^3</code></li>
	<li><code>-10^4&nbsp;&lt;= target&nbsp;&lt;= 10^4</code></li>
</ul>

### 解题思路

q 你之前遇到过类似的问题么

a a + b + c = target

q 两者有什么不同？

a 之前是相等，这里的最接近

q 能不能用数学语言描述这种最接近

a 就是  |a + b + c - target| 越小越好。

q 你能利用a + b + c = b 的思路么，

a 我想可以，就是左右夹逼，然后判断gap，找到最小的gap就行。

### 相关题目


### 代码

```java
public class Three_Sum_Closet {

    static class Right {
        public int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums);

            int closetGap = Integer.MAX_VALUE;
            int closetSum = 0;

            for (int i = 0; i <= nums.length - 3; i++) {

                for(int j = i + 1, k = nums.length - 1; j < k;) {

                    int sum = nums[i] + nums[j] + nums[k];
                    int gap = Math.abs(sum - target);

                    if (gap < closetGap) {
                        closetGap = gap;
                        closetSum = sum;
                    }

                    if (sum < target) j++;
                    else k--;
                }

            }

            return closetSum;
        }

    }
    static class Wrong {
        // 这里的做法是错误的，数轴上的从一个方向逼近就是一种错误的想法，并不是从一个方向进行逼近，而是一会从左，一会从右。
        public int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums);

            int minGap = Integer.MAX_VALUE;                 // 记录全局的最小gap
            int minSum = 0;                                 // 记录全局最小gap的值。
            boolean find = false;                           // 如果找到a + b + c = target，则设置find = true，不再迭代下去

            for (int i = 0; i <= nums.length - 3; i++) {

                /**
                 * 你可以想像，一个数轴，target就是这个数轴上的点，而a + b则是从一个从向不断趋进于该点的另一个点，而gap无非就是
                 * 一个不断缩小然后不断扩大的过程。
                 *
                 * 以上想法是错误的，不是一个不断逼近的过程。是左右往复的过程。
                 *
                 * 比如在趋进过程中，当前点的gap比上一个要远，即上一个就是最小的gap了。
                 *
                 * lastGap ： 本轮上一次的gap
                 * lastSum ： 本轮上一次的sum
                 *
                 * 当跳出循环时，lastGap和lastSum保留的就是本轮最小的sum和gap了。
                 */
                int lastGap = Integer.MAX_VALUE;            // 本轮上一次的gap
                int lastSum = 0;                            // 本轮最小gap对应的sum
                for(int j = i + 1, k = nums.length - 1; j < k;) {

                    int sum = nums[i] + nums[j] + nums[k];

                    //当前gap
                    int gap = Math.abs(sum - target);

                    // 则对于本轮来说不需要再逼进下去了。
                    // lastGap 就是本轮的最小Gap

                    if(gap == 0) {
                        find = true;
                        break;
                    } if(gap > lastGap) {
                        break;
                    } else {         // 继续本轮的夹逼。
                        lastSum = sum;
                        lastGap = gap;

                        if(sum > target){
                            k--;
                        } else {
                            j++;
                        }
                    }
                }

                // 如何找到和target一样的，就不用再继续下去了。
                if (find == true) {
                    minGap = 0;
                    minSum = target;
                    break;
                } else if (lastGap < minGap) {
                    minGap = lastGap;
                    minSum = lastSum;
                }

            }

            return minSum;
        }

    }
}
```
