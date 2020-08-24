package cn.fire.common.web.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @Author: wangzc
 * @Date: 2020/8/6 9:26
 */

@Configuration
public class GlobalJacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer(){
        return builder -> {
            builder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
            builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer());
            builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer());
            builder.serializerByType(Long.TYPE, new DefaultToStringSerializer());
            builder.serializerByType(Integer.TYPE, new DefaultToStringSerializer());
            builder.serializerByType(Double.TYPE, new DefaultToStringSerializer());
            builder.serializerByType(Float.TYPE, new DefaultToStringSerializer());
            builder.serializerByType(Boolean.TYPE, new DefaultToStringSerializer());
        };
    }

    @Bean
    ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.getSerializerProvider().setNullValueSerializer(new NullableSerializer());
        return objectMapper;
    }

}

class DefaultToStringSerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        //安全转换
        jsonGenerator.writeString(String.valueOf(t));
    }
}

/**
 * 保留字段, null转""
 */
class NullableSerializer extends JsonSerializer<Object> {
    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString("");
    }
}

/**
 * deserializer
 */
class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {
    private static final ZoneId DEFAULT_ZONE_ID = ZoneId.of("GMT");
    public LocalDateTimeDeserializer() {
        super(LocalDateTime.class);
    }

    @Override
    public LocalDateTime deserialize(final JsonParser parser,final DeserializationContext context) throws IOException {
        final long value = parser.getValueAsLong();
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(value),DEFAULT_ZONE_ID);
    }
}

/**
 * serializer
 */
class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

    private static final ZoneId DEFAULT_ZONE_ID = ZoneId.of("GMT");
    public LocalDateTimeSerializer() {
        super(LocalDateTime.class);
    }

    @Override
    public void serialize(final LocalDateTime value, final JsonGenerator generator, final SerializerProvider provider) throws IOException {
        if (value != null) {
            final long mills = value.atZone(DEFAULT_ZONE_ID).toInstant().toEpochMilli();
            generator.writeString(String.valueOf(mills));
        } else {
            generator.writeString("");
        }
    }
}