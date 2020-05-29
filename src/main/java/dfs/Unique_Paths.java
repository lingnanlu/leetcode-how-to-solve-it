package dfs;

public class Unique_Paths {

    private int[][] cache;

    public int uniquePaths(int m, int n) {
        cache = new int[m + 1][n + 1];
        return f(m, n);
    }

    private int f(int m, int n) {

        if (m == 0 || n == 0) return 0;
        if (m == 1 || n == 1) return 1;

        if (cache[m][n] > 0) return cache[m][n];
        else {
            cache[m][n] = f(m, n - 1) + f(m - 1, n);
            return cache[m][n];
        }
    }


    /**
     * 注意这个f(m, n)的含义是（0， 0）到（m，n）的路径条数
     * f(m, n) = f(m - 1, n) + f(m, n - 1)
     *
     * 动规，自右向左，迭代，自底向上
     *
     * 自底向上的好处是，减少递归导致的函数调用开销。
     */
    public int uniquePaths2(int m, int n) {

        int[][] f = new int[m + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            f[1][i] = 1;
        }

        for (int i = 1; i <= m; i++) {
            f[i][1] = 1;
        }

        for (int i = 2; i <= m; i++) {
            for (int j = 2; j <= n; j++) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }

        return f[m][n];
    }


    public static void main(String[] args) {
        Unique_Paths test = new Unique_Paths();

        System.out.println(test.uniquePaths2(3, 2));
    }
}
