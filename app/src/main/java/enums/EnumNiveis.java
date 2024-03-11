package enums;

public enum EnumNiveis {
    FACIL("Fácil"),MEDIO("Médio"),DIFICIL("Difícil"),NENHUM("Nenhum");

    private String descricao;

    EnumNiveis(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
