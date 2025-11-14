package software.ulpgc.kata2;

import software.ulpgc.kata2.io.RemoteMonsterLoader;
import software.ulpgc.kata2.model.Monster;

import java.util.List;

public class Main {
    static void main() {
        List<Monster> monsters = new RemoteMonsterLoader().loadAll();
        for (Monster monster : monsters) {
            System.out.println(monster);
        }
    }
}
