import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ConfigReader {

    private String filePath;

    public ConfigReader(String filePath) {
        this.filePath = filePath;
    }

    public JSONArray readJSON() throws IOException, ParseException {
        FileReader reader = new FileReader(filePath);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(reader);

        // Akses array "sellers"
        JSONArray sellers = (JSONArray) json.get("sellers");
        reader.close();
        return sellers;
    }

    public String getEmail(int index) throws IOException, ParseException {
        JSONArray sellersArray = readJSON();
        JSONObject seller = (JSONObject) sellersArray.get(index);
        return (String) seller.get("email");
    }

    public String getPassword(int index) throws IOException, ParseException {
        JSONArray sellersArray = readJSON();
        JSONObject seller = (JSONObject) sellersArray.get(index);
        return (String) seller.get("password");
    }

}
