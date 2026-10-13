class Solution {
    public long maxAlternatingSum(int[] nums) {
        PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->b-a);
        int k;
        if(nums.length%2==0){
            k=(nums.length/2);
        }
        else{
            k=(nums.length/2)+1;
        }

        for(int x:nums){
            pq.offer(x*x);
        }

        long sum=0;
        while(k>0){
            sum+=pq.poll();
            k--;
        }

        while(!pq.isEmpty()){
            sum-=pq.poll();
        }

        return sum;
    }
}