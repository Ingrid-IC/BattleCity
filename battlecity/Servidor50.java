package battlecity;

import java.util.Scanner;

public class Servidor50 {
   TCPServer50 mTcpServer;
   Scanner sc;
   BattleCity bc;
   int cantJugadores = 5;
   Jugador jugadores[] = new Jugador[cantJugadores];
   int jugadorId = 0;
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
								synchronized(this){
									ServidorRecibe(message);
								}
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
				jugadores[jugadorId] = new Jugador(jugadorId);
				bc.ubicarJugador(jugadores[jugadorId]);
				// bc.nuevoJugador(jugadorId);

				while(bc.Vida()){
					bc.moverEnemigos();
					//bc.choqueEnemigos();
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
		int id;
		String inst;
		if(llego!=null){
			if(llego.contains("juego")){
				jugadorId++;
				jugadores[jugadorId] = new Jugador(jugadorId);
				bc.ubicarJugador(jugadores[jugadorId]);
				//bc.nuevoJugador(jugadorId);
			}else{
				id = Integer.parseInt(llego.substring(0,1));
				inst = llego.substring(1);
				if(inst.contentEquals("w")) bc.instruccion(jugadores[id],"arriba");
				else if(inst.contentEquals("a")) bc.instruccion(jugadores[id],"izquierda");
				else if(inst.contentEquals("d")) bc.instruccion(jugadores[id],"derecha");
				else if(inst.contentEquals("ss")) bc.instruccion(jugadores[id],"abajo");
				else System.out.println("Comando no reconocido");
			}
			System.out.println("SERVIDOR40 El mensaje:" + llego);
		}
		
   }
   
	void ServidorEnvia(String envia){
		if (mTcpServer != null) {
			mTcpServer.sendMessageTCPServer(envia);
		}
	}
}
