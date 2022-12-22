package String;

import java.util.HashSet;
import java.util.Set;

/**
 * 其实就是要快速判断每个石头是不是宝石.
 * 使用set来表示jewels
 */
public class Jewels_And_Stones {
    public int numJewelsInStones(String jewels, String stones) {

        Set<Character> jewelSet = new HashSet<>();

        for (char c : jewels.toCharArray()) {
            jewelSet.add(c);
        }

        int count = 0;
        for (char c : stones.toCharArray()) {
            if (jewelSet.contains(c)) {
                count++;
            }
        }

        return count;
    }
}
