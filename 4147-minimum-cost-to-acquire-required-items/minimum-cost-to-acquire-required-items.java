class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {

        long a = (long) need1 * cost1 + (long) need2 * cost2;

        long b = (long) Math.max(need1, need2) * costBoth;

        long c = (long) Math.min(need1, need2) * costBoth
               + (long) Math.max(0, need1 - need2) * cost1
               + (long) Math.max(0, need2 - need1) * cost2;

        return Math.min(a, Math.min(b, c));
    }
}