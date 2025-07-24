package book.store.api.v1.utils;

import com.github.javafaker.Faker;
import org.json.JSONObject;

import static book.store.api.v1.BaseApi.getSharedApiTestData;

public class PayloadBuilder {

    static Faker faker = new Faker();

    public static JSONObject newBookPayload() {
        JSONObject book = new JSONObject();
        String bookTitle = faker.book().title();
        getSharedApiTestData().put("books",bookTitle);
        book.put("id", 0);
        book.put("title", bookTitle);
        book.put("description", faker.lorem().sentence());
        book.put("pageCount", 0);
        book.put("excerpt", faker.lorem().paragraph());
        book.put("publishDate", "2025-05-08T00:00:00Z");
        return book;
    }
    public static JSONObject newBookPayload( String invalid) {
        JSONObject book = newBookPayload();
         if (Boolean.parseBoolean(invalid)){
             book.remove("title");
             getSharedApiTestData().put("books","null");
         }
        return book;
    }

    public static JSONObject updatedBookPayload( Integer id) {
        JSONObject book = newBookPayload();
        String bookTitle = "Updated ".concat(faker.book().title());
        getSharedApiTestData().put("books",bookTitle);
        book.put("id", id);
        book.put("title", bookTitle);
        return book;
    }

    public static JSONObject newAuthorPayload() {
        JSONObject author = new JSONObject();
        String authorFirstName = faker.name().firstName();
        getSharedApiTestData().put("authors",authorFirstName);
        author.put("id", 0);
        author.put("idBook", 0);
        author.put("firstName", authorFirstName);
        author.put("lastName", faker.name().lastName());
        return author;
    }
    public static JSONObject newAuthorPayload( String invalid) {
        JSONObject author = newAuthorPayload();
        if (Boolean.parseBoolean(invalid)){
            author.remove("firstName");
            getSharedApiTestData().put("authors","null");
        }
        return author;
    }
    public static JSONObject updatedAuthorPayload(Integer id) {
        JSONObject author = newAuthorPayload();
        String authorFirstName ="Updated " + faker.name().firstName();
        getSharedApiTestData().put("authors",authorFirstName);
        author.put("id", id);
        author.put("firstName", authorFirstName);
        return author;
    }
}