package com.mmp.xmlproject.services;

import com.mmp.xmlproject.xmlmodel.Students;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

@Service
public class XmlStudentParser {

    @Value("${resources.xsd.path}")
    private String xsdPath;

    public Students parse(InputStream xmlInputStream) throws Exception {
        Schema schema = loadSchema();

        JAXBContext context = JAXBContext.newInstance(Students.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        unmarshaller.setSchema(schema);

        return (Students) unmarshaller.unmarshal(xmlInputStream);
    }

    private Schema loadSchema() throws SAXException {
        InputStream xsdInputStream = getClass()
                .getClassLoader()
                .getResourceAsStream(xsdPath);

        if (xsdInputStream == null) {
            throw new IllegalStateException("Could not find XSD at xsd/students.xsd");
        }

        SchemaFactory schemaFactory =
                SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        return schemaFactory.newSchema(new StreamSource(xsdInputStream));
    }
}