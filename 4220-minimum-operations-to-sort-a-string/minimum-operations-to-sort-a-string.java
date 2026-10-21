class Solution {
    public int minOperations(String s) {
        int n = s.length();

        char ch[] = s.toCharArray();
        char ch1[] = s.toCharArray();
        Arrays.sort(ch1);

        if (Arrays.equals(ch, ch1)) {
            return 0;
        }

        if (n == 2) {
            return -1;
        }

        if (ch[0] == ch1[0] || ch[n - 1] == ch1[n - 1]) {
            return 1;
        }

        if (ch[0] == ch1[n - 1] && ch[n - 1] == ch1[0]
                && s.indexOf(ch1[n - 1]) == s.lastIndexOf(ch1[n - 1])
                && s.indexOf(ch1[0]) == s.lastIndexOf(ch1[0])) {
            return 3;
        }

        return 2;
    }
}
