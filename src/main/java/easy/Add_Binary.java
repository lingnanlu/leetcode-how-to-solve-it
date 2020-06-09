package easy;

import java.util.Stack;

public class Add_Binary {

    public static void main(String[] args) {

        Add_Binary test = new Add_Binary();

        test.addBinary("1", "111");
    }

    public String addBinary(String a, String b) {

        Stack<Character> r = new Stack<Character>();

        int carry = 0;
        int i = a.length() - 1, j = b.length() - 1;

        while (i >= 0 && j >= 0) {
            int n = a.charAt(i) - '0' + b.charAt(j) - '0' + carry;

            if (n == 0) {
                r.push('0');
                carry = 0;
            } else if (n == 1) {
                r.push('1');
                carry = 0;
            } else if (n == 2) {
                r.push('0');
                carry = 1;
            } else {    // n = 3
                r.push('1');
                carry = 1;
            }

            i--;
            j--;
        }

        while(i >= 0) {
            int n = a.charAt(i) - '0' + carry;
            if (n == 0) {
                r.push('0');
                carry = 0;
            } else if (n == 1) {
                r.push('1');
                carry = 0;
            } else {
                r.push('0');
                carry = 1;
            }
            i--;
        }

        while(j >= 0) {
            int n = b.charAt(j) - '0' + carry;
            if (n == 0) {
                r.push('0');
                carry = 0;
            } else if (n == 1) {
                r.push('1');
                carry = 0;
            } else {
                r.push('0');
                carry = 1;
            }
            j--;
        }

        if (carry == 1) {
            r.push('1');
        }

        StringBuilder sb = new StringBuilder();
        while (!r.isEmpty()) {
            sb.append(r.pop());
        }

        return sb.toString();

    }
}
