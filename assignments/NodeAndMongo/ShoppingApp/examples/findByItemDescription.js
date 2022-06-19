// Checks for --custom and if it has a value
const { Console } = require("console");
const fs = require("fs");
const myLogger = new Console({
  stdout: fs.createWriteStream("findByItemDescription.txt"),
  //stderr: fs.createWriteStream("errStdErr.txt"),
});
myLogger.log("**********Query*********");
const descIndex = process.argv.indexOf('--description');
let descValue;

if (descIndex > -1) {
  // Retrieve the value after --custom
  descValue = process.argv[descIndex + 1];
  myLogger.log("UPC argument supplied");
}

const { MongoClient } = require('mongodb');
const { fileURLToPath } = require('url');
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
const cursor = UPC.find({ name: { "$regex": descValue} });

if ((await cursor.count()) === 0) {
    console.log("No items match ", descValue);
    myLogger.log("No match for UPC ", descValue);
  }
  else{
    myLogger.log("UPC query was successful");
    myLogger.log("Printing Results....");
    console.log("findByItemDescription: result contains ", await cursor.count());
    //for(let i = 0; i < Docs.length; i++) {
        await cursor.forEach(doc => console.log("{ itemDescription: ", doc.name, ", upc: ", doc.upc12, " }"));
        myLogger.log("**********END*********");
        //console.log("{ itemDescription: ", Docs.name);
    //console.log(", upc: ", Docs[i].upc);
}
  // replace console.dir with your callback to access individual elements
  //
//console.log(result);


  // the following code examples can be pasted here...

  return '';
}

main()
  .then(console.log)
  .catch(console.error)
  .finally(()=>client.close());

