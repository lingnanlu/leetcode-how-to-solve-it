package string;

import java.util.*;

public class Group_Anagrams {

    // 这是一种通用的方法
    // 即按照一种规则，将每一个元素可以映射成一个key, 然后按照这个key进行分组
    public List<List<String>> groupAnagrams(String[] strs) {

        // 先利用一个中间结构
        Map<String, List<String>> key2Group = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            char[] tmp = strs[i].toCharArray();
            Arrays.sort(tmp);
            String groupKey = new String(tmp);

            if (!key2Group.containsKey(groupKey)) {
                key2Group.put(groupKey, new ArrayList<String>());
            }
            key2Group.get(groupKey).add(strs[i]);
        }

        List<List<String>> result = new ArrayList<>();
        for (String groupKey : key2Group.keySet()) {
            result.add(key2Group.get(groupKey));
        }

        return result;

    }


}
