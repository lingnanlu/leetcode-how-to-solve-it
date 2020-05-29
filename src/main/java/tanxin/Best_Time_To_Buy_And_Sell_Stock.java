package tanxin;

public class Best_Time_To_Buy_And_Sell_Stock {

    public int maxProfit(int[] prices) {

        if(prices.length == 0) return 0;

        int maxProfit = 0;
        int min = prices[0];

        //不变式
        //maxProfix记录[0, i)之间的最大profit
        //min 记录[0, i) 之间的最小值
        for (int i = 1; i < prices.length; i++) {

            maxProfit = Math.max(maxProfit, prices[i] - min);

            if (prices[i] < min) {
                min = prices[i];
            }
        }

        return maxProfit;
    }

}
