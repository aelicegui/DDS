package entities.Converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDateTime;
import java.sql.Timestamp;
@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp){
        return timestamp == null ? null : timestamp.toLocalDateTime();

    }

    @Override
    public  Timestamp convertToDatabaseColumn(LocalDateTime localDateTime){
        return localDateTime == null ? null : Timestamp.valueOf(localDateTime);
    }

}
