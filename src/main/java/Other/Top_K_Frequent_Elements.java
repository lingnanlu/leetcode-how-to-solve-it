package Other;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

@Leetcode(
        title = "Top K Frequent Elements",
        link = "https://leetcode.com/problems/top-k-frequent-elements/",
        category = Category.UNKNOWN,
        how2SolveIt = """
                方法一:
                最直观的, 统计每个元素出现的次数, 然后按次数进行倒序排列, 取前k个. 取前K个, 让人想到利用堆
                
                方法二:
                
                方法一用到了Map和PriorityQueue库函数, 似乎有点重了, 那有没有办法不用这些? 现在如何方便的统计数组中某一元素的个数呢?
                
                方法三:
                
                方法一是利用大顶堆, 比较直观, 但这里也可以使用小顶堆, 假如候选元素个数为10个, 要取前3最大的, 使用大顶堆就是全部插入
                然后从堆顶取前3个.
                
                而使用小顶堆的话, 就是将播入前3个, 插入第四个时, 把最小的排除, 这样, 堆的大小就一直是3, 相比大顶堆, 如果候选元素很多时
                可节约空间.
                
                """,
        relatedQuestions = {}
)
public class Top_K_Frequent_Elements {

    static class First {
        public int[] topKFrequent(int[] nums, int k) {

            Map<Integer, Integer> frequencyCount = new HashMap<>();
            for (int n : nums) {
                if(frequencyCount.containsKey(n)) {
                    frequencyCount.put(n, frequencyCount.get(n) + 1);
                } else {
                    frequencyCount.put(n, 1);
                }
            }

            PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());

            heap.addAll(frequencyCount.entrySet());

            int[] result = new int[k];

            for (int i = 0; i < k; i++) {
                result[i] = heap.remove().getKey();
            }

            return result;

        }
    }

    static class Thrid {

        public int[] topKFrequent(int[] nums, int k) {

            Map<Integer, Integer> frequencyCount = new HashMap<>();
            for (int n : nums) {
                if(frequencyCount.containsKey(n)) {
                    frequencyCount.put(n, frequencyCount.get(n) + 1);
                } else {
                    frequencyCount.put(n, 1);
                }
            }

            // 小顶堆也可
            PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

            // 注意如何利用小顶堆找前K大
            for (Map.Entry<Integer, Integer> entry : frequencyCount.entrySet()) {
                heap.add(entry);
                if (heap.size() > k) {
                    heap.remove();
                }
            }

            int[] result = new int[k];

            for (int i = 0; i < k; i++) {
                result[i] = heap.remove().getKey();
            }

            return result;

        }

    }

}
