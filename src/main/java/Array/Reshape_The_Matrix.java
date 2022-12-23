package Array;


/**
 * 把题翻译一遍
 */
public class Reshape_The_Matrix {

    public int[][] matrixReshape(int[][] mat, int r, int c) {

        int matR = mat.length;
        int matC = mat[0].length;

        if (matR * matC != r * c) {
            return mat;
        }

        int[][] result = new int[r][c];


        // 遍历mat依次填充新矩阵
        for (int i = 0; i < matR; i++) {
            for (int j = 0; j < matC; j++) {
                // 将原坐标转化为新坐标
                // 原坐标=> 线性 => 新坐标
                // 这里关键的就是这个坐标转换
                result[(i * matC + j) / c][(i * matC + j) % c] = mat[i][j];
            }
        }

        return result;
    }

}
