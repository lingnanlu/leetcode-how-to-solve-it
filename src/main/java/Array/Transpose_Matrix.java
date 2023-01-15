package Array;

/**
 * 没什么好说的, 读一行, 写一列
 */
public class Transpose_Matrix {

    public int[][] transpose(int[][] matrix) {

        int[][] result = new int[matrix[0].length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            // 每次处理一行. i是matrix的行, 也是result的列
            for (int j = 0; j < matrix[0].length; j++) {
                result[j][i] = matrix[i][j];
            }
        }

        return result;

    }

}
