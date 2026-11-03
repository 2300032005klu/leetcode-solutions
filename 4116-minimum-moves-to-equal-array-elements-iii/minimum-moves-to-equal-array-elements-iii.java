class Solution {
    public int minMoves(int[] nums) {
        int ele=maxEle(nums);

        int sum=0;
        for(int x:nums){
            sum+=(ele-x);
        }

        return sum;
    }

    static int maxEle(int[] nums){
        int max=Integer.MIN_VALUE;

        for(int x:nums){
            max=Math.max(x,max);
        }

        return max;
    }
}