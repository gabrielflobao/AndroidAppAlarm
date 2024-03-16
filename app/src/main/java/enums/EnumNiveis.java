package enums;
/**
 * Author : Gabriel F F Lobão
 * Date : 16/03/2024
 */
public enum EnumNiveis {
    FACIL("Fácil"),MEDIO("Médio"),DIFICIL("Difícil"),NENHUM("Nenhum");

    private String descricao;

    EnumNiveis(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EnumNiveis getNivel(String name) {

        return ( FACIL.getDescricao() == name ? FACIL :
                MEDIO.getDescricao() == name ? MEDIO :
                DIFICIL.getDescricao() ==name ? DIFICIL:
                NENHUM.getDescricao() == name ? NENHUM : null
                );
    }
}
