package mx.com.eixy.utilities.gson;

import java.util.List;

import com.google.gson.Gson;

public class XGson {

	
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> lista(Class<T> clas) {
		
		Gson gson = new Gson();		
		return (List<T>) gson.fromJson("", clas);			
	}

}
