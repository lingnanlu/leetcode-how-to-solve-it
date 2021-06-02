package question.array;

import elder.Category;
import elder.Leetcode;

import java.util.TreeSet;

@Leetcode(
        title = "Contains Duplicate III",
        category = Category.TODO,
        howToSolveIt = """
                
                q. 你之前有没有遇到过类似的问题？
                
                a Contains Duplicate 2
                
                q. 这两者有什么区别？
                
                a 原来是相同，这里的相差t，其它一样。
                
                q 好，不妨我们先去掉k这个限制，毕竟之前你已经解决这个问题，现在我们专注与相差t，现在的问题成了什么？
                
                a 在数组中找两个元素，其相差最多是t, 即找a - b <= t
                
                q 那么，你有没有办法呢？
                
                a 最简单的是两两比较就行，但这个方法是O(n^2)
                
                q 你再看看未知，有没有遇到过类似的问题？
                
                a 如果有找过 a + b = k
                
                q 你能利用方法么？比如夹逼的思想？HashMap能用么？
                
                a 这里是a - b <= t 夹逼的话无法确定下一步是要增加小的，还是减少大的。HashMap也不行。
                
                q 看似之前的经验不能使用了，现在我们再回到原始问题，你需要在一组数中找到两个数 ，它们满足 a - b <= t，对吗？
                
                a 是的
                
                q 我们现在的结构是一个无序数组，在无序数组上，似乎只能使用暴力算法了，也就是说，对于当前的结构，我们想不到更好的方法了，所以
                我们可以试着重新组织一下结构，比如说，对于数组来说，将它排个序
                
                q 排序之后，你能想象一下，这个问题么？
                
                a 排序之后，就像在数轴上一样，t就是两者之间的距离，应该可以一遍遍历就可以了，我试试。
                
                a 好像不行，因为nums[i + 1] - nums[i] <= t的话，可能 nums[i + 2] - nums[i]也满足，这样并不能简化时间复杂度，所以排序也不行
                
                q 那么我们只能再想其它的数据结构喽？
                
                > 这题其实比较奇怪，如果我们不考虑k这个限制，其实是做不了的，必须加上K这个限制，而加上的话，就是滑动窗口和BST了，在窗口内找到一个满足
                |a -b | <= t的值就行，
                
                注意学习这里滑动窗口的实现方法
                
                k 其实暗示和滑动窗口
                
                其实简单的排序后，也能找到满足|a -b |的，只要对每个元素和其左，其右的元素进行相差比较就行。
                
                但这里麻烦就麻烦在还有一个K的限制，而排完序后就丢失了位置信息
                
                如果先添加位置信息再排序，这里，就又有麻烦的地方了，比如，满足t不满足k，所以，对于第一个元素，还要再往前找，这就成了O(n^2)了。
                
                **所以这里的问题关键是利用滑动窗口，解决了不再需要判断位置的问题。这是这一道题最大的启发，即利用滑动窗口，处理位置相关的问题**
         
               
                这里的关键是窗口的建立
                """
)
public class Contains_Duplicate_3 {

    // 这里的代码其实没有看上去那么好懂的，
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 1 || t < 0) return false;

        // set保存滑动窗口中的数据
        TreeSet<Integer> set = new TreeSet<>();

        for(int i = 0; i < nums.length; i++) {

            // 如果把set看成滑动窗口，那么，x并不在该窗口内的，
            // 这里其实是比较细节的，并不是看上去那么简单，alogrithm java中的解释并不严谨。
            final int x = nums[i];

            final Integer floor = set.floor(x);
            final Integer ceiling = set.ceiling(x);

            if ((floor != null && x <= floor + t) || (ceiling != null && x >= ceiling -t)) return true;
            //在滑动窗口中找|a - b| <= t

            // 找不到就继续移动窗口
            set.add(x);

            // 因为要求是|i - j| <= k,举例来
            if(i >= k) set.remove(nums[i - k]);

        }

        return true;

    }

}
