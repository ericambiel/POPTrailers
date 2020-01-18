package ericambiel.com.br.smartimdb.domain;

import java.io.Serializable;

public class Media implements Serializable {
    private final int id;
    private final String originalTitle;
    private final String posterPath;
    private final String relaseDate;

    public Media(int id, String originalTitle, String posterPath, String relaseDate) {
        this.id = id;
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
        this.relaseDate = relaseDate;
    }

    public int getId() {
        return id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getRelaseDate() {
        return relaseDate;
    }
}
