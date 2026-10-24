class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice,
                                 String arriveBob, String leaveBob) {

        int days[] = {31, 28, 31, 30, 31, 30,
                      31, 31, 30, 31, 30, 31};

        int aa = convert(arriveAlice, days);
        int la = convert(leaveAlice, days);
        int ab = convert(arriveBob, days);
        int lb = convert(leaveBob, days);

        int start = Math.max(aa, ab);
        int end = Math.min(la, lb);

        return Math.max(0, end - start + 1);
    }

    public int convert(String date, int days[]) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3));

        int sum = day;

        for (int i = 0; i < month - 1; i++) {
            sum += days[i];
        }

        return sum;
    }
}