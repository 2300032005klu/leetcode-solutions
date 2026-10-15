class Solution {
    public int sumCounts(List<Integer> nums) {
        int sum=0;
        for(int i=0;i<nums.size();i++){
            HashSet<Integer> hm=new HashSet<>();
            hm.add(nums.get(i));
            sum+=hm.size()*hm.size();
            for(int j=i+1;j<nums.size();j++){
               hm.add(nums.get(j));
               sum+=hm.size()*hm.size();
            }
        }
        return sum;
    }
}