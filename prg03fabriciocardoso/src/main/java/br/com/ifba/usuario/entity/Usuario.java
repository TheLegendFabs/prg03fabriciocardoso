package br.com.ifba.usuario.entity;

import br.com.ifba.usuario.inteface.Autenticavel;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Autenticavel {

    // --- Atributos Originais ---
    private String nome;
    private String cpf;
    private String genero;
    private String dataNascimento;
    private String telefone;
    private String email;
    private String login;
    private String senha;

    // --- Novos Atributos (Atividade 11) ---
    // Task 04: Atributo usando o Enum (inicia inativo)
    private Status status = Status.INATIVO;

    // Task 02: Objeto como atributo[cite: 8]
    private Perfil perfilAtivo;

    // Task 03: Relacionamento 1..N usando List[cite: 8]
    private List<Perfil> perfis = new ArrayList<>();
    
    // Task 04: Construtor 1 (Vazio) - Permite criar o objeto e preencher depois
    public Usuario() {
    }

    // Task 04: Construtor 2 (Com parâmetros) - Força a criação já com os dados obrigatórios
    public Usuario(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    // --- Getters e Setters Originais ---
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean autenticar(String loginDigitado, String senhaDigitada) {
        // Retorna true se o login e a senha coincidirem com this.login e this.senha
        if (this.login != null && this.senha != null
                && this.login.equals(loginDigitado) && this.senha.equals(senhaDigitada)) {
            return true;
        }
        return false;
    }

    // --- Novos Métodos (Atividade 11) ---
    // Task 03: Método para adicionar um item sem expor a lista[cite: 8]
    public void adicionarPerfil(Perfil perfil) {
        this.perfis.add(perfil);
    }

    // Método auxiliar para o JUnit testar o tamanho da lista[cite: 8]
    public int getQuantidadePerfis() {
        return this.perfis.size();
    }

    // Task 02 e 04: Getters e Setters obrigatórios do objeto e enum[cite: 8]
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Perfil getPerfilAtivo() {
        return perfilAtivo;
    }

    public void setPerfilAtivo(Perfil perfilAtivo) {
        this.perfilAtivo = perfilAtivo;
    }
    
    // Método que será sobrescrito pelas classes filhas
    public String obterPapel() {
        return "Usuário Padrão";
    }
    
    // Justificativa: O campo escolhido para identificar a igualdade é o login, pois num sistema de chamados é a credencial única e não podem existir dois utilizadores com o mesmo acesso.[cite: 24]
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Usuario usuario = (Usuario) o;
        return login != null ? login.equals(usuario.login) : usuario.login == null;
    }

    @Override
    public int hashCode() {
        return login != null ? login.hashCode() : 0;
    }
}
