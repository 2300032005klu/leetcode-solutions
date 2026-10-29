class Solution {
    int timer;
    int LOG;
    int[] tin, tout, depth, parent;
    int[][] up;
    int[] prefix;
    List<List<Integer>> li;
    Fenwick bit;
    char[] ch;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        List<Boolean> res = new ArrayList<>();
        li = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            li.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            li.get(edge[0]).add(edge[1]);
            li.get(edge[1]).add(edge[0]);
        }

        ch = s.toCharArray();

        LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        parent = new int[n];
        prefix = new int[n];
        up = new int[LOG][n];

        Arrays.fill(parent, -1);
        timer = 0;

        dfs(0, -1, 0);

        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }

        bit = new Fenwick(n);

        for (String str : queries) {
            String[] parts = str.split(" ");

            if (str.charAt(0) == 'u') {
                int node = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                int delta = (1 << (ch[node] - 'a')) ^ (1 << (c - 'a'));

                bit.update(tin[node], delta);
                bit.update(tout[node] + 1, delta);

                ch[node] = c;
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int lca = getLCA(u, v);

                int pu = prefix[u] ^ bit.query(tin[u]);
                int pv = prefix[v] ^ bit.query(tin[v]);

                int mask = pu ^ pv ^ (1 << (ch[lca] - 'a'));

                res.add((mask & (mask - 1)) == 0);
            }
        }

        return res;
    }

    void dfs(int node, int par, int d) {
        parent[node] = par;
        depth[node] = d;
        tin[node] = timer++;

        up[0][node] = (par == -1) ? node : par;

        prefix[node] = (par == -1 ? 0 : prefix[par])
                       ^ (1 << (ch[node] - 'a'));

        for (int x : li.get(node)) {
            if (x != par) {
                dfs(x, node, d + 1);
            }
        }

        tout[node] = timer - 1;
    }

    int getLCA(int u, int v) {
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

    static class Fenwick {
        int[] tree;
        int n;

        Fenwick(int n) {
            this.n = n + 1;
            tree = new int[n + 2];
        }

        void update(int idx, int val) {
            idx++;
            while (idx < tree.length) {
                tree[idx] ^= val;
                idx += idx & -idx;
            }
        }

        int query(int idx) {
            idx++;
            int ans = 0;

            while (idx > 0) {
                ans ^= tree[idx];
                idx -= idx & -idx;
            }

            return ans;
        }
    }
}
