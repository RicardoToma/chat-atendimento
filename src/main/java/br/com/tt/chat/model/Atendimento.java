package br.com.tt.chat.model;

import java.time.LocalDateTime;

public class Atendimento {

    private LocalDateTime dataHoraInicio;
    private LocalDateTime  dataHoraResolucao;
    private int posicaoFila;
    private Usuario usuario;
    private Atendente atendente;
    private Mensagem[] mensagens;
    private String protocolo;
    private StatusAtendimento status;
    private int nota;
}
