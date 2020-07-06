package battlecity;

import java.util.Scanner;
import battlecity.TCPClient50;

class Cliente50{
	TCPClient50 mTcpClient;
	Scanner sc;
	double rpta;
	int dimX = Global.dimX;
	int dimY = Global.dimY;
	char muro = Global.muro;

	
	public static void main(String[] args)  {
		Cliente50 objcli = new Cliente50();
		objcli.iniciar();
	}
	void iniciar(){
	   new Thread(
			new Runnable() {

				@Override
				public void run() {
					mTcpClient = new TCPClient50("192.168.1.29",
						new TCPClient50.OnMessageReceived(){
							@Override
							public void messageReceived(String message){
								ClienteRecibe(message);
							}
						}
					);
					mTcpClient.run();                   
				}
			}
		).start();
		//---------------------------
	   
		String salir = "n";
		sc = new Scanner(System.in);
		System.out.println("Cliente bandera 01");
		while( !salir.equals("q")){
			salir = sc.nextLine();
			ClienteEnvia(salir);
		}
		System.out.println("Cliente bandera 02");
	
	}

	void ClienteRecibe(String llego){
		//System.out.println("CLINTE50 El mensaje::" + llego);
		if(llego != null){
			System.out.print("\033[H\033[2J");
			System.out.flush();
			imprimirMapa(llego);
		}
	}

	void ClienteEnvia(String envia){
		if (mTcpClient != null)
			mTcpClient.sendMessage(envia);
	}

	void imprimirMapa(String mapa){
		char caracter;
		for (int x = 0; x < mapa.length(); x = x + dimY){
			for(int i = x; i < dimY + x; i++){
				caracter = mapa.charAt(i);
				if(caracter == muro) System.out.print(Global.ANSI_RED + caracter + Global.ANSI_RESET);
                                else if(caracter == Global.balaascii) System.out.print(Global.ANSI_PURPLE+ caracter +Global.ANSI_RESET);
                                else if(caracter == 'o' || caracter == '+') System.out.print(Global.ANSI_GREEN+ caracter +Global.ANSI_RESET);
				else if(caracter == 'o' || caracter == '+') System.out.print(Global.ANSI_GREEN+ caracter +Global.ANSI_RESET);
				else if((int)caracter>=48 && (int)caracter <= 57) System.out.print(Global.ANSI_GREEN+ caracter +Global.ANSI_RESET);
				else if(caracter == 'O' || caracter == '*') System.out.print(Global.ANSI_BLUE+ caracter+Global.ANSI_RESET);
				else System.out.print(caracter);
			}
			System.out.print("\n");
		}
	}
}
