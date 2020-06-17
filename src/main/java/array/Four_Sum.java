//package array;
//
//import elder.Category;
//import elder.Deprecation;
//import elder.Evolution;
//import elder.Leetcode;
//import elder.Solution;
//
//import java.util.*;
//
//@Leetcode(
//        title = "4Sum",
//        category = Category.ARRAY,
//        howToSolveIt = """
//                q. 你遇到过这个问题么？
//
//                a. 和之前的3Sum一样。试着写出来.
//
//                q. 嗯，这次写的没问题，但这种嵌套for写的很不优雅，而且你发现它们是不是有相同的模式？
//
//                q. 另外，它似乎预示着这样一个规律：4Sum -> 3Sum -> 2Sum. 这种模式能给你什么启发么？
//
//                a. 嗯，这种模式和递归很像，所以，使用递归可以简化代码。我试一试。
//
//                """
//)
//public class Four_Sum {
//
//    @Evolution
//    public List<List<Integer>> solution_0(int[] nums, int target){
//
//        if (nums.length < 4) return new ArrayList<>();
//
//        List<List<Integer>> result = new ArrayList<>();
//
//        Arrays.sort(nums);
//
//        Map<Integer, List<Integer>> num2Indexs = new HashMap<>();
//        for (int i = 0; i < nums.length; i++) {
//            List<Integer> indexs = num2Indexs.get(nums[i]);
//            if (indexs == null){
//                indexs = new ArrayList<>();
//                indexs.add(i);
//                num2Indexs.put(nums[i], indexs);
//            } else {
//                indexs.add(i);
//            }
//        }
//
//        // 创建一个和a不等的
//        int preA = nums[0] - 1;
//        for(int i = 0; i <= nums.length - 4; i++) {
//
//            int a = nums[i];
//            if (a == preA) continue;
//
//            int preB = nums[i + 1] - 1;
//            for(int j = i + 1; j <= nums.length - 3; j++) {
//
//                int b = nums[j];
//                if (b == preB) continue;
//
//                int preC = nums[j + 1] -1;
//                for (int k = j + 1; k <= nums.length - 2; k++) {
//
//                    int c = nums[k];
//                    if(c == preC) continue;
//
//                    int d = target - a - b - c;
//
//                    List<Integer> dIndexes = num2Indexs.get(c);
//                    if (dIndexes != null) {
//                        for (int dIndex : dIndexes) {
//                            if (dIndex > k) {      // 排除本身，因为不允许同一元素使用两次
//                                result.add(Arrays.asList(a, b, c, d));
//                                break;
//                            }
//                        }
//                    }
//                    preC = c;
//                }
//                preB = b;
//            }
//            preA = a;
//        }
//
//        return result;
//    }
//
//    public List<List<Integer>> fourSum_2(int nums[], int target){
//        List<List<Integer>> solutions = new ArrayList<>();
//        Arrays.sort(nums);
//
//        if (nums.length < 4) return new ArrayList<>();
//
//        kSum0(nums, target, 4, 0, solutions);
//
//        return solutions;
//    }
//
//    // 不再使用HashMap, 节省空间，以及构造的时间
//    // 使用夹逼的思想，两个指针往中间移动
//    private List<List<Integer>> twoSum0(int nums[], int target, int start) {
//        List<List<Integer>> solutions = new ArrayList<>();
//        int i = start, j = nums.length - 1;
//        while(i < j) {
//            if(nums[i] + nums[j] == target) {
//                List<Integer> oneSolution = new LinkedList<>();
//                oneSolution.add(nums[i]);
//                oneSolution.add(nums[j]);
//                solutions.add(oneSolution);
//                while(i < j && nums[i + 1] == nums[i]) i++;
//                while(i < j && nums[j - 1] == nums[j]) j--;
//            } else if (nums[i] + nums[j] > target) {
//                j--;
//            } else {
//                i++;
//            }
//        }
//        return solutions;
//    }
//
//
//    private List<List<Integer>> kSum0(int nums[], int target, int k, int start) {
//        if (k == 2) {
//            solutions.addAll(twoSum0(nums, target, start));
//        } else {
//            int i = start;
//            while(i <= nums.length - k) {
//                List<List<Integer>> subSolutions = new ArrayList<>();
//                kSum(nums, target - nums[i], start + 1, subSolutions);
//                for (List<Integer> partialSolution : subSolutions) {
//                    partialSolution.add(0, nums[i]);
//                }
//
//                //跳出循环时，nums[i + 1] != nums[i]
//                while(i <= nums.length - k && nums[i + 1] == nums[i]) i++;
//            }
//        }
//    }
//    private List<List<Integer>> kSum(int nums[], int target, int k, int start) {
//        if (k == 2) {
//
//            // 这里可以优化，每次都要建造一个HashMap
//            List<List<Integer>> result = new ArrayList<>();
//            Map<Integer, List<Integer>> num2Indexs = new HashMap<>();
//            for (int i = start; i < nums.length; i++) {
//                List<Integer> indexs = num2Indexs.get(nums[i]);
//                if (indexs == null){
//                    indexs = new ArrayList<>();
//                    indexs.add(i);
//                    num2Indexs.put(nums[i], indexs);
//                } else {
//                    indexs.add(i);
//                }
//            }
//
//            // 创建一个和a不等的
//            int pre = nums[0] - 1;
//            for(int i = start; i <= nums.length - 2; i++) {
//                int a = nums[i];
//
//                if (a == pre) continue;
//                int b = target - a;
//
//                List<Integer> bIndexes = num2Indexs.get(b);
//                if (bIndexes != null) {
//                    for (int bIndex : bIndexes) {
//                        if(bIndex > i) {      // 排除本身，因为不允许同一元素使用两次
//                            List<Integer> temp = new ArrayList<>();
//                            temp.add(a);
//                            temp.add(b);
//                            result.add(temp);
//                            break;
//                        }
//                    }
//                }
//                pre = a;
//            }
//            return result;
//        } else {
//            List<List<Integer>> result = new ArrayList<>();
//            int pre = nums[start] + 1;
//            for(int i = start; i <= nums.length - k; i++) {
//                int n = nums[i];
//                if (n == pre) continue;
//                List<List<Integer>> partialResult = kSum(nums, target - n, k - 1, i + 1);
//                System.out.println("n = " + n + " " + partialResult);
//                for (List<Integer> e : partialResult) {
//                   e.add(0, n);
//                   result.add(e);
//                }
//                pre = n;
//            }
//
//            return result;
//        }
//    }
//
//    @Deprecation
//    public List<List<Integer>> fourSum(int[] nums, int target) {
//
//        Set<List<Integer>> result = new HashSet<>();
//        Arrays.sort(nums);
//
//        for(int i = 0; i < nums.length - 3; i++) {
//       //     if (nums[i] > target) break; 注意这行对负数不成立
//            for(int j = i + 1; j < nums.length - 2; j++) {
//
//                // 关键是最内层的， 找到了相同的后， 怎样去移动指针
//
//                int m = j + 1, n = nums.length -1;
//                while (m < n) {
//                    int sum = nums[i] + nums[j] + nums[m] + nums[n];
//
//                    if(sum == target) {
//                        result.add(Arrays.asList(nums[i], nums[j], nums[m], nums[n]));
//
//                        //随变移动一位就行
//                        m++;
//                    } else if(sum > target) {
//                        n--;
//                    } else {
//                        m++;
//                    }
//                }
//
//            }
//        }
//
//        return new ArrayList<>(result);
//    }
//
//    public static void main(String[] args) {
//        Four_Sum test = new Four_Sum();
//        int[] nums = {1,0,-1,0,-2,2};
//        System.out.println(test.fourSum_2(nums, 0));
//    }
//}
