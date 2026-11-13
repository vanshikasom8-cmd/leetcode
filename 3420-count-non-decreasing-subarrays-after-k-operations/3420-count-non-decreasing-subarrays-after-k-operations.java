
class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        int LOG = 17;

        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        // Find next greater element
        int[] next = new int[n];
        int[] stack = new int[n];
        int top = -1;

        for (int i = n - 1; i >= 0; i--) {
            while (top >= 0 && nums[stack[top]] <= nums[i]) {
                top--;
            }

            next[i] = (top == -1) ? -1 : stack[top];
            stack[++top] = i;
        }

        int[][] jump = new int[LOG][n];
        long[][] cost = new long[LOG][n];

        // Binary lifting initialization
        for (int i = 0; i < n; i++) {
            jump[0][i] = next[i];

            int end = (next[i] == -1) ? n : next[i];

            cost[0][i] = (long) nums[i] * (end - i)
                       - (prefix[end] - prefix[i]);
        }

        // Build binary lifting tables
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                int mid = jump[j - 1][i];

                if (mid != -1) {
                    jump[j][i] = jump[j - 1][mid];
                    cost[j][i] = cost[j - 1][i]
                               + cost[j - 1][mid];
                } else {
                    jump[j][i] = -1;
                    cost[j][i] = cost[j - 1][i];
                }
            }
        }

        int left = 0;
        long ans = 0;

        for (int right = 0; right < n; right++) {

            while (left <= right &&
                   getCost(left, right, nums, prefix,
                           jump, cost, LOG) > k) {
                left++;
            }

            ans += right - left + 1;
        }

        return ans;
    }

    private long getCost(int left, int right,
                         int[] nums, long[] prefix,
                         int[][] jump, long[][] cost,
                         int LOG) {

        int curr = left;
        long total = 0;

        for (int j = LOG - 1; j >= 0; j--) {
            int next = jump[j][curr];

            if (next != -1 && next <= right) {
                total += cost[j][curr];
                curr = next;
            }
        }

        total += (long) nums[curr] * (right - curr + 1)
               - (prefix[right + 1] - prefix[curr]);

        return total;
    }
}
