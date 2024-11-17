const problem = "http://localhost:8080/api/problem";
const problemContentApi = "http://localhost:8080/api/problemContent";
const { Builder, By, Key, until } = require('selenium-webdriver');
require('events').EventEmitter.defaultMaxListeners = 15;  // Increase listener limit
const axios = require("axios");

let problemData = [];

async function fetchData() {
  try {
    const response = await axios.get(problem);
    return response.data; // Return the response data directly
  } catch (error) {
    console.error('Error fetching data:', error);
    return []; // Return empty array in case of error
  }
}

// Call the function and use the result
fetchData().then(problemData => {

  const result = [];

    let min = 0;
    let maxValue = problemData.length;
    while (min < maxValue) {
        let max = min + 10;
        if (max > maxValue) {
            max = maxValue; // For the last element, make sure the max value is 1446
        }
        result.push({min:min, max:max});
        min = max; // Set min to max for the next iteration
    }
    console.log(result.length)
    console.log(process.argv[2])
  // for(let j=0;j<result.length;j++){
    for (let i = result[process.argv[2]].min; i < result[process.argv[2]].max; i++) { // Ensure you iterate over the whole problemData array
      console.log(problemData[i])
      getProblemContent(problemData[i].devDojoLink, problemData[i].pid);
    }
  // }
});

async function createProblemData(data) {
  try {
    const response = await axios.post(problemContentApi, data);
    console.log("Data posted successfully:", response.data);
  } catch (error) {
    console.error("Error posting data:", error.response ? error.response.data : error.message);
  }
}

async function getProblemContent(link, id) {
  // Launch the browser
  let driver = await new Builder().forBrowser('chrome').build();

  try {
    // Navigate to the problem link
    await driver.get(link);

    // Wait for the title element to load
    let element = await driver.wait(
      until.elementLocated(By.className("text-title-large")),
      15000 // Wait for up to 15 seconds
    );

    const innerText = await element.getText(); // Fetch the visible text of the element
    const title = innerText.split("."); // Splitting the text

    const elements = await driver.wait(
      until.elementsLocated(By.className("elfjS")),
      20000 // Wait for up to 20 seconds
    );

    // Extract the content from child nodes of the first element
    const content = [];
    const firstElement = elements[0]; // Assuming there is at least one element with this class
    const childNodes = await firstElement.findElements(By.xpath("./*")); // Get all child elements of the first element

    // Recursive function to process child nodes
    async function processChildNodes(node) {
      let processedContent = [];

      const tagName = await node.getTagName();
      switch (tagName.toLowerCase()) {
        case 'a': // Anchor tag
          const href = await node.getAttribute('href');
          processedContent.push({
            type: 'link',
            content: href
          });
          break;
        case 'img': // Image tag
          const src = await node.getAttribute('src');
          processedContent.push({
            type: 'image',
            content: src,
          });
          break;
        case 'p': // Paragraph tag
        case 'div': // Div containing text
        case 'li': // List item
          const textContent = await node.getText();
          if (textContent) {
            processedContent.push({
              type: 'text',
              content: textContent.split("\n")
            });
          }
          break;
        case 'ol': // Ordered list
        case 'ul': // Unordered list
          const listContent = await node.getText();
          processedContent.push({
            type: 'list',
            content: listContent.split("\n")
          });
          break;
        case 'pre': // Preformatted text
          const preText = await node.getText();
          const childElements = await node.findElements(By.xpath("./*")); // Fetch child nodes
          let preChildren = [];
          for (let child of childElements) {
            preChildren.push(...await processChildNodes(child)); // Recursively process child nodes
          }
          processedContent.push({
            type: 'preformatted',
            content: preText.split("\n"),
          });
          break;
        default: // Handle other elements
          const defaultTag = await node.getAttribute('outerHTML');
          processedContent.push({
            type: 'other',
          });
      }

      return processedContent;
    }

    for (let i = 0; i < childNodes.length; i++) {
      const processedNode = await processChildNodes(childNodes[i]);
      content.push(...processedNode);
    }

    // Prepare the problem content object
    let problemContent = {
      pcid: id,
      title: title[1], // Assuming the title is split and you want the second part
      content: JSON.stringify(content)
    };

    console.log(problemContent);
    // Post the problem content data immediately
    await createProblemData(problemContent); // Ensure data is sent to the server after it's prepared
  } catch (error) {
    console.error("Error extracting problem content:", error);
  } finally {
    // Quit the browser
    await driver.quit();
  }
}

