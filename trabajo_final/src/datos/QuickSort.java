package datos;


public class QuickSort {
	//FUNCION QUE DEFINE AL ALGORITMO DE ORDENAMIENTO	
	public static void quickSort (int[] array, int izq, int der) { //por parametro ingresaremos el array, y dos datos de izquierda a derecha 
		if (izq < der) {										  //que seran las "puntas" del array
			int aux = partition(array, izq, der); //en un auxiliar se guarda la pequeña seccion de array que se ordenará
			quickSort (array, izq, aux - 1);	 //se llama recursivamente a nuestra función para seguir analizando el array,
			quickSort(array, aux + 1, der);		//primero de izq hasta el pivote y luego desde pivote +1 hasta der 
		}
	}

	private static int partition(int[] array, int izq, int der) { //funcion que realiza el ordenamiento de las particiones
		int pivote = mediana(array, izq, der); //selecciona el elemento medio como pivote
		int i = izq - 1;		//inicia i
		for (int j = izq; j < der; j++) { //se recorre el array a ordenar
			if (array[j] < pivote) {    //si el elemento en la posición j es menor al pivote
				i++;				   //la variable i incrementa en 1
				int temp = array[i]; 
				array[i] = array[j]; //se intercambia array [i] con array [y] con el uso de un auxiliar
				array[j] = temp;	
			}
		}
		int temp = array[i + 1];	//se intercambia el temporal con el pivote
		array[i + 1] = array[der];//tambien haciendo uso de un auxiliar
		array[der] = temp;
		return i + 1; //se retorna el indice donde se encuentra el pivote despues de la partición
	}
	
	private static int mediana (int[] array, int izq, int der) { //esta funcion selecionará al pivote como la mediana de 3 elementos
		int medio = izq + (der - izq) / 2;
		//ordenamos los 3 elementos claves del array
		if (array[izq] > array [medio]) {
			int temp = array[izq];
			array[izq] = array[medio];
			array[medio] = temp;
		}
		if (array[izq] > array[der]) {
			int temp = array[izq];
			array[izq] = array[der];
			array[der] = temp;
		}
		if (array[medio] > array[der]) {
			int temp = array[medio];
			array[medio] = array[der];
			array[der] = temp;
		}
		//array en la posición medio se convierte en el pivote
		int temp = array[medio];
		array[medio] = array[der];
		array[der] = temp;
		return array[der];
	}
	
	
	
}
