
class Solution {
    public int minMoves2(int[] nums) {

        if (nums.length == 1) return 0;

        int ele = secEle(nums);

        long sum = 0;

        for (int x : nums) {
            sum += Math.abs((long)x - ele);
        }

        return (int)sum;
    }

    static int secEle(int[] nums) {

        PriorityQueue<Integer> pq =new PriorityQueue<>((a, b) -> b - a);

        for (int x : nums) {
            pq.offer(x);
        }

        int k = nums.length / 2;

        while (k-- > 0) {
            pq.poll();
        }

        return pq.peek();
    }
}
