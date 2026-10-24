class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice,
                                 String arriveBob, String leaveBob) {

        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int startAlice = getDay(arriveAlice, days);
        int endAlice = getDay(leaveAlice, days);
        int startBob = getDay(arriveBob, days);
        int endBob = getDay(leaveBob, days);

        int count = 0;

        // Brute force: check each day
        for (int i = 1; i <= 365; i++) {
            if (i >= startAlice && i <= endAlice &&
                i >= startBob && i <= endBob) {
                count++;
            }
        }

        return count;
    }

    private int getDay(String date, int[] days) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        for (int i = 0; i < month - 1; i++) {
            day += days[i];
        }

        return day;
    }
}