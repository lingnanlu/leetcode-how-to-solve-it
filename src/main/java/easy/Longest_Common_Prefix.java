package easy;

public class Longest_Common_Prefix {

    public static void main(String[] args) {
        String[] test = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(test));
    }
    public static String longestCommonPrefix(String[] strs) {

        if (strs.length == 0) {
            return "";
        }

        String longestCommonPrefx = strs[0];
        for(int i = 1; i < strs.length; i++) {
            //两两的找
            longestCommonPrefx = commonPrefix(longestCommonPrefx, strs[i]);

        }

        return longestCommonPrefx;
    }

    public static String commonPrefix(String first, String second) {

        StringBuilder commonPrefix = new StringBuilder();
        int i = 0;
        while (i < first.length() && i < second.length()) {
            if (first.charAt(i) == second.charAt(i)) {
                commonPrefix.append(first.charAt(i));
                i++;
            } else {
                break;
            }
        }
        return commonPrefix.toString();
    }

}
