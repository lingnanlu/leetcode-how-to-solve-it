#Two Sum

https://leetcode.com/problems/two-sum

### 问题描述

<p>Given an array of integers, return <strong>indices</strong> of the two numbers such that they add up to a specific target.</p>

<p>You may assume that each input would have <strong><em>exactly</em></strong> one solution, and you may not use the <em>same</em> element twice.</p>

<p><strong>Example:</strong></p>

<pre>
Given nums = [2, 7, 11, 15], target = 9,

Because nums[<strong>0</strong>] + nums[<strong>1</strong>] = 2 + 7 = 9,
return [<strong>0</strong>, <strong>1</strong>].
</pre>

### 解题思路

q. 问题是什么？

a. 给定一个数组，找两个数，这两个数之各等于一个给定的数。而且这两个数不能是同一个数

q. 你能想出一种答案么？

a. 把所有两个数的可能穷举出来，就可以了，于是写出了solution 0。

q. ok, 你给出的答案是显而易见的，但是，它的时间复杂度是0(n^2).再想想，有没有更好的方式？

a. ...

q. 你看，你现在对于本问题没有什么思路，可不可以简化一下问题？你遇到过类似的问题么？比如，假如只找一个数呢？

a. 只找一个数的话，就相当于从一个数组中找到一个数，该数和给定的数相等。

q. 好的，这个问题你能解决么？

a. 很简单，遍历一遍数组就行。时间复杂为0(n)

q. 能不能优化一下？再看一下这是一个什么问题？

a. 啊，就是一个查找问题，我可以使用查找相关的算法。

q. 好的，那么怎么快速找到一个元素呢？你知道有哪些方法？

a. 我想想，适用于数组的查找算法有

    1. 顺序查找
    2. 二分查找

q. 好的，现在有一个简化的问题，在一个数组中查找一个元素，和给定的元素相等，这个问题和你当前要解决的问题有关么？
你能将当前的问题转化成一个简单的问题么？

a. 当前问题是要求2个数的和等于一个给定值，而简化问题相当于求一个数的和等于一个给定值。那么如果我能确定其中一个数，就可以转化成一个简化问题了，
等等，简化问题是一个查找一个学的问题，查找一个数，我可以使用二分查找，等等，我好像有一个可以优化时间复杂度的方案了，于是写出solution 2

a. 等等，如果排序的话，原始位置就变了，所以在排序之前，要把原始位置也记录下来，所以，还要改进一下， 于是写出solution 3。

q. 现在，你的时间复杂度变成了O(nlogn),比暴力要好，但是每次其实都要有一个变成tuple和sort操作，如果数据集不变，我们只需要做一次操作即可
那如果每次的数据集要变，而现在的实现，在空间上有更多的要求，查找时间虽然短了，但每次都要多一个排序的时间。所以感觉不太理想哈。

那么，你还能优化一下么？对于我们的问题来说，其实就是再优化一下从一堆数中，找出给定的数的地址。

a. 从一堆数中，找出给定的数的地址。。

q. 也就是给定x ，得到它的address. 你看，用数学表示一下

a. f(x) = address， 这是一个映射，等等，我好像想到了Map，可以建立一个（x) -> (address)的map，这样，我就能使用O(1)的复杂度拿到x的地址了。

兴奋中，尝试写下新的答案



### 相关题目

### 代码

```java
public class Two_Sum {

    static class Tuple{
        int data;
        int index;

        public static Comparator<Tuple> comparator = (o1, o2) -> {
            return o1.data - o2.data;
        };

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

    @Solution("暴力方法")
    class BruteForce {
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
    }


    @Solution("使用二分查找")
    class UsingBinarySearch {
        public int[] twoSum(int[] nums, int target) {

            Arrays.sort(nums);

            for (int i = 0; i <= nums.length - 2; i++) {
                int a = nums[i];
                int b = target - a;
                int j = Arrays.binarySearch(nums, i + 1, nums.length, b);
                if (j != -1) {
                    return new int[]{i, j};
                }
            }
            return new int[]{-1, -1};
        }
    }

    @Solution("二分查找，保留原来的顺序")
    class UsingBinarySearchWithTuple {

        public int[] twoSum(int[] nums, int target) {

            Tuple[] numWithIndex = Tuple.buildFrom(nums);

            Arrays.sort(numWithIndex, Tuple.comparator);

            for (int i = 0; i <= numWithIndex.length - 2; i++) {
                int a = numWithIndex[i].data;
                Tuple bTuple = new Tuple(target - a, -1);
                int j = Arrays.binarySearch(numWithIndex, i + 1, nums.length, bTuple, Tuple.comparator);
                if (j < 0) {            // 这里注意，如果不存在，binarySearch返回的是-(insert_position) - 1,是一个负数
                    return new int[]{numWithIndex[i].index, numWithIndex[j].index};
                }
            }

            return new int[]{-1, -1};
        }
    }

    @Best("使用HashMap")
    class UsingHashMap {
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
    }

    @Solution("原来答案，思路不太清晰")
    class Bad {
        public int[] twoSum(int[] nums, int target) {

            int[] result = new int[2];
            final HashMap<Integer, Integer> num2Index = new HashMap<>();

            // 这里其实有个技巧，如果有两个相同的元素，后面一个会覆盖了前面一个的地址
            // 而最终都能过case，只不过是偶然罢了，你如果不能很清晰的说出这个技巧在
            // 这里有用，那么，你只是在靠巧合编程罢了。
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
    }

}
```
