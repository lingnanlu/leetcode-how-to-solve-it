package search;

/**
 * 对于第i个数
 *
 * 其引用次数为citations[i]
 *
 * 引用次数多于citations[i]的论文的个数为citations.length - i
 *
 * 如果 citations.length - i > citations[i]
 *
 * 则 citations[i]  就是一个有效的h，但不一定是最大的。
 *
 * 那么最大的一定在右边
 *
 * 当 citations.length - i == citations[i],
 *
 * 则 citations[i] 就是h
 *
 * 当 citations.length - i < citations[i]时
 *
 * 我感觉这题，当然也是二分查找的难点在于，最终的left，right，如何去解释。如何去理解left,right的含义
 */
public class H_Index_2 {

    public int hIndex(int[] citations) {

        int left = 0, right = citations.length - 1;
        while(left < right) {
            int middle = (left + right) / 2;
            if(citations[middle] > citations.length - middle) {
                right = middle - 1;
            } else if (citations[middle] == citations.length - middle){
                return citations[middle];
            }
        }

        return 0;
    }
}
