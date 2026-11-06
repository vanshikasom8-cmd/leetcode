
import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        int m = queries.length;
        int[] ans = new int[m];

        // Sort logs by time
        Arrays.sort(logs, (a, b) -> a[1] - b[1]);

        // Store query indices
        Integer[] idx = new Integer[m];

        for (int i = 0; i < m; i++) {
            idx[i] = i;
        }

        // Sort queries according to their values
        Arrays.sort(idx, (a, b) ->
            Integer.compare(queries[a], queries[b]));

        HashMap<Integer, Integer> map = new HashMap<>();

        int left = 0;
        int right = 0;
        int active = 0;

        for (int i = 0; i < m; i++) {

            int q = queries[idx[i]];
            int start = q - x;

            // Add logs within the ending time
            while (right < logs.length &&
                   logs[right][1] <= q) {

                int server = logs[right][0];

                map.put(server, map.getOrDefault(server, 0) + 1);

                if (map.get(server) == 1) {
                    active++;
                }

                right++;
            }

            // Remove logs outside the starting time
            while (left < right &&
                   logs[left][1] < start) {

                int server = logs[left][0];

                map.put(server, map.get(server) - 1);

                if (map.get(server) == 0) {
                    map.remove(server);
                    active--;
                }

                left++;
            }

            ans[idx[i]] = n - active;
        }

        return ans;
    }
}
