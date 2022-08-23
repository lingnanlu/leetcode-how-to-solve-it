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
                
                     
                """,
        relatedQuestions = {}
)
public class Word_Break {
    public boolean wordBreak(String s, List<String> wordDict) {
        int[][] dp = new int[s.length()][s.length()];

        // 这里我们要初始化, 比如说, 第一行是什么意思呢?


    }
}
