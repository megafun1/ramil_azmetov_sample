package prre.ttrr.com.azmetov_ramil.modules;

import dagger.ObjectGraph;

public class Injector {
    public static ObjectGraph graph;

    public static void init(Object... modules) {
        graph = ObjectGraph.create(modules);
    }

    public static void inject(Object target) {
        graph.inject(target);
    }
}
