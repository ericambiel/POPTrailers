package ericambiel.com.br.smartimdb.data.model;

import java.io.Serializable;

/*Implementa Serializable para que possa possa ser entregue a outra Activity que diferente da
 implementada, muito utilizado quando iremos passar dados(objeto) de uma activity para outra.*/
public class Filme implements Serializable {
    private final String tituloOriginalFilme;
    private final String caminhoPosterFilme;

    public Filme(String tituloOriginalFilme, String caminhoPosterFilme) {
        this.tituloOriginalFilme = tituloOriginalFilme;
        this.caminhoPosterFilme = caminhoPosterFilme;
    }

    public String getTituloOriginalFilme() {
        return tituloOriginalFilme;
    }

    public String getCaminhoPosterFilme() {
        return caminhoPosterFilme;
    }
}
