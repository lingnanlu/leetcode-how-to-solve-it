package search;

import java.util.Arrays;

public class H_Index {

    public int hIndex(int[] citations) {

        Arrays.sort(citations);

        int h = 0;
        for (int i = 0; i < citations.length; i++) {
            int currentH = Math.min(citations[i], citations.length - i);
            if (currentH > h) {
                h = currentH;
            }
        }

        return h;
    }


}
