const csvFilePath='..\\data\\Grocery_Brands_Database.csv'
const csvFilePath2='..\\data\\Grocery_UPC_Database.csv'
const csv=require('csvtojson')
const converter=csv({
    delimiter: "|",
    noheader:true,
    headers: ["grb_id", "Brand", "No_of_items", "Manufacturer", "Address", "Website"]
})
const converter2=csv({
    delimiter: "|",
    noheader:true,
    headers: ["grp_id", "upc14", "upc12", "brand", "name"]
})


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
  console.log('Connected successfully to server');
  const db = client.db(dbName);
  const brands = db.collection('brands');
  //const insertResult = await brands.insertMany(converter.fromFile(csvFilePath).then);
//console.log('Inserted documents =>', insertResult);
 await converter
.fromFile(csvFilePath)
.then(async (jsonArray)=>{
    const result = await brands.insertMany(jsonArray);
})

const UPC = db.collection('UPC');
  //const insertResult = await brands.insertMany(converter.fromFile(csvFilePath).then);
//console.log('Inserted documents =>', insertResult);
 await converter2
.fromFile(csvFilePath2)
.then(async (jsonArray)=>{
    const result = await UPC.insertMany(jsonArray);
})
    //console.log(`${result.insertedCount} documents were inserted`);
  //const collection2 = db.collection('UPC')

  // the following code examples can be pasted here...

  return 'done.';
}

main()
  .then(console.log)
  .catch(console.error)
  .finally(()=>client.close());

// const mongo = require('mongodb').MongoClient,
//  url = 'mongodb:localhost:27017';
//  MongoClient.connect("mongodb://localhost:27017/YourDB", { useNewUrlParser: true })
//  let minimal_client, 
//  main;

//  main = function () {
//  if (true) {
//     minimal_client () ;
//  }

//  };
//  main () ;

//  minimal_client = function ( ) {
//  // 2nd parameter: @see https://stackoverflow.com/a/51182415
//  mongo.connect( url, {useNewUrlParser : true}, (err, client)=>{
//  if(err){
//  console.error(err);
//  return;
//  }

//  console.log('Now have a mongo-client handle');
//      const db = client.db('test');
//      const collection = db . collection('inventory');
    
//      console.log ('searching inventory db for all documents');
//      collection.find().toArray((err,items)=>{
//      console.log(items);
//      console.log('Now closing connection to mongo and exiting')
//     ;
//      client.close();
//  }) ;
    
//      }) ;
// } 
// Async / await usage
//const jsonArray= csv().fromFile(csvFilePath);
//console.log(jsonArray[1]);