package Array;

/**
 * 模拟题, 尽最大可能种, 看最后能不能种下.
 */
public class Can_Place_Flowers {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {

        // 能种下的花的位置
        int count = 0;

        for (int i = 0; i < flowerbed.length; i++) {
            // 挨个检查是否能种
            // 当且仅当当前位置没有花且相邻没有花时, 才能种
            // 当前没有花, 左边没有花, 右边也没有花
            boolean leftNoFlower = (i - 1) < 0 || (flowerbed[i - 1] == 0);
            boolean rightNoFlower = (i + 1) >= flowerbed.length || flowerbed[i + 1] == 0;
            if (flowerbed[i] == 0 &&
                    leftNoFlower &&
                    rightNoFlower) {
                count++;
                flowerbed[i] = 1;
            }
        }

        return count >= n;
    }

    public static void main(String[] args) {

    }

}
