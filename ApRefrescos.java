import java.util.Scanner;
public class ApRefrescos{

	public static void main(String[] args)
	{
		int op,op1;
		boolean salir=false;
		Scanner leer=new Scanner(System.in);
		Stock miStock;
		miStock=new Stock();
		miStock.llenarRieles(); 
		do
		{
			miStock.mostrarPrecio();
			System.out.print("Producto: ");
			op=leer.nextInt();
			if(op==25565){
				System.out.println("\n\tSeleccione opci�n\n\t1.-Mostrar stock\n\t2.-Rellenar stock\n\t3.-Mostrar monedas\n\t4.-Apagar maquina");
				System.out.print("Opci�n: ");
				op1=leer.nextInt();
				System.out.println();
				switch(op1){
					case 1:
						miStock.mostrarStock();
						System.out.println("Regresando al men� principal...\n");
						break;
					case 2:
						miStock.llenarRieles();
						System.out.println("Regresando al men� principal...\n");
						break;
					case 3:
						miStock.mostrarMonedas();
						System.out.println("Regresando al men� principal...\n");
						break;
					case 4:
						salir=true;
						System.out.println("Apagando maquina");
						break;
					default:
						System.out.println("Opci�n no valida");
				}
			} else if(op>=1 && op<=miStock.getMaxProductos()){
				if(!miStock.despachar(op-1))
					System.out.println("\nProducto agotado\n");
				else
					miStock.cobrar(op-1);
					
					
					
			} else {
				System.out.println("\nProducto no existente\n");
			}
			
		}while(!salir);
		
		
	}
}