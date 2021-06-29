# Remove Duplicates from Sorted Array
**link** : https://leetcode.com/problems/remove-duplicates-from-sorted-array/
**level** : EASY
## 一 What the question is ?
<p>Given an integer array <code>nums</code> sorted in <strong>non-decreasing order</strong>, remove the duplicates <a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank"><strong>in-place</strong></a> such that each unique element appears only <strong>once</strong>. The <strong>relative order</strong> of the elements should be kept the <strong>same</strong>.</p>

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
<strong>Input:</strong> nums = [1,1,2]
<strong>Output:</strong> 2, nums = [1,2,_]
<strong>Explanation:</strong> Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,0,1,1,1,2,2,3,3,4]
<strong>Output:</strong> 5, nums = [0,1,2,3,4,_,_,_,_,_]
<strong>Explanation:</strong> Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>nums</code> is sorted in <strong>non-decreasing</strong> order.</li>
</ul>

## 二 How to solve it ?

Q. 已知是什么? 未知是什么, 或者说要求的是什么?

A. 已知是一个升序数组, 要求的是将该数组中的重复元素去掉, 剩下的元素没有重复的.

Q. 限制是什么？

A.

1. 空间上要O(1),in-place,不能使用额外的空间
2. 时间上没要求
3. 结果放在数组中的前k个位置

Q. 如果你现在想不出来一个解，如果想不出来, 你能不能放松一下限制？你觉得哪个限制比较禁锢你的思路?

A.额。。。。

q.好吧，还是想不出来哈？现在我们有了一个新的问题，就是创建一个新的数组，但是不能包括重复元素。现在这个问题你还是解不出来。
那么再简化一下，只是复制数组，可以包括重复元素。这个能解决么？

a.想了想，写出

q.现在，你知道如何复制一个数组了，但是我们要添加一个限制，就是不能有重复元素，也就是在复制的时候，要过滤掉重复元素，你能做到么？
试一试

a.想了想，还是不知道。。。

q.你看，在你之前的代码中，i是什么，j又是什么，你能清晰的说出它们的含义么？我们知道，变量的命名是需要准确的，你在这里
你在这里使用了，i，j，可以更准确一些么？修改一下变量名称，或者，添加注释。你能清楚的描述每一步的作用是做什么的么？

a.写出了。。。

q.好的，现在，你对之前自己写的代码有了更清晰的理解，而不是依靠惯性（惯性就是不加思考）的蒙出答案，在此基础上，再去过滤掉重复元素可以么？
也就是在复制的时候，如果是重复的，就不要复制了。

a.写出了答案。

q.很好，现在你已经解决了这个问题，离最终答案不远了，你现在解决了一个类似的问题，你能利用它的结果吗？显然不能，你能利用它的方法么？
就是使用两个指针来复制，之前你有两个数组，现在，你只有一个数组，在一个数组上运用两个指针可不可以？试一试。拿草稿纸写一写。

a.有点激动，似乎可行。。。发现只是简单的去掉newNums就行了，eraka!


## 三 Related questions
## 四 Solutions 
### 1 简单的复制数组

null

```java
        public int[] duplicates(int[] nums) {
            int[] newNums = new int[nums.length];

            //[0, nums.length)
            for (int i = 0, j = 0; i < nums.length; i++, j++) {

                //真正的复制
                newNums[j] = nums[i];
            }

            return newNums;
        }
    }
```
### 2 简单的复制数组, 更好的命名

null

