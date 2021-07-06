# Two Sum
**link** : https://leetcode.com/problems/two-sum/

**level** : EASY
## 一 What the question is ?
<p>Given an array of integers <code>nums</code>&nbsp;and an integer <code>target</code>, return <em>indices of the two numbers such that they add up to <code>target</code></em>.</p>

<p>You may assume that each input would have <strong><em>exactly</em> one solution</strong>, and you may not use the <em>same</em> element twice.</p>

<p>You can return the answer in any order.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,7,11,15], target = 9
<strong>Output:</strong> [0,1]
<strong>Output:</strong> Because nums[0] + nums[1] == 9, we return [0, 1].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,4], target = 6
<strong>Output:</strong> [1,2]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,3], target = 6
<strong>Output:</strong> [0,1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= target &lt;= 10<sup>9</sup></code></li>
	<li><strong>Only one valid answer exists.</strong></li>
</ul>

<p>&nbsp;</p>
<strong>Follow-up:&nbsp;</strong>Can you come up with an algorithm that is less than&nbsp;<code>O(n<sup>2</sup>)&nbsp;</code>time complexity?
## 二 How to solve it ?
Q. 问题是什么？列出已知, 未知, 还有条件与限制

A.

已知

1. 一个整数数组
2. 该数组是无序的
3. 数组元素也不唯一
4. 一个整数target

求

返回两个元素的index, 其和就是该target

Q. 你能想出一种答案么？ 如果不考虑时间和空间复杂度的话

A. 把所有两个数的可能穷举出来，找到结果就返回.

Q. ok, 你给出的答案是显而易见的，但是，它的时间复杂度是0(n^2).再想想，有没有更好的方式？

A. ...

Q. 你看，你现在对于本问题没有什么思路，可不可以简化一下问题？你遇到过类似的问题么？比如，假如只找一个数呢？

A. 只找一个数的话，就相当于从一个数组中找到一个数，该数和给定的数相等。

Q. 好的，这个问题你能解决么？

A. 很简单，遍历一遍数组就行。时间复杂为0(n)

Q. 能不能优化一下？再看一下这是一个什么问题？

A. 啊，就是一个查找问题，我可以使用查找相关的算法。

Q. 好的，那么怎么快速找到一个元素呢？你知道有哪些方法？

A. 我想想，适用于数组的查找算法有

    1. 顺序查找
    2. 二分查找

Q. 好的，现在有一个简化的问题，在一个数组中查找一个元素，和给定的元素相等，这个问题和你当前要解决的问题有关么？
你能将当前的问题转化成一个简单的问题么？

A. 当前问题是要求2个数的和等于一个给定值，而简化问题相当于求一个数的和等于一个给定值。那么如果我能确定其中一个数，就可以转化成一个简化问题了，
等等，简化问题是一个查找一个数的问题，查找一个数，我可以使用二分查找，等等，我好像有一个可以优化时间复杂度的方案了

A. 等等，如果排序的话，原始位置就变了，所以在排序之前，要把原始位置也记录下来，所以，还要改进一下

Q. 现在，你的时间复杂度变成了O(nlogn), 比暴力要好, 因为要使用二分查找, 打乱了原来顺序, 为了保存原来顺序, 又使用了Tuple来记录, 这又使用了额外空间.
所以, 如果不打乱顺序, 就是在一个乱序的数组中快速定位到一个元素, 你知道方法么.

A. 从一堆数中，判断一个数是不是存在... 我想想, 可以使用Hash表

Q. 你能回顾一下Hash表的原理么?

A. Hash表一般就是将一个key和一个value进行关联, 而Hash的原理往往就是能快速的判断和定位一个key是否在表里, 而为什么能这么快呢? 它其实不是将key与表里的元素一个个
比对, 而是先使用hash(key) = int 计算出一个整形值, 这个整形值往往就是Hash表的下标, 而计算机可以随机读取这个下标. 所以通过判断该下标位置的元素就可以知道该元素在不在了.


Q. 那你应该怎么做呢?

A. 嗯, 先创建一个hash表, 再去判断.




## 三 Related questions

## 四 Solutions 
### 1 暴力穷举方法



```java
public int[] twoSum(int[] nums, int target) {

    for (int i = 0; i <= nums.length - 2; i++) {
        int a = nums[i];
        int b = target - a;
        for (int j = i + 1; j <= nums.length - 1; j++) {
            if(nums[j] == b) {
                return new int[]{i, j};
            }
        }
    }
    return new int[]{-1, -1};
}
```
### 2 使用二分查找

确定一个数之后, 另一个数使用二分查找来确定, 但前提是有序

```java
public int[] twoSum(int[] nums, int target) {

    Arrays.sort(nums);

    for (int i = 0; i <= nums.length - 2; i++) {
        int a = nums[i];
        int b = target - a;
        int j = Arrays.binarySearch(nums, i + 1, nums.length, b);
        if (j >= 0) {
            return new int[]{i, j};
        }
    }
    return new int[]{-1, -1};
}
```
### 3 二分查找，保留原来的顺序



```java
public int[] twoSum(int[] nums, int target) {

    Tuple[] numWithIndex = Tuple.buildFrom(nums);

    Arrays.sort(numWithIndex, Tuple.comparator);

    for (int i = 0; i <= numWithIndex.length - 2; i++) {
        int a = numWithIndex[i].data;
        Tuple bTuple = new Tuple(target - a, -1);
        int j = Arrays.binarySearch(numWithIndex, i + 1, nums.length, bTuple, Tuple.comparator);
        if (j >= 0) {            // 这里注意，如果不存在，binarySearch返回的是-(insert_position) - 1,是一个负数
            return new int[]{numWithIndex[i].index, numWithIndex[j].index};
        }
    }

    return new int[]{-1, -1};
}

static class Tuple{
    int data;
    int index;

    public static Comparator<Tuple> comparator = Comparator.comparingInt(o -> o.data);

    public Tuple(int data, int index) {
        this.data = data;
        this.index = index;
    }

    @Override
    public String toString() {
        return "(" + data + "," + index + ")";
    }

    public static Tuple[] buildFrom(int[] nums) {

        Tuple[] result = new Tuple[nums.length];
        for(int i = 0; i < nums.length; i++) {
            result[i] = new Tuple(nums[i], i);
        }

        return result;
    }
}
```
### 4 使用HashMap



```java
public int[] twoSum(int[] nums, int target) {

    int[] result = new int[2];

    // 相同元素的地址都保存在列表中。
    Map<Integer, List<Integer>> num2Indexes = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        List<Integer> indexes = num2Indexes.get(nums[i]);
        if (indexes == null){
            indexes = new ArrayList<>();
            indexes.add(i);
            num2Indexes.put(nums[i], indexes);
        } else {
            indexes.add(i);
        }
    }

    for(int i = 0; i < nums.length; i++) {
        int a = nums[i];
        int b = target - a;
        List<Integer> bIndexes = num2Indexes.get(b);
        if (bIndexes != null) {
            for (int bIndex : bIndexes) {
                if(bIndex > i) {      // 排除本身，因为不允许同一元素使用两次
                    return new int[]{i, bIndex};
                }
            }
        }
    }
    return new int[]{-1, -1};
}
```
### 5 原来答案，思路不太清晰



```java
public int[] twoSum(int[] nums, int target) {

    int[] result = new int[2];
    final HashMap<Integer, Integer> num2Index = new HashMap<>();

    // 这里其实有个技巧，如果有两个相同的元素，后面一个会覆盖了前面一个的地址
    // 而最终都能过case，只不过是偶然罢了，你如果不能很清晰的说出这个技巧在
    // 这里有用，那么，你只是在靠巧合编程罢了。
    // 这个后面覆盖前面和下面的算法是有关的. 不能前面覆盖后面
    for(int i = 0; i < nums.length; i++) {
        num2Index.put(nums[i], i);
    }

    for(int i = 0; i < nums.length; i++) {

        int remain = target - nums[i];
        if(num2Index.containsKey(remain) && num2Index.get(remain) != i) {   //注意这里， 可能有6 - 3 = 3的情况
            result[0] = i;
            result[1] = num2Index.get(remain);
            return result;
        }
    }

    return result;
}
```
### 6 夹逼方法

   利用Two Sum Sorted Array那一题的思路, 只是要保存一个原index.
   其实这种复杂度应该是O(n)的, 虽然要排序, 但如果只排序一次, 而使用多次, 也是可以完全接受的.


```java
public int[] twoSum(int[] nums, int target) {

    Tuple[] numWithIndex = Tuple.buildFrom(nums);

    Arrays.sort(numWithIndex, Tuple.comparator);

    int i = 0, j = nums.length - 1;

    //两者缩进过程决定了, 其一定是会相遇
    while (i != j) {
        int sum = numWithIndex[i].data + numWithIndex[j].data;
        if(sum == target) {
            return new int[]{numWithIndex[i].index, numWithIndex[j].index};
        } else if (sum < target) {
            i++;
        } else {
            j--;
        }
    }

    return new int[]{-1, -1};
}

static class Tuple{
    int data;
    int index;

    public static Comparator<Tuple> comparator = Comparator.comparingInt(o -> o.data);

    public Tuple(int data, int index) {
        this.data = data;
        this.index = index;
    }

    @Override
    public String toString() {
        return "(" + data + "," + index + ")";
    }

    public static Tuple[] buildFrom(int[] nums) {

        Tuple[] result = new Tuple[nums.length];
        for(int i = 0; i < nums.length; i++) {
            result[i] = new Tuple(nums[i], i);
        }

        return result;
    }
}
```
