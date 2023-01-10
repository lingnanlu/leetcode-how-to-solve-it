package Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 方法一:
 * 看样子最直观的就是穷举出所有两两的数对, 找出符合条件的解.
 *
 *
 * 方法二:
 *
 * 能不能优化一个方法一呢?
 *
 * 其实方法一就是对于每一个数, 在剩余的数中找和它相等的.
 *
 * 那么,能不能快速从剩余的数中找到和它相等的, 而排序那么和它不等的?
 *
 * 其实最重要的是和一个数相等的数的个数, 如 [1,2,3,1,1,3]
 *
 * [1, 1, 1] = 3
 * [3, 3] = 1
 *
 * 其实这里给了我们一个思路, 就是将数组的数分组, 然后找每一个组的组合数.
 * 分组就想到了hash
 *
 *
 * 方法三:
 *
 * 方法二本质上是把相同的数放一起, 还有其它方式么? 对于数组, 其实可以排序
 * 这样也把相同的数放在了一起.
 *
 * 方法四:
 *
 * 题目中给出了
 *
 * 1 <= nums[i] <= 100
 * 这个条件, 目前来看, 还没有利用上, 怎么利用上呢?
 *
 * 可以把hash简化一下.
 *
 * 注:
 *
 * 这里的组合其实是C(n, 2) = n ( n - 1 ) / 2, 其实是可以使用公式来计算出来的.
 *
 * 方法五
 *
 * 还是这个条件, 1 <= nums[i] <= 100
 *
 * 其实我们发现, 无论是方法二, 三, 四, 其本质都是一样的, 都是要记录出现的位置, 但位置重要么? 能不能只使用一个整数值?
 *
 * 其实仔细一想是可以的, 位置不重要, 比如 [1, 1, 1, 1], 我们只要知道出现的次数就行, 然后利用C(n, 2)就可以得出. 这个n就是次数.
 *
 * 我们只要保留n就行.
 *
 * 方法六:
 *
 * 其实还是记录出现的次数, 只是不使用C(n, 2)了,
 *
 * 比如 [1, 1, 1, 1]
 *
 * 当第一次遍历到1时, 有数组对 0
 * 第二次遍历到1时, 有数组对 1
 * 第三次 有数组对 2 (和前面的组成)
 * 第四次, 有数组对 3.
 *
 * 启示:
 *
 * 还是要仔细读题, 题目中的已知往往能给你一些线索.
 */
public class Number_Of_Good_Pairs {

    static class First {
        public int numIdenticalPairs(int[] nums) {
            int count = 0;

            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] == nums[j]) {
                        count++;
                    }
                }
            }

            return count;
        }
    }

    static class Second {

        public int numIdenticalPairs(int[] nums) {
            Map<Integer, List<Integer>> group = new HashMap<>();

            for (int i = 0; i < nums.length; i++) {
                if (!group.containsKey(nums[i])) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    group.put(nums[i], list);
                } else {
                    group.get(nums[i]).add(i);
                }
            }

            int count = 0;
            for (int num : group.keySet()) {
                List<Integer> positions = group.get(num);

                for (int j = 0; j < positions.size(); j++) {
                    for (int k = j + 1; k < positions.size(); k++) {
                        count++;
                    }
                }
            }

            return count;
        }

    }

    static class Fifth {

        public int numIdenticalPairs(int[] nums) {

            // 下标是值, 值是出现的次数
            int[] counts = new int[100];

            for (int num : nums) {
                counts[num - 1]++;
            }

            int result = 0;

            for (int count : counts) {
                result += count * (count - 1) / 2;
            }

            return result;
        }



    }

    static class Forth {
        public int numIdenticalPairs(int[] nums) {

            // 下标是值, 值是出现的位置
            ArrayList[] positions = new ArrayList[100];

            for (int i = 0; i < nums.length; i++) {
                if (positions[nums[i] - 1] == null) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(i);
                    positions[nums[i]  - 1] = list;
                } else {
                    positions[nums[i] - 1].add(i);
                }
            }

            int count = 0;

            for (int i = 0; i < positions.length; i++) {
                ArrayList<Integer> position = positions[i];

                if (position != null) {
                    for (int j = 0; j < position.size(); j++) {
                        for (int k = j + 1; k < position.size(); k++) {
                            count++;
                        }
                    }
                }

            }

            return count;



        }


    }

}
