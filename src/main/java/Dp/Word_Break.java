package Dp;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Leetcode(
        title = "Word Break",
        link = "https://leetcode.com/problems/word-break/",
        category = Category.ARRAY,
        how2SolveIt = """
                
                注意这题, 由一个数组中的元素拼成一个值, 而且元素的使用次数不限.
                这让你想起了什么? 完全背包
                
                那么, 思路大概就是
                
                1. 利用完全背包的方法
                2. 将题目转化一下, 转化成数字, 利用完全背包的一些结论.
                
                这题看起来不好转化成数字, 所以我们试试利用完全背包的方法.
                
                f(n)(s)为是否能组成s
                
                那么, f(n)和f(n - 1)是什么关系呢?
                
                即第n个选或不选
                
                不选第n个, 那么
                
                f(n)(s) = f(n - 1)(s)
                
                选第n个, 那么
                
                f(n)(s) = f(n)(s - word(n)) // 如果s包含word(n)
                
                但这里有个问题, 比如说
                
                dicts = [cats, cat]
                
                s = catscat.
                
                对于f(n)(s - word(n)), 这个 - 可以有两种
                
                一种是 scat
                
                另一种是 cats, 对于第一种, 显然 f(n)(scat)为false了. 所以这个 - 把要所有情况列出来, 即
                
                f(n)(s) = f(n - 1)(s) || f(n)(s - word(n))位置一, f(n)(s - word(n)) 位置二, 等等.
                
               
                现在, f(n)(s)是一个布尔值, 但如果利用s来做下标呢?  其实我们是想f(s) = boolean 这样一个映射
                
                那么, 可以试试map                
              
                方法二:
                
                二维还是复杂, 我们看看一维的, 假定dict是固定的.
                
                f(s) = ?
                
                f(s) = f(s - word[1]) || f(s - word[2]) || f(s - word[3] ...
                
                当然, 这里的 - 有两重含义
                
                1. 包含
                2. 可能有多个位置.
                
                现在这个位置有点棘手啊, 看看能不能不考虑位置, 或者把字符串看作整数. 还是举个例子.
                
                其实我们这里强烈的希望, f(s)利用s的字符长度作为下标, 我们试一试看看可不可以
                
                
                s = catscat
                
                dict = [cat, cats]
                
                
                dp[""] = t
                dp["c"] = f
                dp["ca"] = f
                dp["cat"] = true
                dp["cats"] = true
                dp["catsc"] = false
                dp["catsca"] = false
                dp["catscat"] = dp["scat"] = true, (这是因为我们实际记录的是dp[4])
                
                这显示不对, 其实还是要知道remain单词是什么, 那怎么利用数字来记录remain单词呢? 
                
                两个数字可以么?  其实是可以的, 因为, 0, length, 隐式表示了第一个单词的开头和结尾, 举两个例子.
                
                catscaa - cat = scat, [0, 3], 其实表示的是, [0, 0), [3, 7)
                
                catscaa - caa = cats, [4, 7], 其实表示的是, [0, 4), [7, 7)
                
                catscaa - tsc = ca...aa, [2, 5], 其实表示的是, [0, 2), [5, 7)   
                
                所以[a, b]表示, 第一个单词是 [0, a), 第二个单词是 [b, length)
                
                且 a <= b
                
                以上思路不正确, 还是不行.
                   
                   
                方法三:
                
                以上方法是把问题想复杂了, 问题是, 硬往背包问题上以及什么二维数组之类的去凑, 我们还是回归到最初的问题, 比如说, 记
                
                f(s) = T or F, 表示可以由wordDict来组成.
                
                那么, 
                
                f(s) 看样子像是一个递归(反方向就是迭代), 怎样去缩短到更小呢?
                
                其实比较直观的就是, 一个s由 wordDict组成, 那么, 其必然前缀是wordDict中的某一个单词, 并且剩下的也是由wordDict组成, 所以
                
                f(s) = wordDict.contain(prefix1) & f(remain1) || wordDict.contain(prefix2) & f(remain2) ....
                
                那么, 现在的问题有两个
                
                1. 这个前缀指的是什么
                2. 现在函数的参数是s, 怎么表示成数字呢? 
                
                先解决第二个问题, s可以用开始的索引来表示, 比如说
                
                f(s) = f(0, s.length()) 
                
                而对于以上, s.length()是不变的, 所以f(s) = f(0)
                
                这里, 最大的就是f(s.length())了.
                
                那么, 前缀是什么呢? 不如举一个例子. 比如  
                
                s = leetcode
                dict = ["leet", "code"]
                
                f(4) = f(code) = f(ode) & c在dict中 || f(de) & co在dict中 || f(e) & cod在字典中 || f() & code在dict中.
                
                至此找到规律了, 以下就是注意一下细节
                
                其实由以上递推公式可以得到一顆树, 对树进行DFS遍历, 每一个结点返回时的值是一个T or F. 对每一个结点求值是利用了其子结点的值.
                
                叶子结点就是可以直接求值的.
                
                
                """,
        relatedQuestions = {}
)
public class Word_Break {
    public boolean wordBreak(String s, List<String> wordDict) {

        boolean[] exist = new boolean[s.length() + 1];

        // 表示空子符串可以由wordDict组成
        exist[s.length()] = true;

        // exist[i] = T表示, s.substring(i, end)可以由wordDict组成.
        // 整个过程就是设置exist数组的值, 最后exist[0]就是要求的值.

        for (int i = exist.length - 1; i >= 0; i--) {

            // 每一轮判断 exist[i]
            // exist[i] = (exist[i + 1]) & wordDict.contain(i, i + 1) || (exist[i + 2] & wordDict.contain(i, i + 2)
            // .... (exist[s.length() + 1) & wordDict.conatin(i, s.length())
            for (int j = i + 1; j <= s.length() ; j++) {
                exist[i] = exist[i] || (exist[j] && wordDict.contains(s.substring(i, j)));
            }
        }

        return exist[0];

    }
}
