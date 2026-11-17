
class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();

        // cost[i][j] = minimum changes to make s[i...j]
        // a semi-palindrome
        int[][] cost = new int[n][n];

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;

                cost[i][j] = Integer.MAX_VALUE;

                // Try every proper divisor of substring length
                for (int d = 1; d < len; d++) {
                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;

                    // Compare characters in each group
                    for (int r = 0; r < d; r++) {
                        int left = i + r;
                        int right = i + r + ((len - 1 - r) / d) * d;

                        while (left < right) {
                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    cost[i][j] = Math.min(cost[i][j], changes);
                }
            }
        }

        // dp[i][j] = minimum changes to partition
        // first i characters into j substrings
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            java.util.Arrays.fill(dp[i], 1000000);
        }

        dp[0][0] = 0;

        for (int parts = 1; parts <= k; parts++) {
            for (int i = 2 * parts; i <= n; i++) {
                for (int prev = 2 * (parts - 1); prev <= i - 2; prev++) {
                    if (dp[prev][parts - 1] == 1000000) {
                        continue;
                    }

                    dp[i][parts] = Math.min(
                        dp[i][parts],
                        dp[prev][parts - 1] + cost[prev][i - 1]
                    );
                }
            }
        }

        return dp[n][k];
    }
}
