package software.ulpgc.kata2.io;

import org.json.JSONArray;
import org.json.JSONObject;
import software.ulpgc.kata2.model.Monster;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class RemoteMonsterLoader implements MonsterLoader {
    @Override
    public List<Monster> loadAll() {
        try {
            return loadFrom(new URL("https://raw.githubusercontent.com/CrimsonNynja/monster-hunter-DB/refs/heads/master/monsters.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Monster> loadFrom(URL url) throws IOException {
        return loadFrom(url.openConnection());
    }

    private List<Monster> loadFrom(URLConnection urlConnection) throws IOException {
        try(InputStream inputStream = urlConnection.getInputStream()){
            return loadFrom(toJson(inputStream));
        }
    }

    private List<Monster> loadFrom(JSONObject json) {
        return loadFrom(json.getJSONArray("monsters"));
    }

    private List<Monster> loadFrom(JSONArray jsonMonsters) {
        List<Monster> monsters = new ArrayList<>();
        MonsterParser parser = new JsonMonsterParser();
        for (int i = 0; i < jsonMonsters.length(); i++) {
            monsters.add(parser.parse(jsonMonsters.getJSONObject(i).toString()));
        }
        return monsters;
    }

    private JSONObject toJson(InputStream inputStream) throws IOException {
        return new JSONObject(new String(inputStream.readAllBytes()));
    }
}
