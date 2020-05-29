package tanxin;

/**
 * 说实话，有点贪婪的意思，但还是比较tricky的。
 */
public class Jump_Game {

    public boolean canJump(int[] nums) {

        // 循环不变量 reach 表示当前可以到达的最远的位置
        int reach = 0;
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {

            // 如果当前能到达的最远位置走不到当前位置，就退出
            if (reach < i) break;

            // 如果当前能到达的最远位置已经等于或大于最后的位置，则退出
            if (reach >= n - 1) break;

            reach = Math.max(reach, i + nums[i]);
        }

        return reach >= n - 1;

    }

}
