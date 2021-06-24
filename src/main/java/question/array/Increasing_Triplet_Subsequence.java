package question.array;

import run.elder.Category;
import run.elder.Leetcode;
import run.elder.Solution;

@Leetcode(
        title = "Increasing Triplet Subsequence",
        link = "https://leetcode.com/problems/increasing-triplet-subsequence/",
        category = Category.UNKNOWN,
        how2SolveIt = """
                
                q
                
                最简单的方法是穷举任意3个元素，但这样不符合时间复杂度。
                
                可以考虑两个是否有2个元素的情况，如果是2个元素，你怎么做呢？除了穷举？
                
                比如说，给了a[4],你怎么能快速知道a[1],a[2],a[3]中有比它小的呢？
                
                a 其实穷举的话，每次给a[i]，都要重复的在a[0...i-1]中找是否有元素比它小。
                
                这种每次都要重新从a[0...i-1]中找一遍的方式导致了时间复杂度，所以就不能每次重新找。
                
                因为我们是要找尽可能小的元素，所以，可以想办法保存起来a[0...i-1]最小的元素。这样时间复杂度就是O(n)了。
                
                q 好了，现在你解决了2个元素的问题，你能利用其结果么？或者利用其方法？
                
                a ....好像没啥思路
                
                q 你看现在的问题是a[i]< a[j] < a[k],
                
                我们已经解决了a[i] < a[j]的问题，你能把a[i] < a[j] < a[k] ，转化成a[i] < a[j]么
                
                a a[i]< a[j] < a[k] => (a[i] < a[j]) < a[k], 但这样，好像也不太行，因为(a[i] < a[j]) 并不能使用之前那种数组表示。
                
                还有一种转化方法
                
                a[i]< a[j] < a[k] => 
                
                a[i] < a[j] && a[j] < a[k]
                
                其中前半部分可以使用之前的数组，后半部分。。。嗯，感觉很像，也许可以使用另一个数组
                
                q ： 那你能解决后半部分么。。
                
                a 其实就是找a[j + 1...n]中是否有比a[j]大的，嗯，应该是保存最大的元素。应该不难，试一试。
               
               q: 现在要求不使用额外空间，你怎么做呢？
               
               a：我先看看2个元素的情况下吧.
               
               两个好说，使用一个smallest,但3个元素，还要一个largest,因为是边遍历，边更新，边出结果，如果是3个元素，需要同时从左向右和
               从右向左来进行判断，这时，就不能边遍历边出结果了，这个和双方向是矛盾的，所以，不能这样。
               
               q: 其实以上的方法对于3个元素可以适用，但对于4个就不行了，比如a[i] < a[j] < a[k] < a[l] 因为你不能再划分成2个了。
               
               q：可不可以这样，但我们遍历到a[i]时，如果有这样两个元素a,b 我们可以判断a[i] > a,但 a[i] > b, 且a < b ，不就可以了么？
               
               a：确实是这样，**但这里的关键是，如何理解a,b的含义，以及，如何更新a,b**
               
               当然，最关键的是，**说出a，b的含义**
               
               未完待续
               
                """,
        relatedQuestions = {}
)
public class Increasing_Triplet_Subsequence {

    @Solution(name = "试者先解决两元素的问题")
    static class IncreasingDouble {
        public boolean increaingDouble(int[] nums) {

            //left[i],保存的是nums[0...i]中最小的元素
            int[] left = new int[nums.length];

            left[0] = nums[0];
            for(int i = 1; i < nums.length; i++) {
                left[i] = Math.min(left[i - 1], nums[i]);
            }

            for(int i = 1; i < nums.length; i++) {
                if(nums[i] > left[i - 1]) {
                    return true;
                }
            }

            return false;
        }
    }

    @Solution(name = "试者先解决两元素的问题，不使用额外空间")
    static class IncreasingDoubleWithoutConstantSpace {
        public boolean increaingDouble(int[] nums) {

            int smallest = nums[0];

            for(int i = 1; i < nums.length; i++) {
                if(nums[i] > smallest) {
                    return true;
                } else {
                    smallest = nums[i];
                }
            }

            return false;
        }
    }

    @Solution(name = "利用2个元素的思路，解决3元素的问题")
    static class IncreasingTriplet {
        public boolean increasingTriplet(int[] nums) {

            if (nums.length < 3) return false;
            //forward[i],保存的是nums[0...i]中最小的元素
            int[] forward = new int[nums.length];

            //backward[i], 保存nums[i...n]中最大的元素
            int[] backward = new int[nums.length];

            forward[0] = nums[0];
            for(int i = 1; i < nums.length; i++) {
                forward[i] = Math.min(forward[i - 1], nums[i]);
            }

            backward[nums.length - 1] = nums[nums.length - 1];
            for(int i = nums.length - 2; i >= 0; i--) {
               backward[i] = Math.max(backward[i + 1], nums[i]);
            }

            for(int i = 1; i <= nums.length - 2; i++) {
                if(forward[i - 1] < nums[i] && nums[i] < backward[i + 1]) {
                    return true;
                }
            }
            return false;
        }
    }


    @Solution(name = "利用2个元素的思路，解决3元素的问题，不使用额外空间一尝试")
    static class IncreasingTripletWithConstantSpace {
        public boolean increasingTriplet(int[] nums) {

            int smallest = nums[0];
            int largest = nums[nums.length - 1];

            //这里要同时从左到右，以及从右到左，这其实是做不到的。

            return false;

        }
    }

    @Solution(name = "一种tricky的方式")
    static class Tricky {
        public boolean increasingTriplet(int[] nums) {

            // start with two largest values, as soon as we find a number bigger than both, while both have been updated, return true.
            int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
            for (int n : nums) {
                if (n <= small) { small = n; } // update small if n is smaller than both
                else if (n <= big) { big = n; } // update big only if greater than small but smaller than big
                else return true; // return if you find a number bigger than both
            }
            return false;

        }
    }


}
