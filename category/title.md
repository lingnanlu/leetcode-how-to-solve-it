###title

https://leetcode.com/problems/two-sum

**问题描述**

<p>Given an array of integers, return <strong>indices</strong> of the two numbers such that they add up to a specific target.</p>

<p>You may assume that each input would have <strong><em>exactly</em></strong> one solution, and you may not use the <em>same</em> element twice.</p>

<p><strong>Example:</strong></p>

<pre>
Given nums = [2, 7, 11, 15], target = 9,

Because nums[<strong>0</strong>] + nums[<strong>1</strong>] = 2 + 7 = 9,
return [<strong>0</strong>, <strong>1</strong>].
</pre>

**解题思路**

solution 

**代码**

```java
public class Two_Sum {

    public int[] twoSum(int[] nums, int target) {

        int[] result = new int[2];
        final HashMap<Integer, Integer> num2Index = new HashMap<>();

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
```
