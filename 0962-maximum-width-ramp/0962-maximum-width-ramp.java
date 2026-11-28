class Solution {
    public int maxWidthRamp(int[] nums) {
        int n = nums.length;
        Stack<Integer> st = new Stack<>();
        int ans =0;
        for(int i=0; i<n; i++){
            if(st.isEmpty() || nums[st.peek()] > nums[i]){
                st.push(i);
            }
        }
        for(int j=n-1; j>=0; j--){
            while(!st.isEmpty() && nums[j]>= nums[st.peek()]){
                int i=st.pop();
                ans = Math.max(ans,j-i);
            }
        }
        return ans;
        

    }
}