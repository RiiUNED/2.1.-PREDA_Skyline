import java.io.*;
import java.util.*;
import java.util.regex.*;

/*
 * Clase auxiliar con métodos estáticos relacionados con la manipulación de 
 * datos en los ficheros de texto de entrada y salida.
 * 
 * Aligera de texto clases más relacionas con el esquema Divide y Vencerás
 * y el algoritmo Mergesort
 */
public class Auxiliar {
	
	/*
	 * Método para crear ficheros
	 * @param: 	route -> ruta donde crear el fichero 
	 * 			(tomado como ráiz la ubicación del ejecutable)
	 * 			name -> nombre que se le da al fichero que se crea
	 * @return:	false -> el fichero no se creó
	 * 			true -> fichero creado con éxito
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
	 * Método al que se le pasa un String y devuelve un Array
	 * con los número que haya en el String
	 * reconoce decenas, centenas, etc.
	 * @param: String string
	 * @return: Array con los números de cadena
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
	 * Método al que se le pasa un la ruta de un fichero y
	 * devuelve una lista enlazada con la líneas del fichero 
	 * en forma de String
	 * @param: String con la ruta del fichero
	 * @return: lista enlazada de String con las líneas del fichero
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
	 * Método al que se pasa por parámetro la ruta de un fichero con las 
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
