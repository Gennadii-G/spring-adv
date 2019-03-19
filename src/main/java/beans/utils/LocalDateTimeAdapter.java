package beans.utils;


import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Gennadii Borodin
 */
public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
 
    public LocalDateTime unmarshal(String v) throws Exception {
        return LocalDateTime.parse(v);
    }

    @Override
    public String marshal(LocalDateTime v) throws Exception {
        return v.toString();
    }

}
