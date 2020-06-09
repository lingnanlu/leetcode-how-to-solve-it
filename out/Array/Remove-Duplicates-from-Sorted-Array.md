#Remove Duplicates from Sorted Array

https://leetcode.com/problems/remove-duplicates-from-sort-array

### 问题描述


### 解题思路

q.问题是什么？

a.消除数组中的重复元素

q.限制是什么？

a.空间上要O(1),in-place,不能有额外的空间，时间上没要求

q.你能不能放松限制？




### 代码

```java
public class Remove_Duplicates_From_Sort_Array {

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
