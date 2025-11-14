package software.ulpgc.kata2;

import org.json.JSONArray;
import org.json.JSONObject;
import software.ulpgc.kata2.model.Monster;

import java.util.ArrayList;
import java.util.List;

public class JsonMonsterParser implements MonsterParser {
    @Override
    public Monster parse(String str) {
        return toMosnter(new JSONObject(str));
    }

    private Monster toMosnter(JSONObject json) {
        return new Monster(
                json.getString("name"),
                toType(json.getString("type")),
                json.getBoolean("isLarge"),
                toGames(json.getJSONArray("games"))
        );
    }

    private List<String> toGames(JSONArray jsonGames) {
        List<String> games = new ArrayList<>();
        for (int i = 0; i <jsonGames.length(); i++) {
            games.add(jsonGames.getJSONObject(i).getString("game"));
        }
        return games;
    }

    private Monster.Type toType(String type) {
        return Monster.Type.valueOf(type.replace(" ", "_").replace("???", "unknown"));
    }
}
