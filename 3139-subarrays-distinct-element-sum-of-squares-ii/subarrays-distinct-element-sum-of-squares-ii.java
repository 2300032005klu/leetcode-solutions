class Solution {
    static final long MOD = 1_000_000_007L;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        SegmentTree st = new SegmentTree(n);
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int prev = last[nums[i]];

            st.update(1, 0, n - 1, prev + 1, i, 1);

            ans = (ans + st.sq[1]) % MOD;
            last[nums[i]] = i;
        }

        return (int) ans;
    }

    static class SegmentTree {
        long[] sum, sq, lazy;
        int n;

        SegmentTree(int n) {
            this.n = n;
            sum = new long[4 * n];
            sq = new long[4 * n];
            lazy = new long[4 * n];
        }

        void apply(int node, int l, int r, long val) {
            long len = r - l + 1L;

            sq[node] = (sq[node]
                    + 2 * val * sum[node]
                    + val * val % MOD * len) % MOD;

            sum[node] = (sum[node] + val * len) % MOD;
            lazy[node] = (lazy[node] + val) % MOD;
        }

        void push(int node, int l, int r) {
            if (lazy[node] == 0 || l == r) return;

            int mid = (l + r) / 2;

            apply(node * 2, l, mid, lazy[node]);
            apply(node * 2 + 1, mid + 1, r, lazy[node]);

            lazy[node] = 0;
        }

        void update(int node, int l, int r,
                    int ql, int qr, long val) {
            if (ql > r || qr < l) return;

            if (ql <= l && r <= qr) {
                apply(node, l, r, val);
                return;
            }

            push(node, l, r);

            int mid = (l + r) / 2;

            update(node * 2, l, mid, ql, qr, val);
            update(node * 2 + 1, mid + 1, r, ql, qr, val);

            sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
            sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
        }
    }
}