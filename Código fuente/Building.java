import java.util.*;

/*
 * Clase para definir el objeto edificio
 * al edificio lo define 3 Integer: cominezo, final, altura
 * @param: Array<Integer> = [comienzo, final, altura]
 * return: edificio
 */
public class Building {
	
	Integer start;
	Integer end;
	Integer height;
	
	//Constructor
	public Building (List<Integer> coord) {
		
		start = coord.get(0);
		end = coord.get(1);
		height = coord.get(2);
	}

}
