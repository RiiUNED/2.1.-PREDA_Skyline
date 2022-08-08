import java.util.*;

public class Skyline {

	public static void main(String[] args) {
		
		Output output = Parameters.paramManager(args);
		
		if(output.run) {
			List<Building> edificios = buildingListGenerator(output.entrada);
			if(output.newfile) {
				Sky fusion = mergeSkyline(edificios, Parameters.idparam(args)[0]);
				if(fusion.getPoints().size()!=0) {
					fusion.exit(output.salida);
					System.out.println("Puede consultar los datos de salida en el fichero: "+output.salida);
				}
			} else {
				if(output.show) {
					Sky fusion = mergeSkyline(edificios, Parameters.idparam(args)[0]);
					if(fusion.getPoints().size()!=0) {
						System.out.println("Datos de salida");
						fusion.show();
					}
				}
			}
		}
	}
	
	/*
	 * Toma una lista de edificios y devuelve un array formado por las
	 * dos listas que resultan de dividir la lista original
	 * @param: edificios
	 * return [edificios1, edificios2]
	 */
	private static List<List<Building>> split(List<Building> edificios){
		
		int limite = edificios.size() / 2;
		int contador = 1;
		List<Building> edificios1 = new LinkedList<Building>();
		List<Building> edificios2 = new LinkedList<Building>();
		
		Iterator<Building> itEd = edificios.iterator();
		while(itEd.hasNext()) {
			
			if (contador<=limite) {
				edificios1.add(itEd.next());
			} else {
				edificios2.add(itEd.next());
			}
			
			contador++;
		}
		
		List<List<Building>> sol = new LinkedList<List<Building>>();
		sol.add(edificios1);
		sol.add(edificios2);
		
		return sol;
	}
	
	/*
	 * Toma la ruta donde está el fichero que contiene los edificios y devuelve
	 * una lista con objetos de tipo edificio
	 * @param: ruta del fichero
	 * @return: lista de edificios
	 */
	private static List<Building> buildingListGenerator(String rutaFichero) {
		
		String rutaFicheroEntrada = rutaFichero;
		
		List<Building> edificios = new LinkedList<Building>();
		
		Iterator<List<Integer>> it = Auxiliar.coordenadasEdificios(rutaFicheroEntrada).iterator();
		while(it.hasNext()) {
			edificios.add(new Building(it.next()));
		}
		
		return edificios;
	}
	
	/*
	 * Toma un edificio como parámetro y devuelve su silueta
	 * edificio -> horizonte
	 */
	private static Sky lift(Building edificio) {
		
		Point punto1 = new Point(edificio.start, edificio.height);
		Point punto2 = new Point(edificio.end, 0);
		
		List<Point> puntos = new LinkedList<Point>();
		puntos.add(punto1);
		puntos.add(punto2);
		
		return new Sky(puntos);
	}
	
	/*
	 * Toma dos horizontes y devuelve el horizonte que se genera combinándolos
	 * @param: 	sky1, sky2 Horizontes que se combinarán
	 * 			trace -> indica si debe mostrar las trazas por consola
	 * @return: el horizonte que resulta de combinarlos	
	 */
	private static Sky mergeSky(Sky sky1, Sky sky2, boolean trace) {
		
		Integer uh = 0, ia = 0, ib = 0, cax = 0, cbx = 0, cay = 0, cby = 0;
		Integer nx, nh;
		List<Point> template = new LinkedList<Point>();
		List<Point> pa = sky1.getPoints();
		List<Point> pb = sky2.getPoints();
		Integer lastPointerIndexA = pa.size()-1;
		Integer lastPointerIndexB = pb.size()-1;
		
		while(ia<=lastPointerIndexA && ib<=lastPointerIndexB){
			//get Abscisas
			cax = pa.get(ia).getX();
			cbx = pb.get(ib).getX();
			
			//Abscisas iguales
			if(cax==cbx) {
				nx = cax;
				//get Ordenadas
				cay = pa.get(ia).getY();
				cby = pb.get(ib).getY();
				ia++;
				ib++;
				nh = Math.max(cay, cby);
				if(nh!=uh) {
					uh = nh;
					template.add(new Point(nx,nh));
				}
			//Abscisas distintas
			} else {
				//Abscisa de candidato A menor que abscisa de candidato B
				if(cax<cbx) {
					nx = cax;
					//get Ordenadas
					cay = pa.get(ia).getY();
					nh = Math.max(cay, cby);
					ia++;
					if(nh!=uh) {
						uh=nh;
						template.add(new Point(nx,nh));
					}
				}
				//Abscisa de candidato B menor que abscisa de candidato A
				if(cbx<cax) {
					nx = cbx;
					//get Ordenadas
					cby = pb.get(ib).getY();
					nh = Math.max(cay, cby);
					ib++;
					if(nh!=uh) {
						uh=nh;
						template.add(new Point(nx,nh));
					}
				}
			}
		}
		
		//No quedan puntos en uno de ellos
		while(ia<=lastPointerIndexA) {
			template.add(pa.get(ia));
			ia++;
		}
		while(ib<=lastPointerIndexB) {
			template.add(pb.get(ib));
			ib++;
		}
		
		//Trazas
		if(trace) {
			Sky merged = new Sky(template);
			
			System.out.println("*LLAMADA*");
			System.out.println("*Horizonte 1*");
			sky1.show();
			System.out.println("*Horizonte 2*");
			sky2.show();
			System.out.println("Fusión H1 y H2");
			merged.show();
			System.out.println();
			
			return merged;
		} else {
			return new Sky(template);
		}
	}
	
	// Caso base de la recursión
	private static Sky baseCase(List<Building> buildings, boolean trace) {
		
		List<Point> empty = new LinkedList<Point>();
		Sky clear = new Sky(empty);
		if (buildings.size()==0) {
			return clear;}
		else if (buildings.size()==1) {
			return mergeSky(lift(buildings.get(0)),clear, trace);}
		else {
			return mergeSky(lift(buildings.get(0)),lift(buildings.get(1)),trace);}
	}
	
	//función recursiva
	private static Sky mergeSkyline(List<Building> buildings, boolean trace) {
		if (buildings.size()<2) {
			return baseCase(buildings, trace);}
		else {
			return mergeSky(mergeSkyline(split(buildings).get(0),trace),mergeSkyline(split(buildings).get(1),trace),trace);}
	}
}
