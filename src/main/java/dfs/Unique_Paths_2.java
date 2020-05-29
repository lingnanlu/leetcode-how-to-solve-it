package dfs;


public class Unique_Paths_2 {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        visit(0, 0, m, n, obstacleGrid);

        return pathCount;
    }


    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        if (obstacleGrid[m - 1][n - 1] == 1) return 0;
        cache = new int[m][n];

        return visit2(0, 0, m, n, obstacleGrid);
    }

    // visit一个节点的结果是得到该节点到end的path数
    private int visit2(int i, int j, int m, int n, int[][] obstacleGrid) {

        // 先判断是不是障碍节点
        if (obstacleGrid[i][j] == 1) {
            cache[i][j] = 0;
            return 0;
        }

        // 终点
        if (i == m - 1 && j == n - 1) return 0;

        // cache[row][col] > 0也代表该节点有没有访问过
        if (cache[i][j] > 0) return cache[i][j];


        if (i == m - 1) {
            cache[i][j] = visit2(i, j + 1, m, n, obstacleGrid);
            return cache[i][j];
        }

        if (j == n - 1) {
            cache[i][j] = visit2(i + 1, j, m, n, obstacleGrid);
            return cache[i][j];
        }

        cache[i][j] = visit2(i, j + 1, m, n, obstacleGrid) + visit2(i + 1, j, m, n, obstacleGrid);
        return cache[i][j];

    }

    private int[][] cache;


    public static void main(String[] args) {
        int[][] test = {{1, 0}};

        Unique_Paths_2 example = new Unique_Paths_2();
        example.uniquePathsWithObstacles2(test);

    }

    /**
     * 这种方式的灵感来自于，由一个状态变成两个状态，类似于树的结构。
     * <p>
     * 这种方式会超时，但是可以找到所有路径
     *
     * @param i
     * @param j
     * @param m
     * @param n
     * @param obstacleGrid
     */
    private void visit(int i, int j, int m, int n, int[][] obstacleGrid) {

        if (obstacleGrid[i][j] == 1) return;

        if (isEnd(i, j, m, n)) {

            pathCount++;

        } else {

            // 向下能不能通过
            if ((i + 1) <= m - 1 && obstacleGrid[i + 1][j] != 1) {
                visit(i + 1, j, m, n, obstacleGrid);
            }

            if ((j + 1) <= n - 1 && obstacleGrid[i][j + 1] != 1) {
                visit(i, j + 1, m, n, obstacleGrid);
            }

        }
    }

    private int pathCount = 0;

    private boolean isEnd(int i, int j, int m, int n) {
        return i == m - 1 && j == n - 1;
    }

}
