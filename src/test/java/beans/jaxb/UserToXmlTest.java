package beans.jaxb;

import beans.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileNotFoundException;

public class UserToXmlTest {

    private User user;

    @Before
    public void setUp(){
        user = new User();
        user.setId(90);
        user.setEmail("hello@email.com");
        user.setPassword("password");
    }

    @After
    public void clear(){
        user = null;
    }

    @Test
    public void testObjectToXml() throws JAXBException, FileNotFoundException {
        JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(user, new File("user.xml"));
        marshaller.marshal(user, System.out);
    }
}
