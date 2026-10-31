class pair {
    int ro, co;

    pair(int ro, int co) {
        this.ro = ro;
        this.co = co;
    }
}

class Solution {
    public int uniquePaths(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        int MOD = 1000000007;

        long dp[][] = new long[n][m];

        dp[0][0] = 1;

        int row[] = {0, 1};
        int col[] = {1, 0};

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {

                if (dp[r][c] == 0) continue;

                if (r == n - 1 && c == m - 1) continue;

                for (int i = 0; i < 2; i++) {

                    int nr = r + row[i];
                    int nc = c + col[i];

                    int dir = i;

                    while (nr >= 0 && nr < n &&
                           nc >= 0 && nc < m &&
                           grid[nr][nc] == 1) {

                        if (dir == 0) {
                            nr++;
                            dir = 1;
                        } else {
                            nc++;
                            dir = 0;
                        }
                    }

                    if (nr >= 0 && nr < n &&
                        nc >= 0 && nc < m) {

                        dp[nr][nc] =
                            (dp[nr][nc] + dp[r][c]) % MOD;
                    }
                }
            }
        }

        return (int) dp[n-1][m-1];
    }
}
