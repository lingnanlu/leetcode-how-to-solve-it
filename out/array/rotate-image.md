# Rotate Image

[https://leetcode.com/problems/rotate-image](https://leetcode.com/problems/rotate-image)

## 问题描述

You are given an _n_ x _n_ 2D matrix representing an image.

Rotate the image by 90 degrees \(clockwise\).

**Note:**

You have to rotate the image [**in-place**](https://en.wikipedia.org/wiki/In-place_algorithm), which means you have to modify the input 2D matrix directly. **DO NOT** allocate another 2D matrix and do the rotation.

**Example 1:**

```text

Given input matrix = 
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

rotate the input matrix in-place such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
```

**Example 2:**

```text

Given input matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
], 

rotate the input matrix in-place such that it becomes:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
```

## 解题思路

思路一：使用另一个维数组进行辅助操作

思路二：其实和Rotate Array一样，都是将Rotate操作，转化为容易实现的操作，只是这种转化的逻辑比较triky.

## 相关题目

## 代码

```java
public class Rotate_Image {

    // 至于怎么翻转, 实在想不到, 纯靠记忆记住吧, 先副后中, 关键是你能不能快速的操作下标
    public void rotate(int[][] matrix) {

        int n = matrix.length;
        // 先副对角线翻转, 找下标规律比如一个4*4的, (0, 1), 变成(2, 3), 其实是第一行变成最后一列, 第二列变成倒数第二行.
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][n - 1 -i];
                matrix[n - 1 - j][n - 1 - i] = temp;
            }
        }

        // 中心线翻转, 列不变
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - i][j];
                matrix[n - 1 - i][j] = temp;
            }
        }

    }
}
```

