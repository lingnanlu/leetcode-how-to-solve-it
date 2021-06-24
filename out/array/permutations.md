# Permutations

[https://leetcode.com/problems/permutations](https://leetcode.com/problems/permutations)

## 问题描述

Given a collection of **distinct** integers, return all possible permutations.

**Example:**

```text

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
```

## 解题思路

q 这个题是找出所有的，你之前有没有遇到过类似的

a 有，就是找出下一个

q 你能不能利用之前的结果

a 我想可以，不断找出下一个，直到有重复的为止

q 其实这个题还有一个解法，你看，我们想到得一个permutation，你是怎么做的

a 先确定第一位，再确定第二位，再确定第三。。。

q 你看，它是一个明显的分步的过程，而每一步又有很多种可能，这让你想到了什么

a DFS, 整个探索过程可以是一颗树。

q 好，使用DFS来写一下吧

## 相关题目

## 代码

```java
public class Permutations {

    @Solution("使用DFS")
    static class DFS {

        static class Node {
            int val;
            int index;

            public Node(int val, int index) {
                this.val = val;
                this.index = index;
            }
        }

        static class Path {
            List<Integer> path = new ArrayList<>();

            public void add(int val) {
                path.add(val);
            }

            public int length() {
                return path.size();
            }


            public void removeLast() {
                path.remove(path.size() - 1);
            }

            public boolean contain(int val) {
                return path.contains(val);
            }
        }

        static class Tree {
            int[] nums;
            List<List<Integer>> permutations;
            int height;

            public void add(List<Integer> permutation) {
                permutations.add(permutation);
            }

            public Tree(int[] nums, List<List<Integer>> permutations) {
                this.nums = nums;
                this.permutations = permutations;
                this.height = nums.length;
            }

            public int height() {
                return height;
            }
        }

        public void walk(Node node, Path path, Tree tree) {

            if (path.length() == tree.height - 1 && node.index != -1) {
                //说明走到叶子结点了

                path.add(node.val);
                tree.add(new ArrayList<>(path.path));
                path.removeLast();
            } else {

                if (node.index != -1) {
                    path.add(node.val);
                }

                for (int i = 0; i < tree.nums.length; i++) {
                    if (path.contain(tree.nums[i])) continue;
                    walk(new Node(tree.nums[i], i), path, tree);
                }

                if (node.index != -1) {
                    path.removeLast();
                }

            }
        }


        public List<List<Integer>> permute(int[] nums) {
            Node node = new Node(0, -1);
            Path path = new Path();
            List<List<Integer>> permutations = new ArrayList<>();
            Tree tree = new Tree(nums, permutations);
            walk(node, path, tree);
            return permutations;
        }
    }

    @Solution("使用NextPermutatiion")
    static class UsingNextPermutation {
        private void nextPermutation(int[] nums) {

            // 小于等于1位
            if (nums.length <= 1) return;

            // 只有两位，交换即可
            if (nums[nums.length - 2] < nums[nums.length - 1]) {
                int temp = nums[nums.length - 2];
                nums[nums.length - 2] = nums[nums.length - 1];
                nums[nums.length - 1] = temp;
            } else {

                // 3位或3位以上，从最低两位不断调整。
                // 先将低位置的调整成最大的，如果低位置的是最大的，就向前看一位

                int i = nums.length - 3;

                // 一直向前看，跳过最大的低位置
                while(i >= 0 && nums[i] >= nums[i + 1]) {
                    i--;
                }

                if(i < 0) {
                    // 说明整个序列是倒序的，这时反向排序就行
                    Arrays.question.sort(nums);
                } else {
                    // 找第一个比num[i]大的元素
                    int j = nums.length - 1;
                    while(j > i && nums[j] <= nums[i]) j--;

                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;

                    Arrays.question.sort(nums, i + 1, nums.length);

                }
            }

        }

        private List<Integer> array2list(int[] permutation) {
            List<Integer> result = new ArrayList<>();
            for (int value : permutation) {
                result.add(value);
            }
            return result;
        }


        private boolean permutationEqual(int[] a, int[] b) {

            for (int i = 0; i < a.length; i++) {
                if(a[i] != b[i]) return false;
            }

            return true;
        }

        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> permutations = new ArrayList<>();

            int[] first = nums;
            permutations.add(array2list(first));

            int[] next = Arrays.copyOf(first, first.length);
            nextPermutation(next);

            while(!permutationEqual(first, next)) {
                permutations.add(array2list(next));
                next = Arrays.copyOf(next, next.length);
                nextPermutation(next);
            }

            return permutations;

        }



    }

    public static void main(String[] args) {

        DFS question.dfs = new DFS();
        question.dfs.permute(new int[]{1});
    }
}
```

