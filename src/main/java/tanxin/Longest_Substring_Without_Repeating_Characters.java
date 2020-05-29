package tanxin;

import java.util.Arrays;

public class Longest_Substring_Without_Repeating_Characters {

    public int lengthOfLongestSubstring(String s) {

        int maxLength = 0;
        int length = s.length();
        int start = 0;
        int[] last = new int[255];

        Arrays.fill(last, -1);

        for (int i = 0; i < length; i++) {
            int lastPosition = last[s.charAt(i)];

            if (lastPosition >= start) {
                maxLength = Math.max(maxLength, i - start);
                start = lastPosition + 1;
            }

            last[s.charAt(i)] = i;
        }

        maxLength = Math.max(maxLength, length - start);

        return maxLength;
    }

    public static void main(String[] args) {
        Longest_Substring_Without_Repeating_Characters test = new Longest_Substring_Without_Repeating_Characters();

        test.lengthOfLongestSubstring("abcabcbb");
    }
}
