
class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        // Calculate free copies received by buying each item type
        int[] free = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    free[i]++;
                }
            }
        }

        // dp[b] = maximum copies using exactly b budget
        int[] dp = new int[budget + 1];

        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            int bonus = free[i];

            int[] old = dp.clone();

            // Process each remainder separately
            for (int r = 0; r < price && r <= budget; r++) {
                int best = Integer.MIN_VALUE;

                int q = 0;

                for (int b = r; b <= budget; b += price, q++) {

                    if (q > 0 && old[b - price] != Integer.MIN_VALUE) {
                        best = Math.max(best,
                                old[b - price] - (q - 1));
                    }

                    if (best != Integer.MIN_VALUE) {
                        dp[b] = Math.max(dp[b],
                                best + q + bonus);
                    }
                }
            }
        }

        int ans = 0;

        for (int b = 0; b <= budget; b++) {
            ans = Math.max(ans, dp[b]);
        }

        return ans;
    }
}
