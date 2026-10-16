class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for(int i=0; i<numRows; i++){
            List<Integer> temp= new ArrayList<>();
            for(int j=0; j<=i; j++){
                int val = nCr(i,j);
                temp.add(val);
            }
            ans.add(temp);
        }
        return ans;   
    }
    public int nCr(int row ,int col){
        int res = 1;
        for(int i=0; i<col; i++){
            res=res*(row-i);
            res= res/(i+1);
        }
         return res;
    }
        
        
    }
    



// APPROACH 1
        // List<List<Integer>> ans = new ArrayList<>();
        // for(int i=0; i<numRows; i++){
        //      List<Integer> row = new ArrayList<>();
        //      for(int j=0; j<=i; j++){
        //         if(j == 0 || j== i){
        //             row.add(1);
        //         }else{
        //             row.add(ans.get(i-1).get(j-1)+ans.get(i-1).get(j));
        //         }
        //      }
        //      ans.add(row);

        // }
        // return ans;