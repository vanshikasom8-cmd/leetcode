class Solution {
    public int removeDuplicates(int[] nums) {
        int prev = Integer.MIN_VALUE;
        int curr = 0;
        for(int n : nums){
            if(n != prev){
                nums[curr] = n;
                curr++;
                prev = n;
            }

        }
        return curr;
    }
}