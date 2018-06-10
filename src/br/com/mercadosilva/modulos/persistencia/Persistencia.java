package br.com.mercadosilva.modulos.persistencia;

import java.io.*;

public class Persistencia implements PersistenciaInterface, Serializable {

	private static final long serialVersionUID = -8750124577151351713L;

	public void save (String fileName, Object o) throws IOException {

		OutputStream os = null;

		if (isExists(fileName)) {
			try {
				os = new FileOutputStream("data/"+fileName+".dat", true);
				ObjectOutputStream oos = new ObjectOutputStream(os);

				// Escreve o objeto serializado no arquivo de dados especificado por argumento.
				oos.writeObject(o);

				oos.flush();
				oos.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			finally {
				if (os != null) {
					os.flush();
					os.close();
				}
			}
		}
	}
	
	public Object get (String fileName) throws IOException, ClassNotFoundException {

		InputStream is = null;
		Object o = null;
		ObjectInputStream ois;

		if (isExists(fileName)) {
			try {
				is = new FileInputStream("data/"+fileName+".dat");
				ois = new ObjectInputStream(is);

				o = ois.readObject();

				ois.close();
			} catch (FileNotFoundException e) {
				System.out.println("[System log]:\n "+e);
			} finally {
				if (is != null)
					is.close();
			}
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
