package String;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.*;

@Leetcode(
        title = "Group Anagrams",
        link = "https://leetcode.com/problems/group-anagrams/",
        category = Category.STRING,
        how2SolveIt = """
                方法一:
                
                最直观的, 依次判断每一个, 然后进行归类. 但这个超时了.
                
                方法二:
                
                仔细分析方法一中最耗时的地方, 就是将一个s进行归类时, 每次都要和所有的现有类别进行比较, 
                那么, 能不能快速的进行归类呢? 
               
                看了一下其它人的答案, 一种是排序, 另一种是统计str的中每个字符出现的次数, 然后构建新的字符串做为key
                这两种的思路都是一样的.
                
                我感觉这个题不太好, 无法层层递进, 而且也不好说明白时间复杂度
                """,
        relatedQuestions = {}
)
public class Group_Anagrams {

    static class First {
        public List<List<String>> groupAnagrams(String[] strs) {

            Map<String, List<String>> group = new HashMap<>();

            for (String s : strs) {

                boolean groupd = false;

                // 判断属于哪一类, 如果属于现有的类, 就归类
                for (String type : group.keySet()) {
                    if (isAnagram(s, type)) {
                        group.get(type).add(s);
                        groupd = true;
                        break;
                    }
                }

                if (!groupd) {
                    // 非归类,
                    group.put(s, new LinkedList<>());
                    group.get(s).add(s);
                }

            }

            List<List<String>> result = new ArrayList<>();
            for (String type : group.keySet()) {
                result.add(group.get(type));
            }

            return result;

        }

        public boolean isAnagram(String s, String t) {

            if (s.length() != t.length()) {
                return false;
            }

            int[] charCounts = new int[26];


            // 统计s中的字符出现的次数
            for (int i = 0; i < s.length(); i++) {
                charCounts[s.charAt(i) - 'a']++;
                charCounts[t.charAt(i) - 'a']--;
            }


            for (int i = 0; i < 26; i++) {
                if (charCounts[i] != 0) {
                    return false;
                }
            }

            return true;

        }


    }

}
