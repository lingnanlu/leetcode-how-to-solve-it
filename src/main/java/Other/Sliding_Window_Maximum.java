package Other;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

@Leetcode(
        title = "Sliding Window Maximum",
        link = "https://leetcode.com/problems/sliding-window-maximum/",
        category = Category.UNKNOWN,
        how2SolveIt = """
                方法一: 
                最直观的做法, 在移动过程中, 计算出每个窗口的最大值
                
                假如 nums的个数是n, 该方法的时间复杂度是 O(n * k), 该方法在leetcode上超时
                
                方法二:
                
                我们要在方法一的基础上进行改进, 看看一是不是有一些冗余的重复计算.
                
                我们发现, n和k, 尽量要减少k
                
                我们前一次[i, i + k)时, 计算出来了最大值, 那么, 下一次计算时能不能用到上一次的结果?
                
                我觉得可以, 记录一下上一次最大值的位置就好
                
                该方法在leetcode能AC, 具该方法的时间和输入有关. 在完全倒序的情况下, 退化成O(n * k)
                
                注意循环内的代码, 一定要让不变式为真
                
                
                方法三: 行不通
                
                还是分析方法二, 方法二最耗时的地方在与, 如何向右滑了一位, 把最大的那个给滑出去了, 要重新遍历K个元素找最大.
                
                那么, 能不能消除重新遍历K个元素呢?
                
                其实, 在一个遍历过程中, 我们还可以找次大. 这样, 就算是向右滑了一位, 把最大划出去了, 我们还可以利用次大. 
                这样, 就不能遍历K个元素了.
                
                但这个方法行不通, 因为最大不在本次窗口内, 次大变成最大, 原来的次大没了, 为了寻找次大, 还得遍历
                
                方法四: 
                
                再仔细观察这个问题, 其实最关键的是, 要快速找出K的窗口内的最大值. K的窗口内的元素又是个数固定的.
                
                而且每次移动窗口, 都要移除一个值, 再加入一个值, 进而可能更新最大值. 那么, 怎么办呢, 所以这里要求
                
                1. 即方便找最大值
                2. 也方便更新
                
                那么怎么办呢?
                
                1. 如果是排序的话, 找出最大值方便, 但更新其实不方便, 要移动元素
                2. 想到最大堆?
                
                不知道为什么, 依然超时
                
                方法五: 
                
                依然是聚焦在找K个窗口中的最大元素, 不能用排序的方法, 不能使用堆, 这里只能自己再仔细观察, 
                
                怎么能找到滑动窗口中的最大元素
                
                在方法三中, 主要是保留次大, 次次大
                
                能不能方便的更新次大和次次大呢? 这是最关键的
                
                再仔细观察, 如果i指向最大, j指向次大, j > i, 因为i之前的没有意义, 那么, i之后的才有意义, 当然, 次大可能并不存在.
                
                这里相比于方法三的改进, 就是 再仔细分析问题, 发现次大应该在最大之后, 如果加上这个限制, 那么, 就可以很方便的更新次大了.
                
                
                方法六: 
                
                单调队列, 我感觉本质上方法五和方法六是一样的, 为什么五的耗时会长很多?
                
                要想理解这个问题, 还是得理解, 这个队列中保存的是什么, 队头是什么, 队尾是什么
                
                这个队列中的元素, 保存的就是当前滑动窗口中的元素
                
                以下这两句话是关键, 是不变式
                
                队首是当前滑动窗口中最大的
                后面的元素就是依次递减的, 且这些元素的index, 是严格从小到大的.
                
                为什么不只保存最大的? 因为在滑动窗口移动过程中, 最大的可能被移除.
                为什么要保存依次递减的, 这就是保存了滑动窗口内最大的第二大, 第三大, 第n大元素, 而且, 这些元素的index, 是严格从小到大的.
                
                启示:
                
                还是要仔细观察, 这里最trick的是, 想出这么一个单调队列, 作为一个辅助结构, 有点类似与KMP
                这里其实也有那么一点动态规划的意思了, 单调队列表示某种状态, 滑动窗口每走一步, 都要调整这个状态.
                而调整这个状态如果是常量级别的, 那么最后的时间复杂度就很好, 如果不是, 就很差.
                这个调整状态, 其实就是状态转移.
                每一次操作, 都要记录一些信息, 然后让状态转移越快越好.
                
                而且状态的检查, 依然离不开不变式.
                """,
        relatedQuestions = {}
)
public class Sliding_Window_Maximum {

