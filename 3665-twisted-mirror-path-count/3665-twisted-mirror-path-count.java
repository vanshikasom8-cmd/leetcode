
class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long mod = 1_000_000_007L;

        long[][] left = new long[m][n];
        long[][] top = new long[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long fromLeft = left[i][j];
                long fromTop = top[i][j];

                long ways = (fromLeft + fromTop) % mod;

                if (i == 0 && j == 0) {
                    ways = 1;
                }

                if (grid[i][j] == 1) {
                    // Entered mirror from the left:
                    // reflect down
                    if (i + 1 < m) {
                        top[i + 1][j] =
                            (top[i + 1][j] + fromLeft) % mod;
                    }

                    // Entered mirror from above:
                    // reflect right
                    if (j + 1 < n) {
                        left[i][j + 1] =
                            (left[i][j + 1] + fromTop) % mod;
                    }
                } else {
                    // Empty cell: move right or down
                    if (j + 1 < n) {
                        left[i][j + 1] =
                            (left[i][j + 1] + ways) % mod;
                    }

                    if (i + 1 < m) {
                        top[i + 1][j] =
                            (top[i + 1][j] + ways) % mod;
                    }
                }
            }
        }

        return (int) ((left[m - 1][n - 1]
                     + top[m - 1][n - 1]) % mod);
    }
}
