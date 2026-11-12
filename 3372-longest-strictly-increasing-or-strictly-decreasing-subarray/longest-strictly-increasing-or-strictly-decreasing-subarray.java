class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        return Math.max(inc(nums), dec(nums));
    }

    static int inc(int[] nums) {
        int left = 0, right = 1;
        int maxlen = 1;

        while (right < nums.length) {
            if (nums[right] <= nums[right - 1]) {
                maxlen = Math.max(right - left, maxlen);
                left = right;
            }
            right++;
        }

        maxlen = Math.max(right - left, maxlen);
        return maxlen;
    }

    static int dec(int[] nums) {
        int left = 0, right = 1;
        int maxlen = 1;

        while (right < nums.length) {
            if (nums[right] >= nums[right - 1]) {
                maxlen = Math.max(right - left, maxlen);
                left = right;
            }
            right++;
        }

        maxlen = Math.max(right - left, maxlen);
        return maxlen;
    }
}