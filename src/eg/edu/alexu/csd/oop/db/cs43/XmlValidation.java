package eg.edu.alexu.csd.oop.db.cs43;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XmlValidation {
	
	
	public void validateXml(File tablefolder) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		factory.setNamespaceAware(true);

		SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

		try {
			factory.setSchema(schemaFactory.newSchema(new Source[] { new StreamSource(
					tablefolder.getAbsolutePath() + System.getProperty("file.separator") + tablefolder.getName() + ".Xsd") }));
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			builder.setErrorHandler(new ErrorHandler() {

				@Override
				public void warning(SAXParseException exception) throws SAXException {
					System.out.println(exception.getMessage());
					

				}

				@Override
				public void fatalError(SAXParseException exception) throws SAXException {
					System.out.println(exception.getMessage());

				}

				@Override
				public void error(SAXParseException exception) throws SAXException {
					System.out.println(exception.getMessage());

				}
			});
			Document document = builder.parse(new InputSource(
					tablefolder.getAbsolutePath() + System.getProperty("file.separator") +tablefolder.getName() + ".Xml"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
