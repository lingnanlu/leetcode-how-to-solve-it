package dfs;

import java.util.ArrayList;
import java.util.List;

public class Restore_IP_Addresses {

    public List<String> restoreIpAddresses(String s) {

        List<String> result = new ArrayList<>();
        List<Integer> positions = new ArrayList<>(4);
        positions.add(-1);

        for(int i = 0; i < s.length(); i++) {
            visit(s, i, positions, result);
        }

        return result;

    }

    /**
     * @param s
     * @param position  当前要放置的点的位置, 指的是将点放到该字符后面
     * @param positions 保存已放置的点的位置，指的是将点放到该字符后面
     */
    private void visit(String s, int position, List<Integer> positions, List<String> result) {

        int lastIndex = positions.get(positions.size() - 1);

        if ((position - lastIndex) > 3) return;

        if (positions.size() >= 4 && position != s.length() - 1) return;


        // 此时要么是未到最后一层，要么是到了最后一层后且position为s.length() - 1
        String part = s.substring(lastIndex + 1, position + 1);
        if (!valid(part)) return;

        if (positions.size() == 4 && position == s.length() - 1) {
            positions.add(position);
            result.add(parseIp(s, positions));
        } else {
            positions.add(position);
            for (int i = position + 1; i < s.length(); i++) {
                visit(s, i, positions, result);
            }
        }

        positions.remove(positions.size() - 1);

    }

    private boolean valid(String s) {
        if (Integer.parseInt(s) > 255) return false;
        if (s.length() > 1 && s.startsWith("0")) return false;

        return true;
    }
    public String parseIp(String s, List<Integer> dots) {
        StringBuilder ip = new StringBuilder();
        for (int i = 0; i < dots.size() - 1; i++) {
            ip.append(s, dots.get(i) + 1, dots.get(i + 1) + 1).append(".");
        }

        ip.deleteCharAt(ip.length() - 1);
        return ip.toString();
    }

    public static void main(String[] args) {
        Restore_IP_Addresses test = new Restore_IP_Addresses();

        System.out.println(test.restoreIpAddresses("010010"));
    }
}
