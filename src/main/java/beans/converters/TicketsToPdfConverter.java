package beans.converters;

import beans.models.Ticket;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class TicketsToPdfConverter implements Converter<List<Ticket>, byte[]> {



    @Override
    public byte[] convert(List<Ticket> source) {
        byte[] bytes = null;
        try {
            Document document = new Document();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

                PdfWriter.getInstance(document, stream);


            document.open();
            document.addTitle("Tickets");
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

            for(Ticket ticket : source) {
                Paragraph paragraph = new Paragraph(ticket.toString(), font);
                document.add(paragraph);
            }

            document.close();
            bytes = stream.toByteArray();
            stream.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
