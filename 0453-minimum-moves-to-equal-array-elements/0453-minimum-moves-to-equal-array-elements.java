class Solution {
    public int minMoves(int[] nums) {
        int min = nums[0];
        int sum = 0;

        for (int num : nums) {
            min = Math.min(min, num);
            sum += num;
        }

        return sum - nums.length * min;
    }
}



// Example: nums = [1,2,3]
// - Minimum = 1
// - Sum = 6
// - Moves = \(6 - (3 \times 1) = 3\)