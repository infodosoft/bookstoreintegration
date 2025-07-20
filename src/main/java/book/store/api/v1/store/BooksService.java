package book.store.api.v1.store;

import book.store.api.v1.config.ApiConfig;
import book.store.api.v1.utils.PayloadBuilder;
import io.restassured.response.Response;
import lombok.Getter;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class BooksService extends AuthorsService {
    @Getter
    private static final String booksUrl = ApiConfig.get("endpoint.books");
    @Getter
    private static String bookUrl = ApiConfig.get("endpoint.book");
    public Response userRetrievesAListOfInStore(String type) {
        String endpoint = null;
        switch (type.toLowerCase()){
            case "books" -> endpoint = booksUrl;
            case "authors" -> endpoint = getAuthorsUrl();
        }
        return sendGetRequest(endpoint, null);
    }
    public Response userRetrievesADetailsInStore(String type, List<Map<String, Object>> responseBodyList) {
         String currentId = "";
         Response response;
         String endpoint = null;

        switch (type.toLowerCase()){
            case "books" ->{
                if (responseBodyList == null) {
                    response = sendGetRequest(booksUrl, null);
                    responseBodyList = jsonToObject.listObject(response.body().asString());
                    currentId = String.valueOf(((Number) responseBodyList.getFirst().get("id")).intValue());
                } else {
                    currentId = String.valueOf(((Number)responseBodyList.getFirst().get("id")).intValue());
                }
                endpoint = bookUrl;
            }
            case "authors" ->{
                if (responseBodyList == null) {
                    response = sendGetRequest(getAuthorsUrl(), null);
                    responseBodyList = jsonToObject.listObject(response.body().asString());
                    currentId = String.valueOf(((Number) responseBodyList.getFirst().get("id")).intValue());
                } else {
                    currentId = String.valueOf(((Number)responseBodyList.getFirst().get("id")).intValue());
                }
                endpoint = getAuthorUrl();
            }
        }
        return sendGetRequest(endpoint.replace("{id}", currentId),null );
    }
    public Response userAddsANewInTheStore(String type) {
        switch (type.toLowerCase()){
            case "books" -> {
                return sendPostRequest(booksUrl, PayloadBuilder.newBookPayload().toString());
            }
            case "authors" -> {
                return sendPostRequest(getAuthorsUrl(), PayloadBuilder.newAuthorPayload().toString());
            }
        }
        return null;
    }
    public Response userUpdateAnExistingInTheStore(String type, List<Map<String, Object>> responseBodyList) {
        String currentId = "";
        Response response;
        String endpoint = null;
        JSONObject payload = null;
        switch (type.toLowerCase()){
            case "books" -> {
                if (responseBodyList == null) {
                    response = sendGetRequest(booksUrl, null);
                    responseBodyList = jsonToObject.listObject(response.body().asString());
                    currentId = String.valueOf(((Number) responseBodyList.getFirst().get("id")).intValue());
                } else {
                    currentId = String.valueOf(((Number)responseBodyList.getFirst().get("id")).intValue());
                }
                endpoint = bookUrl;
                payload = PayloadBuilder.updatedBookPayload(Integer.valueOf(currentId));
            }
            case "authors" -> {
                if (responseBodyList == null) {
                    response = sendGetRequest(getAuthorsUrl(), null);
                    responseBodyList = jsonToObject.listObject(response.body().asString());
                    currentId = String.valueOf(((Number) responseBodyList.getFirst().get("id")).intValue());
                } else {
                    currentId = String.valueOf(((Number)responseBodyList.getFirst().get("id")).intValue());
                }
                endpoint = getAuthorUrl();
                payload = PayloadBuilder.updatedAuthorPayload(Integer.valueOf(currentId));
            }

        }
        assert endpoint != null;
        return sendPutRequest(endpoint.replace("{id}", currentId),payload.toString());
    }

    public Response userDeletesAnExistingInTheStore(String type, List<Map<String, Object>> responseBodyList) {
        String currentId = "";
        Response response;
        String endpoint = null;
        switch (type.toLowerCase()){
            case "books" -> {
                if (responseBodyList == null) {
                    response = sendGetRequest(booksUrl, null);
                    responseBodyList = jsonToObject.listObject(response.body().asString());
                    currentId = String.valueOf(((Number) responseBodyList.getFirst().get("id")).intValue());
                } else {
                    currentId = String.valueOf(((Number)responseBodyList.getFirst().get("id")).intValue());
                }
                endpoint = bookUrl;
            }
            case "authors" -> {
                if (responseBodyList == null) {
                    response = sendGetRequest(getAuthorsUrl(), null);
                    responseBodyList = jsonToObject.listObject(response.body().asString());
                    currentId = String.valueOf(((Number) responseBodyList.getFirst().get("id")).intValue());
                } else {
                    currentId = String.valueOf(((Number)responseBodyList.getFirst().get("id")).intValue());
                }
                endpoint = getAuthorUrl();
            }
        }
        assert endpoint != null;
        return sendDeleteRequest(endpoint.replace("{id}", currentId),null);
    }
    public Response userAddsANewInTheStoreUsingInvalidPayload(String type, String payload) {
        switch (type.toLowerCase()){
            case "books" -> {
                return sendPostRequest(booksUrl, PayloadBuilder.newBookPayload(payload).toString());
            }
            case "authors" -> {
                return sendPostRequest(getAuthorsUrl(), PayloadBuilder.newAuthorPayload(payload).toString());
            }
        }
        return null;
    }
    public Response userRetrievesADetailsInStoreUsingInvalidId(String type, String id) {
        switch (type.toLowerCase()){
            case "books" -> {
                return sendGetRequest(bookUrl.replace("{id}", id),null );
            }
            case "authors" -> {
                return sendGetRequest(getAuthorUrl().replace("{id}", id),null );
            }
        }
        return null;
    }
    public Response userDeletesAnExistingInTheStoreUsingInvalidId(String type, String id) {
        switch (type.toLowerCase()){
            case "books" -> {
                return sendDeleteRequest(bookUrl.replace("{id}", id),null);
            }
            case "authors" -> {
                return sendDeleteRequest(getAuthorUrl().replace("{id}", id),null);
            }
        }

        return  null;
    }
}
