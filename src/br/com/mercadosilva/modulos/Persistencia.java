package br.com.mercadosilva.modulos;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Persistencia implements PersistenciaInterface, Serializable {

	private static final long serialVersionUID = -8750124577151351713L;

	public void save (String name, Object o) throws IOException {
		
		FileOutputStream file = new FileOutputStream("data/"+name+".dat");
		ObjectOutputStream out = new ObjectOutputStream(file);
		
		try {								
			out.writeObject(o);
			
			out.flush();
			out.close();
			file.flush();
			file.close();
		} catch (FileNotFoundException error) {
			System.out.println("[System log]:\n "+error);
		}
	}
}
