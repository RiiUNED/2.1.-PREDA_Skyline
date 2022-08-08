import java.io.*;
import java.util.*;
import java.util.regex.*;

/*
 * Clase auxiliar con m�todos est�ticos relacionados con la manipulaci�n de 
 * datos en los ficheros de texto de entrada y salida.
 * 
 * Aligera de texto clases m�s relacionas con el esquema Divide y Vencer�s
 * y el algoritmo Mergesort
 */
public class Auxiliar {
	
	/*
	 * M�todo para crear ficheros
	 * @param: 	route -> ruta donde crear el fichero 
	 * 			(tomado como r�iz la ubicaci�n del ejecutable)
	 * 			name -> nombre que se le da al fichero que se crea
	 * @return:	false -> el fichero no se cre�
	 * 			true -> fichero creado con �xito
	 */
	public static boolean createFile(String route, String name) {
		BufferedWriter bufWri;
		File file = new File(route);
		
		try {
			if(file.exists()) {
				System.out.println("ERROR");
				System.out.println("El fichero "+name+" ya existe");
				return false;
			} else {
				bufWri = new BufferedWriter(new FileWriter(file));
				bufWri.close();
				return true;
			}
		} catch (Exception e) {return false;}
		
	}
	
	//de Stack Overflow
	/*
	 * M�todo al que se le pasa un String y devuelve un Array
	 * con los n�mero que haya en el String
	 * reconoce decenas, centenas, etc.
	 * @param: String string
	 * @return: Array con los n�meros de cadena
	 */
	public static List<Integer> numberExtract(String string) {
	      List<Integer> numbers = new ArrayList<Integer>();
	      Matcher finder = Pattern.compile("\\d+").matcher(string);
	      while (finder.find()) { 
	        numbers.add(Integer.parseInt(finder.group()));
	      } 
	      return numbers;
	 }
	
	/*
	 * M�todo al que se le pasa un la ruta de un fichero y
	 * devuelve una lista enlazada con la l�neas del fichero 
	 * en forma de String
	 * @param: String con la ruta del fichero
	 * @return: lista enlazada de String con las l�neas del fichero
	 */
	public static List<String> readFile(String fileRute){
		
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		List<String> lineas = new LinkedList<String>();
		
		try {
			archivo = new File(fileRute);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			String linea;
			while ((linea=br.readLine())!=null) {
				lineas.add(linea);
			}
		}
		catch(Exception e) {
			System.out.println("ERROR.");
			System.out.println("No existe el fichero "+fileRute);
		}finally {
			try {
				if(null!=fr) {
					fr.close();
				}
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return lineas;
	}
	
	/*
	 * M�todo al que se pasa por par�metro la ruta de un fichero con las 
	 * coordenadas de un conjunto de edificios y devuelve una lista de coordenadas
	 * @param: String con la ruta del fichero
	 * @return: lista con las corrdenadas de los edificos
	 */
	public static List<List<Integer>> coordenadasEdificios(String rutaFichero){
		
		List<String> lineas = new LinkedList<String>();
		lineas = readFile(rutaFichero);
		
		List<List<Integer>> numeros = new LinkedList<List<Integer>>();
		
		Iterator<String> it = lineas.iterator();
		while(it.hasNext()) {
			numeros.add(numberExtract(it.next()));
		}
		
		return numeros;
	}

}
