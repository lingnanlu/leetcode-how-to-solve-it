package Array;

/**
 * 就是每一行的和的最大值
 */
public class Richest_Customer_Wealth {

    public int maximumWealth(int[][] accounts) {
        int max = 0;

        for (int i = 0; i < accounts.length; i++) {
            int current = 0;
            for (int j = 0; j < accounts[0].length; j++) {
                current += accounts[i][j];
            }

            max = Math.max(max, current);
        }

        return max;
    }
}
