class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;
        int p = 0;
        int q = n-1;

        while(p<=q){
            int mid = p+(q-p)/2;
            
            if(nums[mid] == target){
                return mid;
            }
            else if(nums[mid] < target){
                p = mid+1;

            }
            else{
                q = mid-1;
            }
        }
        return-1;
        
    }
}