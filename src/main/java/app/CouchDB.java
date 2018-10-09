 package app;

 import com.couchbase.client.java.*;
 import com.couchbase.client.java.document.*;
 import com.couchbase.client.java.document.json.*;
 import com.couchbase.client.java.query.*;

 public class CouchDB {

     public static void main(String... args) throws Exception {

         // Initialize the Connection
         Cluster cluster = CouchbaseCluster.create("http://localhost:8091");
         cluster.authenticate("USERNAME", "PW");
         Bucket bucket = cluster.openBucket("users");

         // Create a JSON Document
         JsonObject jack = JsonObject.create()
             .put("name", "jack")
             .put("email", "jack@couchbase.com")
             .put("interests", JsonArray.from("x", "y"));

         // Store the Document
         bucket.upsert(JsonDocument.create("3", jack));

         // Load the Document and print it
         // Prints Content and Metadata of the stored Document
         System.out.println(bucket.get("1"));

         // Create a N1QL Primary Index (but ignore if it exists)
         bucket.bucketManager().createN1qlPrimaryIndex(true, false);

         // Perform a N1QL Query
         N1qlQueryResult result = bucket.query(
             N1qlQuery.parameterized("SELECT name FROM `users` WHERE $1 IN interests",
             JsonArray.from("Zart"))
         );

         // Print each found Row
         for (N1qlQueryRow row : result) {
             // Prints {"name":"Arthur"}
             System.out.println(row);
         }

     }

     public JsonDocument getUser(String s) {
       Cluster cluster = CouchbaseCluster.create("http://localhost:8091");
       cluster.authenticate("USERNAME", "PW");
       Bucket bucket = cluster.openBucket("users");

       return bucket.get(s);
     }

     public JsonDocument setUser(JsonObject jo) {
       Cluster cluster = CouchbaseCluster.create("http://localhost:8091");
       cluster.authenticate("USERNAME", "PW");
       Bucket bucket = cluster.openBucket("users");

       bucket.upsert(JsonDocument.create("4", jo));
       return bucket.get("4");
     }
 }