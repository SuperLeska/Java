package serialization;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.TypeFactory;

public class JsonSerialize<T> implements Serialization<T> {

	private Class<T> clas;
	public Class<T> getClas() {
		return clas;
	}
	
	public void setClas(Class<T> clas) {
		this.clas = clas;
	}

	public JsonSerialize(Class<T> clas) {
		this.clas = clas;
	}
    

	public void toFile(List<T> object, File file) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			FileWriter fileWriter = new FileWriter(file);
			mapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, object);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<T> fromFile(File file) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		TypeFactory typeFactory = mapper.getTypeFactory();
		CollectionType collectionType = typeFactory.constructCollectionType(ArrayList.class, this.clas);
		return mapper.readValue(file, collectionType);
	}
}