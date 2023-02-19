package repaso.conceptos.model;

import repaso.conceptos.interfaces.IVehiculoElectrico;

public class Carro extends Vehiculo implements IVehiculoElectrico{

	@Override
	public void encenderMotorElectrico(String mensaje, int voltaje) {
		System.out.println("Mensaje de notificación: "+ mensaje);
		System.out.println("Voltaje de encendido "+ voltaje);
	}
	
	
	
}
