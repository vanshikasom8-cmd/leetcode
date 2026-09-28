class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int ans = 0;
        for(int num : nums){
            if(num == 0){
                count=0;
            }else{
                count++;
                ans= Math.max(ans,count);
            }
        }
        return ans;
        
    }
}