package com.abstractionizer.studentInformationSystem6.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;

public class SimpleGrantedAuthorityDeserializer extends StdDeserializer<SimpleGrantedAuthority> {

    public SimpleGrantedAuthorityDeserializer(){
        super(SimpleGrantedAuthority.class);
    }

    @Override
    public SimpleGrantedAuthority deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode tree = jsonParser.getCodec().readTree(jsonParser);
        return new SimpleGrantedAuthority(tree.get("authority").textValue());
    }
}
