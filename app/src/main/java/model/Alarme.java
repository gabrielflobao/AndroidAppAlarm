package model;

import java.time.LocalTime;

public class Alarme {
    private Enum Niveis;
    private String nome;
    private Boolean diasUteis;

    private Boolean ativo;

    private LocalTime hora;

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Alarme() {

    }

    public Alarme(Enum niveis, String nome, Boolean diasUteis, Boolean ativo, LocalTime hora) {
        Niveis = niveis;
        this.nome = nome;
        this.diasUteis = diasUteis;
        this.ativo = ativo;
        this.hora = hora;
    }

    public Alarme(Enum niveis, String nome, Boolean diasUteis, Boolean ativo) {
        Niveis = niveis;
        this.nome = nome;
        this.diasUteis = diasUteis;
        this.ativo = ativo;
    }

    public Enum getNiveis() {
        return Niveis;
    }

    public void setNiveis(Enum niveis) {
        Niveis = niveis;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getDiasUteis() {
        return diasUteis;
    }

    public void setDiasUteis(Boolean diasUteis) {
        this.diasUteis = diasUteis;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
