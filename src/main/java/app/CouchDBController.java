 package app;

 import org.springframework.web.bind.annotation.RequestMapping;
 // import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.RestController;
 // import com.couchbase.client.java.*;
 import com.couchbase.client.java.document.*;
 import com.couchbase.client.java.document.json.*;
 // import com.fasterxml.jackson.core.*;
 // import com.fasterxml.jackson.annotation.*;
 // import com.fasterxml.jackson.databind.*;
 import app.CouchDB;
 import app.User;

@RestController
  public class CouchDBController {

    @RequestMapping("/couchbase")
    public User couching() {
      CouchDB db = new CouchDB();
      JsonObject userJsonObject = JsonObject.create();

      JsonObject john = JsonObject.create()
      .put("name", "john")
      .put("email", "john@couchbase.com")
      .put("interests", JsonArray.from("some", "thing"));

      JsonDocument userJsonDocument = db.setUser(john);
      JsonDocument.from(userJsonDocument, userJsonObject);
      System.out.println(userJsonDocument);

      return new User("uid'im", "email'im");
    }
  }
    /*public User couching(@RequestParam(value="id", defaultValue="1") String id) {
      CouchDB db = new CouchDB();
      ObjectMapper objectMapper = new ObjectMapper();
      JsonDocument userJsonDocument = db.getUser(id);
      String stringifiedJsonDocument = userJsonDocument.toString();
      User user = objectMapper.readValue(stringifiedJsonDocument, User.class);

      return user;
    }
  }*/

      /*public String couching(@RequestParam(value="id", defaultValue="1") String id) {
        JsonObject userJsonObject = JsonObject.create();

        CouchDB db = new CouchDB();
        JsonDocument userDocument = db.getUser(id);

        JsonDocument.from(userDocument, userJsonObject);

        return userJsonObject.getString("name");
      }*/
