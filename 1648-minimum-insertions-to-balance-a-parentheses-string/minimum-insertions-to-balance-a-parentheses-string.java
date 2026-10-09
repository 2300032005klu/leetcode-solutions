
class Solution {
    public int minInsertions(String s) {
        int cnt = 0, oc = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                oc += 2;

                if (oc % 2 != 0) {
                    cnt++;
                    oc--;
                }
            } else {
                oc--;

                if (oc < 0) {
                    cnt++;
                    oc = 1;
                }
            }
        }

        return cnt + oc;
    }
}
