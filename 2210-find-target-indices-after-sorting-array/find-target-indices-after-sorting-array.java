class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);
        List<Integer> result = new ArrayList<>();

        int  first=firstOccur(nums,target);
        int  last=secondOccur(nums,target);

        if(first == -1){
            return result;
        }

        for(int i=first;i<=last;i++){
            result.add(i);
        }
        return result;
    }

    static int firstOccur(int[] nums, int target) {
        int low=0;
        int high=nums.length-1;
        int first=-1;
        while(low <= high){
            int mid = low +(high-low)/2;
            if(nums[mid] < target){
                low=mid+1;
            }
            else if(nums[mid] == target){
                first=mid;
                high=mid-1;
            }
            else{
                high=mid-1;
            }      
        }

        return first;
    }
    static int secondOccur(int[] nums, int target) {
        int low=0;
        int high=nums.length-1;
        int last=-1;
        while(low <= high){
            int mid = low +(high-low)/2;
            if(nums[mid] < target){
                low=mid+1;
            }
            else if(nums[mid] == target){
                last=mid;
                low=mid+1;
            }
            else{
                high=mid-1;
            }

        }

        return last;
    }
}