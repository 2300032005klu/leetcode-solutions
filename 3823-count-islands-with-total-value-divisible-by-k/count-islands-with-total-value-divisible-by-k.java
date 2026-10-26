class pair{
    int r,c;
    pair(int r,int c){
        this.r=r;
        this.c=c;
    }
}

class Solution {
    public int countIslands(int[][] grid, int k) {
        int n=grid.length;
        int m=grid[0].length;
        boolean vis[][]=new boolean[n][m];

        int cnt=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]>0 && !vis[i][j]){
                    long sum=bfs(n,m,i,j,grid,vis);
                    if(sum%k == 0) cnt++;
                }
            }
        }
        return cnt;
    }

    static long bfs(int n,int m,int row,int col,int[][] grid,boolean[][] vis){
        Queue<pair> q=new LinkedList<>();
        q.offer(new pair(row,col));
        vis[row][col]=true;
        long sum=0;
        int ro[]={-1,0,1,0};
        int co[]={0,1,0,-1};
        while(!q.isEmpty()){
            int nr=q.peek().r;
            int nc=q.peek().c;
            q.poll();

            sum+=grid[nr][nc];

            for(int i=0;i<4;i++){
                int nro=nr+ro[i];
                int nco=nc+co[i];

                if(nro>=0 && nro<n && nco>=0 && nco<m && grid[nro][nco]>0 && !vis[nro][nco]){
                    q.offer(new pair(nro,nco));
                    vis[nro][nco]=true;
                }
            }
        }
        return sum;
    }
}