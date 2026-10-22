class Solution {
    public int minOperations(int[] nums) {
        if (isSorted(nums)) return 0;

        int n = nums.length;
        int idx = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                idx = i;
                break;
            }
        }

        boolean fwd = true;
        boolean bwd = true;

        for (int i = 0; i < n; i++) {
            if (nums[(idx + i) % n] != i) {
                fwd = false;
            }
            if (nums[((idx - i) % n + n) % n] != i) {
                bwd = false;
            }
        }

        int cnt = Integer.MAX_VALUE;

        if (fwd) {
            cnt = Math.min(cnt, Math.min(idx, n - idx + 2));
        }

        if (bwd) {
            cnt = Math.min(cnt, (idx + 1) % n + 1);
            cnt = Math.min(cnt, n - idx);
        }

        return cnt == Integer.MAX_VALUE ? -1 : cnt;
    }

    static boolean isSorted(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                return false;
            }
        }
        return true;
    }
}