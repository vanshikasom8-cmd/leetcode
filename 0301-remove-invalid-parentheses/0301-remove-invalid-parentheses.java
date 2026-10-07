import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        dfs(s, 0, 0, '(', ')', result);
        return result;
    }

    private void dfs(String s, int start, int lastRemove,
                     char open, char close,
                     List<String> result) {

        int balance = 0;

        for (int i = start; i < s.length(); i++) {

            if (s.charAt(i) == open) {
                balance++;
            } else if (s.charAt(i) == close) {
                balance--;
            }

            if (balance < 0) {

                for (int j = lastRemove; j <= i; j++) {

                    if (s.charAt(j) == close &&
                        (j == lastRemove || s.charAt(j - 1) != close)) {

                        String next =
                            s.substring(0, j) +
                            s.substring(j + 1);

                        dfs(next, i, j, open, close, result);
                    }
                }

                return;
            }
        }

        String reversed = new StringBuilder(s)
                .reverse()
                .toString();

        if (open == '(') {
            dfs(reversed, 0, 0, ')', '(', result);
        } else {
            result.add(reversed);
        }
    }
}