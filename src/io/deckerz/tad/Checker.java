package io.deckerz.tad;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class Checker implements Runnable {
    String channel;

    Checker(String channel) {
        this.channel = channel;
    }

    Boolean isOnline() throws IOException, JSONException {
        InputStream is = new URL("https://api.twitch.tv/kraken/streams/" + channel).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readJSON(rd);
            JSONObject json = new JSONObject(jsonText);
            if (json.has("stream")) {
                if (!json.isNull("stream")) {
                    System.out.println('Y' + channel);
                    return true;
                } else {
                    System.out.println('N' + channel);
                    return false;
                }
            } else {
                System.out.println('N' + channel);
                return false;
            }
        } finally {
            is.close();
        }
    }

    String readJSON(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    @Override
    public synchronized void run() {
        Boolean online = false;
        while (true) {
            try {
                Boolean status = isOnline();
                if (status) {
                    if (!online) {
                        Notify notify = new Notify(channel + " is online!");
                        notify.push();
                        online = true;
                    } else {
                        if (!status) {
                            online = false;
                        }
                    }
                    wait(1 * 60 * 1000);
                } else {
                    wait(1 * 60 * 1000);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
