class Solution {
    public String digitSum(String s, int k) {
        String str = s;

        while(str.length() > k) {
            str = calSum(str, k);
        }

        return str;
    }

    static String calSum(String str, int k) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < str.length(); i += k) {
            int sum = 0;

            for(int j = i; j < Math.min(i+k, str.length()); j++) {
                sum += str.charAt(j) - '0';
            }

            sb.append(sum);
        }

        return sb.toString();
    }
}
