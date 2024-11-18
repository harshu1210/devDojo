function twoSum(nums, target) {
  let result = []
  for(let i=0;i<nums.length;i++){
      for(let j=0;j<nums.length;j++){
          if(i!=j && target == (nums[i]+nums[j])){
              return result = [i,j];
              break;
          }
      }
  }
};

let nums=[];
let target =0;
let result=[];
for(let i=0;i<Math.floor(Math.random() *10**2)+30;i++){
  let n = Math.floor(Math.random() * 10**4);
  for(let j=0;j<n;j++){
    nums[j] = Math.floor(Math.random() * 2*10**9) - Math.floor(Math.random() *10**9);
  }
  target = nums[Math.floor(Math.random() * n)] + nums[Math.floor(Math.random() *n)];
  result.push({testcaseid:i,testcase:1,input:[nums,target],expectedoutput:twoSum(nums,target)});
}

const fs = require('fs');
const path = "/Users/harshal/devDojo/devDojo-UI/src/testcases/";
result = JSON.stringify(result, null, 2); // Indent with 2 spaces for readability

// Write JSON string to a file
fs.writeFile(path+'two-sum_testcases.json', result, (err) => {
    if (err) {
        console.error('Error writing to file', err);
    } else {
        console.log('JSON data saved to data.json');
    }
});
