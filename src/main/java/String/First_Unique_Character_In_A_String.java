package String;


import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "First Unique Character in a String",
        link = "https://leetcode.com/problems/first-unique-character-in-a-string/",
        category = Category.STRING,
        how2SolveIt = """
                寻找满足条件的字符, 这个潜在的最优应该就是O(n), 一遍遍历. 所以想办法进行一遍遍历.
                
                只用一个指针不行, 这里感觉要记录一些遍历过的信息.
                
                当然也可在一遍遍历过后, 此时已经得到了一些信息, 再进行一遍遍历就好.
                
                启示:
                遇到这次遍历题, 首先有一种预感, 就是可以O(n)
                
                然后, 如果要得到一些信息, 可以先一遍遍历得到一些信息, 然后再进行一遍遍历. 这样多遍遍历有时也不错.
                """,
        relatedQuestions = {}
)
public class First_Unique_Character_In_A_String {

    public int firstUniqChar(String s) {

        // 记录每个字符出现的次数
        int[] count = new int[26];

        // 第一遍统计其次数
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // 第一遍
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i)] == 1) {
                return i;
            }
        }

        return -1;

    }
}
