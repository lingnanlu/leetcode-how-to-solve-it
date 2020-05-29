package tanxin;


public class Best_Time_To_Buy_And_Sell_Stock_II {

    public int maxProfit(int[] prices) {

        /**
         * 只看明天, 如果明天价格高, 就明天卖, 这样赚.
         *
         * 如果明天价格低, 就今天卖, 这样不亏
         *
         * 最后的多次交易的和只会>=0
         */

        if(prices.length == 0) return 0;


        int maxProfit = 0;

        for (int i = 0; i <= prices.length - 2; i++) {
            if(prices[i + 1] > prices[i]) {
                maxProfit += prices[i + 1] - prices[i];
            }
        }

        return maxProfit;

    }


    public int maxProfit2(int[] prices) {

        if(prices.length == 0) return 0;

        int today = 0;
        int tomorrow = 1;
        int lastday = prices.length - 1;
        int maxProfit = 0;

        while(tomorrow <= lastday) {
            if(prices[today] < prices[tomorrow]) {
                maxProfit += prices[tomorrow] - prices[today];
            }
            today++;
            tomorrow++;
        }


        return maxProfit;

    }
}
