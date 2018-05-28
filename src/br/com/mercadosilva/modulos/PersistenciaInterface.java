/**
 * 
 */
package br.com.mercadosilva.modulos;

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
	 * 
	 */
	public void get (String fileName);
}
