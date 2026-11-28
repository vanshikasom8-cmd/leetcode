class Solution {
    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int left=0;
        int right =1;
        int count =0;
        int n=nums.length;
        while(right<n){
            if(left==right){
                right++;
                continue;
            }
            int diff=Math.abs(nums[right]-nums[left]);
            if(diff<k){
                right++;
            }else if(diff>k){
                left++;
            }else{
                count++;
                int a= nums[left];
                int b= nums[right];
                while(right < n && nums[right] == b) right++;
                while(left <n && nums[left] == a) left++;
            }
        }
        return count;
        
    }
}