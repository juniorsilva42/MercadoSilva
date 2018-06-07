package br.com.mercadosilva.modulos.persistencia;

import java.io.IOException;

/**
 * @author Junior Silva <jsiilva@outlook.com.br>
 *
 */
public interface PersistenciaInterface {
	
	/**
	 * 
	 * Método para salvar um determinado objeto em um arquivo
	 * @author Ivanicio Jr
	 * @param String name, Object o
	 * @throws IOException 
	 * 
	 */
	public void save (String fileName, Object o) throws IOException;
	
	/**
	 * 
	 * Método para obter os objetos objeto em um dado arquivo
	 * @author Ivanicio Jr
	 * @param String fileName
	 * @return 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * 
	 */
	public Object get (String fileName) throws IOException, ClassNotFoundException;
}
