package br.com.ifba.usuario.entity;

public class Tecnico extends Usuario {

    @Override
    public String obterPapel() {
        return "Técnico de TI";
    }

    // Task 01: Segunda forma do mesmo contrato (Polimorfismo)
    @Override
    public boolean autenticar(String loginDigitado, String senhaDigitada) {
        // O técnico loga se a senha for a senha dele, OU se for a senha mestre de emergência da TI
        if (this.getLogin() != null && this.getLogin().equals(loginDigitado) && "senha_mestre_ti".equals(senhaDigitada)) {
            return true;
        }
        // Se não usou a senha mestre, chama a validação normal da classe mãe
        return super.autenticar(loginDigitado, senhaDigitada);
    }
}
