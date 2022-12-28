package Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 方法一:
 *
 * 这题不难, 按照题意做就行了.
 *
 * 1. 从nums中找出最大度的数, 可能有多个
 * 2. 对于1中的每一个, 找nums中第一个和最后一个, 然后统计其长度, 找到最短的.
 *
 * 方法二:
 *
 * 方法一比较消耗时间, 它做了
 * 1. 遍历一遍nums O(n)
 * 2. 遍历两遍frequency O(n)
 * 3. 对于O(n)中的每一个, 又可能要遍历一次, 所以是O(n^2)
 *
 * 那么, 能不能是O(n)?
 * 我们遍历一次nums能不能得到更多信息, 然后利用这些信息呢? 方法一中, 遍历一次, 只是记录了频度
 * 那么,能不能在这个遍历过程中, 记录更多信息呢? 在遍历过程中, 其实可以得到每一个数的开始和结尾信息的.
 * 而开始结尾信息和计算长度有很密切的关系, 所以我觉得可以利用.
 *
 * 方法三:
 *
 * 方法二其实简化了第三步的O(n^2), 现在执行更快了, 能不能更快一些?
 * 减少几次O(n)?
 *
 * 遍历一次, 找到频率, 第一次出现位置, 最后一次出现位置, 这个能不能再得到更多的信息? 其实可以在这一次遍历过程中得到长度信息.
 *
 * 而且这里是使用了三个map, 其实只使用一个map就好了. 三个更浪费时间
 *
 * 然后, 在接下来的, 先找最大频度的数, 然后再找最短, 可不可以在一遍遍历过程中做到?
 *
 * 启示:
 *
 * 这一题充分体现出了从一个简单的解, 然后不断审视, 不断优化, 最后得出一个满意的解的过程.
 */
public class Degree_Of_An_Array {

    static class First {

        public int findShortestSubArray(int[] nums) {

            // 统计频数
            Map<Integer, Integer> frequency = new HashMap<>();

            for (int n : nums) {
                // 注意这个新函数, 用的还比较方便.
                frequency.merge(n, 1, Integer::sum);
            }

            // 现在frequency中的就是每个数的频度了.
            // 下一步就找最大的频度

            int maxFrequency = 0;
            for (int f : frequency.values()) {
                maxFrequency = Math.max(maxFrequency, f);
            }

            // 保存最大频度的数
            List<Integer> maxFrequencyNumber = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
                if (entry.getValue() == maxFrequency) {
                    maxFrequencyNumber.add(entry.getKey());
                }
            }

            // 记录最长
            int shortest = Integer.MAX_VALUE;

            for (int n : maxFrequencyNumber) {

                // 用于记录n的开始和结束位置
                int start = 0, end = 0;

                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] == n) {
                        start = i;
                        break;
                    }
                }

                for (int i = nums.length - 1; i >= 0; i--) {
                    if (nums[i] == n) {
                        end = i;
                        break;
                    }
                }

                shortest = Math.min(shortest, end - start + 1);
            }

            return shortest;
        }
    }

    static class Second {

        public int findShortestSubArray(int[] nums) {

            // 统计频数
            Map<Integer, Integer> frequency = new HashMap<>();

            // 记录一个数的开始位置
            Map<Integer, Integer> firstPosition = new HashMap<>();

            // 记录一个数的结束位置
            Map<Integer, Integer> lastPosition = new HashMap<>();

            for (int i = 0; i < nums.length; i++) {

                // 记录频率
                frequency.merge(nums[i], 1, Integer::sum);

                // 记录开始位置
                if (!firstPosition.containsKey(nums[i])) {
                    firstPosition.put(nums[i], i);
                }

                // 记录结束位置
                lastPosition.put(nums[i], i);

            }

            // 目前找到的最大频度

            int maxFrequency = 0;
            for (int f : frequency.values()) {
                maxFrequency = Math.max(maxFrequency, f);
            }

            // 保存最大频度的数
            List<Integer> maxFrequencyNumber = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
                if (entry.getValue() == maxFrequency) {
                    maxFrequencyNumber.add(entry.getKey());
                }
            }

            // 记录最短
            int shortest = Integer.MAX_VALUE;
            for (int n : maxFrequencyNumber) {
                int start = firstPosition.get(n);
                int end = lastPosition.get(n);
                shortest = Math.min(shortest, end - start + 1);
            }

            return shortest;
        }

    }

    static class Third {

        public int findShortestSubArray(int[] nums) {
            // 对于每一个数, 使用三元组 [频率, 第一次出现位置, 第一次和最后一次之间的长度]
            Map<Integer, int[]> triple = new HashMap<>();

            for (int i = 0; i < nums.length; i++) {

                // 第一次出现
                if (triple.get(nums[i]) == null) {
                    triple.put(nums[i], new int[]{1, i, 1});
                } else {
                    // 非第一次出现, 更新v
                    int[] v = triple.get(nums[i]);
                    v[0] = v[0] + 1;
                    v[2] = i - v[1] + 1;
                    triple.put(nums[i], v);
                }

            }

            // 想办法构造一个循环, 使以下断言一直为真.
            // 目前找到的最大频度
            int maxFrequency = 0;

            // 目前找到的最大频度的最短
            int shortest = Integer.MAX_VALUE;

            for (Map.Entry<Integer, int[]> entry : triple.entrySet()) {
                int[] value = entry.getValue();
                int key = entry.getKey();

              //  maxFrequency = Math.max(maxFrequency, value[0]);
                // 说明当前数就是目前找到的最大频度的数, 更新最短
//                if (value[0] == maxFrequency) {
//
//                    // 注意这样写并不对, shortest频度最大的相同的数之中, 最短的.
//                    // 比如shortest是最一个数, 而那个数的频度并不是目前最大的. 则, shortest要更新为当前这个数的距离.
//                    shortest = Math.min(shortest, value[2]);
//                }

                if (value[0] > maxFrequency) {
                    // 说明是一个新的最大值.
                    maxFrequency = value[0];
                    shortest = value[2];
                } else if (value[0] == maxFrequency){
                    // 说明是另一个相同频度的, 最大值的数
                    shortest = Math.min(shortest, value[2]);
                }

            }

            return shortest;
        }


    }


}
