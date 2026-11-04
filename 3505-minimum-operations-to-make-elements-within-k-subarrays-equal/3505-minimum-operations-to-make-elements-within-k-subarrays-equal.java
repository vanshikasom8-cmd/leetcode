
import java.util.*;

class Solution {

    TreeMap<Long, Integer> left = new TreeMap<>();
    TreeMap<Long, Integer> right = new TreeMap<>();

    long sumLeft = 0, sumRight = 0;
    int sizeLeft = 0, sizeRight = 0;

    void add(TreeMap<Long, Integer> map, long val) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    void remove(TreeMap<Long, Integer> map, long val) {
        int count = map.get(val);

        if (count == 1) map.remove(val);
        else map.put(val, count - 1);
    }

    void balance() {
        while (sizeLeft > sizeRight + 1) {
            long val = left.lastKey();

            remove(left, val);
            sumLeft -= val;
            sizeLeft--;

            add(right, val);
            sumRight += val;
            sizeRight++;
        }

        while (sizeLeft < sizeRight) {
            long val = right.firstKey();

            remove(right, val);
            sumRight -= val;
            sizeRight--;

            add(left, val);
            sumLeft += val;
            sizeLeft++;
        }
    }

    void insert(long val) {
        if (left.isEmpty() || val <= left.lastKey()) {
            add(left, val);
            sumLeft += val;
            sizeLeft++;
        } else {
            add(right, val);
            sumRight += val;
            sizeRight++;
        }

        balance();
    }

    void erase(long val) {
        if (left.containsKey(val)) {
            remove(left, val);
            sumLeft -= val;
            sizeLeft--;
        } else {
            remove(right, val);
            sumRight -= val;
            sizeRight--;
        }

        balance();
    }

    long getCost() {
        long median = left.lastKey();

        return median * sizeLeft - sumLeft
             + sumRight - median * sizeRight;
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        long[] cost = new long[n - x + 1];

        for (int i = 0; i < n; i++) {
            insert(nums[i]);

            if (i >= x) {
                erase(nums[i - x]);
            }

            if (i >= x - 1) {
                cost[i - x + 1] = getCost();
            }
        }

        long INF = Long.MAX_VALUE / 4;

        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        for (int j = 1; j <= k; j++) {

            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {

                curr[i] = curr[i - 1];

                if (i >= x && prev[i - x] != INF) {

                    long take = prev[i - x] + cost[i - x];

                    curr[i] = Math.min(curr[i], take);
                }
            }

            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }
}
