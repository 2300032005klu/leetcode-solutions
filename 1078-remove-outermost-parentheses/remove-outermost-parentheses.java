class Solution {
    public String removeOuterParentheses(String s) {
        List<String> li=new ArrayList<>();
        int oc=0;
        int start=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                oc++;
            }
            else if(s.charAt(i)==')'){
                oc--;
            }

            if(oc==0){
                li.add(s.substring(start,i+1));
                start=i+1;
            }
        }
        
        String str="";
        for(String ss:li){
            str+=ss.substring(1,ss.length()-1);
        }

        return str;
    }
}