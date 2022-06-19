const { Console } = require("console");
const fs = require("fs");
const myLogger = new Console({
  stdout: fs.createWriteStream("createShoppingListLog.txt"),
  //stderr: fs.createWriteStream("errStdErr.txt"),
});
myLogger.log("**********Query*********");


const descIndex = process.argv.indexOf('--description');
let descValue;

if (descIndex > -1) {
  // Retrieve the value after --custom
  descValue = process.argv[descIndex + 1];
  myLogger.log("Description argument supplied");
}
else{
    myLogger.log("No Description Argument Supplied!");
    console.log("Please enter a description of The shopping list");
}


var moment = require('moment');
const { MongoClient } = require('mongodb');
const { fileURLToPath } = require('url');
// import { MongoClient } from 'mongodb'

// Connection URL
const url = 'mongodb://localhost:27017';
const client = new MongoClient(url, { useNewUrlParser: true } );

// Database Name
const dbName = 'shoppingList';

async function main() {
  // Use connect method to connect to the server
  await client.connect();
  myLogger.log('Connected successfully to server');
  const db = client.db(dbName);
  const lists = db.collection('lists');
  //const insertResult = await brands.insertMany(converter.fromFile(csvFilePath).then);
//console.log('Inserted documents =>', insertResult);
const doc = {
    description: descValue,
    date_created: Date.now(),
  }
  myLogger.log("Inserting Document into database...")
  const result = await lists.insertOne(doc);
  console.log(moment().format("ddd MMM D YYYY, HH:mm:ss ZZ zz"), "(Eastern Standard Time)");//ZZ z
  //console.log(`A document was inserted with the _id: ${result.insertedId}`);
  myLogger.log("Document inserted.");
  myLogger.log("**********END*********");
  // the following code examples can be pasted here...

  return 'done.';
}

main()
  .then(console.log)
  .catch(console.error)
  .finally(()=>client.close());