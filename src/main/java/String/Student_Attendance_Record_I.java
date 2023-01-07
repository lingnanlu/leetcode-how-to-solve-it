package String;

/**
 * 本质是上遍历字符串的问题, 因为要检查是否同时满足两个条件
 * 一种方便的方法是遍历两遍.
 *
 * 有难度一点就是遍历一遍
 */
public class Student_Attendance_Record_I {

    public boolean checkRecord(String s) {
        int absentCount = 0;
        int consecutiveLateCount = 0;

        for (char c : s.toCharArray()) {

            // 判断缺勤
            if (c == 'A') {
                absentCount++;

                // 注意这里也要把它变成0
                consecutiveLateCount = 0;
                if (absentCount == 2) {
                    return false;
                }
            } else if (c == 'L') {
                // 判断迟到
                consecutiveLateCount++;
                if (consecutiveLateCount == 3) {
                    return false;
                }
            } else {
                consecutiveLateCount = 0;
            }

        }

        return true;
    }
}
