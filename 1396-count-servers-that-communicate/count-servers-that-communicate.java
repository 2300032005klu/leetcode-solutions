class Pair {
    int ro, co;

    Pair(int ro, int co) {
        this.ro = ro;
        this.co = co;
    }
}

class Solution {
    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean vis[][] = new boolean[m][n];
        int sum = 0;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1 && !vis[i][j]) {
                    int cnt = bfs(i, j, grid, vis, m, n);

                    if(cnt > 1) {
                        sum += cnt;
                    }
                }
            }
        }

        return sum;
    }

    static int bfs(int r, int c, int[][] grid, boolean[][] vis, int m, int n) {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(r, c));
        vis[r][c] = true;

        int cnt = 1;

        while(!q.isEmpty()) {
            Pair p = q.poll();

            for(int j = 0; j < n; j++) {
                if(grid[p.ro][j] == 1 && !vis[p.ro][j]) {
                    vis[p.ro][j] = true;
                    q.offer(new Pair(p.ro, j));
                    cnt++;
                }
            }

            for(int i = 0; i < m; i++) {
                if(grid[i][p.co] == 1 && !vis[i][p.co]) {
                    vis[i][p.co] = true;
                    q.offer(new Pair(i, p.co));
                    cnt++;
                }
            }
        }

        return cnt;
    }
}