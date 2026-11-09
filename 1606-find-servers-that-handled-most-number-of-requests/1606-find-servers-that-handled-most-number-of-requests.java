
import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        TreeSet<Integer> available = new TreeSet<>();

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        int[] count = new int[k];

        for (int i = 0; i < arrival.length; i++) {

            // Free completed servers
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                int[] curr = busy.poll();
                available.add(curr[1]);
            }

            // All servers are busy
            if (available.isEmpty()) {
                continue;
            }

            int server = i % k;

            // Find next available server
            Integer assigned = available.ceiling(server);

            if (assigned == null) {
                assigned = available.first();
            }

            available.remove(assigned);

            count[assigned]++;

            // Add server to busy queue
            busy.offer(new int[]{
                arrival[i] + load[i],
                assigned
            });
        }

        // Find maximum request count
        int max = 0;

        for (int c : count) {
            max = Math.max(max, c);
        }

        // Find busiest servers
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                ans.add(i);
            }
        }

        return ans;
    }
}
