package com.br.Modelo;

import com.google.gson.Gson;

import java.util.List;

public class Nota {

    private int codigo;
    private String titulo;
    private String conteudo;
    private int dono;
    private List<Usuario> colaboradores;
    private String endereco;

    public Nota(){}

    public Nota(int codigo, String titulo, String conteudo, int dono){
        this.codigo = codigo;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.dono = dono;
    }

    public Nota(int codigo, String titulo, String conteudo){
        this.codigo = codigo;
        this.titulo = titulo;
        this.conteudo = conteudo;
    }

    public Nota(String titulo, String conteudo, String endereco) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.endereco = endereco;
    }

    public Nota(String titulo, String conteudo, int dono) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.dono = dono;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public int getDono() {
        return dono;
    }

    public void setDono(int dono) {
        this.dono = dono;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String toJson(){
        Gson json = new Gson();
        return json.toJson(this);
    }

    public Nota fromJosn(String notaJson){
        Gson json = new Gson();
        return json.fromJson(notaJson, Nota.class);
    }
}
