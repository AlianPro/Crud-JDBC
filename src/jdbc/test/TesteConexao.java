package jdbc.test;

import jdbc.classes.Comprador;
import jdbc.db.CompradorDB;

import java.util.List;

public class TesteConexao {
    public static void main(String[] args) {
        //inserir();
        //atualizar();
        //deletar();
    List<Comprador> listaComprador = selecionarTudo();
    System.out.println(listaComprador);
    List<Comprador> listaComprador2 = buscarPorNome("ao");
    System.out.println(listaComprador2);
    }
    public static void inserir(){
        Comprador comprador = new Comprador(1,"111.111.111-22","Priscila");
        CompradorDB.save(comprador);
    }
    public static void deletar(){
        Comprador comprador = new Comprador();
        comprador.setId(1);
        CompradorDB.delete(comprador);
    }
    public static void atualizar(){
        Comprador comprador = new Comprador(1,"000.000.000-16","Maria");
        CompradorDB.update(comprador);
    }
    public static List<Comprador> selecionarTudo(){
        return CompradorDB.selectAll();
    }
    public static List<Comprador> buscarPorNome(String nome){
        return CompradorDB.searchByName(nome);
    }
}
