package cn.fire.common.web.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @Author: wangzc
 * @Date: 2020/8/6 9:26
 */

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();

        final SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }

}

/**
 * deserializer
 */
class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

    private static final DateTimeFormatter DEFAULT_LOCALDATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    public LocalDateTimeDeserializer() {
        super(LocalDateTime.class);
    }

    @Override
    public LocalDateTime deserialize(final JsonParser parser,final DeserializationContext context) throws IOException {
        final String value = parser.getValueAsString();
        return LocalDateTime.parse(value,DEFAULT_LOCALDATETIME_FORMATTER);
    }
}

/**
 * serializer
 */
class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

    private static final ZoneId DEFAULT_ZONE_ID = ZoneId.of("GMT+8");
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