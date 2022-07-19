package star;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Wiggle Subsequence",
        link = "https://leetcode.com/problems/wiggle-subsequence/",
        category = Category.ARRAY,
        how2SolveIt = """
                本题的目标是找一个子序列, 且该子序列要满足某种特性, 且找最长的那个.
                
                方法一:
                
                穷举所有子序列(就是所有子集), 一一判断每一个是否满足这种交替的特性, 然后记录下最长的那个.
                
                方法二:
                
                这个子序列各以看成从原数组中选择多个元素
                
                每次选择一个
                每次有多个候选, 是否要选择这个要看满足不满足条件
                
                由以上讨论, 发现, 似乎可以使用DFS
                
                方法三: 动态规划
                
                即然是一个数组, 我们看看能不能使用O(n)的方法
                
                你遇到过类似的题目么, 其实, 就是找最大, 找最大的关键是一个由
                
                [0, n]中的最大, 求出 [0, n + 1]中的最大.
                
                以上是很容易证明的,
                
                类似的, 如果我们能从
                
                [0, n]中的最长, 求出 [0, n + 1]中的最长, 这样, 最终不就是求出整个数组的最长了么.
                
                所以, 这里的关键是, 假如我们知道, [0, n]中的最长, 那么, 如何得出[0, n+1]中的最长
                
                为了迭代下去, 在求[0, n]最长过程中, 要看看哪些信息需要记录的.
                
                
                假设, 我们现在知道了 [0, n]中最长的以下信息
                
                1. 长度 length
                2. 最后一个元素 last
                
                那么, 怎么求[0, n + 1]中的最长呢?
                
                如果[0, n + 1]中的最长的前缀是[0, n]中的最长, 那么就好说了, 比较 n + 1和last就行, 但会不会 [0, n]中的前缀不是[0, n+1]中的前缀呢
                
                这就需要证明一下, 假如, [0, n + 1]中的最长的前缀为另一个, 其长度为length2吧
                
                如果 length2 > length, 那么矛盾
                
                如果 length2 = length , 但两者不一样, 因为两者的长度一样,所以正负模式相同(即大小模式相同), 假设最后一个模式为小吧.
                
                那么, n + 1在什么情况下会增加长度呢? 因为下一个模式应该为大, 前一个元素越小越好, 所以last2比last1要小就行.
                
                如果最后一个模式为大, 下一个模式为小, 那么last应该越大越好.
                
                就给我们一个启示, 那就是, 对于一个模式, 如果有多个可以满足的, 那么就选择尽量大或尽量小的.
                
                如果 length2 < length 这个好像不怎么能证明
                
                抛开 length2 < length这个不谈, 我们似乎找到了递推的公式, 感觉应该也是这样的, 试着写出来
                
                
                启示:
                
                1. 这题复杂就复杂在形式证明上面, 并不是那么简单.
                2. 思路的启示来自于曾经未知类似的题目, 如最大值.
                3. 这种其实就是动态规划了, 动态规划有一个特点
                    1. 初始状态很简单
                    2. 由一个状态到最一个状态需要仔细推敲和证明(这是最难的一步)
                    3. 证明了第二步, 就可以考虑记录哪些状态了, 这时利用好不变式来确保正确性
                    
                其实求一个数组的最大值就满足以上三个特点, 就是最简单的一个动态规划
                4. 动态规划是由套路的, 一个题目符合以上三点即可, 而贪心还没什么套路.
               
               
                
                本题基本一遍过, 说明自己以上方法思路是正确的, 这题是一个典型的题目
         
                """,
        relatedQuestions = {}
)
public class Wiggle_Subsequence {

    static class Third {
        public int wiggleMaxLength(int[] nums) {

            if (nums.length == 0 || nums.length == 1) return nums.length;

            // 先找到第一个满足要求的两元素构成的子序列, 作为初始状态.

            int lastButTwo = 0;
            int last = 1;

            while(last != nums.length && nums[last] == nums[lastButTwo]) {
                last++;
                lastButTwo++;
            }

            // 所有元素都相同
            if (last == nums.length) {
                return 1;
            }

            // 此时找到第一对不同的元素, 作为迭代的初始化
            int count = 2;

            int p = last + 1;
            // 递推公式
            // 已知[0, p)的 => 求[0, p + 1)的
            while (p != nums.length) {
                // 最后模式是大, 下一个模式要小
                if (nums[last] > nums[lastButTwo]) {
                    if (nums[p] < nums[last]) {
                        lastButTwo = last;
                        last = p;
                        count++;
                    } else if (nums[p] > nums[last]){
                        last = p;
                    }
                } else if(nums[last] < nums[lastButTwo]){ // 最后模式是小, 下一个模式要大
                    if (nums[p] > nums[last]) {
                        lastButTwo = last;
                        last = p;
                        count++;
                    } else if (nums[p] < nums[last]){
                        last = p;
                    }
                }
                p++;
            }

            return count;
        }
    }
}
