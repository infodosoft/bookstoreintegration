package book.store.api.v1;

import book.store.api.v1.config.ApiConfig;
import book.store.api.v1.utils.PayloadBuilder;
import book.store.api.v1.utils.jsonToObject;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.impl.client.DefaultHttpClient;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;


public class BaseApi {

    public final RestAssuredConfig restConfig = createRestAssuredConfig();
    private static final ThreadLocal<Map<String, Object>> sharedApiTestData = ThreadLocal.withInitial(HashMap::new);
    public final jsonToObject jsonToObject = new jsonToObject();
    protected PayloadBuilder payload;
    // Initialize RestAssured to disable SSL verification globally
    static {
        // Disable SSL verification globally for all requests
        io.restassured.RestAssured.useRelaxedHTTPSValidation();
        io.restassured.RestAssured.defaultParser = Parser.JSON;
        io.restassured.RestAssured.baseURI = ApiConfig.get("base.uri");
    }
    public Response the_api_is_running() {
         return sendGetRequest("/");
    }
    protected  Map<String, String> commonHeader(){
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return headers;
    }
    public static RestAssuredConfig createRestAssuredConfig() {
        return RestAssuredConfig.newConfig().httpClient(HttpClientConfig.httpClientConfig()
                .httpClientFactory(() -> {
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    return httpClient;
                })
        );
    }

    public synchronized static Map<String, Object> getSharedApiTestData() {
        return sharedApiTestData.get();
    }

    public synchronized static void setSharedApiTestData(Map<String, Object> data) {
        sharedApiTestData.set(data);
    }

    protected Response sendRequest(String method, String endpoint,Object payload){
        // Enable relaxed SSL validation here (in case you want to bypass SSL for a specific request)
        RequestSpecification requestSpec = given()
                .log().all()
                .config(restConfig)
                .headers(commonHeader())
                .relaxedHTTPSValidation();  // Disable SSL verification for this request

        if (payload != null) {
            requestSpec.body(payload.toString());
        }

        Response response = null;
        switch (method.toUpperCase()) {
            case "POST" ->    response = requestSpec.post(endpoint);
            case "PUT"  ->    response = requestSpec.put(endpoint);
            case "DELETE" -> response = requestSpec.delete(endpoint);
            case "GET" ->    response = requestSpec.get(endpoint);
        }

        response.prettyPrint();
        return response;
    }

    protected Response sendGetRequest(String endpoint) {
        return sendRequest("GET", endpoint, null);
    }
    protected Response sendPostRequest(String endpoint, Object payload) {
        return sendRequest("POST", endpoint, payload);
    }
    protected Response sendPutRequest(String endpoint, Object payload) {
        return sendRequest("PUT", endpoint,  payload);
    }
    protected Response sendDeleteRequest(String endpoint) {
        return sendRequest("DELETE", endpoint, null);
    }


}
