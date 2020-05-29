package search;


/**
 * 关键是找到一种方法， 使用的检索集的范围不变缩小。
 *
 * 如果从中间， 从左上角开始不行。
 *
 * 可以试试， 右上角， 左下角， 右下角。 沿对角等等
 *
 * 这题从左下角开始也行
 */
public class Search_A_2D_matrix_2 {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;

        int i = 0;
        int j = matrix[0].length - 1;
        while(i <= matrix.length - 1 && j >= 0) {
            int value = matrix[i][j];

            if (value == target) return true;
            else if (value < target) {  // 排除所在的行
                i++;
            } else {        // 排除所在的列
                j--;
            }
        }

        return false;
    }
}
