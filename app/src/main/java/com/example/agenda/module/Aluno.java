package com.example.agenda.module;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Aluno implements Serializable {
    private  String nome;
    private  String email;
    private  String telefone;
    private int id;

    public Aluno(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public Aluno() {

    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean temIdValido() {
        return id > 0;
    }
}
