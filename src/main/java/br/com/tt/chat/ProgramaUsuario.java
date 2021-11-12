package br.com.tt.chat;

import br.com.tt.chat.dao.AtendenteDao;
import br.com.tt.chat.dao.UsuarioDao;
import br.com.tt.chat.excecoes.UsuarioException;
import br.com.tt.chat.model.Perfil;
import br.com.tt.chat.model.Sexo;
import br.com.tt.chat.model.Usuario;
import br.com.tt.chat.util.Conexao;
import br.com.tt.chat.util.UserInterface;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ProgramaUsuario {

    private static UsuarioDao usuarioDao;

    public static void main(String[] args) throws SQLException {
        Conexao conexao = new Conexao();
        try {
            conexao.conecta();
        }catch (SQLException e){
            System.out.println("Deu ruim na conexão: "+ e.getMessage());
            e.printStackTrace();
            return;
        }
        usuarioDao = new UsuarioDao(conexao);

        criar();
        listar();

        conexao.desconecta();
    }

    private static void criar() throws SQLException {

        //Peça os dados do usuário
        String nome = UserInterface.pedirTexto("Informe o nome:")
                .orElseThrow(() -> new UsuarioException("Nome deve ser informado!"));

        String cpf = UserInterface.pedirTexto("Informe o CPF:")
                .orElseThrow(() -> new UsuarioException("CPD deve ser informado!"));

        Sexo sexo = UserInterface.pedirTexto("Informe o sexo:")
                .map(s -> Sexo.valueOf(s))
                .orElse(Sexo.NAO_INFORMADO);

        Perfil perfil = UserInterface.pedirTexto("Informe o perfil:")
                .map(p -> Perfil.valueOf(p))
                .orElse(Perfil.NORMAL);

        String telefone = UserInterface.pedirString("Informe o telefone:");
        String email = UserInterface.pedirString("Informe o email:");
        String endereco = UserInterface.pedirString("Informe o endereco:");

        String nascimento = UserInterface.pedirString(
                "Informe o nascimento (dd/MM/yyyy): ");
        LocalDate nascimentoLocalDate = LocalDate
                .parse(nascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Usuario usuario = new Usuario(null, nome, cpf, sexo, perfil, telefone, email,
                endereco, nascimentoLocalDate);

        usuarioDao.salvar(usuario);
    }

    private static void listar() throws SQLException {

        List<Usuario> usuarios = usuarioDao.listar();

        UserInterface.imprimir("Lista de usuários cadastrados:");
        for(Usuario usuario : usuarios){
            UserInterface.imprimir(usuario.getDescricao());
        }
    }
}