    static class First {
        public int[] maxSlidingWindow(int[] nums, int k) {

            // 保存结果, nums.length - k + 1就是可能的窗口个数, 每个窗口都有一个最大值
            int[] result = new int[nums.length - k + 1];
            // result指针
            int p = 0;
            // 对每个窗口, 计算出最大的, i <= nums.length - k 画个图
            for (int i = 0; i <= nums.length - k; i++) {
                // 此时的窗口是, [i, i + k)
                int max = nums[i];
                for (int j = i; j < i + k; j++) {
                    max = Math.max(max, nums[j]);
                }
                result[p++] = max;
            }

            return result;
        }
    }

    static class Second {
        public int[] maxSlidingWindow(int[] nums, int k) {

            // 保存结果, nums.length - k + 1就是可能的窗口个数, 每个窗口都有一个最大值
            int[] result = new int[nums.length - k + 1];
            // result指针
            int p = 0;

            // 上一次最大值的索引
            // 注意这里为什么会初始化为-1, 对于第一次来说, 上一次的索引其实不存在的, 所以
            // 不能 >= 0, 那么, 给-1试试. 发现正好可以让每一次循环进行下去. 那么, 就可以了
            // 因为 -1 表示正好当 窗口划向0时, 把它排除了, 所以重新计算
            int lastMaxIndex = -1;

            // 对每个窗口, 计算出最大的, i <= nums.length - k 画个图
            for (int i = 0; i <= nums.length - k; i++) {
                // 此时的窗口是, [i, i + k)
                int max;
                // 先不急着遍历找
                if (lastMaxIndex != (i - 1)) {
                    // 上一次的最大还在本次的窗口内, 比较上一次的最大和本次新添加的最后一个元素大小.
                    if (nums[lastMaxIndex] > nums[i + k - 1]) {
                        max = nums[lastMaxIndex];
                        // 不更新lastMaxIndex
                    } else {
                        max = nums[i + k - 1];
                        // 更新lastMaxIndex
                        lastMaxIndex = i + k - 1;
                    }
                } else {
                    max = nums[i];
                    lastMaxIndex = i;
                    for (int j = i + 1; j < i + k; j++) {
                        if (nums[j] >= max) {
                            max = nums[j];
                            lastMaxIndex = j;
                        }
                    }
                }
                result[p++] = max;

            }

            return result;
        }
    }

    static class Third {
        public int[] maxSlidingWindow(int[] nums, int k) {

            if (k == 1) {
                // 特殊情况, 如果窗口中就一个元素, 就没有最大和次大的事了
                return nums;
            } else {

                // 此时, 窗口中至少两个元素, 所以可以使用最大与次大

                // 保存结果, nums.length - k + 1就是可能的窗口个数, 每个窗口都有一个最大值
                int[] result = new int[nums.length - k + 1];
                // result指针
                int p = 0;

                int lastMaxIndex = 0;
                int lastButTwoIndex = 0; // 次大
                // 对每个窗口, 计算出最大的, i <= nums.length - k 画个图
                for (int i = 0; i <= nums.length - k; i++) {

                    // 窗口是 [i, i + k)
                    // 第一次要遍历完找最大和次大
                    if (i == 0) {
                       if (nums[i] > nums[i + 1]) {
                           lastMaxIndex = i;
                           lastButTwoIndex = i + 1;
                       } else {
                           lastMaxIndex = i + 1;
                           lastButTwoIndex = i;
                       }

                       for (int j = i + 2; j < i + k; j++) {
                           if (nums[j] >= nums[lastMaxIndex]) {
                               lastButTwoIndex = lastMaxIndex;
                               lastMaxIndex = j;
                           } else if (nums[j] >= nums[lastButTwoIndex]) {
                               lastButTwoIndex = j;
                           }
                       }

                    } else {
                        // 非第一次
                        if (lastMaxIndex != (i - 1)) {
                            // 上一次的最大还在本次的窗口内
                            // 新加入的元素比最大还大
                            if (nums[i + k - 1] >= nums[lastMaxIndex]) {
                                // 原最大, 变成次大
                                lastButTwoIndex = lastMaxIndex;
                                // 更新新的最大
                                lastMaxIndex = i + k - 1;
                            } else if (nums[i + k - 1] >= nums[lastButTwoIndex]){
                                // 新加入的元素比最大小, 比次大大, 更新次大
                                lastButTwoIndex = i + k - 1;
                            }
                        } else {
                            // 上一次的最大不在本次窗口内了
                            // 这里行不通, 因为最大不在本次窗口内, 次大变成最大, 原来的次大没了, 为了寻找次大, 还得遍历
                        }

                    }

                    result[p++] = nums[lastMaxIndex];

                }

                return result;
            }


        }
    }

