package serialization;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface Serialization<T> {

	void toFile(List<T> object, File file);
	List<T> fromFile(File file) throws Exception;
}
