package String;

// 没什么意思, 就是简单字符串替换
public class Defanging_An_IP_Address {
    public String defangIPaddr(String address) {

        StringBuilder sb = new StringBuilder();

        for (char c : address.toCharArray()) {
            if (c == '.') {
                sb.append("[.]");
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
