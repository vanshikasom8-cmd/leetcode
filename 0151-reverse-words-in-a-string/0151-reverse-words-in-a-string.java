class Solution {
    public String reverseWords(String s) {
        StringBuilder ans = new StringBuilder();
        int i = s.length()-1;

        while(i>=0){
            // remove all trailing spaces
            while(i>=0 && s.charAt(i)==' '){
                i--;
            }
            // check value of i
            if(i<0){
                break;
            }
            int j = i;
            // find starting index of the word
            while(j>=0 && s.charAt(j)!=' '){
                j--;
            }
            // jaise hi j space wale index pr aaya stop hoyega and is word ko ans mei append kr dena
            ans.append(s.substring(j+1,i+1));
            // remove extra space where j is standing and add a space in ans

            while(j>=0 && s.charAt(j)==' '){
                j--;
            }
            // j<0 its mean first word pr h main no extrra space needed
            // j>0 space needed
            if(j>=0){
                ans.append(' ');

            }
            i=j;

        }
        return ans.toString();
        
    }
}