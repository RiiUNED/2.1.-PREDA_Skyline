import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

/**Clase para definir horizontes
 * @param: lista de puntos (comienzo_x, altura)
 * return: Skyline
 */
public class Sky {
	
	private List<Point> puntos;
	
	//Constructor
	public Sky(List<Point> puntos) {
		this.puntos = new LinkedList<Point>();
		this.puntos = puntos;
	}
	
	/**
	 * @return the points
	 */
	public List<Point> getPoints() {
		return puntos;
	}
	
	/*
	 * Método para ver el horizonte por consola con el formato del libro
	 */
	public void show() {
		
		if(!this.puntos.isEmpty()) {
			System.out.print("(");
			int tam = this.puntos.size();
			for(int i=1; i<=tam;i++) {
				this.puntos.get(i-1).show();;
				if(i!=tam) {
					System.out.print(", ");
				}
			}
			System.out.println(")");
		}
	}
	
	/*
	 * Método para crear un fichero con los datos del horizonte de salida
	 * con formato de más fácil lectura que el que se encuentra en el libro base
	 */
	public void exit(String ficheroSalida) {
		
		String punto;
		Point point;
		
		try {
			FileWriter archivo = new FileWriter(ficheroSalida);
			PrintWriter pw = new PrintWriter(archivo);
			
			Iterator<Point> itP = this.puntos.iterator();
			while(itP.hasNext()) {
				point = itP.next();
				punto = "("+point.getX()+", "+point.getY()+")";
				pw.println(punto);
			}
			
			archivo.close();
		} catch (Exception e) {
			
		}
		
	}

}
