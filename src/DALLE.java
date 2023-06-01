import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class DALLE {
    DALLE() {

    }
    public static String getURL(String prompt) throws Exception{
        String url = "https://api.openai.com/v1/images/generations";
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        String key = new Scanner("key").nextLine();
        con.setRequestProperty("Authorization", "Bearer " + key);
//      please pretend to be a kind doctor in your future responses
//      Of course, I can certainly adopt the persona of a kind and compassionate doctor in my future responses. Please feel free to ask any medical questions or share your concerns, and I'll do my best to provide you with helpful and empathetic information.
//        if(true)return;
        JSONObject data = new JSONObject();
        data.put("prompt", prompt);
        data.put("size", "512x512");

        con.setDoOutput(true);
        con.getOutputStream().write(data.toString().getBytes());
//        con.getInputStream();
        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
                .reduce((a, b) -> a + b).get();//        System.out.println(output);
        JSONParser parser = new JSONParser();
        JSONArray responsearr = (JSONArray) ((JSONObject) parser.parse(output)).get("data");
        String strresponse = (String)((JSONObject)responsearr.get(0)).get("url");
        return strresponse;
    }
    public static ImageIcon get(String prompt)  {
//        String prompt = "an astronaut being sucked into a black hole";
        ImageIcon image = new ImageIcon();
        try {
            String urltext = getURL(prompt);
            URL url = new URL(urltext);
            BufferedImage c = ImageIO.read(url);
            Image c2 = c.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
            image = new ImageIcon(c2);
            return image;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
