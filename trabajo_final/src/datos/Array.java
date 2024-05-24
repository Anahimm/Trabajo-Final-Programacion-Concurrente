package datos;

import java.util.Random;

public class Array {
	
	public static int[] ArrayAleatorio (int n, int min, int max) { //funcion que recibe por parametro la longuitud y el rango 
		int[] array = new int [n];									//en el que se creara el array
		Random random = new Random();
		for (int i = 0; i < n; i++) {
			array[i] = random.nextInt(max - min + 1)+ min;
		}
		return array;
	}

	public static int[] ArrayDescendente (int n) { //esta funcion solo recibe el largo por parametro
		int[] array = new int [n];
		for (int i = 0; i < n; i++) {
			array[i] = n - 1 - i; //carga el vector con numeros ordenados de forma descendente desde n hasta 0
		}
		return array;
	}
	
	public static void mostrarArray(int[] array) { //muestra el array, en el main no es utilizado pero fue utilizado durante la creacion 
		System.out.println("\t--------------------------\n");	//del proyecto para probar el correcto funcionamiento de las 
		for (int num : array) {									//demas funciones
			System.out.println(num+" - ");
		}
	}
	
	
	
	
}
