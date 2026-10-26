class Solution {
    int m, n;

    public int countIslands(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;

        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] > 0) {
                    long sum = dfs(grid, i, j);

                    if (sum % k == 0) {
                        ans++;
                    }
                }
            }
        }

        return ans;
    }

    private long dfs(int[][] grid, int r, int c) {

        // Out of bounds
        if (r < 0 || r >= m || c < 0 || c >= n) {
            return 0;
        }

        // Water / already visited
        if (grid[r][c] == 0) {
            return 0;
        }

        // Current cell
        long sum = grid[r][c];

        // Mark visited
        grid[r][c] = 0;

        // 4 directions
        sum += dfs(grid, r + 1, c);
        sum += dfs(grid, r - 1, c);
        sum += dfs(grid, r, c + 1);
        sum += dfs(grid, r, c - 1);

        return sum;
    }
}