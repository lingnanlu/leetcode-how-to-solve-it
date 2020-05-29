package medium;

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
