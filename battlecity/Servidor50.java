package battlecity;

import java.util.Scanner;

public class Servidor50 {
   TCPServer50 mTcpServer;
   Scanner sc;
   BattleCity bc;
   Jugador jugadores[];
   int cantJugadores=0;
   String mapa;
   
   public static void main(String[] args) throws InterruptedException {
	   Servidor50 objser = new Servidor50();
	   objser.iniciar();
   }
   
   void iniciar() throws InterruptedException{
		new Thread(
			new Runnable() {
				@Override
				public void run() {
					mTcpServer = new TCPServer50(
						new TCPServer50.OnMessageReceived(){
							@Override
							public void messageReceived(String message){
								ServidorRecibe(message);
								//ServidorEnvia(message);
							}
						}
					);
					mTcpServer.run();
				}
			}
		).start();
		
		Thread battlecity = new Thread() {
			@Override
			public void run() {
				bc = new BattleCity();
				bc.nuevoJugador();

				while(bc.Vida()){
					bc.moverEnemigos();
					bc.choqueEnemigos();
					mapa = bc.mapa();
					try{
						Thread.sleep(900);
					} 
					catch(InterruptedException e){
						 // this part is executed when an exception (in this example InterruptedException) occurs
					}
					ServidorEnvia(mapa);
				}
			}
		};
		battlecity.sleep(12000);
		battlecity.start();
		
		//-----------------

		String salir = "n";
		sc = new Scanner(System.in);
		System.out.println("Servidor bandera 01");
		while( !salir.equals("s")){
			salir = sc.nextLine();
			ServidorEnvia(salir);
		}
		System.out.println("Servidor bandera 02"); 
   
	}
	void ServidorRecibe(String llego){

		if(llego.contains("juego"))  bc.nuevoJugador();
		if(llego.contentEquals("w")) bc.instruccion("arriba");
		if(llego.contentEquals("a")) bc.instruccion("izquierda");
		if(llego.contentEquals("d")) bc.instruccion("derecha");
		if(llego.contentEquals("ss")) bc.instruccion("abajo");
		System.out.println("SERVIDOR40 El mensaje:" + llego);
		/*if(llego.contains("juego")){
			cantJugadores = mTcpServer.nrcli;
			jugadores[cantJugadores] = new Jugador();
			
			bc.nuevoJugador(jugadores[mTcpServer.nrcli]);
		}*/
   }
	void ServidorEnvia(String envia){
		if (mTcpServer != null) {
			mTcpServer.sendMessageTCPServer(envia);
		}
    }
}
