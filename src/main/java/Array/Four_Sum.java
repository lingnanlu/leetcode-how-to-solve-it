package Array;

import run.elder.Category;
import run.elder.Leetcode;
import run.elder.Solution;

import java.util.*;

@Leetcode(
        title = "4sum",
        link = "https://leetcode.com/problems/4sum/",
        category = Category.ARRAY,
        how2SolveIt = """
                Q. 你遇到过这个问题么？

                A. 之间的3Sum, 只是这里是a + b + c + d= target ，固定其中一个，就可以是3Sum. 我想可以模仿3Sum的思路来写
                
                Q. 嗯，这次写的没问题，但这种嵌套for写的很不优雅，而且你发现它们是不是有相同的模式？假如是5Sum呢？ 
                另外，它似乎预示着这样一个规律：4Sum -> 3Sum -> 2Sum. 这种模式能给你什么启发么？

                A. 嗯，这种模式和递归很像，所以，使用递归可以简化代码。我试一试。
                
                Q. 好的。你可以试试使用DFS的思想来
                
                A. 写出DFS的解法. 
                
                """,
        relatedQuestions = {}
)
public class Four_Sum {

    @Solution(name = "嵌套写法",
    detail = """
             这个相当于利用之前的方法, 并不是利用结果.
             """)
    static class FourSumNest {
        public List<List<Integer>> fourSum(int[] nums, int target){

            List<List<Integer>> solutions = new ArrayList<>();
            Arrays.sort(nums);

            for(int h = 0; h <= nums.length - 4;) {
                for(int i = h + 1; i <= nums.length - 3;) {
                    for(int j = i + 1, k = nums.length - 1; j < k;) {
                        if (nums[h] + nums[i] + nums[j] + nums[k] == target) {
                            List<Integer> solution = Arrays.asList(nums[h], nums[i], nums[j], nums[k]);
                            solutions.add(solution);
                            j++;
                            while(j < k && nums[j - 1] == nums[j]) j++;
                        } else if (nums[h] + nums[i] + nums[j] + nums[k] > target) {
                            k--;
                        } else {
                            j++;
                        }
                    }
                    i++;
                    while(i <= nums.length - 3 && nums[i - 1] == nums[i]) i++;
                }

                h++;
                while(h <= nums.length - 4 && nums[h - 1] == nums[h]) h++;

            }
            return solutions;
        }
    }

    @Solution(
            name = "递归写法, 数学归纳法",
            detail = """
        你能利用之前的结果吗?
        其实这里是要证明一下的.
    """)
    static class FourSumRecursive {

        public List<List<Integer>> fourSum(int[] nums, int target) {
            Arrays.sort(nums);
            return kSum(nums, target, 4, 0);
        }

        public List<List<Integer>> kSum(int[] nums, int target, int k, int start) {

            List<List<Integer>> solutions = new ArrayList<>();

            if (k == 2) {
                for(int i = start, j = nums.length - 1; i < j;) {
                    if(nums[i] + nums[j] == target) {
                        List<Integer> solution = new LinkedList<>();
                        solution.add(nums[i]);
                        solution.add(nums[j]);
                        solutions.add(solution);
                        i++;
                        while(i < j && nums[i - 1] == nums[i]) i++;
                    } else if (nums[i] + nums[j] > target) {
                        j--;
                    } else {
                        i++;
                    }
                }
            } else {
                for(int i = start; i <= nums.length - k;) {
                    List<List<Integer>> subSolutions = kSum(nums, target - nums[i], k - 1,i + 1);
                    for (List<Integer> solution : subSolutions) {
                        solution.add(0, nums[i]);
                    }
                    solutions.addAll(subSolutions);

                    i++;
                    while(i <= nums.length - k && nums[i - 1] == nums[i]) i++;
                }
            }

            return solutions;
        }
    }

    @Solution(name = "DFS思想的写法")
    static class DFS {

        public List<List<Integer>> fourSum(int[] nums, int target) {
            Arrays.sort(nums);
            Tree tree = new Tree(nums, 4, target);
            Path path = new Path(4);
            // 一个启动节点，作为根，目的是要启动整个流程
            Node startNode = new Node(0, -1);
            walk(startNode, path, tree);
            return tree.solutions;
        }


        static class Path {
            private int length = 0;
            private int accumulate = 0;
            // 最好不要在进入结点前将结点的数据加入到path中，因为有加入，就要有删除。
            // 但想了想，使用DFS只能这样干，路径就要反映路径的信息，而不是
            private int[] path;

            public Path(int k) {
                path = new int[k];
            }

            public void add(int value) {
                path[length++] = value;
                accumulate += value;
            }

            public void removeLast() {
                length--;
                accumulate -= path[length];
            }

            public int getAccumulate() {
                return accumulate;
            }

            public int length() {
                return length;
            }

            public List<Integer> tolist() {

                List<Integer> result = new ArrayList<>(length);
                for(int i = 0; i < length; i++) {
                    result.add(path[i]);
                }

                return result;
            }
        }

        // 和每一个结点无关信息
        // 只传给visit
        static class Node {
            int val;
            int index;

            public Node(int val, int index) {
                this.val = val;
                this.index = index;
            }
        }

        // 层无关和结点无关的信息
        // 可以传给visit和walk
        // 可以有最终结果的保留信息
        static class Tree {
            int[] nums;
            int target;
            int k;
            List<List<Integer>> solutions = new ArrayList<>();
            public Tree(int[] nums, int k, int target) {
                this.k = k;
                this.nums = nums;
                this.target = target;
            }
        }

        /**
         * walk只是利用信息来决定访问路径
         * @param node 当前访问的结点
         * @param path 当前已经访问的路径信息
         * @param tree 全局信息
         */
        public void walk(Node node, Path path, Tree tree) {

          if (path.length() == 2) {

                for(int i = node.index + 1, j = tree.nums.length - 1; i < j;) {
                    if(tree.nums[i] + tree.nums[j] == tree.target - path.getAccumulate()) {
                        path.add(tree.nums[i]);
                        path.add(tree.nums[j]);
                        tree.solutions.add(path.tolist());
                        path.removeLast();
                        path.removeLast();

                        i++;
                        while(i < j && tree.nums[i - 1] == tree.nums[i]) i++;
                    } else if (tree.nums[i] + tree.nums[j] > tree.target - path.getAccumulate()) {
                        j--;
                    } else {
                        i++;
                    }
                }

            } else {

                // 这里相当于访问顺序
                for(int i = node.index + 1; i <= tree.nums.length - (tree.k - path.length());) {
                    Node nextNode = new Node(tree.nums[i], i);

                    // 最好不要在进入结点前将结点的数据加入到path中，因为有加入，就要有删除。
                    // 其实是做不到的，path必须反映其抽象

                    // 这相当于visit操作

                    path.add(tree.nums[i]);
                    walk(nextNode, path, tree);

                    // 另一个visit操作
                    path.add(tree.nums[i]);

                    i++;
                    while(i <= tree.nums.length - (tree.k - path.length()) && tree.nums[i - 1] == tree.nums[i]) {
                        i++;
                    }
                }
            }
        }


       // 不应该有layer这个概念，因为这是DFS，不应该有层相关的东西
//        static class LayerInfo {
//            int layer;
//            int k;
//            int accumulate;
//            public LayerInfo(int layer, int k, int accumulate) {
//                this.layer = layer;
//                this.k = k;
//                this.accumulate = accumulate;
//            }
//        }


        // visit规定了到达结点，离开结点时要做的操作，这个题比较简单，就不单独写出来了
        public void visit(Node node, Path path, Tree tree) { }
    }
}
