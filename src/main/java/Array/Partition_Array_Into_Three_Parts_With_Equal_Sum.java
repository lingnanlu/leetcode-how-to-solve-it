package Array;


import java.sql.PreparedStatement;
import java.util.Arrays;

/**
 * 其实你是知道每一部分的值是多少.
 *
 * 试着划分, 如果能划分就返回true
 *
 * 这题注意两点
 * 1. first和second 初始化为-1表示还没找到.
 * 2. 还要每一部分非空才行.
 */
public class Partition_Array_Into_Three_Parts_With_Equal_Sum {

    public boolean canThreePartsEqualSum(int[] arr) {
        int sum = Arrays.stream(arr).sum();

        if (sum % 3 != 0) {
            //不能被3除, 肯定不行
            return false;
        } else {
            // 每一份的和
            int partitionSum = sum / 3;

            // 先找到第一个分界点, -1表示还没找到
            int first = -1;
            int accu = 0;

            int i = 0;

            while (i != arr.length) {
                accu += arr[i];
                if (accu == partitionSum) {
                    // 说明是第一个分界点
                    first = i;
                    break;
                }
                i++;
            }

            // 说明没找到
            if (first == -1) {
                return false;
            }


            // 找第二个分界点
            int second = -1;
            i++;
            accu = 0;
            while (i != arr.length) {
                accu += arr[i];
                if (accu == partitionSum) {
                    second = i;
                    break;
                }
                i++;
            }

            // 第二个没找到
            if (second == -1) {
                return false;
            }

            // 找到了, 但第三部分为空
            if (second == arr.length - 1) {
                return false;
            }


            return true;
        }
    }
}
