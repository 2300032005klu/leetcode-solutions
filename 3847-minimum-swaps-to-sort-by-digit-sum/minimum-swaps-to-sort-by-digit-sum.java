class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = digitSum(nums[i]);
            arr[i][1] = nums[i];
        }

        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        Map<Integer, Integer> pos = new HashMap<>();
        for (int i = 0; i < n; i++) {
            pos.put(nums[i], i);
        }

        int[] target = new int[n];
        for (int i = 0; i < n; i++) {
            target[i] = arr[i][1];
        }

        int cnt = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] != target[i]) {
                cnt++;

                int j = pos.get(target[i]);

                pos.put(nums[i], j);
                pos.put(target[i], i);

                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }

        return cnt;
    }

    static int digitSum(int n) {
        int sum = 0;

        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }

        return sum;
    }
}