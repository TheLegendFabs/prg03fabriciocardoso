/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.validar;

/**
 *
 * @author Alfa
 */
public class ValidadorUsuario {
    
    // Método para testar se há campos vazios
    public boolean camposPreenchidos(String nome, String cpf, String login, String senha) {
        if (nome.isEmpty() || cpf.isEmpty() || login.isEmpty() || senha.isEmpty()) {
            return false;
        }
        return true;
    }

    // Método para testar se a senha tem o tamanho e os requisitos corretos
    public boolean senhaForte(String senha) {
        // Exemplo: se a senha for nula ou menor que 6 caracteres, é inválida
        if (senha == null || senha.length() < 6) {
            return false;
        }
        return true;
    }

    // Método para verificar se as duas senhas inseridas coincidem
    public boolean senhasCoincidem(String senha1, String senha2) {
        if (senha1 != null && senha1.equals(senha2)) {
            return true;
        }
        return false;
    }
    
}
