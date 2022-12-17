package Array;

import java.util.*;

/**
 *
 * 方法一:
 * 第一步: 统计每个元素的频率
 * 第二步: 按照频率排序, 同频率的按照数值排序
 * 第三步: 再展开
 *
 * 这个方法的问题是, 数字的频率如果使用map来保存的话, 如何方便的对map进行排序?
 *
 * 方法二:
 * 就对原数组进行排序, 只是这个排序需要利用额外的频率信息
 * 但该方法相比方法一, 排序的元素数量更多, 其实不如一
 * 而且一感觉更直观一些
 *
 * 方法二写法更简洁, 方法一效率更好, 但因为java丑陋和啰嗦的语言, 使得代码在写的过程中很不爽.
 */
public class Sort_Array_By_Increasing_Frequency {

    static class First {
        public int[] frequencySort(int[] nums) {
            Map<Integer, Integer> frequencyMap = new HashMap<>();

            for (int n : nums) {
                if (frequencyMap.containsKey(n)) {
                    frequencyMap.put(n, frequencyMap.get(n) + 1);
                } else {
                    frequencyMap.put(n, 1);
                }
            }

            // 先转化成列表, 好排序
            List<Map.Entry<Integer, Integer>> frequencyList = new ArrayList<>();
            frequencyList.addAll(frequencyMap.entrySet());

            // 排序
            Collections.sort(frequencyList, (o1, o2) -> {
                if (o1.getValue() < o2.getValue()) {
                    return -1;
                } else if (o1.getValue() > o2.getValue()) {
                    return 1;
                } else {
                    return -(o1.getKey() - o2.getKey());
                }
            });

            int[] result = new int[nums.length];
            int i = 0;
            // 展开
            for (Map.Entry<Integer, Integer> each : frequencyList) {
                int value = each.getKey();
                int frequency = each.getValue();

                while (frequency != 0) {
                    result[i] = value;
                    i++;
                    frequency--;
                }
            }

            return result;
        }
    }

    static class Second {
        public int[] frequencySort(int[] nums) {

            Map<Integer, Integer> frequency = new HashMap<>();

            for (int n : nums) {
                if (frequency.containsKey(n)) {
                    frequency.put(n, frequency.get(n) + 1);
                } else {
                    frequency.put(n, 1);
                }
            }

            // java不能直接对int[]使用comparator, 有点恶心
            return Arrays.stream(nums).
                    boxed().
                    sorted((a, b) -> {
                        if (frequency.get(a) < frequency.get(b)) {
                            return -1;
                        } else if (frequency.get(a) > frequency.get(b)) {
                            return 1;
                        } else {
                            return -(a - b);
                        }
                    }). // sort descending
                    mapToInt(i -> i).
                    toArray();


        }
    }

}
