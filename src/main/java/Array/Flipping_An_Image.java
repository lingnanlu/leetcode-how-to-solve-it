package Array;

/**
 * 按照题意做就行了
 */
public class Flipping_An_Image {

    public int[][] flipAndInvertImage(int[][] image) {

        int n = image.length;

        for (int i = 0; i < n; i++) {
            int p = 0, q = n - 1;
            while (p < q) {
                int temp = image[i][p];
                image[i][p] = image[i][q];
                image[i][q] = temp;
                p++;
                q--;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (image[i][j] == 0) {
                    image[i][j] = 1;
                } else {
                    image[i][j] = 0;
                }
            }
        }

        return image;
    }
}
