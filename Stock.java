import java.util.Scanner;

public class Stock{
	
	private int maxProductos=8;
	private int capacidadRiel=10;
	private int marxMonedas=50;
	private int corte;
	private int tipoMonedas=4;
	private Refresco rieles[]=new Refresco[maxProductos];
	private Dinero monedero[]=new Dinero[tipoMonedas];
	private Dinero ranura[]=new Dinero[tipoMonedas];
	private Dinero cambioBandeja[]=new Dinero[tipoMonedas];
	
	public Stock(){
		rieles[0]=new Refresco("Coca Cola      ",13);
		rieles[1]=new Refresco("Sprite         ",12);
		rieles[2]=new Refresco("Agua Mineral   ",10);
		rieles[3]=new Refresco("Fanta          ",12);
		rieles[4]=new Refresco("Coca Light     ",13);
		rieles[5]=new Refresco("Coca Zero      ",12);
		rieles[6]=new Refresco("Coca sin azucar",10);
		rieles[7]=new Refresco("Limon y nada   ",12);	

		monedero[0]=new Dinero("Un Peso",1,0);
		monedero[1]=new Dinero("Dos pesos",2,0);
		monedero[2]=new Dinero("Cinco pesos",5,0);
		monedero[3]=new Dinero("Diez pesos",10,5);
		
		for(int i=0; i<tipoMonedas;i++){
		corte += monedero[i].getDenominacion()*monedero[i].getCantidad();
		}
	}
	
