package ericambiel.com.br.smartimdb.data.model;

public class Filme {
    private final String tituloOriginal;
    private final String caminhoPoster;

    public Filme(String tituloOriginal, String caminhoPoster) {
        this.tituloOriginal = tituloOriginal;
        this.caminhoPoster = caminhoPoster;
    }

    public String getTituloOriginal() {
        return tituloOriginal;
    }

    public String getCaminhoPoster() {
        return caminhoPoster;
    }
}
