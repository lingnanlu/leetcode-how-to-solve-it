package easy;

public class Search_Insert_Position {

    public int searchInsert(int[] nums, int target) {


        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int middle = (l + r) / 2;
            if (nums[middle] == target) {
                return middle;
            } else if(nums[middle] > target) {
                r = middle - 1;
            } else {
                l = middle + 1;
            }
        }

        //此时应该没有找到, 关键是理解, 为什么l所指向的位置应该是要插入的位置
        //由逼近的过程可以知道, target一定比l的左边(不包括l)大
        //比r所指的右边小(不包括r)
        //当l>r时
        //由以上分析可知, 一定比nums[r]要大(因为r在l的左边), 而比nums[l]的值小(因为l在r的右边)
        //所以应该插到r之后, 即l的位置

        // 实在想不明白就举只有一个元素的数组的例子
        return l;
    }

}
