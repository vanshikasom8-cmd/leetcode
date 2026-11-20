
import java.util.Arrays;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        int[] dp = new int[1000005];

        int ans = 0;

        for (int x : nums) {

            dp[x + 2] = Math.max(dp[x + 2], dp[x + 1] + 1);

            dp[x + 1] = Math.max(dp[x + 1], dp[x] + 1);

            ans = Math.max(ans, Math.max(dp[x + 1], dp[x + 2]));
        }

        return ans;
    }
}