    static class Fourth {


        public int[] maxSlidingWindow(int[] nums, int k) {


            // 利用一个堆来方便找最大值
            // maxHeap是上一次滑动窗口中的元素组成的堆
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, (o1, o2) -> o2 - o1);

            // 保存结果, nums.length - k + 1就是可能的窗口个数, 每个窗口都有一个最大值
            int[] result = new int[nums.length - k + 1];
            // result指针
            int p = 0;

            // 对每个窗口, 计算出最大的, i <= nums.length - k 画个图
            for (int i = 0; i <= nums.length - k; i++) {
                // 此时的窗口是, [i, i + k)
                if (i == 0) {
                    for (int j = i; j < i + k; j++) {
                        maxHeap.add(nums[j]);
                    }
                } else {
                    // 此时maxHeap保存着上一次窗口中的最大.
                    // 满足不变式
                    maxHeap.remove(nums[i - 1]);
                    maxHeap.add(nums[i + k - 1]);
                }
                result[p++] = maxHeap.peek();
            }

            return result;
        }

    }

    static class Fifth {

        public int[] maxSlidingWindow(int[] nums, int k) {

            if (k == 1) {
                // 特殊情况, 如果窗口中就一个元素, 就没有最大和次大的事了
                return nums;
            } else {

                // 此时, 窗口中至少两个元素, 所以可以使用最大与次大

                // 保存结果, nums.length - k + 1就是可能的窗口个数, 每个窗口都有一个最大值
                int[] result = new int[nums.length - k + 1];
                // result指针
                int p = 0;

                // 不变式, lastMaxIndex指向本次的最大, lastButTwoIndex指向上一次的次大
                // lastButTwoIndex > lastMaxIndex 或者不存在
                int lastMaxIndex = -1;
                int lastButTwoIndex = 0;
                // 对每个窗口, 计算出最大的, i <= nums.length - k 画个图
                for (int i = 0; i <= nums.length - k; i++) {

                    // 窗口是 [i, i + k)
                    // 第一次要遍历完找最大和次大, 记住, 次大一定在最大的后面
                    if (i == 0) {

                        // 先找最大
                        lastMaxIndex = i;
                        for (int j = i + 1; j < i + k; j++) {
                            if (nums[j] >= nums[lastMaxIndex]) {
                                lastMaxIndex = j;
                            }
                        }

                        // 从最大后面找次大
                        lastButTwoIndex = lastMaxIndex + 1;
                        for (int j = lastButTwoIndex + 1; j < i + k; j++) {
                            if (nums[j] >= nums[lastButTwoIndex]) {
                                lastButTwoIndex = j;
                            }
                        }

                        // 此时找到了最大和次大, 注意, 次大可能不存在
                        // 这里用-1表示次大不存在, 其实也可以使用i + k, 但-1好像还好一些.
                        if (lastButTwoIndex == i + k) {
                            lastButTwoIndex = -1;
                        }

                    } else {
                        // 非第一次
                        // 此时调整lastMaxIndex和lastButTwoIndex让不变式为真, 两者此时指向上一次滑动窗口的最大和次大.
                        if (lastMaxIndex + 1 == i) {
                            // 上一次的最大不在本次窗口内了.
                            // 现在有, 上一次次大lastButTwoIndex和新加入的元素i + k - 1;

                            if (lastButTwoIndex != -1) {
                                // 次大存在
                                if (nums[i + k - 1] >= nums[lastButTwoIndex]) {
                                    // 窗口最后的元素比次大要大
                                    lastMaxIndex = i + k - 1;
                                    lastButTwoIndex = -1;  // 此时次大不存在了.
                                } else {
                                    // 窗口最后的元素比次大要小, 次大为最大.
                                    lastMaxIndex = lastButTwoIndex;

                                    // 找到新的次大
                                    // 其实这里依然是遍历, 但比原来的遍历整个窗口, 少了很多元素
                                    // 单调队列方法中的push操作本质上就是这个
                                    lastButTwoIndex = lastButTwoIndex + 1;
                                    for (int j = lastButTwoIndex + 1; j <= i + k -1; j++) {
                                        if (nums[j] >= nums[lastButTwoIndex]) {
                                            lastButTwoIndex = j;
                                        }
                                    }
                                }
                            } else {
                                // 次大不存在, 这说明上一次的最大就是窗口的开头, 也是窗口的结尾, 也k=1;
                                lastMaxIndex = i + k - 1;
                            }
                        } else {
                            // 上一次的最大还在本次的窗口内
                            // 新加入的元素比最大还大
                            if (nums[i + k - 1] >= nums[lastMaxIndex]) {
                                // 更新新的最大
                                lastMaxIndex = i + k - 1;
                                // 次大不存在
                                lastButTwoIndex = -1;
                            } else {
                                // 新加入的元素比最大小, 比次大大, 更新次大
                                if (lastButTwoIndex == -1) {
                                    // 没有次大, 新加入的就是次大
                                    lastButTwoIndex = i + k - 1;
                                } else {
                                    // 有次大, 新加入的比次大要大. 更新次大
                                    if (nums[i + k - 1] >= nums[lastButTwoIndex]){
                                        lastButTwoIndex = i + k - 1;
                                    }
                                }

                            }


                        }

                    }

                    result[p++] = nums[lastMaxIndex];

                }

                return result;
            }


        }

    }

    static class Sixth {

        public int[] maxSlidingWindow(int[] nums, int k) {

            // 创建一个队列, 满足如下不变式
            // 1. 队列中的元素是当前滑动窗口中的元素
            // 2. 队首是当前滑动窗口中最大的元素
            // 3. 后面的元素就是依次递减的, 且这些元素的index, 是严格从小到大的.
            LinkedList<Integer> queue = new LinkedList<>();
            // 每滑动一次, 更新一次队列, 由不变式可知道, 队列头部就是最大元素

            // 保存结果, nums.length - k + 1就是可能的窗口个数, 每个窗口都有一个最大值
            int[] result = new int[nums.length - k + 1];
            // result指针
            int p = 0;
            // 对每个窗口, 计算出最大的, i <= nums.length - k 画个图
            for (int i = 0; i <= nums.length - k; i++) {
                // 每一次滑动, 更新队列, 让其满足不变式
                // [i, i + k)
                if (i == 0) {
                    // 第一次, 相当于初始化队列
                    // 注意, 这里是最trick的地方, 看是怎么插入元素使其满足不变式的.
                    for (int j = i; j < i + k; j++) {

                        // 1. 从队尾开始删除元素, 直到第一个比要插入的元素大的元素,
                        while(!queue.isEmpty()) {
                            if (nums[queue.peekLast()] < nums[j]) {
                                queue.removeLast();
                            } else {
                                break;
                            }
                        }
                        // 2. 此时队尾的元素 >= 要插入的
                        queue.addLast(j);

                    }

                    // 到此, 队列满足不变式
                } else {

                    // 删除一个元素, 保证队列中的元素是当前滑动窗口中的元素
                    // 要删除的元素的index为 i - 1, 但i - 1不一定在queue里面, 如果在的话, 一定在队首
                    // 这个可以画个图证明一下
                    if (!queue.isEmpty() && queue.peekFirst() == i - 1) {
                        queue.removeFirst();
                    }

                    // 添加元素的index 为 i + k - 1, 和之前一样
                    while(!queue.isEmpty()) {
                        if (nums[queue.peekLast()] < nums[i + k - 1]) {
                            queue.removeLast();
                        } else {
                            break;
                        }
                    }
                    queue.addLast(i + k - 1);

                    // 此时, 队列满足不变式
                }

                result[p++] = nums[queue.peekFirst()];
            }

            return result;
        }

    }

}
