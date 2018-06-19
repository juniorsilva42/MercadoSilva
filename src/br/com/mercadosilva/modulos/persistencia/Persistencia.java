package br.com.mercadosilva.modulos.persistencia;

import java.io.*;

public class Persistencia implements PersistenciaInterface, Serializable {

	private static final long serialVersionUID = -8750124577151351713L;

	private String ext = ".dat";
	private String dir = "data/";

	/*
	*
	* Procedimento para salvar um dado objeto em um arquivo
	*
	* @arguments String filename, Object o
	* FileName: Nome do arquivo a ser gravado
	* o: Objeto a ser gravado
	*
	* @return void
	*
	* */
	public void save (String fileName, Object o) throws IOException {
		OutputStream os = null;
		ObjectOutputStream oos = null;

		try {

			os = new FileOutputStream(dir+fileName+ext);
			oos = new ObjectOutputStream(os);

			// Escreve o objeto serializado no arquivo de dados especificado por argumento.
			oos.writeObject(o);

			oos.flush();
			oos.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			if (os != null) {
				os.flush();
				os.close();
			}
		}
	}

	/*
	 *
	 * Método para obter objetos de um dado arquivo
	 *
	 * @arguments String filename
	 * FileName: Nome do arquivo que será consumido
	 *
	 * @return Object
	 *
	 * */
	public Object get (String fileName) throws IOException, ClassNotFoundException {

		InputStream is = null;
		Object o = null;
		ObjectInputStream ois = null;

		try {
			is = new FileInputStream(dir+fileName+ext);
			ois  = new ObjectInputStream(is);

			o = ois.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("[System log]:\n "+e);
		}
		finally {
			try {
				ois.close();
				is.close();
			} catch (IOException e) {
				System.out.println("Ocorreu um erro fatal enquanto o canal de arquivos tentava fechar. => \n"+e);
			}
		}

		return o;
	}

	/*
	 *
	 * Método para verificar se um arquivo existe
	 *
	 * @arguments String filename
	 * FileName: Nome do arquivo que será verificado
	 *
	 * @return Boolean
	 *
	 * */
	public boolean isExists (String fileName) throws IOException {
		File file = new File(dir+fileName+ext);
		
		if (file.exists())
			return true; 
		
		return false;
	}

	public boolean isEmpty (String fileName) throws IOException {
		File file = new File(dir+fileName+ext);

		if (file.length() == 0)
			return true;

		return false;
	}
}
