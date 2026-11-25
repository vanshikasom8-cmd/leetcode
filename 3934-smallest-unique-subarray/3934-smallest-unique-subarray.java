
import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        long mod1 = 1000000007L;
        long mod2 = 1000000009L;
        long base = 131L;

        long[] pow1 = new long[n + 1];
        long[] pow2 = new long[n + 1];

        pow1[0] = 1;
        pow2[0] = 1;

        for (int i = 1; i <= n; i++) {
            pow1[i] = (pow1[i - 1] * base) % mod1;
            pow2[i] = (pow2[i - 1] * base) % mod2;
        }

        long[] h1 = new long[n + 1];
        long[] h2 = new long[n + 1];

        for (int i = 0; i < n; i++) {
            h1[i + 1] = (h1[i] * base + nums[i]) % mod1;
            h2[i + 1] = (h2[i] * base + nums[i]) % mod2;
        }

        int low = 1, high = n;

        while (low < high) {
            int mid = low + (high - low) / 2;

            HashSet<Long> set = new HashSet<>();
            boolean found = false;

            for (int i = 0; i + mid <= n; i++) {
                long x = (h1[i + mid] -
                    h1[i] * pow1[mid] % mod1 + mod1) % mod1;

                long y = (h2[i + mid] -
                    h2[i] * pow2[mid] % mod2 + mod2) % mod2;

                long key = x * mod2 + y;

                if (!set.add(key)) {
                    // Duplicate subarray
                } else {
                    // First occurrence
                }
            }

            // Count occurrences to check uniqueness
            HashMap<Long, Integer> freq = new HashMap<>();

            for (int i = 0; i + mid <= n; i++) {
                long x = (h1[i + mid] -
                    h1[i] * pow1[mid] % mod1 + mod1) % mod1;

                long y = (h2[i + mid] -
                    h2[i] * pow2[mid] % mod2 + mod2) % mod2;

                long key = x * mod2 + y;

                freq.put(key, freq.getOrDefault(key, 0) + 1);
            }

            for (int count : freq.values()) {
                if (count == 1) {
                    found = true;
                    break;
                }
            }

            if (found) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }
}
