import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


public class ConvertingToXml implements ConvertToXml {
    private final URL url = new URL(new SavingXmlFromConfig().getUrl() + GettingCurrentDate.getCurrentDate());
    private final Path file = Path.of(new SavingXmlFromConfig().getFilePath());

    public ConvertingToXml() throws MalformedURLException {
    }


    @Override
    public Document convertToXml() throws ParserConfigurationException, IOException, SAXException {

        try (InputStream inputStream = url.openStream()) {
            Files.copy(inputStream, file, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("URL's problem");
        }

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(new SavingXmlFromConfig().getFilePath());

        return document;
    }
}
