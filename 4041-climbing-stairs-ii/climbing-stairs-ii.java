
class Pair {
    int node;
    int cost;

    Pair(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }
}

class Solution {
    public int climbStairs(int n, int[] costs) {

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);

        int dist[] = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[0] = 0;
        pq.offer(new Pair(0, 0));

        while(!pq.isEmpty()) {

            Pair p = pq.poll();

            int node = p.node;
            int cost = p.cost;

            if(cost > dist[node]) continue;

            if(node == n) return cost;

            for(int jump = 1; jump <= 3; jump++) {

                int next = node + jump;

                if(next > n) continue;

                int newCost = cost + costs[next - 1] + jump * jump;

                if(newCost < dist[next]) {

                    dist[next] = newCost;

                    pq.offer(new Pair(next, newCost));
                }
            }
        }

        return dist[n];
    }
}
