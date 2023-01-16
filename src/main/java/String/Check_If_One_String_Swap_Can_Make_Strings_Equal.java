package String;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟题
 * 从前往后遍历, 找到两个位置不同的, 交换, 然后看剩下的相不相同
 *
 * 其实交换不直观, 这里感受到一中不同的东西, 能不能把交换转化成等价的不同?
 *
 * 就是把问题变化一下, 变化成一种更容易使用代码描述的
 *
 * 至多一次交换 等价与
 * 最多有两个位置的对应字符不同, 且前一个位置的字符和后一个位置的相同.
 *
 * 启示:
 *
 * 这题虽然简单, 但其实也有启发
 * 当未知不好使用代码描述时(就是不好翻译成简单的操作时), 可以找一个等价问题, 而这个等价问题方便使用代码来操作.
 *
 * 如这里的交换 => 相同和不相同. 以及数量.
 */
public class Check_If_One_String_Swap_Can_Make_Strings_Equal {

    public boolean areAlmostEqual(String s1, String s2) {

        // 字符不同的index
        List<Integer> diffIndex = new ArrayList<>();

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diffIndex.add(i);
            }
        }

        if (diffIndex.size() == 0) {
            return true;
        } else if (diffIndex.size() == 2) {
            return s1.charAt(diffIndex.get(0)) == s2.charAt(diffIndex.get(1)) &&
                    s1.charAt(diffIndex.get(1)) == s2.charAt(diffIndex.get(0));
        } else {
            return false;
        }
    }
}
