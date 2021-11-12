package br.com.tt.chat;

import br.com.tt.chat.dao.AtendenteDao;
import br.com.tt.chat.excecoes.AtendenteException;
import br.com.tt.chat.model.Atendente;
import br.com.tt.chat.model.Nivel;
import br.com.tt.chat.model.Senioridade;
import br.com.tt.chat.util.Conexao;
import br.com.tt.chat.util.UserInterface;

import java.sql.SQLException;
import java.util.List;

public class ProgramaAtendente {

    private static AtendenteDao dao;

    public static void main(String[] args) throws SQLException {
        Conexao conexao = new Conexao();
        conexao.conecta();
        dao = new AtendenteDao(conexao);

        while (true) {
            try {
                adicionarAtentende();
                break; //quer dizer sucesso
            } catch (AtendenteException e) {
                System.err.println("Ops... aconteceu algo errado: " + e.getMessage());
            }
        }

        listarAtendentes();

        conexao.desconecta();
    }

    private static void listarAtendentes() throws SQLException {
        List<Atendente> atendentes = dao.listarAtendentes();

        UserInterface.imprimir("Atendentes:");
        for (Atendente atend : atendentes) {
            UserInterface.imprimir(atend.getDescricao());
        }
    }

    private static void adicionarAtentende() throws SQLException {
        Nivel nivel = UserInterface
                .pedirTexto("Informe o nível do atendente:")
                .map(t -> Nivel.valueOf(t))
                .orElse((Nivel.N1));

        Senioridade senioridade = UserInterface
                .pedirTexto("Informe a senioridade do atendente:")
                .map(t -> Senioridade.valueOf(t))
                .orElse((Senioridade.JUNIOR));

        Atendente atendente = new Atendente(pedirNome(), senioridade, nivel);
        System.out.println(atendente.getNome());
        System.out.println(atendente.getStatus());

        // Data Access Objet - Objeto de acesso a dados (banco de dados)
        dao.salvarAtendente(atendente);
        System.out.println("Atendente adicionado!");
    }

    private static String pedirNome() {
        String nome = UserInterface.
                pedirTexto("Informe o nome do atendente:")
                .orElseThrow(() -> new AtendenteException("O nome não pode ser vazio! "));

                // Poderia usar o filter, mas exigiria uma mensagem "padrão" pros dois casos
                //.filter(texto -> texto.length() > 100)
                //.orElseThrow(() -> new AtendenteException("O nome não pode ser vazio! e deve conter 100 caracteres"));



//        if(nome.isBlank()){
//            throw new AtendenteException("O nome não pode ser vazio!");
//        }
        if(nome.length() > 100){
            throw new AtendenteException("O nome não pode passar de 100 caracteres!!");
        }
        return nome;
    }

}
