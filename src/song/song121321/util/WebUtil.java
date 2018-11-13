package song.song121321.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WebUtil {
    protected String GetStr(String getAddr) throws IOException {
        URL url = new URL(getAddr);
        URLConnection connection = url.openConnection();
        InputStream urlStream = connection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(urlStream, "utf-8"));
        String ss = null;
        String resultStr = "";
        while ((ss = bufferedReader.readLine()) != null) {
            resultStr += ss;
            resultStr += "\n";
        }
        return resultStr;
    }
}
