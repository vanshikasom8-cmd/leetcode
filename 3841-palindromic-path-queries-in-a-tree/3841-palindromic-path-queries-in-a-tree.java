
import java.util.*;

class Solution {
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        List<Integer>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        int LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        int[][] up = new int[LOG][n];
        int[] depth = new int[n];
        int[] tin = new int[n];
        int[] tout = new int[n];
        int[] parent = new int[n];
        int[] rootMask = new int[n];
        int[] iter = new int[n];
        int[] stack = new int[n];
        char[] chars = s.toCharArray();

        int top = 0, timer = 0;
        stack[top++] = 0;
        parent[0] = 0;
        tin[0] = timer++;

        while (top > 0) {
            int u = stack[top - 1];

            if (iter[u] < adj[u].size()) {
                int v = adj[u].get(iter[u]++);

                if (v == parent[u]) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;
                up[0][v] = u;
                rootMask[v] = rootMask[u] ^ (1 << (chars[v] - 'a'));
                tin[v] = timer++;
                stack[top++] = v;
            } else {
                tout[u] = timer - 1;
                top--;
            }
        }

        up[0][0] = 0;

        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }

        Fenwick fw = new Fenwick(n);
        List<Boolean> answer = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split(" ");

            if (p[0].equals("update")) {
                int u = Integer.parseInt(p[1]);
                char c = p[2].charAt(0);

                int delta = (1 << (chars[u] - 'a'))
                          ^ (1 << (c - 'a'));

                if (delta != 0) {
                    fw.rangeXor(tin[u], tout[u], delta);
                    chars[u] = c;
                }
            } else {
                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);

                int l = lca(u, v, depth, up, LOG);

                int maskU = rootMask[u] ^ fw.pointQuery(tin[u]);
                int maskV = rootMask[v] ^ fw.pointQuery(tin[v]);

                int pathMask = maskU ^ maskV
                             ^ (1 << (chars[l] - 'a'));

                answer.add(Integer.bitCount(pathMask) <= 1);
            }
        }

        return answer;
    }

    private int lca(int u, int v, int[] depth, int[][] up, int LOG) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) return u;

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return up[0][u];
    }

    class Fenwick {
        int[] tree;

        Fenwick(int n) {
            tree = new int[n + 2];
        }

        void update(int i, int val) {
            for (; i < tree.length; i += i & -i) {
                tree[i] ^= val;
            }
        }

        int query(int i) {
            int ans = 0;

            for (; i > 0; i -= i & -i) {
                ans ^= tree[i];
            }

            return ans;
        }

        void rangeXor(int l, int r, int val) {
            update(l + 1, val);
            update(r + 2, val);
        }

        int pointQuery(int i) {
            return query(i + 1);
        }
    }
}
