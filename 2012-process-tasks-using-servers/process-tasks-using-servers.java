class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;

        PriorityQueue<int[]> free = new PriorityQueue<>((a,b) -> {
            if(a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        PriorityQueue<long[]> busy = new PriorityQueue<>((a,b) -> {
            if(a[0] != b[0]) return Long.compare(a[0], b[0]);
            return Long.compare(a[1], b[1]);
        });

        for(int i = 0; i < n; i++) {
            free.add(new int[]{servers[i], i});
        }

        int ans[] = new int[m];
        long time = 0;

        for(int i = 0; i < m; i++) {
            time = Math.max(time, i);

            while(!busy.isEmpty() && busy.peek()[0] <= time) {
                long temp[] = busy.poll();
                free.add(new int[]{(int)temp[2], (int)temp[1]});
            }

            if(free.isEmpty()) {
                time = busy.peek()[0];

                while(!busy.isEmpty() && busy.peek()[0] <= time) {
                    long temp[] = busy.poll();
                    free.add(new int[]{(int)temp[2], (int)temp[1]});
                }
            }

            int curr[] = free.poll();

            ans[i] = curr[1];

            busy.add(new long[]{
                time + tasks[i],
                curr[1],
                curr[0]
            });
        }

        return ans;
    }
}
