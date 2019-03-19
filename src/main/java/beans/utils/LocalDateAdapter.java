package beans.utils;


import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 *
 * @author Gennadii Borodin
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
 
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v);
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }

}
