class Solution {
    public int missingNumber(int[] nums) {
        int xorSum = 0;
        int n = nums.length;
        for (int k : nums) {
            xorSum = xorSum ^ k;
        }
        
        for (int i = 0; i <= n; i++) {
            xorSum = xorSum ^ i;

        }
        return xorSum;

    }
}
// int sum = 0;
// for(int val: nums){
//     sum = sum+val;
// }
// int n = nums.length;
// int totalSum =(n*(n+1)/2);
// return totalSum = sum;
// }
        