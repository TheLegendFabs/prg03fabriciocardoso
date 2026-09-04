/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.validar;

/**
 *
 * @author fabri
 */
public class ValidarUsuario {
    
    public static boolean contemPalavraProibida(String texto){
        
        
        String[] palavras = {"admin", "teste", "root", "senha123"};
        
        for(String textoAux : palavras){
          if(textoAux.equals(texto)){
            return true;
           } 
        }
        
        return false;
    }    
}
