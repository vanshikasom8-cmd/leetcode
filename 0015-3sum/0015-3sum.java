class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
    int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans=new ArrayList<>();
        for(int i=0;i<n-2;i++){
            if(i>0 && nums[i]==nums[i-1]){
                continue;
            }
            int low=i+1;
            int high=n-1;
            while(low<high){
                int sum=nums[i]+nums[low]+nums[high];
                if(sum==0){
                    int a=nums[low];
                    int b=nums[high];
                    ans.add(Arrays.asList(nums[i],nums[low],nums[high]));
                    while(low<high && nums[low]==a)low++;
                    while(low<high && nums[high]==b)high--;
                }
                else if(sum<0){
                    low++;
                }else{
                    high--;
                }
                   
            }

         
        
        }
    return ans;
    } 
}

// BRUTE FORCE
        // Set<List<Integer>> st = new HashSet<>();
        // int n=nums.length;
        // // check all possible triplets:
        // for (int i = 0; i < n; i++) {
        //     for (int j = i + 1; j < n; j++) {
        //         for (int k = j + 1; k < n; k++) {
        //             if (nums[i] + nums[j] + nums[k] == 0) {
        //                 List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k]);
        //                 temp.sort(null);
        //                 st.add(temp);
        //             }
        //         }
        //     }
        // }

        // // store the set elements in the answer:
        // List<List<Integer>> ans = new ArrayList<>(st);
        // return ans;