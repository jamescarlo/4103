package rem;

import griffon.core.artifact.GriffonService;
import griffon.core.resources.ResourceHandler;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonService;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@javax.inject.Singleton
@ArtifactProviderFor(GriffonService.class)
public class Storage extends AbstractGriffonService {

    private static String filepath = System.getProperty("user.home") +"\\AppData\\data4103.json";

	  public static void saveItem(String key, String value) {
   		  JSONObject obj = new JSONObject();
   		  obj.put(key, value);

   		  try {
            FileWriter file = new FileWriter(filepath);
            file.write(obj.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
   	}

    public static String getItem(String key) {
        String value = "";
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(filepath));
            JSONObject jsonObject = (JSONObject) obj;
            value = (String) jsonObject.get(key);
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
        } catch (ParseException e) {
            //e.printStackTrace();
        }
        return value;
    }   	
}