	public boolean despachar(int indice){
		
		if(rieles[indice].getCant()>0){
			rieles[indice].setCant(rieles[indice].getCant()-1);
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean cobrar(int indice){
		Scanner leer = new Scanner(System.in);
		
		int moneda,saldo,deuda,saldoAux;
		
		
		saldo=0;
		deuda=rieles[indice].getPrecio();
		if(monedero[0].getCantidad()<marxMonedas || monedero[1].getCantidad()<marxMonedas || monedero[2].getCantidad()<marxMonedas || monedero[3].getCantidad()<marxMonedas){
			do{
				System.out.println("\nA pagar: "+(deuda-saldo));
				System.out.println("\nSeleccione la moneda a ingresar.");
				for(int i=0; i<tipoMonedas;i++){
					System.out.print((i+1)+") "+monedero[i].getNombre()+"\t");
				}
				System.out.print("5)Cancelar\tOpci�n: ");
				
				moneda = leer.nextInt();
				
				switch(moneda){
					case 1:
						if(monedero[0].getCantidad()<marxMonedas){
							saldo+=monedero[0].getDenominacion();
							monedero[0].setCantidad(monedero[0].getCantidad()+1);
						} else
							System.out.println("Monedero lleno, pruebe con otra moneda o cancele la operaci�n.");
						break;
					case 2:
						if(monedero[1].getCantidad()<marxMonedas){
							saldo+=monedero[1].getDenominacion();
							monedero[1].setCantidad(monedero[1].getCantidad()+1);
						} else
							System.out.println("Monedero lleno, pruebe con otra moneda o cancele la operaci�n.");
						break;
					case 3:
						if(monedero[2].getCantidad()<marxMonedas){
							saldo+=monedero[2].getDenominacion();
							monedero[2].setCantidad(monedero[2].getCantidad()+1);
						} else
							System.out.println("Monedero lleno, pruebe con otra moneda o cancele la operaci�n.");
						break;
					case 4:
						if(monedero[3].getCantidad()<marxMonedas){
							saldo+=monedero[3].getDenominacion();
							monedero[3].setCantidad(monedero[3].getCantidad()+1);
						} else
							System.out.println("Monedero lleno, pruebe con otra moneda o cancele la operaci�n.");
						break;
					case 5:
						rieles[indice].setCant(rieles[indice].getCant()+1);
						darCambio(saldo);
						return false;
					default:
						System.out.println("Moneda no reconocida.");
				}
				
			}while(saldo<deuda);
		}else{
			System.out.println("La maquina est� llena.");
		}
		
		saldoAux=saldo;
		saldo-=deuda;
		
		if(saldo>0)
			if(!darCambio(saldo)){
				darCambio(saldoAux);
				rieles[indice].setCant(rieles[indice].getCant()+1);
				
			}else{
				System.out.println("\nSe ha despachado correctamente 1 "+rieles[indice].getNombre());
			}
		else
			System.out.println("\nSe ha despachado correctamente 1 "+rieles[indice].getNombre());
		return true;
	}
	
	public boolean darCambio(int saldo){
		int cambio[]={0,0,0,0};
		
		//System.out.println("Saldo a cambiar: "+saldo);
		
		do{
			
			/*System.out.println("Saldo a cambiar:"+saldo);
			for(int i=0; i< tipoMonedas; i++){
				System.out.println("["+monedero[i].getDenominacion()+"] "+monedero[i].getCantidad());
			}*/
			
			if(saldo>=monedero[3].getDenominacion() && monedero[3].getCantidad()>0){
				
				saldo-=monedero[3].getDenominacion();
				cambio[3]++;
				monedero[3].setCantidad(monedero[3].getCantidad()-1);
				
			} else if(saldo>=monedero[2].getDenominacion() && monedero[2].getCantidad()>0){
				
				saldo-=monedero[2].getDenominacion();
				cambio[2]++;
				monedero[2].setCantidad(monedero[2].getCantidad()-1);
				
			} else if(saldo>=monedero[1].getDenominacion() && monedero[1].getCantidad()>0){
				
				saldo-=monedero[1].getDenominacion();
				cambio[1]++;
				monedero[1].setCantidad(monedero[1].getCantidad()-1);
				
			} else if(saldo>=monedero[0].getDenominacion() && monedero[0].getCantidad()>0){
				
				saldo-=monedero[0].getDenominacion();
				cambio[0]++;
				monedero[0].setCantidad(monedero[0].getCantidad()-1);
				
			} else if(monedero[2].getCantidad()==0 && monedero[1].getCantidad()==0 && monedero[0].getCantidad()==0){
				
				for(int i=0;i<tipoMonedas;i++)
					monedero[i].setCantidad(monedero[i].getCantidad()+cambio[i]);
				System.out.println("No hay cambio suficiente");
				return false;
				
			} else {
				for(int i=0;i<tipoMonedas;i++)
					monedero[i].setCantidad(monedero[i].getCantidad()+cambio[i]);
				System.out.println("No hay cambio suficiente");
				return false;
			}
			
			
		}while(saldo>0);
		System.out.println("Su cambio es:");
				for(int i=0;i<4;i++){
					System.out.println((i+1)+")"+monedero[i].getNombre()+": "+cambio[i]+"\t");
				}
		return true;
	}
	
	public void llenarRieles(){
		for(int i=0;i<maxProductos;i++)
			rieles[i].setCant(capacidadRiel);			
	}
	
	public void mostrarPrecio(){
		System.out.println("\n");
		for(int i=0;i<maxProductos;i++)
			System.out.println((i+1)+")"+rieles[i].getNombre()+" $"+rieles[i].getPrecio());
	}
	
	public void mostrarStock(){
		for(int i=0;i<maxProductos;i++)
			System.out.println((i+1)+")"+rieles[i].getNombre()+" -> "+rieles[i].getCant());
	}	
	
	public void actualizarCorte(){
		corte = 0;
		for(int i=0; i<tipoMonedas;i++){
			corte += monedero[i].getDenominacion()*monedero[i].getCantidad();
		}
	}
	
	public void mostrarMonedas(){
		for(int i=0; i<tipoMonedas;i++){
			System.out.println(monedero[i].getNombre()+":\t"+monedero[i].getCantidad());
		}
		actualizarCorte();
		System.out.println("La cantidad total de dinero es: $"+corte);
	}
	
	public int getMaxProductos(){
		return maxProductos;
	}
	
	public void setMaxProductos(int maxProductos){
		this.maxProductos=maxProductos;
	}
	
}








