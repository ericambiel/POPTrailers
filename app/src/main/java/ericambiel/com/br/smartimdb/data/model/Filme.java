package ericambiel.com.br.smartimdb.data.model;

import java.io.Serializable;

/*Implementa Serializable para que possa possa ser entregue a outra Activity que diferente da
 implementada, muito utilizado quando iremos passar dados(objeto) de uma activity para outra.*/
public class Filme implements Serializable {
    private final String tituloOriginal;
    private final String caminhoPoster;
    //private final String trailer;

    public Filme(String tituloOriginal, String caminhoPoster) {
        this.tituloOriginal = tituloOriginal;
        this.caminhoPoster = caminhoPoster;
    //    this.trailer = trailer;
    }

    public String getTituloOriginal() {
        return tituloOriginal;
    }

    public String getCaminhoPoster() {
        return caminhoPoster;
    }
}
