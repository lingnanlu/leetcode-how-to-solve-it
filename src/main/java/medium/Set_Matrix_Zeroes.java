package medium;

import java.util.HashSet;
import java.util.Set;

public class Set_Matrix_Zeroes {
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
