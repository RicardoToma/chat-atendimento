package br.com.tt.chat.dao;

import br.com.tt.chat.model.*;
import br.com.tt.chat.util.Conexao;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class MensagemDao {
    Conexao conexao;

    public MensagemDao(Conexao conexao) throws SQLException {
        this.conexao = conexao;
    }

    public void adicionar(Mensagem mensagem) throws SQLException {
        String sql = "insert into mensagem (texto, dataHoraEnvio) values (?, ?)";
        PreparedStatement st = conexao.getConexao().prepareStatement(sql);
        st.setString(1, mensagem.getTexto());
        st.setTimestamp(2, converteParaTimestamp(mensagem.getDataHoraEnvio()));
        st.execute();
    }

    public void salvarMensagem(Mensagem mensagem) throws SQLException {

        String sql = "insert into mensagem (texto, dataHoraEnvio, Remetente, Destinatario, dataHoraLeitura) values (?,?,?,?, ?)";
        //prepareStatement quando tiver parâmetros para informar depois
        PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
        ps.setString(1, mensagem.getTexto());
        ps.setTimestamp(2, converteParaTimestamp(mensagem.getDataHoraEnvio()));
        ps.setString(3, mensagem.getRemetente());
        ps.setString(4, mensagem.getDestinatario());
        ps.setTimestamp(5, converteParaTimestamp(mensagem.getDataHoraLeitura()));
        ps.execute();
    }

    public List<Mensagem> listarMensagem() throws SQLException {

        Statement statement = conexao.getConexao().createStatement();
        String sql = "select id,texto, dataHoraEnvio, remetente, destinatario, dataHoraLeitura from mensagem";
        ResultSet rs = statement.executeQuery(sql);

        List<Mensagem> mensagens = new LinkedList<>();

        while (rs.next()){
            Long id = rs.getLong("id");
            String texto = rs.getString("texto");
            LocalDateTime dataHoraEnvio = rs.getTimestamp("dataHoraEnvio").toLocalDateTime();
            String remetente = rs.getString("remetente");
            String destinatario = rs.getString("destinatario");
            LocalDateTime dataHoraLeitura = rs.getTimestamp("dataHoraLeitura").toLocalDateTime();
            Mensagem mensagem  = new Mensagem(id, texto, dataHoraEnvio, remetente, destinatario, dataHoraLeitura);
            mensagens.add(mensagem);
        }

        return mensagens;
    }

    private static Timestamp converteParaTimestamp(LocalDateTime dataHora) {
        if (dataHora != null) {
            return Timestamp.from(dataHora.toInstant(ZoneOffset.UTC));
        }
        return null;
    }

    public List<Mensagem> listar() throws SQLException {
        List<Mensagem> mensagens;

        try(Statement statement = conexao.getConexao().createStatement()){
            try(ResultSet resultSet = statement.executeQuery("select * from mensagem")) {
                mensagens = montarMensagens(resultSet);
            }
        }
        return mensagens;
    }

    private List<Mensagem> montarMensagens(ResultSet resultSet) throws SQLException {

        List<Mensagem> mensagens = new LinkedList<>();

        while (resultSet.next()){
            Long id = resultSet.getLong("id");
            String texto = resultSet.getString("texto");
            LocalDateTime dataHoraEnvio = converteParaLocalDateTime(resultSet.getTimestamp("dataHoraEnvio"));
            String remetente = resultSet.getString("remetente");
            String destinatario = resultSet.getString("destinatario");
            LocalDateTime dataHoraLeitura = converteParaLocalDateTime(resultSet.getTimestamp("dataHoraLeitura"));
            Mensagem mensagem  = new Mensagem(id, texto, dataHoraEnvio, remetente, destinatario, dataHoraLeitura);
            mensagens.add(mensagem);
        }
        return mensagens;

    }

    public LocalDateTime converteParaLocalDateTime(Timestamp timestamp) {
        if(timestamp != null) {
            return timestamp.toLocalDateTime();
        }
        return null;
    }
}
