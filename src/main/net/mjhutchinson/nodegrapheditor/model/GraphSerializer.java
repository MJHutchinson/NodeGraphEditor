package net.mjhutchinson.nodegrapheditor.model;

import java.io.File;
import java.io.IOException;

public class GraphSerializer {
    private Graph graph;
    private File file;

    public GraphSerializer() {
        int i = 0;

        do {
            file = new File(String.format("./graph_%04d", ++i));
        } while (file.exists());

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GraphSerializer(Graph graph) {
        this();
        this.graph = graph;
    }

    public GraphSerializer(File file) {
        this.file = file;
    }

    public GraphSerializer(Graph graph, File file) {
        this(file);
        this.graph = graph;
    }
}
