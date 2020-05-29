package sort;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1. 最直观的想法：从大到小排
 * 2. 你能想到一个类似未知的题目吗？堆排序， 先构建大顶堆，复杂度是O(logN), 然后弹出K次， 可以使用java的优先级队列， 复杂度为O(klogN)
 * 3. 选择排序， 每次选择一个最大的， k次后就可以得到第k个了。
 * 4. 使用一个k大小的小根堆，最终目标是构建的这个小根堆包括前k大的元素。O(nlogK)
 * 5. 利用快排的思路， 但是得记录下partition的位置
 */
public class Kth_Largest_Element_In_An_Array {

    // 选择排序
    public int findKthLargest1(int[] nums, int k) {

        // i 表示要插入的位置，i位置上的元素就是第i大的。
        for(int i = 0; i <= k; i++) {

            // 找到剩余数组中最大的元素
            int max = i;
            for(int j = i; j < nums.length; j++) {
                if (nums[j] > nums[max]) {
                    max = j;
                }
            }

            int temp = nums[i];
            nums[i] = nums[max];
            nums[max] = temp;
        }

        return nums[k];
    }

    // 大顶堆排序
    public int findKthLargest2(int[] nums, int k) {

        PriorityQueue<Integer> heap = new PriorityQueue<>(nums.length, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });


        for (int n : nums) {
            heap.offer(n);
        }


        int count = k - 1;
        while(count != 0) {
            heap.poll();
            count--;
        }

        return heap.poll();

    }

    // 小顶堆排序， 相比大顶堆， 不是那么直观, 最终的目标是构建的小顶堆包含最大的k个元素
    public int findKthLargest3(int[] nums, int k) {

        PriorityQueue<Integer> heap = new PriorityQueue<>(nums.length);

        for (int i = 0; i < k; i++) {
            heap.offer(nums[i]);
        }

        // 尽量把大的往堆里插入
        for (int i = k; i < nums.length; i++) {
            int min = heap.peek();
            if (nums[i] > min) {
                heap.poll();
                heap.offer(nums[i]);
            }
        }

        return heap.poll();

    }

    // 快排的思路
    // 时间复杂度是
    // 最好是一次partition就找到了， 此时时O(n)
    // 最坏是n次partition , 此时是O(n * n）比如 num = 1 6 5 4 3 2, k = 1
    public int findKthLargest4(int[] nums, int k) {
        return findKthLargest(nums, 0, nums.length - 1, k);
    }

    private int findKthLargest(int[] nums, int begin, int end, int k){

        int pivotIndex = partition(nums, begin, end);

        int leftLength = pivotIndex - begin;
        if (leftLength + 1 == k) {
            return nums[pivotIndex];
        } else if (leftLength + 1 > k) {
            return findKthLargest(nums, begin, pivotIndex - 1, k);
        } else {
            return findKthLargest(nums, pivotIndex + 1, end, k - (leftLength + 1));
        }
    }


    private int partition(int[] nums, int begin, int end) {

        if (begin == end) return begin;

        int pivot = nums[begin];

        // 这里的不变式其实就是
        // i(不包括i)之前的元素都比pivot大
        // j(不包括j)的元素都比pivot小
        // 那么最终i=j的位置就是pivot的位置
        // 一趟循环表式什么？ 表式一次从右向左和从左向右处理完成
        // 其实也可以设置一个标志位， 一趟从左向右， 一趟从右向左
        // i,j肯定能相遇
        int i = begin, j = end;
        while(i != j) {

            // 从右向左处理
            while(nums[j] <= pivot && j > i) {
                j--;
            }

            if (j == i) {
                break;
            } else {
                nums[i] = nums[j];
                i++;
            }

            // 从左向右处理
            while(nums[i] > pivot && i < j) {
                i++;
            }

            if (i == j) {
                break;
            } else {
                nums[j] = nums[i];
                j--;
            }

        }

        nums[i] = pivot;

        return i;
    }


    public static void main(String[] args) {
        int[] test = {3, 2, 1, 5, 6, 4};
        Kth_Largest_Element_In_An_Array a = new Kth_Largest_Element_In_An_Array();

        a.findKthLargest4(test, 2);
    }


}
