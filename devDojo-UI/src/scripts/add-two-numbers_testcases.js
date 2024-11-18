function addTwoNumbers(l1, l2) {
  let carry = 0;
  let dummyHead = new Listnode(0);
  let current = dummyHead;

  while (l1 || l2 || carry) {
    let val1 = l1 ? l1.value : 0; // Use 0 if l1 is null
    let val2 = l2 ? l2.value : 0; // Use 0 if l2 is null
    let sum = val1 + val2 + carry;

    carry = Math.floor(sum / 10); // Calculate carry
    current.next = new Listnode(sum % 10); // Create a new node with the sum modulo 10
    current = current.next;

    // Move to the next nodes in l1 and l2
    if (l1) l1 = l1.next;
    if (l2) l2 = l2.next;
  }

  return reverseList(dummyHead.next); // Return the head of the resultant list
}

function reverseList(head) {
  let prev = null;
  let current = head;
  while (current) {
    let next = current.next;
    current.next = prev;
    prev = current;
    current = next;
  }
  return prev; // New head of the reversed list
}


function Listnode(value,next){
  this.value = value !== undefined ? value : 0; // Assign value or default to 0
  this.next = next !== undefined ? next : null;
}

function createLinkedList(n){
  let dummyHead = new Listnode(0)
  let current = dummyHead;
  for(let i=0;i<n;i++){
    current.next = new Listnode((Math.floor(Math.random() *10)));
    current =current.next;
  }
  return dummyHead.next;
}

let l1,l2, result=[];
for(let i=0;i<Math.floor(Math.random() *10**2)+30;i++){
  let n = Math.floor(Math.random() ** 100)+10;
  l1 = createLinkedList(n)
  n =Math.floor(Math.random() ** 100)+10;
  l2 = createLinkedList(n)
  result.push({testcaseid:i,testcase:2,input:[l1,l2],output:addTwoNumbers(l1,l2)})
}

const fs = require('fs');
const path = "/Users/harshal/devDojo/devDojo-UI/src/testcases/";

result = JSON.stringify(result, null, 2); // Indent with 2 spaces for readability

// Write JSON string to a file
fs.writeFile(path+'add-two-numbers_testcases.json', result, (err) => {
    if (err) {
        console.error('Error writing to file', err);
    } else {
        console.log('JSON data saved to data.json');
    }
});
