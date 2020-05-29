package search;

/**
 * 利用一维的二分法的方法
 */
public class Search_A_2D_matrix {


    public boolean searchMatrix2(int[][] matrix, int target) {

        int row = matrix.length;
        if (row == 0) return false;

        int col = matrix[0].length;
        if (col == 0) return false;

        int begin = 0;
        int end = row * col - 1;

        while (begin <= end) {
            int mid = begin + (end - begin) / 2;

            // 这里的关键是计算的时候，都在使用二维坐标的一维化方式进行计算，只有在取坐标元素时， 再转化为二维的形式
            int mid_value = matrix[mid / col][mid % col];

            if (mid_value == target) {
                return true;
            } else if (mid_value < target) {
                begin = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return false;
    }

    /**
     * 其实涉及到二维坐标计算有个通用的方法
     *
     * 1. 先将二维坐标转化为一个距离初始元素的距离
     * 2. 进行计算
     * 3. 再转化为二维坐标
     *
     * 以上将两个方向的计算转化成一个方向的计算，轻松了很多
     *
     *
     */
    class Coordinate {
        int x;
        int y;
        int row;
        int col;

        public Coordinate(int x, int y, int row, int col) {
            this.x = x;
            this.y = y;
            this.row = row;
            this.col = col;
        }

        /**
         * 一个坐标加上距离， 得到新的坐标
         * @param distance 距离, 为正数
         * @return 新的坐标
         */
        public Coordinate add(int distance) {

            int d = x * col + y;
            return new Coordinate(
                    (d + distance) / col,
                    (d + distance) % col,
                    this.row,
                    this.col
            );
        }

        /**
         * 两坐标相减，得到之间的距离
         * @param another
         * @return
         */
        public int minus(Coordinate another) {
            return (x * col + y) - (another.x * col + another.y);
        }

        /**
         * 两坐标相比较
         * @param another
         * @return
         */
        public int compare(Coordinate another) {
            int flatA = x * col + y;
            int flatB = another.x * col + another.y;

            return flatA - flatB;
        }

    }


    public static void main(String[] args) {
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};

        Search_A_2D_matrix test = new Search_A_2D_matrix();

        boolean result = test.searchMatrix2(matrix, 3);
    }
}

