#Permutations II

https://leetcode.com/problems/permutations-ii

### 问题描述

<p>Given a collection of numbers that might contain duplicates, return all possible unique permutations.</p>

<p><strong>Example:</strong></p>

<pre>
<strong>Input:</strong> [1,1,2]
<strong>Output:</strong>
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
</pre>

### 解题思路

q 这个和Permutations有什么不同？

a 这个包含重复元素。

q 这能利用Permutations的方法和思路么？

a 如果使用NextPermutations，应该不做修改就行

而使用DFS，我猜需要做一些剪枝操作

q 试一试


### 相关题目


### 代码

```java
public class Permutations_II {

    static class DFS {
        static class Node {
            int val;
            int index;

            public Node(int val, int index) {
                this.val = val;
                this.index = index;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Node node = (Node) o;
                return val == node.val &&
                        index == node.index;
            }

        }

        static class Path {
            List<Node> path = new ArrayList<>();

            public void add(Node node) {
                path.add(node);
            }

            public int length() {
                return path.size();
            }


            public List<Integer> toRealPath() {
                return path.stream().map(e -> e.val).collect(Collectors.toList());
            }

            public void removeLast() {
                path.remove(path.size() - 1);
            }

            public boolean contain(Node node) {
                return path.contains(node);
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

                path.add(node);
                tree.add(path.toRealPath());
                path.removeLast();
            } else {

                if (node.index != -1) {
                    path.add(node);
                }

                for (int i = 0; i < tree.nums.length;) {
                    Node nextNode = new Node(tree.nums[i], i);
                    if (path.contain(nextNode)) {
                        i++;
                        continue;       // 剪枝自身
                    }

                    walk(nextNode, path, tree);

                    i++;
                    while (i < tree.nums.length && tree.nums[i - 1] == tree.nums[i]) i++;
                }

                if (node.index != -1) {
                    path.removeLast();
                }

            }
        }

        public List<List<Integer>> permuteUnique(int[] nums) {
            Arrays.question.sort(nums);
            Node node = new Node(0, -1);
            Path path = new Path();
            List<List<Integer>> permutations = new ArrayList<>();
            Tree tree = new Tree(nums, permutations);
            walk(node, path, tree);
            return permutations;
        }
    }

    public static void main(String[] args) {
        DFS question.dfs = new DFS();
        question.dfs.permuteUnique(new int[]{1, 1, 2});
    }


}
```
