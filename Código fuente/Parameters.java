/*
 * Clase con funciones para manejar los parámetros de entrada
 * al llamar al programa 
 */
public class Parameters {
	
	private final static String paramT = "-t";
	private final static String paramH = "-h";
	
	public static boolean[] idparam(String[] args) {
		
		boolean[] exit = {false, false};
		
		for(int i=0; i<args.length; i++) {
			if(args[i].equals(paramT)) {
				exit[0] = true;
			}
			if(args[i].equals(paramH)) {
				exit[1] = true;
			}
		}
		
		return exit;
	}
	
	/*
	 * Escribe la ayuda
	 */
	private static void help() {
		System.out.println();
		System.out.println("SKYLINE");
		System.out.println();
		System.out.println("NOMBRE");
		System.out.println("	Skyline - Aplicación del algoritmo Mergesort");
		System.out.println();
		System.out.println("SINTAXIS");
		System.out.println("	java -jar skyline.jar [-t] [-h] [fichero de entrada] [fichero de salida]");
		System.out.println();
		System.out.println("DESCRIPCIÓN");
		System.out.println("	Aplicando el esquema Divide y Vencerás - Mergesort,");
		System.out.println("	calcula la silueta en el horizonte que genera un conjunto de edificios");
		System.out.println();
		System.out.println("OPCIONES");
		System.out.println("	-t	muestra la traza de cada llamada recursiva describiendo la parametrización");
		System.out.println("	-h	muestra la ayuda y sintaxis del comando");
		System.out.println("	fichero de entrada");
		System.out.println("		nombre del fichero del que se leen los datos, Conjunto de edificios de la ciudad");
		System.out.println("		");
		System.out.println("	fichero de salida");
		System.out.println("		nombre del fichero que se crea para almacenar la salida.");
		System.out.println("		si el fichero ya existe, el comando dará un error. Si falta el argumento, se da el resultado por pantalla");
		System.out.println("		Datos de salida: Secuencia que representa el Skyline de la ciudad.");
		System.out.println();
		System.out.println("	El ejecutable skyline.jar y las carpetas 'ficheros entrada', 'ficheros salida'");
		System.out.println("	deben estar juntas en el sistema de archivos");
	}
	
	/*
	 * Genera la información con las distintas combinaciones de parámetros posibles
	 * para que la función main de los resultados esperados
	 * @param:	String[] args -> parámetros de entrada por consola
	 * @return: datos en forma Output
	 */
	public static Output paramManager(String[] args) {
		
		int tam = args.length;
		
		String entrada = new String(); 
		String salida = new String(); 
		
		boolean run = false;
		boolean newfile = false;
		boolean show = false;
		
		switch(tam) {
		case 0:
			System.out.println("ERROR");
			System.out.println("No introdujo ningún parámetro de entrada");
			break;
		case 1:
			if(Parameters.idparam(args)[1]) {
				Parameters.help();
			} else {
				entrada = args[0];
				run = !run;
				show = true;
			}
			break;
		case 2:
			if(Parameters.idparam(args)[1]) {
				Parameters.help();
				System.out.println();
				System.out.println("Introduzca el resto de parámetros sin invocar la ayuda");
			} else {
				if(!Parameters.idparam(args)[0]) {
					salida = args[1];
					newfile = Auxiliar.createFile(salida, args[1]);
					entrada = args[0];
					run=!run;
				} else {
					entrada = args[1];
					run = !run;
					show = true;
				}
			}
			break;
		case 3:
			if(Parameters.idparam(args)[1]) {
				Parameters.help();
				System.out.println();
				System.out.println("Introduzca el resto de parámetros sin invocar la ayuda");
			} else {
				salida = args[2];
				newfile = Auxiliar.createFile(salida, args[2]);
				entrada = args[1];
				run=!run;
			}
			break;
		case 4:
			if(Parameters.idparam(args)[1]) {
				Parameters.help();
				System.out.println();
				System.out.println("Introduzca el resto de parámetros sin invocar la ayuda");
			} else {
				System.out.println("ERROR");
				System.out.println("Revise los parámetros de entrada");
			}
			break;
		default:
			if(Parameters.idparam(args)[1]) {
				Parameters.help();
				System.out.println();
				System.out.println("Introduzca el resto de parámetros sin invocar la ayuda");
			} else {
				System.out.println("ERROR");
				System.out.println("Revise los parámetros de entrada");
			}
			break;
		}	
	return new Output(run, newfile, show, entrada, salida);
	}
}
