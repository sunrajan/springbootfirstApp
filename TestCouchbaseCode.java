package sprintBootdemoproject;

import com.couchbase.client.core.config.ConfigurationException;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.RawJsonDocument;
import com.ual.des.cbutils.Couchbaseutil;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

 @FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCouchbaseCode {
    public static final String CB_URL = "http://localhost";
    public static final String UCD_BUCKET = "UFD";
    public static final String UCD_BUCKET_PASS = "ufd123";
    private static Logger logger = Logger.getLogger(Couchbaseutil.class);

    @Rule
    public ExpectedException thrown = ExpectedException.none();




    @org.junit.Test
    public void createDocForCB() {
        String jsonStr = "{\"testFlt\": \"test value\"}";
        String key = "test";
        int expirationInSecs = 100;
        RawJsonDocument jsonDoc = Couchbaseutil.createDocForCB(key, jsonStr, expirationInSecs);
        assertThat(jsonDoc.id(), is(equalTo(key)));
        assertThat((String) jsonDoc.content().toString(), is(equalTo(jsonStr)));
        assertThat(expirationInSecs, is(equalTo(jsonDoc.expiry())));

    }

    @org.junit.Test
    public void processDocs() {
        Couchbaseutil.init(CB_URL);
        Couchbaseutil.initBucket(UCD_BUCKET,UCD_BUCKET_PASS);

        String jsonStr = "{\"testsprintFlt\": \"test springbootvalue\"}";
        String key = "testspringboot";
        int expirationInSecs = 100;
        Bucket bucket = Couchbaseutil.getBucket(UCD_BUCKET);

        Couchbaseutil.deleteDoc(bucket, key);

        RawJsonDocument doc = Couchbaseutil.getDoc(bucket, key, true);
        assertThat(doc, is(equalTo(null)));
        logger.info("Tested : delete doc");
        RawJsonDocument docIn = Couchbaseutil.createDocForCB(key, jsonStr, expirationInSecs);
        Couchbaseutil.upsertDoc(bucket, docIn, null, true);
        RawJsonDocument docOut = Couchbaseutil.getDoc(bucket, key);
        assertThat(docOut, is(not(equalTo(null))));
        logger.info("Tested : upsert doc call inserted something");
        assertThat(docIn.content().toString(), is(equalTo(docOut.content().toString())));
        logger.info("Tested : upserted doc is correct");
        Couchbaseutil.disconnect();
    }

    @After
    public void disconnect(){
        Couchbaseutil.disconnect();
    }


}
