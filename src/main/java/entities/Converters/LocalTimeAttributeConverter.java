package entities.Converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Converter(autoApply = true)
public class LocalTimeAttributeConverter implements AttributeConverter<LocalTime, Time> {
    @Override
    public Time convertToDatabaseColumn(LocalTime localTime){
        return localTime == null ? null : Time.valueOf(localTime);
    }
    @Override
    public LocalTime convertToEntityAttribute (Time time){
        return time == null ? null : time.toLocalTime();
    }

}
