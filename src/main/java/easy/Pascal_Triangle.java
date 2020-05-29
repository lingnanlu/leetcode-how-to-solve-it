package easy;

import java.util.ArrayList;
import java.util.List;

public class Pascal_Triangle {


    public static void main(String[] args) {
        Pascal_Triangle test = new Pascal_Triangle();
        test.generate(5);
    }
    public List<List<Integer>> generate(int numRows) {

        if(numRows == 0) return new ArrayList<List<Integer>>();

        List<List<Integer>> result = new ArrayList<List<Integer>>();

        List<Integer> firstRow = new ArrayList<Integer>();
        firstRow.add(1);
        result.add(firstRow);

        //从第二行到最后一行
        for (int i = 1; i < numRows; i++) {
            List<Integer> lastRow = result.get(i - 1);
            List<Integer> currentRow = new ArrayList<Integer>();
            currentRow.add(1);
            for (int j = 1; j < i; j++) {
                currentRow.add(lastRow.get(j - 1) + lastRow.get(j));
            }
            currentRow.add(1);
            result.add(currentRow);
        }

        return result;
    }



}
