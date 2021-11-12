package br.com.tt.chat;

import br.com.tt.chat.dao.MensagemDao;
import br.com.tt.chat.model.Mensagem;
import br.com.tt.chat.util.Conexao;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProgramaWeb {

    private static MensagemDao mensagemDao;

    public static void main(String[] args) throws SQLException {

        Conexao conexao = new Conexao();
        conexao.conecta();
        mensagemDao = new MensagemDao(conexao);

        Spark.get("/",(req, resp) -> homePage());
        Spark.get("/mensagens", 
                    (req, resp) -> listarMensagens() , new ThymeleafTemplateEngine());
        Spark.get("/mensagens",
                (req,resp) -> listarMensagens(), new ThymeleafTemplateEngine());
        Spark.get("/adicionar",
                (req, resp) -> adicionar(req, resp), new ThymeleafTemplateEngine());
    }

    private static ModelAndView adicionar(Request request, Response response) throws SQLException {

        String texto = request.queryParams("mensagem");
        mensagemDao.adicionar(new Mensagem(texto));

        //Atualizar as mensagens da página
        return listarMensagens();
    }

    private static ModelAndView listarMensagens() throws SQLException {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("titulo", "Pagina de mensagens");
        parametros.put("mensagens", mensagemDao.listar());
        return new ModelAndView(parametros, "Pagina_mensagens");
    }


    private  static  String homePage() {
        return "Olá ! Bem vindo! <br> <a href='/mensagens'>Ir para mensagens</a>";
    }
}
