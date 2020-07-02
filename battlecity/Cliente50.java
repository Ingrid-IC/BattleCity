package battlecity;

import java.util.Scanner;
import battlecity.TCPClient50;

class Cliente50{
    TCPClient50 mTcpClient;
    Scanner sc;
    String indication = "tarea";
    String sumatoria = "suma";
    double rpta;
    
    public static void main(String[] args)  {
        Cliente50 objcli = new Cliente50();
        objcli.iniciar();
    }
    void iniciar(){
       new Thread(
            new Runnable() {

                @Override
                public void run() {
                    mTcpClient = new TCPClient50("192.168.0.106",
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
        while( !salir.equals("s")){
            salir = sc.nextLine();
            ClienteEnvia(salir);
        }
        System.out.println("Cliente bandera 02");
    
    }
    void ClienteRecibe(String llego){
        System.out.println("CLINTE50 El mensaje::" + llego);
    }
    void ClienteEnvia(String envia){
        if (mTcpClient != null) {
            //if(envia.contains("juego")){
            //    Jugador jugador = new Jugador();
            //}
            mTcpClient.sendMessage(envia);
        }
    }

}
