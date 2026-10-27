class Solution {
    public String[] createGrid(int m, int n) {
        String[] grid = new String[m];

        // First row: all open
        StringBuilder first = new StringBuilder();
        for (int j = 0; j < n; j++) {
            first.append('.');
        }
        grid[0] = first.toString();

        // Remaining rows: only last cell open
        for (int i = 1; i < m; i++) {
            StringBuilder row = new StringBuilder();

            for (int j = 0; j < n - 1; j++) {
                row.append('#');
            }

            row.append('.');
            grid[i] = row.toString();
        }

        return grid;
    }
}