# Remove Duplicates from Sorted Array II
**link** : https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/

**level** : EASY
## 一 What the question is ?
<p>Given an integer array <code>nums</code> sorted in <strong>non-decreasing order</strong>, remove some duplicates <a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank"><strong>in-place</strong></a> such that each unique element appears <strong>at most twice</strong>. The <strong>relative order</strong> of the elements should be kept the <strong>same</strong>.</p>

<p>Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the <strong>first part</strong> of the array <code>nums</code>. More formally, if there are <code>k</code> elements after removing the duplicates, then the first <code>k</code> elements of <code>nums</code>&nbsp;should hold the final result. It does not matter what you leave beyond the first&nbsp;<code>k</code>&nbsp;elements.</p>

<p>Return <code>k</code><em> after placing the final result in the first </em><code>k</code><em> slots of </em><code>nums</code>.</p>

<p>Do <strong>not</strong> allocate extra space for another array. You must do this by <strong>modifying the input array <a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank">in-place</a></strong> with O(1) extra memory.</p>

<p><strong>Custom Judge:</strong></p>

<p>The judge will test your solution with the following code:</p>

<pre>
int[] nums = [...]; // Input array
int[] expectedNums = [...]; // The expected answer with correct length

int k = removeDuplicates(nums); // Calls your implementation

assert k == expectedNums.length;
for (int i = 0; i &lt; k; i++) {
    assert nums[i] == expectedNums[i];
}
</pre>

<p>If all assertions pass, then your solution will be <strong>accepted</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,2,2,3]
<strong>Output:</strong> 5, nums = [1,1,2,2,3,_]
<strong>Explanation:</strong> Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,0,1,1,1,1,2,3,3]
<strong>Output:</strong> 7, nums = [0,0,1,1,2,3,3,_,_]
<strong>Explanation:</strong> Your function should return k = 7, with the first seven elements of nums being 0, 0, 1, 1, 2, 3 and 3 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>nums</code> is sorted in <strong>non-decreasing</strong> order.</li>
</ul>

## 二 How to solve it ?

Q. 你之前做过一个类似的问题**Remove Duplicates from Sorted Array**，现在这个和原来的有什么区别?

A. 原来只是要求最多出现一次, 这个要求最多出现两次

Q. 现在有一个问题和你现在要解决的类似, 你能利用它的方法么?

A. 应该不难，只要在复制动作发生的时候做一些处理就行，试一试

Q. 回顾你的解法，你能不能把你的方法用到其它类似的问题上来

A. 这两个题其实可以使用同一个框架来解决，不同的是，要满足复制的条件不同，所以，可以抽象出一个canCopy条件，检查当前状态是否可以进行手复制。

## 三 Related questions

## 四 Solutions 
### 1 更优雅，可以一眼看出思路，很清晰



```java
        public int removeDuplicates(int[] nums) {

            // 起个别名
            int[] newNums = nums;

            // 注意这里也是使用的循环不变式
            int i = 0;  // 指向原数组中下一个要copy的元素
            int j = 0;  // 指向新数组中下一个空位

            while(i != nums.length) {

                //要改变的地方就是这里，更一般的理解就是，要满足一个复制条件。
                if(canCopy(nums, j, nums[i])) {
                    nums[j] = nums[i];
                    // 这里，i，j的含义发生变化了，所以要移动两者，让其恢复到本来的含义。
                    i++;
                    j++;
                } else {
                    // 不进行复制, 所以i要指向下一个元素
                    i++;
                }
            }

            //[0,j)为newNums中的元素，newNums.length == j,所以j就是新数组的长度。
            return j;
        }

        private boolean canCopy(int[] nums, int size, int n) {

            if(size == 0 || size == 1) return true;

            if (nums[size - 1] != n) {   // 前一个是否相等
                return true;
            } else if(nums[size - 2] != n) { // 往前第二个是否相等
                return true;
            } else {                     // 已经有两个相等的了, 不再复制
                return false;
            }
        }
    }
```
