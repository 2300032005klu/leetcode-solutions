
class pair {
    int r, w;

    pair(int r, int w) {
        this.r = r;
        this.w = w;
    }
}

class pair1 {
    int node, cnt;
    long w;

    pair1(int node, int cnt, long w) {
        this.node = node;
        this.cnt = cnt;
        this.w = w;
    }
}

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {

        if (k == 0) return -1;

        List<List<pair>> gph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            gph.add(new ArrayList<>());
        }

        for (int edge[] : edges) {
            gph.get(edge[0]).add(new pair(edge[1], edge[2]));
        }

        long dist[][] = new long[n][k + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        PriorityQueue<pair1> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a.w, b.w)
        );

        dist[0][1] = 0;
        pq.offer(new pair1(0, 1, 0));

        while (!pq.isEmpty()) {

            pair1 p = pq.poll();

            int curr = p.node;
            int cnt = p.cnt;
            long w = p.w;

            if (w != dist[curr][cnt]) continue;

            if (curr == n - 1) return (int) w;

            for (pair e : gph.get(curr)) {

                int nn = e.r;
                int we = e.w;

                int newCnt = 1;

                if (labels.charAt(curr) == labels.charAt(nn)) {
                    newCnt = cnt + 1;
                }

                if (newCnt > k) continue;

                long newW = w + we;

                if (newW < dist[nn][newCnt]) {
                    dist[nn][newCnt] = newW;
                    pq.offer(new pair1(nn, newCnt, newW));
                }
            }
        }

        return -1;
    }
}
