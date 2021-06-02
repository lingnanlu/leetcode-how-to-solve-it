#3Sum

https://leetcode.com/problems/3sum

### 问题描述

<p>Given an question.array <code>nums</code> of <em>n</em> integers, are there elements <em>a</em>, <em>b</em>, <em>c</em> in <code>nums</code> such that <em>a</em> + <em>b</em> + <em>c</em> = 0? Find all unique triplets in the question.array which gives the sum of zero.</p>

<p><strong>Note:</strong></p>

<p>The solution set must not contain duplicate triplets.</p>

<p><strong>Example:</strong></p>

<pre>
Given question.array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
</pre>

### 解题思路

q. 问题是什么？

a. 找出所有a + b + c = 0, 要求不重复

q. 你能把未知分开描述么？比如，列一个列表

a.
    1. 找出a + b + c = 0
    2. 找出所有
    3. 要求不重复

q. 好的，现在，你要求的未知需要同时满足以上三者，你现在还没有什么思路，但，你可以先只满足部分要求，你有没有遇到过类似的问题？

a. 有的啊，刚才那个two sum，就是求 a + b = c

q. 你能说出两者的异同么？

a.

    1. two sum只是找出一个解。而这里是要找出所有的，而且不重复。
    2. two sum求的是index,而这里是要找的数组中元素的值
    3. two sum是 a + b = c, 而这里的 a + b + c = 0...

q. 你可以将现在的问题变换一下，使其尽量接近two-sum的描述么，这样，有可能之前two-sum的思路会帮你解决这个问题的某个方面，比如说第三方面

a. 嗯， a + b + c = 0 => a + b = -c的话，就和two sum一样了。如果把数组中的一个元素当成-c的话，这个问题就是在
剩余数组中的two-sum问题。但1，2点还是不同

q. 关于第一点，是不是可以把限制变换一下，这里，我们也只求出一组，这样，也没有问题，而关于第二点，index和值其实很好转化，我们暂时可以求index.

你能利用two sum中的方法（代码）么？或者，你也可以利用解two sum中的思想，对，就是那个Map

a. 我感觉可以复用之前的代码，我试试.

