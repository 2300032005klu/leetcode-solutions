class Solution {
    public int minMoves(int[] nums) {
        int min=minEle(nums);
        int sum=0;
        for(int x:nums){
            sum+=(x-min);
        }
        return sum;
    }


    static int minEle(int[] nums){
        int min=Integer.MAX_VALUE;
        for(int x:nums){
            min=Math.min(min,x);
        }
        return min;
    }
}