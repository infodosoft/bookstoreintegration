package book.store.api.v1.store;

import book.store.api.v1.BaseApi;
import book.store.api.v1.config.ApiConfig;
import lombok.Getter;

public class AuthorsService extends BaseApi {


    @Getter
    private static final String authorsUrl = ApiConfig.get("endpoint.authors");

    @Getter
    private static final String authorUrl = ApiConfig.get("endpoint.author");


}
