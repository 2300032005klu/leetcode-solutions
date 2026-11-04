class Solution {

    static class Window {
        TreeMap<Integer, Integer> low = new TreeMap<>();
        TreeMap<Integer, Integer> high = new TreeMap<>();

        long lowSum = 0, highSum = 0;
        int lowSize = 0, highSize = 0;

        void add(int val) {
            if (highSize == 0 || val >= high.firstKey()) {
                high.put(val, high.getOrDefault(val, 0) + 1);
                highSum += val;
                highSize++;
            } else {
                low.put(val, low.getOrDefault(val, 0) + 1);
                lowSum += val;
                lowSize++;
            }

            balance();
        }

        void remove(int val) {
            if (high.containsKey(val)) {
                removeValue(high, val);
                highSum -= val;
                highSize--;
            } else {
                removeValue(low, val);
                lowSum -= val;
                lowSize--;
            }

            balance();
        }

        void removeValue(TreeMap<Integer, Integer> map, int val) {
            int count = map.get(val);

            if (count == 1) {
                map.remove(val);
            } else {
                map.put(val, count - 1);
            }
        }

        void balance() {
            int target = (lowSize + highSize + 1) / 2;

            while (highSize > target) {
                int val = high.firstKey();

                removeValue(high, val);
                highSum -= val;
                highSize--;

                low.put(val, low.getOrDefault(val, 0) + 1);
                lowSum += val;
                lowSize++;
            }

            while (highSize < target) {
                int val = low.lastKey();

                removeValue(low, val);
                lowSum -= val;
                lowSize--;

                high.put(val, high.getOrDefault(val, 0) + 1);
                highSum += val;
                highSize++;
            }
        }

        long getCost() {
            long median = high.firstKey();

            return median * lowSize - lowSum
                 + highSum - median * highSize;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        long[] arr = new long[n - x + 1];

        Window window = new Window();

        for (int i = 0; i < x; i++) {
            window.add(nums[i]);
        }

        arr[0] = window.getCost();

        for (int i = x; i < n; i++) {
            window.remove(nums[i - x]);
            window.add(nums[i]);

            arr[i - x + 1] = window.getCost();
        }

        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[k + 1][n + 1];

        for (long[] row : dp) {
            Arrays.fill(row, INF);
        }

        Arrays.fill(dp[0], 0);

        for (int j = 1; j <= k; j++) {

            for (int i = 1; i <= n; i++) {

                dp[j][i] = dp[j][i - 1];

                if (i >= x) {
                    dp[j][i] = Math.min(
                        dp[j][i],
                        dp[j - 1][i - x] + arr[i - x]
                    );
                }
            }
        }

        return dp[k][n];
    }
}