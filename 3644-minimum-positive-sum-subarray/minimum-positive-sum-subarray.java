class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        return   positiveSum(nums,l,r);
    }

    static int positiveSum(List<Integer> nums, int l, int r){
        int left=0;
        int ans=Integer.MAX_VALUE;

        for(int i=l;i<=r;i++){
            int sum=0;
            for(int j=0;j<i;j++){
                sum=sum+nums.get(j);
            }
              
            if (sum > 0) {
                ans = Math.min(ans, sum);
            }
            for(int k=i;k<nums.size();k++){
                sum=sum+nums.get(k)-nums.get(k-i);
                 if (sum > 0) {
                ans = Math.min(ans, sum);
            }
             }
        }

        return ans==Integer.MAX_VALUE?-1:ans;
    }
}