package battlecity;

import java.util.Scanner;
import battlecity.TCPClient50;

class Cliente50{
    TCPClient50 mTcpClient;
    Scanner sc;
    double rpta;
    int dimX = 35;
    int dimY = 80;

    
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
        for (int x=0; x<mapa.length(); x=x+80){
            for(int i=x; i<dimY+x; i++){
                System.out.print(mapa.charAt(i));
            }
            System.out.print("\n");
        }
    }
}
