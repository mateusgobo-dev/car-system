package br.com.mgobo.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpErrorsMessage {
    CREATED("Registro % criado com sucesso"),
    ACCEPTED("Registro % alterado com sucesso"),
    BAD_REQUEST("Erro na requisicao. Acesso %s. erro => %s");
    String message;
}
