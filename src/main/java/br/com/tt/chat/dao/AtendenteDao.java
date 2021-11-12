package br.com.tt.chat.dao;

import br.com.tt.chat.model.Atendente;
import br.com.tt.chat.model.Nivel;
import br.com.tt.chat.model.Senioridade;
import br.com.tt.chat.model.StatusAtendente;
import br.com.tt.chat.util.Conexao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AtendenteDao {

    Conexao conexao;

    public AtendenteDao(Conexao conexao) throws SQLException {
        this.conexao = conexao;
    }

    public void salvarAtendente(Atendente atendente) throws SQLException {

        String sql = "insert into atendente (nome, status, nivel, senioridade) values (?,?,?,?)";
        //prepareStatement quando tiver parâmetros para informar depois
        PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
        ps.setString(1, atendente.getNome());
        ps.setString(2, atendente.getStatus().name());
        ps.setString(3, atendente.getNivel().name());
        ps.setString(4, atendente.getSenioridade().name());
        ps.execute();
    }

    public List<Atendente> listarAtendentes() throws SQLException {

        String sql = "select id,nome,status,nivel,senioridade from atendente Where status = ? ";
        PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(sql);
        preparedStatement.setString(1,"ATIVO");
        ResultSet rs = preparedStatement.executeQuery();

        List<Atendente> atendentes = new LinkedList<>();

        while (rs.next()){
            Long id = rs.getLong("id");
            String nome = rs.getString("nome");
            String status = rs.getString("status").toUpperCase();
            String nivel = rs.getString("nivel").toUpperCase();
            String senioridade = rs.getString("senioridade").toUpperCase();
            Atendente atendente = new Atendente(id, nome, StatusAtendente.valueOf(status),
                    Nivel.valueOf(nivel), Senioridade.valueOf(senioridade));
            atendentes.add(atendente);
        }

        return atendentes;
    }
}
