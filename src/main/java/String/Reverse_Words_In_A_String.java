package String;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.List;

@Leetcode(
        title = "Reverse Words in a String",
        link = "https://leetcode.com/problems/reverse-words-in-a-string/",
        category = Category.STRING,
        how2SolveIt = """
                方法一:
                最直观的, 将String切成一个字符串数组, 然后就像交换每个元素那样, 交换单词, 最后再拼接起来
                
                方法二:
                
                方法一使用了额外空间, 能不能不使用额外空间, 就原地翻转呢?
                这需要自己举个例子, 在纸上画一画
                
                自已实践一下可知
                1. 先全部翻转
                2. 再对每个单词进行翻转.
                
                当然也可以先翻转单词, 再全部翻转
                
                启示:
                
                方法二可以看出, 有很多边界条件, 以及指针操作, 其实, 只要你分步, 利用不变式, 就可以有信心的把它们做出来.
                该代码在没有调试的情况下, 也是一遍AC
                边写代码, 边写注释, 边写不变式
                """,
        relatedQuestions = {}
)
public class Reverse_Words_In_A_String {

    static class First {
        public String reverseWords(String s) {

            // 1. 转化成一个字符串数组
            List<String> words = splitToWords(s);
            // 2. reverse
            reverse(words);
            // 3. 将数组拼接成一个字符串
            StringBuilder sb = new StringBuilder();
            for (String word : words) {
                sb.append(word);
                sb.append(' ');
            }

            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();

        }

        private void reverse(List<String> words) {
            int i = 0, j = words.size() - 1;
            while (i < j) {
                String temp = words.get(i);
                words.set(i, words.get(j));
                words.set(j, temp);
                i++;
                j--;
            }
        }

        private List<String> splitToWords(String s) {

            /**
             * 不变式
             * [i, j)指向下一个单词的开头和末尾, 这里都初始化0没问题, 指向一个虚假的单词, 满足不变式.
             * result中储存已找到的单词
             */
            List<String> result = new ArrayList<>();
            int i = 0, j = 0;
            while (true) {
                // 1. 将i指向下一个单词的开头
                i = j;
                while(i != s.length() && s.charAt(i) == ' ') {
                    i++;
                }

                if (i == s.length()) {
                    // 找不到下一个单词了
                    break;
                } else {
                    // 2. 将j指向下一个单词的末尾
                    j = i + 1;
                    while(j != s.length() && s.charAt(j) != ' ') {
                        j++;
                    }
                    result.add(s.substring(i, j));
                }
            }

            return result;
        }
    }

    static class Second {
        public String reverseWords(String s) {

            char[] array = s.toCharArray();

            // 1. 去除多余的空格
            // i用来遍历 array, j是要插入的下一个位置
            int i = 0, j = 0;

            // 1.1 往前跳过前导空格
            while(i != s.length() && array[i] == ' ') {
                i++;
            }

            //全是空格, 返回一个空字符串
            if (i == s.length()) {
                return "";
            }

            // i指向下一个要复制的单词的开头
            // 每一轮都是复制一个单词, 然后添加一个空格
            // 如果还有字符可能要复制
            // 由i的变化过程可知, 这里可以使用!=
            while (i != s.length()) {

                // 复制一个单词
                while(i != s.length() && array[i] != ' ') {
                    array[j] = array[i];
                    j++;
                    i++;
                }

                // 为了简化操作, 系缚 个单词复制完成后, 再复制一个空格.
                // 注意, 这里要求结果数组的长度要比原来长1, 如
                // "hello world"
                // "hello world "
                // 所以, 这里要判断一下还有没有空间, 没有的话就不必添加空格了
                if (j != s.length()) {
                    array[j++] = ' ';
                }

                // 找到下一个单词的开始
                while (i != s.length() && array[i] == ' ') {
                    i++;
                }
            }


            // 消除最后一个可能添加的空格
            if (array[j - 1] == ' ') {
                j--;
            }

            // 至此, [0, j)之间的array就是处理后的字符串了, 下一步可以全部翻转
            // 2. 全部翻转

            int p = 0, q = j - 1;
            while (p < q) {
                char temp = array[p];
                array[p] = array[q];
                array[q] = temp;
                p++;
                q--;
            }

            // 现在已经全部翻转过来了, 然后就可以对每个
            // 3. 再对翻转后的每个单词进行翻转

            // 不变式
            // 1. p指向下一个要翻转的单词开头
            // 2. q用来指向下一个要翻转的单词末尾, 但这里初始化时并没有指向末尾, 而是给一个值让循环可以进行第一次
            //    可以理解成, q指向下一个要翻转的单词开头的下一个元素, 这样用不变式就好解释了.
            // 3. array[p...]是将要进行翻转的子数组
            // 这其实也是使用了不变式, 所以每轮循环也要让其为真
            // 这里并不是不变式, 因为q = 1, 但
            p = 0;
            q = 1;
            while (p != j) {
                // 找到单词的末尾
                while(q != j && array[q] != ' ') {
                    q++;
                }

                // 暂时记录下这个位置, 一会有用
                int k = q;
                q--;
                // 此时p指向开头, q指向末尾, 翻转它们之间的单词
                while (p < q) {
                    char temp = array[p];
                    array[p] = array[q];
                    array[q] = temp;
                    p++;
                    q--;
                }

                // 以下就是要让不变式再次为真
                // 1. p指向下一个单词开头
                p = k;
                while (p != j && array[p] == ' ') {
                    p++;
                }

                // 2. q指向下一个单词开头的后一个元素.
                q = p + 1;
            }

            return new String(array, 0, j);

        }
    }

}
