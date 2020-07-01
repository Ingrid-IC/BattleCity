package battlecity;

import java.util.Scanner;

public class Servidor50 {
   TCPServer50 mTcpServer;
   Scanner sc;
   String rpta = "respuesta";
   BattleCity bc;
   Jugador jugadores[];
   int cantJugadores=0;
   
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
                    //bc = new BattleCity(mTcpServer.nrcli);
                    mTcpServer.run();
                }
            }
        ).start();
        
        Thread battlecity = new Thread() {
            @Override
            public void run() {
                bc = new BattleCity();
                bc.nuevoJugador();
                bc.run();
                //System.out.println("Does it work?");
                //Thread.sleep(1000);
                
                //System.out.println("Nope, it doesnt...again.");
            }
        };
        battlecity.sleep(20000);
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
        //bc.run();
        System.out.println("SERVIDOR40 El mensaje:" + llego);
        /*if(llego.contains("juego")){
            cantJugadores = mTcpServer.nrcli;
            jugadores[cantJugadores] = new Jugador();
            
            bc.nuevoJugador(jugadores[mTcpServer.nrcli]);
        }*/
        
        //bc.instruccion(llego);
        //bc.run();
   }
    void ServidorEnvia(String envia){
        if (mTcpServer != null) {
            mTcpServer.sendMessageTCPServer(envia);
        }
   }
}
