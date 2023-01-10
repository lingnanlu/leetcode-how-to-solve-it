package Array;

/**
 * 这题不难啊, 为什么通过率不过5成?
 *
 * 这里唯一可以优化的是二分查找
 *
 * 二分查找找的是什么? 找的是第一个比target大的数.
 * 即找这样一个数, 它比target大, 但左边的数 <= target
 *
 * 二分查找充分利用了不变式, 这次也是一遍过.
 */
public class Find_Smallest_Letter_Greater_Than_Target {

    static class First {
        public char nextGreatestLetter(char[] letters, char target) {

            for (char c : letters) {
                if (c > target) {
                    return c;
                }
            }

            return letters[0];
        }
    }

    static class Second {

        public char nextGreatestLetter(char[] letters, char target) {

            // 下一次要查找的范围 [i, j]
            int i = 0, j = letters.length - 1;

            while(i <= j) {
                int mid = i + (j - i) / 2;

                // mid处比target大, 且左边有数, 且比target小
                if ((mid - 1) >= 0 && letters[mid - 1] <= target && letters[mid] > target) {
                    return letters[mid];
                } else if (mid == 0 && letters[mid] > target) {
                    // mid处比target大, 且左边没有数
                    return letters[mid];
                } else if (letters[mid] > target) {
                    // 结果在左半边
                    j = mid - 1;
                } else {
                    i = mid + 1;
                }
            }

            // 没找到
            return letters[0];

        }

    }

}
