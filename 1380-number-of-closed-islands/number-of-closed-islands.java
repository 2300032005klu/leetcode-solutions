import java.util.*;

class pair {
    int r, c;

    pair(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

class Solution {
    public int closedIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int cnt = 0;

        boolean vis[][] = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((i == 0 || j == 0 || i == n - 1 || j == m - 1)
                    && grid[i][j] == 0 && !vis[i][j]) {
                    bfs(n, m, grid, i, j, vis);
                }
            }
        }

       
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0 && !vis[i][j]) {
                    cnt++;
                    bfs(n, m, grid, i, j, vis);
                }
            }
        }

        return cnt;
    }

    static void bfs(int n, int m, int[][] grid, int row, int col,
                    boolean[][] vis) {
        Queue<pair> q = new LinkedList<>();

        vis[row][col] = true;
        q.offer(new pair(row, col));

        int r[] = {-1, 0, 1, 0};
        int c[] = {0, 1, 0, -1};

        while (!q.isEmpty()) {
            int ro = q.peek().r;
            int co = q.poll().c;

            for (int i = 0; i < 4; i++) {
                int nr = ro + r[i];
                int nc = co + c[i];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m
                    && grid[nr][nc] == 0 && !vis[nr][nc]) {
                    vis[nr][nc] = true;
                    q.offer(new pair(nr, nc));
                }
            }
        }
    }
}