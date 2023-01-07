package Stack;

//

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 其实就是对于每一个操作符, 拿到前一次或前两次的值.
 * 而拿前一次或前两次的值需要的数据结构是栈
 *
 * 值得注意的是, 不要使用stack, 使用deque, stack的操作都是加锁的, 性能很差
 */
public class Baseball_Game {

    public int calPoints(String[] operations) {
        Deque<Integer> score = new ArrayDeque<>();

        for (String op : operations) {
            switch (op) {
                case "C" -> score.pop();
                case "D" -> score.push(score.peek() * 2);
                case "+" -> {
                    int last = score.pop();
                    int second2Last = score.pop();
                    score.push(second2Last);
                    score.push(last);
                    score.push(last + second2Last);
                }
                default -> score.push(Integer.parseInt(op));
            }
        }

        int accu = 0;
        while (!score.isEmpty()) {
            accu += score.pop();
        }

        return accu;
    }
}
