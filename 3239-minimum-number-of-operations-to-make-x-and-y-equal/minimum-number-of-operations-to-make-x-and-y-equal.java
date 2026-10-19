
class Solution {
    public int minimumOperationsToMakeEqual(int x, int y) {
        Queue<Integer> q = new LinkedList<>();
        HashSet<Integer> vis = new HashSet<>();

        q.offer(x);
        vis.add(x);
        int cnt = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int n = q.poll();

                if (n == y) {
                    return cnt;
                }

                if (n % 11 == 0 && !vis.contains(n / 11)) {
                    q.offer(n / 11);
                    vis.add(n/11);
                }

                if (n % 5 == 0 && !vis.contains(n / 5)) {
                    q.offer(n / 5);
                    vis.add(n / 5);
                }

                if (n > 0 && !vis.contains(n - 1)) {
                    q.offer(n - 1);
                    vis.add(n - 1);
                }

                if (!vis.contains(n + 1)) {
                    q.offer(n + 1);
                    vis.add(n + 1);
                }
            }

            cnt++;
        }

        return -1;
    }
}
