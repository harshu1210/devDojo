const fs = require('fs');
const path = require('path');
const Papa = require('papaparse');

const folderPath = '../../../LeetCode-Questions-CompanyWise';  // Folder containing CSV files
const axios = require('axios');

const problemApi = 'http://localhost:8080/api/problem';
const firmApi = 'http://localhost:8080/api/firms';
// Function to parse all CSV files in the folder
function parseCsvFilesInFolder(folderPath) {
  // Read the contents of the folder
  fs.readdir(folderPath, (err, files) => {
    if (err) {
      console.error('Error reading the folder:', err);
      return;
    }
    // Filter out only CSV files
    const csvFiles = files.filter(file => file.endsWith('.csv'));
    // If there are no CSV files
    if (csvFiles.length === 0) {
      console.log('No CSV files found in the folder.');
      return;
    }

    let allData = [];
    csvFiles.forEach(file => {
      const filePath = path.join(folderPath, file);
      allData = allData.concat(parseCsvFile(filePath));  // Collect all parsed data
    });

    // After parsing all files, save the combined JSON data to a file
    let firms = [];
    if (allData.length > 0) {
      //Data Transformation
      allData.sort((a,b) => a.ID - b.ID)
      temp = allData[allData.length-1];
      for(let i=allData.length-2;i>=0;i--){
        if(allData[i].ID == null){
          allData.splice(i,1);
        }else if(allData[i].ID == temp.ID){
          firms.push({cid:allData[i].ID,companyName:allData[i].company})
          allData.splice(i,1);
        }else if(allData[i].ID != temp.ID){
          temp = allData[i];
          firms.push({cid:allData[i].ID,companyName:allData[i].company})
        }

      }

      axios
          .post(firmApi, firms)
          .then((response) => {
          })
          .catch((error) => {
          });

      newData=[];
      for(let i=0;i<allData.length;i++){
        newData.push({
          pid:allData[i].ID,
          title:allData[i].Title.toString(),
        acceptance:allData[i].Acceptance.toString(),
        difficulty:allData[i].Difficulty.toString(),
        frequency:allData[i].Frequency.toString(),
        devDojoLink:allData[i]["Leetcode Question Link"].toString()
        })
      }

      axios
          .post(problemApi, newData)
          .then((response) => {
          })
          .catch((error) => {
          });
    }
  });
}

// Function to parse a single CSV file
function parseCsvFile(filePath) {
  const data = fs.readFileSync(filePath, 'utf8'); // Read the CSV file synchronously
  const results = Papa.parse(data, {
    header: true,  // Treat the first row as column headers
    dynamicTyping: true,  // Automatically convert data types (e.g., string to number)
  });
  const match = filePath.match(/\/([^\/]+?)_/); // Match the part before the first underscore
  for(let i=0;i<results.data.length;i++){
    results.data[i].company = match ? match[1] : null;
  }
  return results.data; // Return parsed data
}

// Call the function to parse CSV files in the folder and save the combined data to a JSON file
parseCsvFilesInFolder(folderPath);
