package software.ulpgc.kata2.io;

import software.ulpgc.kata2.model.Monster;

import java.util.List;

public interface MonsterLoader {
    List<Monster> loadAll();
}
