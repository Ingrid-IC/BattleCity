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
   boolean vida = true;
   
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
		
		try{
			Thread.sleep(1000);
			jugadorId = mTcpServer.nrcli;
		} 
		catch(InterruptedException e){
			 // this part is executed when an exception (in this example InterruptedException) occurs
		
		}
		
		Thread battlecity = new Thread() {
			@Override
			public void run() {
				bc = new BattleCity();
				jugadores[jugadorId] = new Jugador(jugadorId);
				bc.ubicarJugador(jugadores[jugadorId]);

				while(Vida()){
                                        
					bc.moverEnemigos();
                                        vidaJugadores(); //cambio
                                        mapa = bc.mapa();
					for (int i= 0; i <= jugadorId; i++) {
                                             bc.choqueEnemigos(jugadores[jugadorId]);
                                             //bc.moverBalasJugadores(jugadores[jugadorId]);
                                             /*for(int j = 0; j< bc.cantEnemigos;j++){
                                                bc.choqueDisparoEnemigo(bc.enemigos[j].bala, jugadores[jugadorId]);
                                            }
                                            */
                                            
                                        }
	
					try{
						Thread.sleep(800);
					} 
					catch(InterruptedException e){
						 // this part is executed when an exception (in this example InterruptedException) occurs
					}
					ServidorEnvia(mapa);
				}
			}
		};
		//battlecity.sleep(12000);
		battlecity.start();
		
		//-----------------

		String salir = "n";
		sc = new Scanner(System.in);
		System.out.println("Servidor bandera 01");
		while( !salir.equals("q")){
			salir = sc.nextLine();
			//ServidorEnvia(salir);
		}
		System.out.println("Servidor bandera 02"); 
   
	}

	void ServidorRecibe(String llego){
		int id;
		String inst;
		if(llego.contains("juego")){
			jugadorId++;
			jugadores[jugadorId] = new Jugador(jugadorId);
			bc.ubicarJugador(jugadores[jugadorId]);
			//bc.nuevoJugador(jugadorId);
		}else{
			try{
				id = Integer.parseInt(llego.substring(0,1));
				inst = llego.substring(1);
				if(inst.contentEquals("w")) bc.instruccion(jugadores[id],"arriba");
				else if(inst.contentEquals("a")) bc.instruccion(jugadores[id],"izquierda");
				else if(inst.contentEquals("d")) bc.instruccion(jugadores[id],"derecha");
				else if(inst.contentEquals("s")) bc.instruccion(jugadores[id],"abajo");
                                else if(inst.contentEquals("t")) bc.instruccion(jugadores[id], "disparar");
				else System.out.println("Comando no reconocido");
			}
			catch(NumberFormatException e){
				// bla
			}
		}
		System.out.println("SERVIDOR40 El mensaje:" + llego);
   }
   
	void ServidorEnvia(String envia){
		if (mTcpServer != null) {
			mTcpServer.sendMessageTCPServer(envia);
		}
	}

	boolean Vida(){
		int count = 0;
		for(int i = 0; i<= jugadorId; i++)
			if(jugadores[i].vida == true) count++;
                        else bc.eliminarJugador(jugadores[i]);
		if(count == 0) vida = false;
		return vida;
	}
        //cambio
	void vidaJugadores(){
		for (int i= 0; i <= jugadorId; i++)
			bc.choqueEnemigos(jugadores[jugadorId]);
	}
}
