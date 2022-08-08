import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Clase para definir un punto en RxR
 * @param: x, y
 * return: (x, y)
 */
public class Point {
	
	private Integer x;
	private Integer y;
	
	//Constructor -> Coordenadas (x, y)
	public Point(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return the x
	 */
	public Integer getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public Integer getY() {
		return y;
	}
	
	/*
	 * Método para ver el punto por consola en el contexto del horizonte
	 * con el formato del libro
	 */
	public void show() {
		System.out.print(this.x+", "+this.y);
	}
	
	/*
	 * Escribe el punto en un fichero de salida
	 * @param:	ruta del fichero donde se escribe el punto
	 */
	public void exit(String ficheroSalida) {
		String punto = "("+this.x+", "+this.y+")";
		
		try {
			FileWriter archivo = new FileWriter(ficheroSalida);
			PrintWriter pw = new PrintWriter(archivo);
			
			pw.println(punto);
			
			archivo.close();
		}
		catch (Exception e) {
			System.out.println("Hay un fallo");
		}
	}

}
