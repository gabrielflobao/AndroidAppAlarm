package model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.time.LocalTime;
/**
 * Author : Gabriel F F Lobão
 * Date : 16/03/2024
 */
@Entity
public class Alarme implements Serializable {
    @PrimaryKey
    private Long id;

    private String nivel;
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

    public Alarme(String nivel, String nome, Boolean diasUteis, Boolean ativo, LocalTime hora) {
        this.nivel = nivel;
        this.nome = nome;
        this.diasUteis = diasUteis;
        this.ativo = ativo;
        this.hora = hora;
    }

    public Alarme(String niveis, String nome, Boolean diasUteis, Boolean ativo) {
        this.nivel = niveis;
        this.nome = nome;
        this.diasUteis = diasUteis;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
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
