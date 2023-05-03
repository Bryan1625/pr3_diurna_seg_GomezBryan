package programacion3.parcial2_Punto4;

public class Hilo2 extends Thread{
	int[][] matriz = {{ 3, 4, 10},
					  {11, 7, 3},
					  { 6, 4, 1}};
	int i = 0;
	int j = 0;
	double prom_Matriz;
	
	@Override
	public void run(){
		prom_Matriz = promedioMatriz(matriz, i, j)/(matriz.length+0.0);
		System.out.println("El promedio de la matriz en diagonal es: "+prom_Matriz);
	}

	private int promedioMatriz(int[][] matriz, int i, int j) {
		if(i<0 || j>= matriz.length){
			return 0;
			
		}else{ 
			return promedioMatriz(matriz, i+1, j+1)+ matriz[i][j];
		}
	}	
}



