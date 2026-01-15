package software.ulpgc.kata2.App;

import software.ulpgc.kata2.io.RemoteMonsterLoader;

public class Main {
    static void main() {
        new RemoteMonsterLoader().loadAll().forEach(System.out::println);
    }
}
