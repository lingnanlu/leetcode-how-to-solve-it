package easy;

public class Trapping_Rain_Water {

    // 其实最简单的想法就是, 对于每个柱子, 左右找到最高的柱子, 该柱子上方所能存的水量就是, min(maxLeft, maxRight) - height
    // 复杂度是O(n^2)
    public int trap(int[] height) {

        int sum = 0;
        for(int i = 1; i < height.length - 1; i++) {

            // 向左找最高的
            int maxLeft = height[0];
            for(int j = 0; j < i; j++) {
                if(height[j] > maxLeft) {
                    maxLeft = height[j];
                }
            }

            int maxRight = height[height.length - 1];
            for(int k = height.length - 1; k > i; k--) {
                if(height[k] > maxRight) {
                    maxRight = height[k];
                }
            }

            if (Math.min(maxLeft, maxRight) > height[i]) {
                sum += Math.min(maxLeft, maxRight) - height[i];
            }

        }

        return sum;
    }


    // 一可以优化一下, 时间主要浪费在找左右最高, 可以缓存一些部分结果.
    public int trap2(int[] height) {

        int[] maxLeft = new int[height.length];     //maxLeft[i]是柱子i的左边最高的柱子高度
        int[] maxRight = new int[height.length];    //maxRigth[i]是柱子i右边的最高的柱子高度

        //先找左边最高
        for (int i = 1; i < height.length; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i - 1]);
        }

        for (int j = height.length - 2; j >= 0; j--) {
            maxRight[j] = Math.max(maxRight[j + 1], height[j + 1]);
        }

        int sum = 0;
        for(int i = 1; i < height.length - 1; i++) {
            if(Math.min(maxLeft[i], maxRight[i]) > height[i]) {
                sum += Math.min(maxLeft[i], maxRight[i]) - height[i];
            }
        }

        return sum;
    }
}
