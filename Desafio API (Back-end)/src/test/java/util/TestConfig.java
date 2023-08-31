package util;
import io.restassured.http.ContentType;

public class TestConfig{

    public static final String BASE_URL = "http://localhost";
    public static final int APP_PORT = 8080;
    public static final String basePath = "/api/v1";


    public static final ContentType APP_CONTENT_TYPE = ContentType.JSON;

}
