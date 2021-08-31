package jdbc.db;

import jdbc.classes.Comprador;
import jdbc.conn.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompradorDB {
    public static void save(Comprador comprador){
        String sql = "INSERT INTO `agencia`.`comprador` (`id`,`cpf`, `nome`) VALUES (?, ?, ?)";
        Connection conn = ConexaoFactory.getConexao();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,comprador.getId());
            ps.setString(2,comprador.getCpf());
            ps.setString(3,comprador.getNome());
            ps.executeUpdate();
            ConexaoFactory.close(conn,ps);
            System.out.println("Registro inserido com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void delete(Comprador comprador){
        if (comprador == null || comprador.getId() == null) {
            System.out.println("Nao foi possivel excluir o registro");
            return;
        }
        String sql = "DELETE FROM `agencia`.`comprador` WHERE (`id` = ?)";
        Connection conn = ConexaoFactory.getConexao();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,comprador.getId());
            ps.executeUpdate();
            ConexaoFactory.close(conn,ps);
            System.out.println("Registro excluido com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void update(Comprador comprador){
        if (comprador == null || comprador.getId() == null) {
            System.out.println("Nao foi possivel excluir o registro");
            return;
        }
        String sql = "UPDATE `agencia`.`comprador` SET `cpf` = ?, `nome` = ? WHERE (`id` = ? )";
        Connection conn = ConexaoFactory.getConexao();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,comprador.getCpf());
            ps.setString(2,comprador.getNome());
            ps.setInt(3,comprador.getId());
            ps.executeUpdate();
            ConexaoFactory.close(conn,ps);
            System.out.println("Registro atualizado com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<Comprador> selectAll(){
        String sql = "select id, cpf, nome from agencia.comprador";
        Connection conn = ConexaoFactory.getConexao();
        List<Comprador> compradorList = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                compradorList.add(new Comprador(rs.getInt("id"),rs.getString("cpf"),rs.getString("nome")));
            }
            ConexaoFactory.close(conn,ps,rs);
            return compradorList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<Comprador> searchByName(String nome){
        String sql = "select id, cpf, nome from agencia.comprador where nome like ? ";
        Connection conn = ConexaoFactory.getConexao();
        List<Comprador> compradorList = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,"%"+nome+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                compradorList.add(new Comprador(rs.getInt("id"),rs.getString("cpf"),rs.getString("nome")));
            }
            ConexaoFactory.close(conn,ps,rs);
            return compradorList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
