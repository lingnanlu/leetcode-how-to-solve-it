package Array;

import java.util.ArrayList;
import java.util.List;

/**
 * 其实就是把extraCandies全给了每一个孩子, 看其是否是所有孩子中的最多.
 * 但每一次判断一个数是不是最多显然是重复工作.
 *
 * 于是就是先找出现有的最多, 然后和这个现有的最多比就好了.
 */
public class Kids_With_The_Greatest_Number_Of_Candies {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {

        // 保存现有的最多的.
        int max = 0;

        for (int i = 0; i < candies.length; i++) {
            max = Math.max(max, candies[i]);
        }

        // 试着分配给每一个孩子, 看其是不是能成为最多的.

        List<Boolean> result = new ArrayList<>();

        for (int i = 0; i < candies.length; i++) {
            if ((candies[i] + extraCandies) >= max) {
                result.add(true);
            } else {
                result.add(false);
            }

        }

        return result;
    }
}
