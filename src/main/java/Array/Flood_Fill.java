package Array;

/**
 *
 * todo
 *
 * 这题初一看, 好像不好下手.
 * 因为由一个初始点, 可以得到四个方向上的点, 然后再由四个方向上的点, 还能得到另外四个方向的点
 * 它不是一个一维的, 只有一个方向, 而是四个方向.
 * 而且初始点不一定是左上角, 而且可能是任何位置.
 *
 * 这怎么办呢? 人的思维喜欢线性, 而不喜欢这种四处发散的.
 *
 * 能不能把这种四处发散的, 变成某种线性呢?
 *
 * 比如说, 围绕初始点, 把周围的点划分成四个部分, 每一次处理一个部分, 这样不就行了么?
 *
 * 这四个部分 + 初始点就是原矩阵, 而且又互不重叠.
 *
 *
 */
public class Flood_Fill {

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {

        int m = image.length;
        int n = image[0].length;

        int initColor = image[sr][sc];

        // 左上角部分矩阵 [0, 0] -> [sr, sc - 1], 遍历方向是 [sr, sc -1]到[0, 0]
        for (int i = sc; i >= 0; i--) {
            for (int j = sc - 1; j >= 0; j--) {
                if (image[i][j] == initColor) {
                    image[i][j] = color;
                }
            }
        }

        // 右上角部分矩阵 [0, sc] -> [sr - 1, n - 1], 遍历方向是
        for (int i = 0; i < ; i++) {

        }

        // 左下角部分矩阵

        // 右下角部分矩阵
    }
}
