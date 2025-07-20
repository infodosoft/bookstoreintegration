package book.store.api.v1.store;

import book.store.api.v1.BaseApi;
import book.store.api.v1.config.ApiConfig;

public class AuthorsService extends BaseApi {


    private static String authorsUrl = ApiConfig.get("endpoint.authors");

    private static String authorUrl = ApiConfig.get("endpoint.author");

    public static String getAuthorUrl() {
        return authorUrl;
    }

    public static String getAuthorsUrl() {
        return authorsUrl;
    }


}
