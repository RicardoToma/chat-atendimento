package br.com.tt.chat.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mensagem {

    private Long id;
    private String texto;
    private LocalDateTime dataHoraEnvio;//Date,Calendar,ZonedDateTime

    private String remetente;
    private String destinatario;
    private LocalDateTime dataHoraLeitura;

    public Mensagem (String texto, LocalDateTime dataHoraEnvio, String remetente, String destinatario, LocalDateTime dataHoraLeitura) {
        this.texto = texto;
        this.dataHoraEnvio = dataHoraEnvio;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.dataHoraLeitura = dataHoraLeitura;
    }

    public Mensagem(Long id, String texto, LocalDateTime dataHoraEnvio, String remetente, String destinatario, LocalDateTime dataHoraLeitura) {
        this.texto = texto;
        this.dataHoraEnvio = dataHoraEnvio;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.dataHoraLeitura = dataHoraLeitura;
    }

    public Mensagem(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public LocalDateTime getDataHoraEnvio() {
        return dataHoraEnvio;
    }

    public String getRemetente() {
        return remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public LocalDateTime getDataHoraLeitura() {
        return dataHoraLeitura;
    }

/*    public String getDescricao() {
        return String.format("%s - %s - %s - %s - %s - %s - %s", texto, dataHoraEnvio, remetente, destinatario,
                dataHoraLeitura);
    }
*/
    public String getDescricao() {
        String dataHoraFormatada = dataHoraEnvio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy mm:ss"));
        return String.format("%s (%s)", texto, dataHoraFormatada);
    }

}
