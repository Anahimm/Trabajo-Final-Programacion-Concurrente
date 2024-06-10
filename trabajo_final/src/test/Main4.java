package test;

import datos.Array;    //se importan las clases donde se desarrollaron las funciones
import datos.QuickSort;
import java.util.Arrays;

public class Main4 implements Runnable{ 
	private int inicio;					//representa el inicio del array
	private int fin;					//final del array
	private int idHilo;					
	public static int array1[];			//declaramos los arrays
	public static int array2[];
	
	public Main4(int inicio, int fin, int idHilo) {
		super();
		this.inicio = inicio;
		this.fin = fin;
		this.idHilo = idHilo;
	}



	@Override
	public void run() { //en el run desarrollamos las tareas que tendran los hilos
		int arrayCopiaConcurrente[] = Arrays.copyOf(array1, array1.length); //primero copiamos el array para facilidad de trabajo
		QuickSort.quickSort(arrayCopiaConcurrente, inicio, fin -1); //se produce el ordenamiento
		System.out.println("El hilo "+idHilo+" termino de ordenar desde la posición "+inicio+ " a la posición "+(fin - 1)+"\n");
		

	}
	
	
	public static void main(String[] args) {
	
		Runtime runtime = Runtime.getRuntime();	
		int numeroNucleos = runtime.availableProcessors(); //La cantidad de hilos es igual a la cantidad de procesadores lógicos disponibles en mi PC
		
		array1 = Array.ArrayAleatorio(50000, 1, 20000); //se declaran la longuitud y rango 
		array2 = Array.ArrayDescendente(50000);		//para ambos arrays
		
		int n = array1.length;
		int parte = (int)n / numeroNucleos; //cantidad de "partes" en las que se repartira el array entre los hilos
		double tiempoInicial;
		double tiempoFinal;
		
		//VERSION SECUENCIAL
		tiempoInicial = System.nanoTime(); //comienzo del contador 
		int arrayCopiaSecuencial[] = Arrays.copyOf(array1, array1.length);
		QuickSort.quickSort(arrayCopiaSecuencial, 0, array1.length -1);
		tiempoFinal = System.nanoTime() - tiempoInicial; //finaliza el contador
		System.out.println("La ejecucion secuencial de quick sort demoró "+tiempoFinal/1000+ " microsegundos\n"); 
		
		
		
		
		//VERSION CONCURRENTE
		tiempoInicial = System.nanoTime();
		Thread[] hilos = new Thread[numeroNucleos];
		for (int i = 0; i < numeroNucleos; i++) { //en este for se reparten las partes en las que cada hilo trabajará
			int inicio = i * parte;
			int fin;
			if (i == numeroNucleos -1) {
				fin = n;
			}else {
				fin = inicio + parte;
			}
			hilos[i] = new Thread(new Main4(inicio, fin, i));	
		}
		
		
		for (int i = 0; i < numeroNucleos; i++) { //inicio de los hilos
			hilos[i].start();
		}
		
		for (int i = 0; i < numeroNucleos; i++) {
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		tiempoFinal = System.nanoTime() - tiempoInicial;
		System.out.println("La ejecucion concurrente de quick sort demoró "+tiempoFinal/1000+ " microsegundos\n"); 
		
		
		/*	//VERSION DESCENDENTE SECUENCIAL
		tiempoInicial = System.nanoTime(); //comienzo del contador 
		int arrayCopiaSecuencial2[] = Arrays.copyOf(array2, array2.length);
		QuickSort.quickSort(arrayCopiaSecuencial2, 0, array2.length -1);
		tiempoFinal = System.nanoTime() - tiempoInicial; //finaliza el contador
		System.out.println("La ejecucion secuencial de quick sort demoró "+tiempoFinal/1000+ " microsegundos\n"); 
		
		
		
		
		//VERSION DESCENDENTE CONCURRENTE
		tiempoInicial = System.nanoTime();
		Thread[] hilos2 = new Thread[numeroNucleos];
		for (int i = 0; i < numeroNucleos; i++) { //en este for se reparten las partes en las que cada hilo trabajará
			int inicio = i * parte;
			int fin;
			if (i == numeroNucleos -1) {
				fin = n;
			}else {
				fin = inicio + parte;
			}
			hilos2[i] = new Thread(new Main4(inicio, fin, i));	
		}
		
		
		for (int i = 0; i < numeroNucleos; i++) { //inicio de los hilos
			hilos2[i].start();
		}
		
		for (int i = 0; i < numeroNucleos; i++) {
			try {
				hilos2[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		tiempoFinal = System.nanoTime() - tiempoInicial;
		System.out.println("La ejecucion concurrente de quick sort demoró "+tiempoFinal/1000+ " microsegundos\n"); 
		
		*/
		

	}

}
