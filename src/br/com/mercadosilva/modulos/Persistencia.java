package br.com.mercadosilva.modulos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Persistencia implements PersistenciaInterface, Serializable 
{
	private static final long serialVersionUID = -8750124577151351713L;

	public void save (String fileName, Object o) throws IOException {
		File file = new File("data/"+fileName+".dat");
		FileOutputStream is = new FileOutputStream("data/"+fileName+".dat");
		ObjectOutputStream out = new ObjectOutputStream(is);
		
		try {								
			out.writeObject(o);
			
			out.flush();
			out.close();
			is.flush();
			is.close();
		} catch (FileNotFoundException error) {
			System.out.println("[System log]:\n "+error);
		}
	}
	
	public Object get (String fileName) throws IOException, ClassNotFoundException {
		
		FileInputStream file = new FileInputStream("data/"+fileName+".dat");
		ObjectInputStream in = new ObjectInputStream(file);
		Object o = in.readObject();
		
		in.close();
		file.close();
		
		return o;
	}
	
	public boolean isExists (String fileName) throws IOException {
		File file = new File("data/"+fileName+".dat");
		
		if (file.exists())
			return true; 
		
		return false;
	}
}