a. 嗯，利用two Sum，发现接口上不太适配，我可以试试利用其思想, 这个解法是O(n2）

q. 好，你现在是得到index，当要求的是元素的值。

a. 返回元素的值就可以。

q. 但现在要给所有未重复的解。

a. 是的，我可以找到一组解，然后不返回，加入到结果集中，而加入的时候，可以使用一个set来判断是否重复，但我感觉这好像变的越来越复杂？

q. 确实是这样，我们这里仅仅利用two-sum的方法，似乎并不能很完美的解决这个问题。无论是利用结果，还是利用方法上面，似乎都不太可行。

q. 所以这里，我们需要重新审视一下这个问题，现在，无论是利用two-sum的结果还有思路，都不太能很好的匹配我们的问题。而且使用HashMap总是要有额外的空间和建立HashMap的
时间。

而且two-sum中使用HashMap一个主要的目的就是要得到index，而我们这里并不要求得到index. 所以某种程度上放松了问题。

现在，让我们来重新审视一下这个问题。你再观察一下这个问题要求的未知。

a.

    1. 找出a + b + c = 0
    2. 找出所有
    3. 要求不重复

q. 现在，你能不能放开一些限制，然后，简化一些条件？

a

可以不考虑2，3，只考虑1.

q. 好，现在未知成什么了？

a. 找出一组a + b + c = 0

q. 你能解决这个问题么？

a. ....

q 如果是a + b = 0 呢？

a 可以使用暴力，但显然不好。

q 好吧，看来你实在是想不出来了，这里告诉你一个技巧，你可以先排序，然后使用夹逼的方式。给你演示一下。

q 这是一个在数组上进行操作的技巧，使用两个指针来优化时间复杂度，你最好记住。

现在，只是给出了一个解，你能不能找出的有解？

a 只要找到一组解后，继续移动指针，就行，直到i,j相遇。

q. 好的，看起来是可行的，但你能证明这种方式找到的解就是所有解么？

a ...

q 其实这个证明并不好证明，这里只是提醒你，做出一个答案，不要感觉对就是对的，你要能一眼看出来就是对的，而且要严谨的证明它是对的。

因为我们的要求不高，所以先告诉你答案，它确实是对的。我们还是追求实践，那些理论证明就留给数学家吧。

现在还有一点没有完成，就是重复性的问题，你怎么避免重复？

a. 可以在移动指针时，判断是不是和之前的一样，因为有一个一样，另一个也一样，所以就重复了，如果一样，就再继续移动。

q. 现在你解决了two-sum问题，而threeSum和它基本一样，就是a + b + c = 0,

a. 我可以将 a + b + c = 0 => a + b = -c 然后利用同样的方式。

### 相关题目


### 代码

```java
public class Three_Sum {

    @Try("试着利用TwoSum结果，但接口不匹配")
    static class UsingTwoSumResult {
        public int[] threeSum(int[] nums) {
            //target 可以是数组中的任意值，而数组中剩下的元素来找其它两个值

            for (int i = 0; i <= nums.length - 3; i++) {
                int target = -nums[i];
                // 等等，这twoSum是直接作用在整个数组中，而这里是需要作用在数组上的一个片段，好像要对twoSum进行修改才行

//            twoSum(nums, target);
            }

            return null;
        }
    }

    @Try("利用HashMap的方法，返回index")
    static class UsingHashMapFindOne {
        public int[] threeSum(int[] nums) {

            int[] result = {-1, -1, -1};

            Map<Integer, List<Integer>> num2Indexs = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                List<Integer> indexs = num2Indexs.get(nums[i]);
                if (indexs == null){
                    indexs = new ArrayList<>();
                    indexs.add(i);
                    num2Indexs.put(nums[i], indexs);
                } else {
                    indexs.add(i);
                }
            }

            for(int i = 0; i <= nums.length - 3; i++) {
                int c = nums[i];
                // 这里就和two-sum基本一样了
                for (int j = i + 1; j <= nums.length - 2; j++) {
                    int a = nums[j];
                    int b = -c - a;
                    List<Integer> bIndexes = num2Indexs.get(b);
                    if (bIndexes != null) {
                        for (int bIndex : bIndexes) {
                            if (bIndex > j) {   //>j 就行，保证第三个元素不是第一，第二个就可
                                result[0] = i;
                                result[1] = j;
                                result[2] = bIndex;
                                return result;
                            }
                        }
                    }
                }
            }

            return result;

        }
    }


    @Try("利用HashMap思想,返回一组解")
    static class UsingHashMapFindOneReturnElement {

        public int[] threeSum(int[] nums) {

            Map<Integer, List<Integer>> num2Indexs = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                List<Integer> indexs = num2Indexs.get(nums[i]);
                if (indexs == null){
                    indexs = new ArrayList<>();
                    indexs.add(i);
                    num2Indexs.put(nums[i], indexs);
                } else {
                    indexs.add(i);
                }
            }

            for(int i = 0; i <= nums.length - 3; i++) {
                int c = nums[i];
                // 这里就和two-sum基本一样了
                for (int j = i + 1; j <= nums.length - 2; j++) {
                    int a = nums[j];
                    int b = -c - a;
                    List<Integer> bIndexes = num2Indexs.get(b);
                    if (bIndexes != null) {
                        for (int bIndex : bIndexes) {
                            if (bIndex > j) {   //>j 就行，保证第三个元素不是第一，第二个就可
                                return new int[]{a, b, c};
                            }
                        }
                    }
                }
            }
            return new int[]{-1, -1, -1};
        }
    }

    @Solution("利用HashMap,返回所有解")
    static class UsingHashMapReturnFindALl {
        public List<List<Integer>> threeSum(int[] nums) {

            List<List<Integer>> result = new ArrayList<>();

            Map<Integer, List<Integer>> num2Indexs = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                List<Integer> indexs = num2Indexs.get(nums[i]);
                if (indexs == null){
                    indexs = new ArrayList<>();
                    indexs.add(i);
                    num2Indexs.put(nums[i], indexs);
                } else {
                    indexs.add(i);
                }
            }

            for(int i = 0; i <= nums.length - 3; i++) {
                for (int j = i + 1; j <= nums.length - 2; j++) {
                    int first = nums[i];
                    int second = nums[j];
                    int target = 0 - (first + second);
                    List<Integer> thirdIndexs = num2Indexs.get(target);
                    if (thirdIndexs != null) {
                        for (int thirdIndex : thirdIndexs) {
                            if (thirdIndex > j) {
                                result.add(Arrays.asList(i, j, thirdIndex));
                            }
                        }
                    }
                }
            }

            return result;
        }
    }

    @Try("使用夹逼方式，找到a + b = 0,的一个解")
    static class JiaBiFindOne {
       public List<Integer> twoSum(int[] nums) {

           Arrays.question.sort(nums);

           int i = 0, j = nums.length - 1;
           while(i < j) {
                if(nums[i] + nums[j] == 0) {
                    return Arrays.asList(nums[i], nums[j]);
                } else if (nums[i] + nums[j] > 0) {
                    j--;
                } else {
                    i++;
                }
           }

           //没找到。
           return null;
       }
    }

    @Try("使用夹逼技巧，找到a + b = 0的所有解")
    static class JiaBiFindAll {

        public List<List<Integer>> twoSum(int[] nums) {

            List<List<Integer>> solutions = new ArrayList<>();
            Arrays.question.sort(nums);

            int i = 0, j = nums.length - 1;
            while(i < j) {
                if(nums[i] + nums[j] == 0) {
                    List<Integer> solution = Arrays.asList(nums[i], nums[j]);
                    solutions.add(solution);

                    i++;
                } else if (nums[i] + nums[j] > 0) {
                    j--;
                } else {
                    i++;
                }
            }

            //没找到。
            return null;
        }
    }


    @Try("使用夹逼技巧，找到a + b = 0的所有解，没有重复")
    static class JiaBiFindAllWithoutDuplicate {

        public List<List<Integer>> twoSum(int[] nums) {

            List<List<Integer>> solutions = new ArrayList<>();
            Arrays.question.sort(nums);

            int i = 0, j = nums.length - 1;
            while(i < j) {
                if(nums[i] + nums[j] == 0) {
                    List<Integer> solution = Arrays.asList(nums[i], nums[j]);
                    solutions.add(solution);
                    // 跳出循环时，nums[i + 1] != nums[i]
                    while(i < j && nums[i + 1] == nums[i]) i++;
                } else if (nums[i] + nums[j] > 0) {
                    j--;
                } else {
                    i++;
                }
            }

            //没找到。
            return solutions;
        }
    }

    @Best("使用夹逼技巧，找到a + b + c= 0的所有解，没有重复")
    static class ThreeSumSolution {

        public List<List<Integer>> threeSum(int[] nums) {

            List<List<Integer>> solutions = new ArrayList<>();
            Arrays.question.sort(nums);

            for(int i = 0; i <= nums.length - 3;) {
                for(int j = i + 1, k = nums.length - 1; j < k;) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> solution = Arrays.asList(nums[i], nums[j], nums[k]);
                        solutions.add(solution);
                        j++;
                        while(j < k && nums[j - 1] == nums[j]) j++;
                    } else if (nums[i] + nums[j] + nums[k] > 0) {
                        k--;
                    } else {
                        j++;
                    }
                }

                // 注意这里跳过重复的写法
                i++;
                while(i <= nums.length - 3 && nums[i - 1] == nums[i]) i++;
            }

            //没找到。
            return solutions;
        }
    }

    public static void main(String[] args) {
        ThreeSumSolution solution = new ThreeSumSolution();
        int[] nums = {-1,0,1,2,-1,-4};
        solution.threeSum(nums);
    }

}
```
