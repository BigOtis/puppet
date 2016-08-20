package src.sound.puppet.persistent;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class WordDataBaseManager {

	public void savePerson(Person person){
		try {
			File file = new File("people/" + person.getName() + ".xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(person, file);
	      } 
		catch (JAXBException e) {
			e.printStackTrace();
	     }
	}
	
	public Person loadPerson(String name){
		try {
			File file = new File("people/" + name + ".xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
			Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();

			return (Person) jaxbUnMarshaller.unmarshal(file);
	      } 
		catch (JAXBException e) {
			e.printStackTrace();
			return null;
	     }
	}
	
}
