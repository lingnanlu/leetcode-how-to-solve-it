package easy;

public class Length_Of_The_Last_Word {

    public int lengthOfLastWord(String s) {

        int lastAlphaIndex = s.length() - 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            if(s.charAt(i) != ' ') {
                lastAlphaIndex = i;
                break;
            }
        }

        int lastSpaceIndex = -1;
        for (int i = lastAlphaIndex; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                lastSpaceIndex = i;
                break;
            }
        }

        return lastAlphaIndex - lastSpaceIndex;
    }
}
