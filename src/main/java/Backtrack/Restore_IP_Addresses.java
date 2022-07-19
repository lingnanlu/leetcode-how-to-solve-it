package Backtrack;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.List;

@Leetcode(
        title = "Restore IP Addresses",
        link = "https://leetcode.com/problems/restore-ip-addresses/",
        category = Category.ARRAY,
        how2SolveIt = """
                
                方法一:
                其实和分割回文类似, 都是某种分割, 然后每个分割要满足一定条件.
                
                不同的是
                
                1. 这里是分割成固定的4个子段
                2. 要求每个子段满足一定条件
                
                
                这个题也是一遍过.
                
                这个题剪枝挺多, 细节也挺多, 但思路清晰的话, 也是一遍过
                
                方法二:
                
                即然知道要分割4次了, 就不要思维定式了, 可以使用四层for循环, 
                
                而且这个for循环同样可以剪枝
                
                启示: 
                
                有时不要陷入思维定式, 想想还有没有其它方法
                
                """,
        relatedQuestions = {}
)
public class Restore_IP_Addresses {

    static class First {
        public List<String> restoreIpAddresses(String s) {
            StringBuilder path = new StringBuilder();
            List<String> result = new ArrayList<>();

            for (int i = 1; i < s.length() && i <= 3; i++) {
                walk(s, 0, i, 1, path, result);
            }

            return result;
        }

        /**
         *
         * @param s
         * @param from
         * @param to
         * @param count 访问的当前子段是第几个, 这里最多是4.
         * @param path
         * @param result
         */
        private void walk(String s, int from, int to, int count, StringBuilder path, List<String> result) {
            // 最后一个
            if (count == 4) {
                if (valid(s, from, to)) {
                    path.append(s, from, to);
                    result.add(path.toString());
                    path.delete(path.length() - (to - from), path.length());
                    return;
                } else {
                    // 如果不是有效的, 什么也不做, 直接返回
                    return;
                }
            } else {
                // 不是最后一个.
                if (valid(s, from, to)) {
                    path.append(s, from, to);
                    path.append('.');

                    // 选后一个
                    // 注意, 如果后一个是每四个, 结尾就固定了.
                    count++;
                    if (count == 4) {
                        walk(s, to, s.length(), count, path, result);
                    } else {
                        for (int i = to + 1; i < s.length() && (i - to) <= 3; i++) {
                            walk(s, to, i, count, path, result);
                        }
                    }

                    path.delete(path.length() - (to - from) - 1, path.length());
                } else {
                    // 如果不是有效的, 什么也不做, 直接返回
                    return;
                }
            }
        }

        private boolean valid(String s, int from, int to) {
            if ((to - from) <= 0) return false;
            if ((to - from) > 3) return false;
            if ((to - from) != 1 && s.charAt(from) == '0') return false;

            String sub = s.substring(from, to);

            try {
                int n = Integer.parseInt(sub);
                return n >= 0 && n <= 255;
            } catch (NumberFormatException e) {
                return false;
            }

        }
    }

    static class Second {

        public List<String> restoreIpAddresses(String s) {
            List<String> res = new ArrayList<String>();
            int len = s.length();
            for(int i = 1; i<4 && i<len-2; i++){
                String s1 = s.substring(0, i);
                if (isValid(s1)) {
                    for(int j = i+1; j<i+4 && j<len-1; j++){
                        String s2 = s.substring(i, j);
                        if (isValid(s2)) {
                            for(int k = j+1; k<j+4 && k<len; k++){
                                String s3 = s.substring(j,k), s4 = s.substring(k,len);
                                if(isValid(s3) && isValid(s4)){
                                    res.add(s1+"."+s2+"."+s3+"."+s4);
                                }
                            }
                        }
                    }
                }

            }
            return res;
        }
        public boolean isValid(String s){
            if(s.length()>3 || s.length()==0 || (s.charAt(0)=='0' && s.length()>1) || Integer.parseInt(s)>255)
                return false;
            return true;
        }
    }

}
