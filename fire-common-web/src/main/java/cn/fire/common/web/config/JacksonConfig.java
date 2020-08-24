package cn.fire.common.web.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @Author: wangzc
 * @Date: 2020/8/6 9:26
 */

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer(){
        return builder -> {
            builder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
            builder.serializerByType(LocalDateTime.class,new LocalDateTimeSerializer());
            builder.deserializerByType(LocalDateTime.class,new LocalDateTimeDeserializer());
            builder.serializers(new NullSerializer());
        };
    }

}

/**
 * 保留字段, null转""
 */
class NullSerializer extends JsonSerializer<Object> {

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
            generator.writeNumber(mills);
        } else {
            generator.writeNull();
        }
    }
}