package math;

/**
 * 解题步骤很直观, 就是翻译成代码就行
 */
public class Number_Of_Steps_To_Reduce_A_Number_To_Zero {

    public int numberOfSteps(int num) {
        int step = 0; // 记录已操作的step
        while (num != 0) {
            if (num % 2 == 0) {
                num = num / 2;
            } else {
                num = num - 1;
            }

            step++;
        }

        return step;
    }
}
