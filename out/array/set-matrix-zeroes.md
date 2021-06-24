# Set Matrix Zeroes

[https://leetcode.com/problems/set-matrix-zeroes](https://leetcode.com/problems/set-matrix-zeroes)

## 问题描述

Given a _m_ x _n_ matrix, if an element is 0, set its entire row and column to 0. Do it [**in-place**](https://en.wikipedia.org/wiki/In-place_algorithm).

**Example 1:**

```text

Input: 
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
Output: 
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
```

**Example 2:**

```text

Input: 
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
Output: 
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]
```

**Follow up:**

* A straight forward solution using O\(_mn_\) space is probably a bad idea.
* A simple improvement uses O\(_m_ + _n_\) space, but still not the best solution.
* Could you devise a constant space solution?

## 解题思路

O\(m+n\)的空间复杂度的好想，那么能不能不使用额外空间呢？

这里要看O\(m+n\)的额外空间记录的是什么信息，这里记录的是哪些行为0，哪些列为0，

而如果不使用额外的空间，那么，只能利用Matrix本身来记录这些值了。

那么，Matrix的什么地方可以用来记录这些值？最容易想到的是第一行和第一列

这里可以先简化一下，如果只要求把行设置为0，你会怎么做？

## 相关题目

## 代码

```java
public class Set_Matrix_Zeroes {

    @Solution("使用O(m + n)的空间")
    static class UsingExtraSpace {
        public void setZeroes(int[][] matrix) {
            // 首先不能遇到0就将行列都置为0, 因为后来出现的0你不知道是原来的还是你置的.
            // 所以一个最自然的想法就是先便利一遍, 将0出现的行和列记录下来.
            // 用一个集合记录出现0的行, 一个集合记录出现0的列.

            Set<Integer> zeroRows = new HashSet<>();
            Set<Integer> zeroColumns = new HashSet<>();

            int m = matrix.length;
            int n = matrix[0].length;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if(matrix[i][j] == 0) {
                        zeroRows.add(i);
                        zeroColumns.add(j);
                    }
                }
            }

            // 将所有0行设置成0
            for(int row : zeroRows) {
                for(int j = 0; j < n; j++) {
                    matrix[row][j] = 0;
                }
            }

            // 将所有0列设置成0
            for(int i = 0; i < m; i++) {
                for(int col : zeroColumns) {
                    matrix[i][col] = 0;
                }
            }
        }
    }

    @Solution("使用O(1)的空间")
    static class UsingConstSpace {
        public void setZeroes(int[][] matrix) {

            int m = matrix.length;
            int n = matrix[0].length;

            // 这里使用第0列，来保存是否对应的行有0
            // 使用第0行，来保存是否对应的列有0
            // 因为要修改第0行和第0列，所以在修改之前要先检查一个第0行和第0列本身有没有0

            boolean firstRowHasZero = false;
            boolean firstColHasZero = false;

            for(int i = 0; i < n; i++) {
                if(matrix[0][i] == 0) {
                    firstRowHasZero = true;
                    break;
                }
            }

            for(int i = 0; i < m; i++) {
                if(matrix[i][0] == 0) {
                    firstColHasZero = true;
                    break;
                }
            }

            for(int i = 1; i < m; i++) {
                for(int j = 1; j < n; j++) {
                    if(matrix[i][j] == 0) {
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;
                    }
                }
            }

            //设置行为0
            for(int i = 1; i < m; i++) {
                if(matrix[i][0] == 0) {
                    Arrays.fill(matrix[i], 0);
                }
            }

            //设置列为0
            for(int j = 1; j < n; j++) {
                if (matrix[0][j] == 0) {
                    for(int i = 1; i < m; i++) {
                        matrix[i][j] = 0;
                    }
                }
            }

            if(firstRowHasZero) {
                Arrays.fill(matrix[0], 0);
            }

            if(firstColHasZero) {
                for(int i = 0; i < m; i++){
                    matrix[i][0] = 0;
                }
            }



        }
    }


    public void setZeroes2(int[][] matrix) {
        // 方法一使用了额外空间, 如何不使用额外空间呢? 能不能利用数组本身来保存0行, 0列的信息?
        // 可以利用第一行和第一列来代替两个set

        int m = matrix.length;
        int n = matrix[0].length;


        boolean firstRowHasZero = false;
        boolean firstColumnHasZero = false;

        for(int i = 0; i < m; i++) {
            if(matrix[i][0] == 0) {
                firstColumnHasZero = true;
                break;
            }
        }

        for(int i = 0; i < n; i++) {
            if(matrix[0][i] == 0) {
                firstRowHasZero = true;
                break;
            }
        }

        /**
         * 注意这里不去动(0, 0)这个元素,
         *
         * 对于第一列来说, 无论原来是不是0, 都可以根据是0来将这一行重置为0,
         * 对于第一行也是.
         *
         * 但如果设置了(0, 0)这个元素, 你就不知道它原来是不是0,
         *
         * 如果不是0, 则不能把第一行, 第一列也设置为0.
         *
         *         // 这里的关键是, (0, 0)这个元素, 如果设置成0的话, 不知道是因为
         *         // 1. 列中有0导致的0,
         *         // 2. 行中有0导致的0
         *         // 3. 本身就是0
         *         // 所以需要额外记录第一行是否有0, 第一列是否有0.
         */

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if(matrix[i][j] == 0) {
                    matrix[i][0] = 0;   // 第一列来保存哪些行是0
                    matrix[0][j] = 0;   // 第一行来保存哪些列是0
                }
            }
        }



        // 将所有0行设置成0, 不处理第一行
        for(int i = 1; i < m; i++) {
            if(matrix[i][0] == 0) {
                for(int j = 0; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 将所有0列设置成0, 不处理第一列
        for(int i = 1; i < n; i++) {
            if(matrix[0][i] == 0) {
                for(int j = 0; j < m; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

        if(firstRowHasZero) {
            for(int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        }

        if(firstColumnHasZero) {
            for(int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }


    }
}
```

