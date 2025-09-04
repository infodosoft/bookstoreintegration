package mainApp;

import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;

import java.nio.charset.StandardCharsets;

public class DigestGenerator{

    public static void main(String []args){}

    public String digest(String originalString) {
        return BaseEncoding.base64().encode(Hashing.sha256().hashString(originalString, StandardCharsets.UTF_8).asBytes());
    }
}