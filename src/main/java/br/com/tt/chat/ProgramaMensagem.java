package br.com.tt.chat;

import br.com.tt.chat.dao.AtendenteDao;
import br.com.tt.chat.dao.MensagemDao;
import br.com.tt.chat.dao.UsuarioDao;
import br.com.tt.chat.excecoes.MensagemException;
import br.com.tt.chat.model.Atendente;
import br.com.tt.chat.model.Mensagem;
import br.com.tt.chat.model.Usuario;
import br.com.tt.chat.util.Conexao;
import br.com.tt.chat.util.UserInterface;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

public class ProgramaMensagem {


    // Constante, não pertence a 1 mensagem
    // static + final + nome em maiusculo
    private static final  int LIMITE_MINIMO_DE_CARACTERES = 5;
    private static final  int LIMITE_MAXIMO_DE_CARACTERES = 200;
    private static MensagemDao mensagemDao;
    private static UsuarioDao usuarioDao;
    private static AtendenteDao atendenteDao;

    private  static Usuario usuarioLogado;
    private  static Atendente atendenteEscolhido;

    public static void main(String[] args) throws SQLException {
        Conexao conexao = new Conexao();
        conexao.conecta();
        mensagemDao = new MensagemDao(conexao);
        usuarioDao = new UsuarioDao(conexao);

        login();
        adicionarMensagem();
        listarMensagem();
        conexao.desconecta();
    }

    public static void login() throws SQLException {
        // Pedir pro usuario seu id
        // Buscar o usuario na base (usuarioDao.lista()) e checa se o usuario existe com esse ID.
        // se não existir usuario, damos uma exceção
        // existir, loga

        Long id  = Long.valueOf(UserInterface.pedirString("Informe o ID do Usuario:"));
/*        List<Usuario> lista = usuarioDao.listar();
                Stream<Usuario> usuario = lista.stream()
                        .filter(u -> u.getId().equals(id)).findFirst()
                        .
*/
        // List<Usuario> lista;
        //Integer id;
        //lista.stream().filter(u -> u.getId() == id).findFirst()
        //usuarioLogado = ;
    }

    public static void excolherAtendente() throws SQLException {
        //pedir o nome do atendente
        // injetar
    }


    public static void adicionarMensagem() throws SQLException {
        // Ler a mensagem informada pelo usuario
        String texto = pedirMensagem();
        LocalDateTime dataHoraEnvioLocalDate = LocalDateTime.now();

        String remetente = UserInterface.pedirString("Informe o Remetente:");;
        String destinatario = UserInterface.pedirString("Informe o Destinatario:");

        LocalDateTime dataHoraLeituraLocalDate = LocalDateTime.now();

        Mensagem mensagem = new Mensagem(texto, dataHoraEnvioLocalDate, remetente, destinatario, dataHoraLeituraLocalDate);
        System.out.println(mensagem.getTexto());
        System.out.println(mensagem.getRemetente());
        System.out.println(mensagem.getDestinatario());

        // Data Access Objet - Objeto de acesso a dados (banco de dados)
        mensagemDao.salvarMensagem(mensagem);
        System.out.println("Mensagem adicionada!");
    }

    public static String pedirMensagem() {
        String texto = UserInterface
                .pedirTexto("Informe o Texto:")
                .orElseThrow(() -> new MensagemException("A mensagem não pode ser vazio! "));
        if(texto.length() < LIMITE_MINIMO_DE_CARACTERES ||
                texto.length() > LIMITE_MAXIMO_DE_CARACTERES){
           throw new MensagemException("A Mensagem deve ter no minimo 5 caracteres e no maximo 200 caracteres!!");
        }
        return texto;
    }

    public static void listarMensagem() throws SQLException {
        List<Mensagem> mensagens = mensagemDao.listar();

        UserInterface.imprimir("Lista de Mensagem cadastrados:");
        for(Mensagem msg : mensagens){
            UserInterface.imprimir(msg.getDescricao());
        }
    }
}
