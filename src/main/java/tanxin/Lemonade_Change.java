package tanxin;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.HashMap;
import java.util.Map;

@Leetcode(
        title = "Lemonade Change",
        link = "https://leetcode.com/problems/lemonade-change/",
        category = Category.ARRAY,
        how2SolveIt = """
                其实最关键的是如下问题
                
                给了几张, 5, 10, 20的钞票, 问, 能不能凑出, 15, 5, 这样的数
                
                直观的做法是, 先用10块的, 再用5块的, 因为10可以由2个5代替, 而5不能用10来代替.
                """,
        relatedQuestions = {}
)
public class Lemonade_Change {

    public boolean lemonadeChange(int[] bills) {

        // 因为就两个元素, 这里也可以不使用数组
        // 每种钞票以及其个数
        int[] changesCount = new int[2];

        for (int i = 0; i < bills.length; i++) {

            // 1. 先收钱
            switch (bills[i]) {
                case 5 -> changesCount[0]++;
                case 10 -> changesCount[1]++;
            }

            // 1. 得到要找的钱的数额
            int n = bills[i] - 5; // 0, 5, 15

            if (n == 5) {
                if (changesCount[0] == 0) {
                    return false;
                } else {
                    changesCount[0]--;
                }
            } else if (n == 15){
                if (changesCount[1] != 0 && changesCount[0] != 0) {
                    changesCount[1]--;
                    changesCount[0]--;
                } else if (changesCount[1] == 0 && changesCount[0] >= 3){
                    changesCount[0] -= 3;
                } else {
                    return false;
                }
            }

        }

        return true;
    }
}
