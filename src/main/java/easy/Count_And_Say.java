package easy;

public class Count_And_Say {

    public static String countAndSay(int n) {

        //result是第i个的结果
        String result = "1";

        for(int i = 1; i != n; i++) {
            result = countAndSay(result);
        }

        return result;
    }


    // 对input进行count and say
    private static String countAndSay(String input) {

        StringBuilder sb = new StringBuilder();

        int i = 0, j = 1;
        while(j < input.length()) {
            while(j < input.length() && input.charAt(i) == input.charAt(j)) {
                j++;
            }

            sb.append(j - i).append(input.charAt(i));
            i = j;
            j++;
        }

        if(i < input.length()) sb.append(j - i).append(input.charAt(i));
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(countAndSay("1211"));
    }

}
