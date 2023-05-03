package programacion3.parcial2_Punto4;

public class Hilo1 extends Thread{
	
	int[] arreglo = {4,3,12,55,30};
	int num_Mayor;
	int inicio= 0;
	int fin= arreglo.length-1;
	int num=0;
	
	@Override
	public void run(){
		num_Mayor = recorrerArreglo(arreglo, inicio, fin, num);
		System.out.println("El numero mayor es: "+num_Mayor);
		
	}
	private static int recorrerArreglo(int[] arreglo, int inicio, int fin, int num) {
		if(inicio> fin){
			return num;
		}else{
			if(arreglo[inicio]>num){
				num = arreglo[inicio];
				inicio ++;
				
			}else{
				inicio ++;
			}
		}
		return recorrerArreglo(arreglo, inicio, fin, num);
	}
	

}
