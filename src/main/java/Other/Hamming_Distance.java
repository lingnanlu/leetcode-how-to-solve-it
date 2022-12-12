package Other;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Hamming Distance",
        link = "https://leetcode.com/problems/hamming-distance/",
        category = Category.UNKNOWN,
        how2SolveIt = """
                这题一看就让人想到位运算, 其实就是按位异或, 然后统计结果中的1的个数.
                """,
        relatedQuestions = {}
)
public class Hamming_Distance {

    public int hammingDistance(int x, int y) {
        int result = x ^ y;

        int count = 0;
        while (result != 0) {
            if ((result & 1) == 1) {
                count++;
            }

            result >>>= 1;
        }

        return count;
    }
}
