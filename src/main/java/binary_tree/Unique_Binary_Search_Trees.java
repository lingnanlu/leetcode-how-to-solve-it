package binary_tree;

import java.util.HashMap;
import java.util.Map;

// 这题就是一个数学归纳法。找f(n)的规律
// f(n) = f(0) * f(n - 1) + f(1) * f(n - 2)... + f(n - 1) * f(0)
// 也可以使用一维动态规则来解决
public class Unique_Binary_Search_Trees {

    private static Map<Integer, Integer> cache = new HashMap<>();

    static {
      cache.put(0, 1);
    }

    public int numTrees(int n) {
        if (cache.get(n) != null) {
            return cache.get(n);
        } else {
            int sum = 0;
            for(int i = 0; i <= n - 1; i++) {
                sum += numTrees(i) * numTrees(n - 1 - i);
            }
            cache.put(n, sum);
            return sum;
        }
    }
}
