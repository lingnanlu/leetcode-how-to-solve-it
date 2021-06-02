#Trapping Rain Water

https://leetcode.com/problems/trapping-rain-water

### 问题描述

<p>Given <em>n</em> non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.</p>

<p><img src="https://assets.leetcode.com/uploads/2018/10/22/rainwatertrap.png" style="width: 412px; height: 161px;" /><br />
<small>The above elevation map is represented by question.array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. <strong>Thanks Marcos</strong> for contributing this image!</small></p>

<p><strong>Example:</strong></p>

<pre>
<strong>Input:</strong> [0,1,0,2,1,0,1,3,2,1,2,1]
<strong>Output:</strong> 6</pre>

### 解题思路

q 有没有见过类似的题？

a 没有

q 那这个题的已知是什么，未知是什么？

a 已知是一个数组，未知是由该数组计算出某个值来。

q 好，既然这个题对你来说是完全一个新的题，即，你没有相关的经验，那么，我们来做这个完全新的题。

这里给你一个例子，比如说，[0,1,0,2,1,0,1,3,2,1,2,1], 结果是6.

你能形象的想象一下么？

a 就想象成一个坑吧，中间低，两头高。

q 你观察这个数组，那些子序列可以存水。

a [1, 0, 2], [2, 1, 0, 1, 3], [2, 1, 2]

q 它们的特点是什么？

a 中间低，两头小

q 你是如何通过这个序列计算出最后的值的？

a

[1, 0, 2] => 1
[2, 1, 0, 1, 3] => 3 * 2 - 2 = 4
[2, 1, 2] => 1 * 2 - 1 = 1

规则就是，宽 * 高 - 中间的 高就是两头中较小的

q 好了，现在，我们的问题其实发生了一些改变，原来的问题现在转化成了一个数学问题，你能再描述一遍么?

a 给定一个数组，找出所有满足以上条件的子序列，并基于该子序列，计算出一个值来。

q 好，我们先试着找出一个，然后再说所有。你能不能试着找出一个来，比如说，第一个

a 我想应该可以，就是找到两个边界就行。我试试

q 你找到valley，包话[4, 2, 3]这种么？再比如[3, 0, 1, 0, 1, 0, 2]

a 嗯，好像代码不对，但是我发现，找valley的代码不好写啊。

q 其实从另一个角度来看valley,我们能不能找峰？找到两个山峰是不是也可以？

a 如果使用比两边高的方式来找山峰的话，比如[0, 3, 0, 1, 0, 1, 2], 其实要找的是3， 2，但比两边高的方式找到了1， 1，这样也
不行。

q 其实我们无论是找valley,还是找peak,难点就是找到两个边界. 而之所以之前的方式不行，是因为你对valley的定义有问题，它真的是
两边高，中间低么？ 你再看看它有什么特征，为什么人眼一眼能看出来的，但你却不能表述出来。


### 相关题目


### 代码

```java
public class Trapping_Rain_Water {

    // 其实最简单的想法就是, 对于每个柱子, 左右找到最高的柱子, 该柱子上方所能存的水量就是, min(maxLeft, maxRight) - height
    // 复杂度是O(n^2)

    // 寻找valley的思路，是错误的，因为这个代码，不能处理[3, 0, 1, 0 , 1, 0, 2]这种
    public int[] findValley(int[] height) {
        for (int i = 0; i < height.length; i++) {

            int j = i + 1;
            while(j < height.length && height[j] < height[i]) j++;

            if(j < height.length) {
                // 找到了右边界
                return new int[]{i, j};
            }
        }

        // 不存在valley
        return new int[]{-1, -1};
    }

    // 换个思路找山峰，山峰好找，只要比两头高就行。对于左边界来说，是比右边高，右边界来说，是比左边高
    public

    @Solution("找到所有Valley，然后加起来, 这种方式是错误的，因为使用这种方式找到的valley，并不包括[4, 2, 3]这种")
    static class SumValley {
        public int trap(int[] height) {

            int sum = 0;
            for (int i = 0; i < height.length;) {

                int j = i + 1;

                while(j < height.length && height[j] < height[i]) j++;

                if(j < height.length) {
                    // 找到了一个valley, 是[i, j], 然后计算出存水量
                    // 存水量 = 宽 * 高 - 中间的。
                    int minus = 0;
                    for (int k = i + 1; k <= j - 1; k++) {
                        minus += height[k];
                    }

                    sum += (j - i - 1) * Math.min(height[i], height[j]) - minus;

                    i = j;
                } else {

                    i++;
                }

            }
            return sum;
        }
    }

    public static void main(String[] args) {
        SumValley sumValley = new SumValley();
        sumValley.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1});
    }
    public int trap(int[] height) {

        int sum = 0;
        for(int i = 1; i < height.length - 1; i++) {

            // 向左找最高的
            int maxLeft = height[0];
            for(int j = 0; j < i; j++) {
                if(height[j] > maxLeft) {
                    maxLeft = height[j];
                }
            }

            int maxRight = height[height.length - 1];
            for(int k = height.length - 1; k > i; k--) {
                if(height[k] > maxRight) {
                    maxRight = height[k];
                }
            }

            if (Math.min(maxLeft, maxRight) > height[i]) {
                sum += Math.min(maxLeft, maxRight) - height[i];
            }

        }

        return sum;
    }


    // 一可以优化一下, 时间主要浪费在找左右最高, 可以缓存一些部分结果.
    public int trap2(int[] height) {

        int[] maxLeft = new int[height.length];     //maxLeft[i]是柱子i的左边最高的柱子高度
        int[] maxRight = new int[height.length];    //maxRigth[i]是柱子i右边的最高的柱子高度

        //先找左边最高
        for (int i = 1; i < height.length; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i - 1]);
        }

        for (int j = height.length - 2; j >= 0; j--) {
            maxRight[j] = Math.max(maxRight[j + 1], height[j + 1]);
        }

        int sum = 0;
        for(int i = 1; i < height.length - 1; i++) {
            if(Math.min(maxLeft[i], maxRight[i]) > height[i]) {
                sum += Math.min(maxLeft[i], maxRight[i]) - height[i];
            }
        }

        return sum;
    }
}
```
