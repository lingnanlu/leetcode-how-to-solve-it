package easy;

import java.util.ArrayList;
import java.util.List;

public class Pascal_Triangle_2 {

    /**
     * 因为无法找到rowIndex和各个位置数字之间的计算关系, 所以依赖需要由上一行递推出下一行
     * 只能使用K的空间, 所以要复用List
     * 只为不能覆盖元素, 所以考虑从后向前
     */
    public List<Integer> getRow(int rowIndex) {

        int[] row = new int[rowIndex + 1];

        row[0] = 1;
        for (int i = 1; i <= rowIndex; i++) {

            //从后往前填充
            //最后一位固定为一
            row[i] = 1;

            //倒数第二位到正数第二位
            for (int j = i - 1; j >= 1; j--) {
                row[j] = row[j] + row[j - 1];
            }
        }

        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i <= rowIndex; i++) {
            result.add(row[i]);
        }

        return result;

    }
}
