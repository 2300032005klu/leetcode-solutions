class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        HashMap<Integer, Integer> hm = new HashMap<>();

        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int arr[][] = new int[queries.length][2];

        for (int i = 0; i < queries.length; i++) {
            arr[i][0] = queries[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        int ans[] = new int[queries.length];

        int left = 0, right = 0;
        int cnt = 0;

        for (int q[] : arr) {

            int start = q[0] - x;
            int end = q[0];

            while (right < logs.length && logs[right][1] <= end) {
                int id = logs[right][0];

                hm.put(id, hm.getOrDefault(id, 0) + 1);

                if (hm.get(id) == 1) {
                    cnt++;
                }

                right++;
            }

            while (left < right && logs[left][1] < start) {
                int id = logs[left][0];

                hm.put(id, hm.get(id) - 1);

                if (hm.get(id) == 0) {
                    cnt--;
                    hm.remove(id);
                }

                left++;
            }

            ans[q[1]] = n - cnt;
        }

        return ans;
    }
}
