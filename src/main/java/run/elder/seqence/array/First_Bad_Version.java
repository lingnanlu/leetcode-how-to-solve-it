package run.elder.seqence.array;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "First Bad Version",
        link = "https://leetcode.com/problems/first-bad-version/",
        category = Category.ARRAY,
        how2SolveIt = """
                这题依然注意利用循环不变式搞明白i, j的含义
                """,
        relatedQuestions = {
        }
)
public class First_Bad_Version {

    public int firstBadVersion(int n) {

        int i = 1, j = n;

        /**
         * i, j为要搜索的版本区间
         * 不变式
         * < i的都是好版本
         * > j的都是坏版本
         */
        int middle = i + (j - i) / 2;

        // 还是要注意, 不要死循环, 要让循环停止
        while(i <= j) {
            if (isBadVersion(middle)) {
                j = middle - 1;
            } else {
                i = middle + 1;
            }

            middle = i + (j - i) / 2;
        }

        /**
         * 此时, i = j + 1
         * 而由不变式可知
         *
         * < i的都是好版本
         * > j的都是坏版本
         */
        return i;
    }

    private boolean isBadVersion(int n) {
        return true;
    }


}
