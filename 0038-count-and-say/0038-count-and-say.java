
class Solution {
    public String countAndSay(int n) {

        String s = "1";

        for (int i = 2; i <= n; i++) {

            StringBuilder ans = new StringBuilder();

            int count = 1;

            for (int j = 1; j < s.length(); j++) {

                if (s.charAt(j) == s.charAt(j - 1)) {
                    count++;
                } 
                else {
                    ans.append(count);
                    ans.append(s.charAt(j - 1));

                    count = 1;
                }
            }

            ans.append(count);
            ans.append(s.charAt(s.length() - 1));

            s = ans.toString();
        }

        return s;
    }
}
