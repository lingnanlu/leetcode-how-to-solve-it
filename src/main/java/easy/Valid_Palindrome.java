package easy;

public class Valid_Palindrome {

    public static void main(String[] args) {
        Valid_Palindrome test = new Valid_Palindrome();

        System.out.println(test.isPalindrome("OP"));
    }

    public boolean isPalindrome(String s) {

        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {

            while(i < s.length() && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }

            while(j >= 0 && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }

            //i < s.length() 和 j >=0 确保了不越界

            if (i <= j) {
                Character a = Character.toUpperCase(s.charAt(i));
                Character b = Character.toUpperCase(s.charAt(j));

                if (a == b) {
                    i++;
                    j--;
                } else {
                    return false;
                }
            }
        }

        return true;
    }
}
