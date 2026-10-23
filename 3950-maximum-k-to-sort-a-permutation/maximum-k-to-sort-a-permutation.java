class Solution {
    public int sortPermutation(int[] nums) {
        int arr[] = nums.clone();
        Arrays.sort(arr);

        int ans = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != nums[i]) {
                ans &= nums[i];
            }
        }

        return ans == -1 ? 0 : ans;
    }
}