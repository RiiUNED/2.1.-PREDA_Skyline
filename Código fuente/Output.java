/*
 * Clase para poder trabajar con la informaci�n que generan los par�metros de entrada
 * en un funci�n separada
 */
public class Output {
	
	public boolean run;
	public boolean newfile;
	public boolean show;
	public String entrada;
	public String salida;
	
	/*
	 * Constructor de la clase
	 */
	public Output(boolean run, boolean newfile, boolean show, String entrada, String salida) {
		
		this.run = run;
		this.newfile = newfile;
		this.show = show;
		this.entrada = entrada;
		this.salida = salida;
	}
}
