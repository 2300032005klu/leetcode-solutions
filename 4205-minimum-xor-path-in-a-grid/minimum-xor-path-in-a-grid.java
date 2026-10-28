class pair {
    int r, c, val;

    pair(int r, int c, int val) {
        this.r = r;
        this.c = c;
        this.val = val;
    }
}

class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Set<Integer>[][] mat = new HashSet[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = new HashSet<>();
            }
        }

        bfs(m, n, grid, mat);

        int ans = Integer.MAX_VALUE;

        for (int x : mat[m - 1][n - 1]) {
            ans = Math.min(ans, x);
        }

        return ans;
    }

    static void bfs(int m, int n, int[][] grid, Set<Integer>[][] mat) {
        Queue<pair> q = new LinkedList<>();

        q.offer(new pair(0, 0, grid[0][0]));
        mat[0][0].add(grid[0][0]);

        int r[] = {0, 1};
        int c[] = {1, 0};

        while (!q.isEmpty()) {
            pair p = q.poll();

            int ro = p.r;
            int co = p.c;
            int prevdist = p.val;

            for (int i = 0; i < 2; i++) {
                int nro = ro + r[i];
                int nco = co + c[i];

                if (nro >= 0 && nro < m && nco >= 0 && nco < n) {
                    int newdist = prevdist ^ grid[nro][nco];

                    if (mat[nro][nco].add(newdist)) {
                        q.offer(new pair(nro, nco, newdist));
                    }
                }
            }
        }
    }
}