```java
        public int[] duplicatesBetterName(int[] nums) {

            int[] newNums = new int[nums.length];

            // 注意，这两个变量的含义不应该在程序运行期间改变
            int i = 0;  //指向nums中下一个要复制的元素的index.
            int j = 0;  //指向newNums中下一个要填充元素的index.

            /**
             *   while 比do while更安全，因为它在事情真正执行前（事情的真正执行可能带来问题
             *   要避免问题的最佳方式就是在事情真正开始执行前，检查事情是否能真正的开始。
             *   这里的例子就是，如果i== nums.length，所以没有可以要复制的元素，也就没必要进行复制的动作了。
             */
            while(i != nums.length) {

                // 真正的复制动作
                newNums[j] = nums[i];

                // 这里，i，j的含义发生变化了，所以要移动两者，让其恢复到本来的含义。
                i++;
                j++;

                // 现在要考虑的问题是，要不要继续复制下去。由i的含义可知，当i所指的元素是最后一个的下一个是，应该不再进行复制，所以
            }

//        do {
//            // 真正的复制动作
//            newNums[j] = nums[i];
//
//            // 这里，i，j的含义发生变化了，所以要移动两者，让其恢复到本来的含义。
//            i++;
//            j++;
//
//            // 现在要考虑的问题是，要不要继续复制下去。由i的含义可知，当i所指的元素是最后一个的下一个是，应该不再进行复制，所以
//        } while(i != nums.length);             // 这里控制着，是否要继续下去，当i == nums.length时，说明已经复制完了。


            /**
             * do while和while这里有个区别就是
             *
             * do while相关于先让事情可以进行，然后判断什么时候要终止
             *
             * 而while比do while要好的一点就是，它不仅仅能判断什么时候要终止，而且可以判断是否满足条件来开始，这就相关于多了一层检查，所以更安全。
             *
             * 所以要从开始，执行，结束的角度来考虑这两种循环。
             */

            return newNums;
        }
    }
```
### 3 使用新数组去掉重复元素

null

```java
        public int removeDuplicates(int[] nums) {

            int[] newNums = new int[nums.length];

            int i = 0;  //指向nums中下一个要复制的元素的index.
            int j = 0;  //指向newNums中下一个要填充元素的index.

            while(i != nums.length) {

                //要改变的地方就是这里在复制之前，要做重复性检查
                if(
                        j == 0                              // 说明newNums还没有元素，自然不会重复
                                || nums[i] != newNums[j - 1]        // 说明将要复制的元素可newNums中最后一个元素不相等
                ) {
                    newNums[j] = nums[i];
                    // 这里，i，j的含义发生变化了，所以要移动两者，让其恢复到本来的含义。
                    i++;
                    j++;
                } else {
                    // 不进行复制，说明元素重复了，所以i要指向下一个元素
                    i++;
                }
            }

            //[0,j)为newNums中的元素，newNums.length == j,所以j就是新数组的长度。
            return j;
        }
    }
```
### 4 原地去除重复元素

null

```java
        public int removeDuplicates(int[] nums) {

            int i = 0;  //指向nums中下一个要复制的元素的index.
            int j = 0;  //指向newNums中下一个要填充元素的index.

            while(i != nums.length) {

                //要改变的地方就是这里在复制之前，要做重复性检查
                if(
                        j == 0                              // 说明newNums还没有元素，自然不会重复
                                || nums[i] != nums[j - 1]           // 说明将要复制的元素可newNums中最后一个元素不相等
                ) {
                    nums[j] = nums[i];
                    // 这里，i，j的含义发生变化了，所以要移动两者，让其恢复到本来的含义。
                    i++;
                    j++;
                } else {
                    // 不进行复制，说明元素重复了，所以i要指向下一个元素
                    i++;
                }
            }

            //[0,j)为newNums中的元素，newNums.length == j,所以j就是新数组的长度。
            return j;
        }
    }
```
### 5 思路不清晰的答案

null

```java
        public int removeDuplicates(int[] nums) {

            if (nums.length == 0) {
                return 0;
            }

            int cur = 0;            //指向结果
            int result_len = 1;     //结果已经有一个元素了

            //从第2个元素开始迭代nums
            int i = 1;
            while(i < nums.length) {
                //找到与cur所指向的元素不同的元素
                while (i < nums.length && nums[i] == nums[cur] ) i++;

                //此时, i所指向的元素与cur的不同
                if (i < nums.length) {
                    nums[++cur] = nums[i];
                    result_len++;
                    i++;
                } else {
                    break;
                }
            }

            return result_len;
        }
    }
```
