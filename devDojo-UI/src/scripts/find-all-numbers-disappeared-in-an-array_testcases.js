function findDisappearedNumbers(nums) {
  let auxArray=[];
  let output=[]
  for(let i=0;i<nums.length;i++){
      auxArray[i] = false;
  }
  for(let i=0;i<nums.length;i++){
      auxArray[nums[i]-1] = true;
  }
  for(let i=0;i<nums.length;i++){
      if(auxArray[i] !=true){
          output.push(i+1);
      }
  }
  return output;
}

let result=[],nums=[];
for(let i=0;i<Math.floor(Math.random() *100)+30;i++){
  let n = Math.floor(Math.random() *10**5);
  for(let j=0;j<n;j++){
    nums[j] = Math.floor(Math.random() *n)+1;
  }
  result.push({testcaseid:i,testcase:448,input:[nums],output:findDisappearedNumbers(nums)})
}

const fs = require('fs')
const path = "/Users/harshal/devDojo/devDojo-UI/src/testcases/find-all-numbers-disappeared-in-an-array_testcases.json"
result = JSON.stringify(result, null, 2); // Indent with 2 spaces for readability

// Write JSON string to a file
fs.writeFile(path, result, (err) => {
    if (err) {
        console.error('Error writing to file', err);
    } else {
        console.log('JSON data saved to data.json');
    }
});
