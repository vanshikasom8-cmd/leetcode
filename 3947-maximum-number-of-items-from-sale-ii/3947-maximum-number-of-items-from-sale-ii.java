
import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;
        int maxFactor = 0;
        int minPrice = Integer.MAX_VALUE;

        // Find maximum factor and minimum price
        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }

        // Count frequency of each factor
        int[] freq = new int[maxFactor + 1];

        for (int[] item : items) {
            freq[item[0]]++;
        }

        // Count how many eligible free items each factor can provide
        for (int i = 1; i <= maxFactor; i++) {
            for (int j = 2 * i; j <= maxFactor; j += i) {
                freq[i] += freq[j];
            }
        }

        // Sort items by price
        Arrays.sort(items, (a, b) -> Integer.compare(a[1], b[1]));

        long ans = 0;
        long remaining = budget;

        for (int[] item : items) {

            int factor = item[0];
            int price = item[1];

            // Buying this item is not better than
            // purchasing cheapest items directly
            if (price > 2L * minPrice) {
                break;
            }

            // Exclude the item itself
            int freeCount = freq[factor] - 1;

            // Maximum copies that can generate free items
            long take = Math.min(freeCount, remaining / price);

            // Each purchase gives one purchased + one free
            ans += 2L * take;

            remaining -= take * price;
        }

        // Spend remaining budget on cheapest items
        ans += remaining / minPrice;

        return (int) ans;
    }
}
