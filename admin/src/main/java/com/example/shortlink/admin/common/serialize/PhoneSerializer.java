package com.example.shortlink.admin.common.serialize;

import cn.hutool.json.JSON;
import cn.hutool.json.serialize.JSONSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class PhoneSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        char[] chars = value.toCharArray();
        for (int i = 3; i < 7; i++) {
            chars[i] = '*';
        }
        gen.writeString(new String(chars));
    }
}
