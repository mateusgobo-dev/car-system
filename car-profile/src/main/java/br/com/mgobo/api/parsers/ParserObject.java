package br.com.mgobo.api.parsers;

import br.com.mgobo.web.dto.ColorDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@FunctionalInterface
public interface ParserObject {
    String toJson(Object dto);
    ParserObject parserObject = object -> {
        try {
            return new ObjectMapper().writeValueAsString(object);
        }catch (JsonProcessingException ex){
            throw new RuntimeException(ex);
        }
    };
}
