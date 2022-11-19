package Other;


import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Valid Perfect Square",
        link = "https://leetcode.com/problems/valid-perfect-square/",
        category = Category.UNKNOWN,
        how2SolveIt = """
                似乎是一个数学智力题, 就是如何高效的判断一个数是不是平方数.
                
                这是一个证明题.
                
                方法一: 最朴素的方法
                
                最容易想到的就是从小到大去试, 直到某个数的平方比它大时就停止.
                
                方法二:
                
                方法一超时了, 说明尝试次数过多了, 是不是有些尝试是无效的? 比如说10000, 要从1 - 100尝试100次.
                这样每次+1的逼近似乎很慢, 能不能快速逼近一些呢? 
                
                我们不防试试+2?    
                            
                方法三:
                方法二还是慢, 这种一个一个尝试的方法, 其实就是要找满足某条件的数, 这让我们想起了二分查找, 可以试试.
                
                注意: 遇到数学计算和大数时, 记得不要有溢出问题.
                """,
        relatedQuestions = {}
)
public class Valid_Perfect_Square {

    static class First {
        public boolean isPerfectSquare(int num) {

            long i = 1;
            while(i * i < num) {
                i++;
            }

            //此时 i * i >= num, 但不知道是>还是=, 所以再判断一次
            return i * i == num;
        }
    }

    static class Second {

        public boolean isPerfectSquare(int num) {

            long i = 1;
            while(i * i < num) {
                i += 2;
            }

            /**
             * 此时 i * i >= num, 且有递增过程可知 (i - 2) * (i - 2) < num,
             *
             * 这里漏了i + 1, 所以判断一个i, i - 1, 就行.
             */

            return i * i == num || (i - 1) * (i - 1) == num;
        }
    }

    static class Third {

        public boolean isPerfectSquare(int num) {

            // 其实就是要找这么一个数, 平方为num
            long i = 1, j = num;

            while(i <= j) {
                long mid = i + (j - i) / 2;
                long v = mid * mid;
                if (v == num) {
                    return true;
                } else if (v > num) {
                    j = mid - 1;
                } else {
                    i = mid + 1;
                }
            }

            // 此时 i > j, 说明没找到
            return false;

        }
    }

    public static void main(String[] args) {
        int num = 2147483647;
        First first = new First();
        Second second = new Second();
        Third third = new Third();
        long start = System.currentTimeMillis();
        first.isPerfectSquare(num);
        long firstEnd = System.currentTimeMillis();
        System.out.println(firstEnd - start);
        second.isPerfectSquare(num);
        long secondEnd = System.currentTimeMillis();
        System.out.println(secondEnd - firstEnd);
        third.isPerfectSquare(num);
        long thirdEnd = System.currentTimeMillis();
        System.out.println(thirdEnd - secondEnd);
    }
}
