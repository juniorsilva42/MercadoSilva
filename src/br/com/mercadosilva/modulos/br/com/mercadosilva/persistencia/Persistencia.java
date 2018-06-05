package br.com.mercadosilva.modulos.br.com.mercadosilva.persistencia;

import java.io.*;

public class Persistencia implements PersistenciaInterface, Serializable
{
	private static final long serialVersionUID = -8750124577151351713L;

	public void save (String fileName, Object o) throws IOException {

		OutputStream os = null;

		try {
			os = new FileOutputStream("data/"+fileName+".dat");
			ObjectOutputStream oos = new ObjectOutputStream(os);

			oos.writeObject(o);

			oos.flush();
			oos.close();

		} catch (FileNotFoundException error) {
			System.out.println("[System log]:\n "+error);
		} finally {
			if (os != null) {
				os.flush();
				os.close();
			}
		}
	}
	
	public Object get (String fileName) throws IOException, ClassNotFoundException {

		InputStream is = null;
		Object o = null;

		try {
			is = new FileInputStream("data/"+fileName+".dat");
			ObjectInputStream ois = new ObjectInputStream(is);

			o = ois.readObject();

			ois.close();
		} catch (FileNotFoundException e) {
			System.out.println("[System log]:\n "+e);
		} finally {
			if (is != null)
				is.close();

		}

		return o;
	}
	
	public boolean isExists (String fileName) throws IOException {
		File file = new File("data/"+fileName+".dat");
		
		if (file.exists())
			return true; 
		
		return false;
	}
}
