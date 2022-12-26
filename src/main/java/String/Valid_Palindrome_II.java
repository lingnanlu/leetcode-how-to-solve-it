package String;

/**
 * 之前类似的题目是验证一个字符串是否是回文串, 只是不要求删除.
 *
 * 方法一:
 *
 * 一种最直观的方法就是, 利用之前验证一个字符串是否是回文串的方法, 只是这次将每个字符都删除一遍, 然后再进行判断
 * 但这种方法很显然, 时间复杂度很高.
 *
 * 方法二:
 *
 * 再次读题, 如果一个字符串是回文那么就是在不删除字母情况下, 是回文, 或者删除一个字母的情况下是回文.
 *
 * 这里的关键是第二种情况, 删除一个字母的情况下是回文.
 *
 * 如果能知道删除哪个字母就好了.
 *
 * 方法一人问题就是, 因为不知道要删除哪一个字母, 所以每一个都要试一遍.
 * 那么, 有没有很快的方式来找到要删除的是哪一个字母呢?
 *
 * 要删除的肯定是多余的.
 * 那么, 怎么找到多余的呢? 这里我也想不出来怎么找到多余的那个, 目前没有思路.
 *
 * 方法三:
 *
 * 找多余的似乎走不通, 那能不能换个思路? 就是前后匹配的过程中, 能不能排除掉多余的? 如果能, 岂不是就很方便了.
 *
 * 我们举几个例子看下
 *
 * 1. abca
 * aa 匹配
 * bc 不匹配, 此时是删除b还是c呢? 都行.
 *
 * 2. abbca
 * aa 匹配
 * bc不匹配, 此时是删除b还是c呢? 即然无法判断, 能不能两种情况都试一下?
 *
 * 删除b
 * bc不匹配, 即不是回文了.
 *
 * 删除c
 * bb匹配, 还是回文, 还可以继续走下去.
 *
 * 3. abcdbca
 *
 * aa 匹配
 * bc 不匹配, 删除c
 * bb匹配
 * cd 不匹配, 不能再删除了, 所以不是回文.
 *
 * 由以上讨论似乎是这样的, 两两匹配, 如果遇到不匹配的,就两种情况都试一下, 一种走的通, 一种走不通.
 * 那么就选择走的通的, 此时不选择走不通的就一定是要删除的.
 *
 * 如果有没有这种情况, 遇到不匹配的, 两种情况都会走的通? 但又会影响后来的结果? 因为如果两种情况都走的通且影响后来的结果的话.
 * 那么就得记住两种情况都走的通时, 选择的是什么.这就很麻烦了.
 *
 * 所以, 两种情况都会走的通时, 会不会导致不同结果? 找个例子
 *
 * abaab
 *
 * 这种就是, 如果当ab不匹配时, 删除a, 结果是回文, 删除b, 结果就不是回文.
 *
 * 所以两种方向都要尝试一下.
 *
 * 其实这里没那么复杂, 也就第一次遇到不同时, 两个方向要尝试一下, 因为最多只删除一次.
 *
 * 接下来就不必再尝试两个方向了.
 *
 * 启示:
 *
 * 这题其实是自己想复杂了, 对于方法三, 虽然要判断两种情况, 但不是每一次都要判断两种情况, 因为只删除一次.
 *
 *
 */
public class Valid_Palindrome_II {

    static class Third {

        public boolean validPalindrome(String s) {

            int left = 0, right = s.length() - 1;

            while (left < right) {
                if (s.charAt(left) == s.charAt(right)) {
                    left++;
                    right--;
                } else {
                    // 此时遇到不匹配的, 那么就两种情况都尝试一下.
                    return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
                }
            }

            return true;
        }

        //判断 s[left, right]是否是回文
        public boolean isPalindrome(String s, int left, int right) {

            while (left < right) {
                if (s.charAt(left) == s.charAt(right)) {
                    left++;
                    right--;
                } else {
                    return false;
                }
            }

            return true;
        }
    }

    public static void main(String[] args) {
        Third test = new Third();
        test.validPalindrome("cbbcc");
    }

}
