

// Checks for --custom and if it has a value
const { Console } = require("console");
const fs = require("fs");
const myLogger = new Console({
  stdout: fs.createWriteStream("findByUPCLog.txt"),
  //stderr: fs.createWriteStream("errStdErr.txt"),
});
myLogger.log("**********Query*********");
const upcIndex = process.argv.indexOf('--upc');
let upcValue;

if (upcIndex > -1) {
  // Retrieve the value after --custom
 
  upcValue = process.argv[upcIndex + 1];
  myLogger.log("UPC argument supplied");
}

const { MongoClient } = require('mongodb');
const { fileURLToPath } = require('url');


// saving to normalStdout.txt file
//myLogger.log("Hello 😃. This will be saved in normalStdout.txt file");

// saving to errStdErr.txt file
//myLogger.error("Its an error ❌. This will be saved in errStdErr.txt file");
// import { MongoClient } from 'mongodb'

// Connection URL
const url = 'mongodb://localhost:27017';
const client = new MongoClient(url, {useNewUrlParser: true} );

// Database Name
const dbName = 'shoppingList';

async function main() {
  // Use connect method to connect to the server
  await client.connect();
  myLogger.log('Connected successfully to server');
  const db = client.db(dbName);
    const UPC = db.collection('UPC');
    myLogger.log("Querying database....");
    const Doc = await UPC.findOne({upc12: upcValue});
    if(Doc === null){
      console.log("No match for UPC ", upcValue);
      myLogger.log("No match for UPC ", upcValue);
    }
    else{
      console.log("\n", Doc.name);
      myLogger.log("UPC query was successful")
      myLogger.log("**********END*********");
    }

  // the following code examples can be pasted here...

  return '';
}

main()
  .then(console.log)
  .catch(console.error)
  .finally(()=>client.close());

