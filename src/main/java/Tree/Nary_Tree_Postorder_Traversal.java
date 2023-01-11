package Tree;

import java.util.*;

/**
 * N叉树后序遍历
 * <p>
 * 方法一:
 * 就是DFS
 * <p>
 * 方法二:
 * 其实就是利用栈来模拟递归过程.
 * <p>
 * 这里就要回顾一下栈的操作与遍历顺序与访问时机之间的关系.
 * <p>
 * DFS的本质其实就是: 遍历顺序一样, 但访问时机不同.
 * 我猜对入栈来说, 一定也有某种一样,和某种不同与之对应.
 * <p>
 * 对于栈来说, 就两种操作
 * 1. 入栈
 * 2. 出栈
 * <p>
 * 入栈对应的DFS的什么呢? 应该是走到了这个结点.
 * 但走到了并不代表要访问这个结点.
 * 出栈说明要从这个结点离开了, 也再也不会走这个结点了.
 * <p>
 * 我想, 就想DFS一样,可以写一个通用的模板.
 * <p>
 * 这个模板其实就是写了
 * <p>
 * 1.
 * 其实还是要不变式, 关键是明白
 * <p>
 * 1. 栈空的含义
 * 2. 栈顶元素含义
 * 3. 栈非空含义
 *
 *
 * 启示:
 *
 * 本题最有价值的点在于写出了一般化的模板.
 *
 */
public class Nary_Tree_Postorder_Traversal {

    static class First {

        public List<Integer> postorder(Node root) {
            List<Integer> list = new ArrayList<>();
            dfs(root, list);
            return list;
        }

        private void dfs(Node node, List<Integer> list) {
            if (node == null) {
                return;
            }

            for (Node child : node.children) {
                dfs(child, list);
            }

            list.add(node.val);
        }
    }

    static class Second {

        public List<Integer> postorder(Node root) {
            List<Integer> list = new ArrayList<>();

            Deque<Node> stack = new ArrayDeque<>();
            Deque<Integer> meetTimes = new ArrayDeque<>();

            if (root != null) {
                stack.push(root);
                meetTimes.push(0);
            }

            // 用来指向走到的结点
            Node next;
            while ((next = stack.peek()) != null) {
                Integer time = meetTimes.peek();
                if (time == next.children.size()) {
                    list.add(next.val);
                    meetTimes.pop();
                    stack.pop();
                } else {
                    Node child = next.children.get(time);
                    stack.push(child);
                    meetTimes.push(meetTimes.pop() + 1);
                    meetTimes.push(0);
                }
            }

            return list;

        }

        /**
         * 通用的模板,使用栈遍历结点
         */
        private void common(Node node) {

            /**
             * stack栈顶是下一步要走到的结点
             * time表示走到该结点时, 是第几次遇到
             * 0 = 表示第一次走到该结点
             * 1 = 表示从第一个孩子返回
             * children.size = 表示从所有孩子返回了.
             *
             * 所谓走到该结点, 就是通过peek取出该结点
             *
             * 两者的数据要对应起来.
             */
            Deque<Node> stack = new ArrayDeque<>();
            Deque<Integer> meetTimes = new ArrayDeque<>();

            if (node != null) {
                stack.push(node);
                meetTimes.push(0);
            }

            // 用来指向走到的结点
            Node next;
            while ((next = stack.peek()) != null) {
                // stack.peek表示走到下一结点. 此时走到一个结点
                // time 表示是第几次遇到next
                Integer time = meetTimes.peek();
                // 这里其实假设叶子结点的children size为0, 而不是null
                if (time == next.children.size()) {
                    // 此时是最后一次走到这个结点
                    // 注意这里也处理了没有孩子的情况.
                    // visit(node) 此时是一个后序访问时机
                    meetTimes.pop();
                    stack.pop();
                } else {
                    // 此时可以根据times的值来判断在第几个孩子返回时, 访问该结点.
                    // time == 0就是一个特殊值
                    if (time == 0) {
                        // 此时是第一次走到这个结点.
                        // 可用于先序遍历
                        // visit(node) 先序访问时机
                    }
                    // 此时表示, 只是从某个孩子回来, 还有其它孩子
                    // 得到下一个要访问的孩子
                    // 没有孩子的情况其实在上面处理了, 不需要在这里处理了. 所以这里一定是有孩子的.
                    Node child = next.children.get(time);

                    stack.push(child);

                    // 下一次走到next时, 是当前遇到的次数+1
                    meetTimes.push(meetTimes.pop() + 1);

                    // 这个对应的是孩子, 而不是next
                    meetTimes.push(0);

                }
            }
        }
    }
}
