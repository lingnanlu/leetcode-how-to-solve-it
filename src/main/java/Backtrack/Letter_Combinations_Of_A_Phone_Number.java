package Backtrack;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Leetcode(
        title = "Letter Combinations of a Phone Number",
        link = "https://leetcode.com/problems/letter-combinations-of-a-phone-number/",
        category = Category.ARRAY,
        how2SolveIt = """
                
                嗯, 一看又是组和题
                
                digit的个数就是树的深度
                
                而每个数字对应的字母就是可能的选择
                
                
                """,
        relatedQuestions = {}
)
public class Letter_Combinations_Of_A_Phone_Number {

    static Map<Character, char[]> digitToChars = new HashMap<>();

    static {
        digitToChars.put('2', new char[]{'a', 'b', 'c'});
        digitToChars.put('3', new char[]{'d', 'e', 'f'});
        digitToChars.put('4', new char[]{'g', 'h', 'i'});
        digitToChars.put('5', new char[]{'j', 'k', 'l'});
        digitToChars.put('6', new char[]{'m', 'n', 'o'});
        digitToChars.put('7', new char[]{'p', 'q', 'r', 's'});
        digitToChars.put('8', new char[]{'t', 'u', 'v'});
        digitToChars.put('9', new char[]{'w', 'x', 'y', 'z'});

    }

    public List<String> letterCombinations(String digits) {

        if (digits.length() == 0) {
            return new ArrayList<>();
        }

        StringBuilder path = new StringBuilder();
        List<String> result = new ArrayList<>();

        char[] digitMapChars = digitToChars.get(digits.charAt(0));

        for (char e : digitMapChars) {
            walk(0, digits, e, path, result);
        }

        return result;
    }

    /**
     *
     * @param pos 走到digits的某个位置
     * @param c 走到了digits的某个位置数字对应的某个字符
     * @param path
     * @param result
     */
    private void walk(int pos, String digits, char c, StringBuilder path, List<String> result) {

        path.append(c);

        if (pos == digits.length() - 1) {
            result.add(path.toString());
        } else {
            char[] digitMapChars = digitToChars.get(digits.charAt(pos + 1));
            for (char e : digitMapChars) {
                walk(pos + 1, digits, e, path, result);
            }
        }

        path.deleteCharAt(path.length() - 1);
    }
}
