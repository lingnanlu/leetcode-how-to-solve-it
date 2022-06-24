package Array.SlideWindow;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Leetcode(
        title = "Fruit Into Baskets",
        link = "https://leetcode.com/problems/fruit-into-baskets/",
        category = Category.ARRAY,
        how2SolveIt = """
             
                这题描述有些啰嗦, 换一种说法
                
                已知
                1. 一个整数数组
                2. 数组都是正整数, 且可以有重复元素
                
                求
                
                这样一个子数组
                1. 最多包含两种值
                2. 在最多包含两种值的所有子数组中, 长度最长
                3. 在原数组中是连续的
                4. 给出该数组的长度
                
                方法一:
                
                先想一种直观的, 暴力的方法. 其实就是遍历所有满足要求的子数组, 在这些子数组中找出最长的.
                满足要求就是最多包含两种值.
                
                复杂度大概就是O(n^2), 注意观察i, j的移动规律
                
                方法二
                
                这题和之前的很相似(Minimum Size Subarray Sum)
                1. 都是一个连续的子数组
                2. 都是要求这个子数组满足一定条件
                3. 都是求所有满足要求的子数组中, 最xx那一个.
                所以思路可以借鉴
                
                O(n^2)的根源就是, j每次要从头移动, 所以看能不能不让它动
                
                
                方法三
                
                网上的使用快慢指针更精简一些, 但俺感觉不如方法二, 因为方法二的思路是自然的, 直观的, 而快慢指针就像是蹦出来的一样.
                """,
        relatedQuestions = {}
)
public class Fruit_Into_Baskets {

    static class First {

        public int totalFruit(int[] fruits) {

            int maxLength = 0;
            for (int i = 0; i < fruits.length; i++) {
                for (int j = i; j < fruits.length; j++) {
                    // 如果i,j 区间的子数组满足要求, 就
                    if (containsAtMostTwo(fruits, i, j)) {
                        maxLength = Math.max(maxLength, j - i + 1);
                    } else {
                        // 此时[i, j]之间的元素种类 > 2了, 就没必要再进行j++了
                        break;
                    }
                }
            }

            return maxLength;
        }

        // [i, j]之间包含最多两个
        private boolean containsAtMostTwo(int[] fruits, int i, int j) {

            Set<Integer> types = new HashSet<>();
            for (int k = i; k <= j ; k++) {
                types.add(fruits[k]);
            }
            return types.size() <= 2;
        }
    }

    static class Second {

        public int totalFruit(int[] fruits) {

            /**
             * j上一次循环后找到的区间的末index.
             * typeCount表示上一次找到的区间的元素及其个数, 排除了上一次区间的第一个元素的元素集合, typeCount.key().size() <= 2; (这里是要数学证明一下)
             * maxLength为截至到上一次为止, 所找到的最长的满足条件的子数组.
             */
            int maxLength = 0;
            int j = -1;
            Map<Integer, Integer> typeCount = new HashMap<>();

            for (int i = 0; i < fruits.length; i++) {
                // 每次循环, 都找到以fruits[i]为开头的最长的
                /**
                 * 不变式
                 * typeCount是[i,j]之间的元素type的count
                 */
                while (typeCount.size() <= 2) {
                    j++;
                    if (j != fruits.length) {
                        if (typeCount.containsKey(fruits[j])) {
                            typeCount.put(fruits[j], typeCount.get(fruits[j]) + 1);
                        } else {
                            typeCount.put(fruits[j], 1);
                        }
                    } else {
                        // 这个j--只是为了简化不变式.
                        j--;
                        // 此时没有元素了.
                        break;
                    }
                }

                /**
                 * 由不变式知道, typeCount是[i,j]之间的元素type的count
                 * 现在要让最外层的不变式为真.
                 */

                if (typeCount.keySet().size() <= 2) {
                    // 说明找到了最后一个元素都是 <= 2的, 那么不用再往后找了
                    return Math.max(maxLength, j - i + 1);
                } else {
                    typeCount.remove(fruits[j]);    // 删除最后一个
                    j--;                            // 减一是因为添加了fruits[j]之后, 才变成3个的.
                    maxLength = Math.max(maxLength, j - i + 1); // 本次找到的最长子数组

                    int count = typeCount.get(fruits[i]) - 1;
                    if (count == 0) {
                        typeCount.remove(fruits[i]);
                    } else {
                        typeCount.put(fruits[i], count);
                    }
                }
            }

            return maxLength;
        }

    }

    public static void main(String[] args) {
        Second test = new Second();

        int[] input = {3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4};

        test.totalFruit(input);
    }
}
