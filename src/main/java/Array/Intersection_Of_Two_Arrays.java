package Array;


import run.elder.Category;
import run.elder.Leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Leetcode(
        title = "Intersection of Two Arrays",
        link = "https://leetcode.com/problems/intersection-of-two-arrays/",
        category = Category.ARRAY,
        how2SolveIt = """
              
                方法一:
                
                既然是求两个数组的交集, 那么, 就创建两个集合, 然后利用集合运算就行了, 这里就是依赖语言提供的库函数
                
                方法二:
                
                这题看起来不难, 引入Set这种复杂的数据结构没有必要, 本着使用简单的方式来解决问题的原则, 看能不能直接使用数组来解决.
                
                方法三:
                
                方法二中还是使用到了Set, 这是因为, 要对num中的元素去重, 否则, 对于乱序的, 不好直接判断是否重复, 所以, 可以先都排序
                这样可能就消灭了Set, 并且排序后, 是不是可以利用两者都有序这个性质做一些操作呢?
                """,
        relatedQuestions = {}
)
public class Intersection_Of_Two_Arrays {

    static class First {
        public int[] intersection(int[] nums1, int[] nums2) {
            Set<Integer> set1 = new HashSet<>();
            Set<Integer> set2 = new HashSet<>();

            for (int e : nums1) {
                set1.add(e);
            }

            for (int e : nums2) {
                set2.add(e);
            }

            Set<Integer> result = new HashSet<>();
            for (Integer e : set1) {
                if (set2.contains(e)) {
                    result.add(e);
                }
            }

            return result.stream().mapToInt(Integer::intValue).toArray();

        }
    }

    static class Second {

        public int[] intersection(int[] nums1, int[] nums2) {

            // 1. 遍历数量少的一方: 想想如果元素个数差距太大还是很可观的.
            // 2. 对数量少的一方中的每个元素, 查看其是否存在在另一方中(其实就是在另一方中查找是否存在, 想到二分查找)
            // 3. 如果存在, 放到结果中.

            Set<Integer> intersection = new HashSet<>();
            if (nums1.length < nums2.length) {
                // 排序
                Arrays.sort(nums2);
                for (int e : nums1) {
                    if (!intersection.contains(e) && exist(nums2, e)) {
                        intersection.add(e);
                    }
                }

            } else {
                Arrays.sort(nums1);
                for (int e : nums2) {
                    if (!intersection.contains(e) && exist(nums1, e)) {
                        intersection.add(e);
                    }
                }
            }

            return intersection.stream().mapToInt(Integer::intValue).toArray();


        }

        private boolean exist(int[] nums, int n) {
            int i = 0, j = nums.length - 1;
            while (i <= j) {
                int middle = (i + j) / 2;
                if (nums[middle] == n) {
                    return true;
                } else if (nums[middle] < n) {
                    i = middle + 1;
                } else {
                    j = middle - 1;
                }
            }

            return false;

        }
    }

    static class Third {

        public int[] intersection(int[] nums1, int[] nums2) {
            int[] result = new int[Math.min(nums1.length, nums2.length)];
            int p = 0; // result中下一个空位.

            Arrays.sort(nums1);
            Arrays.sort(nums2);

            // i, j用来依次遍历两个数组
            int i = 0, j = 0;
            while (i < nums1.length && j < nums2.length) {
                if (nums1[i] == nums2[j]) {
                    int n = nums1[i];
                    if (p == 0 || result[p - 1] != n) {
                        result[p++] = n;
                    }
                    i++;
                    j++;
                } else if (nums1[i] > nums2[j]) {
                    j++;
                } else {
                    i++;
                }
            }

            int[] finalResult = new int[p];
            for (int k = 0; k < p; k++) {
                finalResult[k] = result[k];
            }
            return finalResult;

        }

    }

}
