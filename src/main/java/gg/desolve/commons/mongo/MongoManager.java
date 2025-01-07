package gg.desolve.commons.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import gg.desolve.commons.Commons;
import lombok.Getter;
import org.bson.UuidRepresentation;

@Getter
public class MongoManager {

    private MongoClient mongoClient;

    public MongoManager(String url) {
        try {
            MongoClientSettings mongoSettings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(url))
                    .uuidRepresentation(UuidRepresentation.STANDARD)
                    .build();

            mongoClient = MongoClients.create(mongoSettings);

            String timing = String.valueOf(System.currentTimeMillis() - Commons.getInstance().getInstanceManager().getInstance().getBooting());
            Commons.getInstance().getLogger().info("Merged Mongo @ " + timing + "ms.");
        } catch (Exception e) {
            Commons.getInstance().getLogger().warning("There was a problem connecting to MongoDB.");
            e.printStackTrace();
        }
    }

}
