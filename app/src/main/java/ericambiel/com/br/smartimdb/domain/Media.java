package ericambiel.com.br.smartimdb.domain;

import java.io.Serializable;

public class Media implements Serializable {
    private final int id;
    private final String originalTitle;
    private final String posterPath;

    public Media(int id, String originalTitle, String posterPath) {
        this.id = id;
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public int getId() {
        return id;
    }
}
