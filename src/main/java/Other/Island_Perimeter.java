package Other;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Island Perimeter",
        link = "https://leetcode.com/problems/island-perimeter/description/",
        category = Category.UNKNOWN,
        how2SolveIt = """
                这题并不难, 就是直接模拟人工最直观的方式, 比如对于例子
                [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]], 我们人工统计的结果为
                
                3 + 3 + 0 + 3 + 2 + 3 + 2 = 16
                
                注意, 在人工统计时, 对于每一个格子, 要判断它的邻居.
                """,
        relatedQuestions = {}
)
public class Island_Perimeter {

    public int islandPerimeter(int[][] grid) {

        int perimeter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    // 统计它的邻居数
                    int neighbor = 0;
                    // 上邻居
                    if (i - 1 >= 0 && grid[i - 1][j] == 1) neighbor++;
                    // 左邻居
                    if (j - 1 >= 0 && grid[i][j - 1] == 1) neighbor++;
                    // 下邻居
                    if (i + 1 <= grid.length - 1 && grid[i + 1][j] == 1) neighbor++;
                    // 右邻居
                    if (j + 1 <= grid[0].length - 1 && grid[i][j + 1] == 1) neighbor++;

                    perimeter += 4 - neighbor;
                }
            }
        }

        return perimeter;
    }
}
