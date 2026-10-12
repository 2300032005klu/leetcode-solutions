class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int c[] = new int[32];

        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                if (((num >> i) & 1) == 1) {
                    c[i]++;
                }
            }
        }

        long ans = 0;
        long mod = 1000000007L;

        for (int j = 0; j < k; j++) {
            long x = 0;

            for (int b = 0; b < 32; b++) {
                if (c[b] > 0) {
                    x |= (1L << b);
                    c[b]--;
                }
            }

            ans = (ans + (x * x) % mod) % mod;
        }

        return (int) ans;
    }
}