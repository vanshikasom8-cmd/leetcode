class Solution {
    public int maxArea(int[] height) {
        int ans = Integer.MIN_VALUE;
        int low = 0;
        int high = height.length-1;
        while(low<high){
            int lH = height[low];
            int rH = height[high];
            int min = Math.min(lH,rH);
            ans = Math.max(ans,min*(high-low));
            if(lH <rH){
                low++;
            }else{
                high--;
            }
        }
        return ans;
    }
}
        // int ans = 0;
        // int n = height.length;
        // for(int i=0; i<n-1; i++){
        //     for(int j=i+1; j<n; j++){
        //         int length = Math.min(height[i],height[j]);
        //         int width = j-1;
        //         ans = Math.max(ans, length * width);
        //     }
        // }
        // return ans;
    