package battlecity;

public final class BattleCity {
    boolean vida = true;
    int nrclient;
    int dimX = 15;
    int dimY = 80;
    
    char mapa[][] = new char[dimX][dimY];
    
    int posX = 2;
    int posY = 2;
    
    int cantEnemigos = 8;
    Enemigos enemigos[] = new Enemigos[cantEnemigos];
    Jugador jugador;

    public BattleCity(){
        //this.nrclient = nrclient;
        llenarMapa();
    }
    
    public void run(){
        while(vida){
            //if(vida){
                mostrarMapa();
                //enemigos();
                moverEnemigos();
                choqueEnemigos();
            //}else System.out.println("=======Perdiste=======");
        }
    }
    
    public void llenarMapa(){
        for(int i=0;i<dimX;i++){
            for(int j=0;j<dimY;j++){
                mapa[i][j] = ' ';
                
                mapa[0][j] = '#';
                mapa[1][j] = '#';
                
                mapa[i][0] = '#';
                mapa[i][1] = '#';
                
                mapa[i][dimY-1] = '#';
                mapa[i][dimY-2] = '#';
                mapa[dimX-1][j] = '#';
                mapa[dimX-2][j] = '#';
                
                mapa[dimX-1][dimY-1] = '#';
            }
        }
        enemigos();
    }
    
    public void mostrarMapa(){
        for(int i=0;i<dimX;i++){
            for(int j=0;j<dimY;j++)
                System.out.print(mapa[i][j]);
            System.out.print("\n");
        }
    }
    
    public void nuevoJugador(){
        jugador = new Jugador(posX,posY,dimX,dimY);
        mapa[posX][posY] = 'x';
        //ubicarJugador(posX,posY);
    }
    
    public void ubicarJugador(){
        int posJX = jugador.positionX();
        int posJY = jugador.positionY();
        mapa[posJX][posJY] = 'x';
    }
    public void limpiarPos(int posx, int posy){
        mapa[posx][posy] = ' ';
    }
    
    public void instruccion(String inst){
        //mapa[posX][posY] = ' ';
        limpiarPos(jugador.positionX(),jugador.positionY());
        if("arriba".equals(inst))    jugador.moverArriba();//posX = posX -1;
        if("abajo".equals(inst))     jugador.moverAbajo();//posX = posX +1;
        if("derecha".equals(inst))   jugador.moverDerecha();//posY = posY+1;
        if("izquierda".equals(inst)) jugador.moverIzquierda();//posY = posY -1;
        
        //if(posX == 1) posX++;
        //if(posY == 1) posY++;
        //if(posX == dimX-2) posX--;
        //if(posY == dimY-2) posY--;
        
        if(mapa[jugador.positionX()][jugador.positionY()] != 'E') ubicarJugador();
        else vida = false;//System.out.println("=======Perdiste=======");
    }
    
    public void enemigos(){
        int posEX;
        int posEY;
        for (int i=0; i<enemigos.length; i++){
            posEX = (int) (Math.random()*(dimX-4) +2);
            posEY = (int) (Math.random()*(dimY-4) +2);
            enemigos[i] = new Enemigos(posEX,posEY,dimX,dimY);
            mapa[posEX][posEY] = 'E';
        }
    }
    
    public void moverEnemigos(){
        int nuevPosX;
        int nuevPosY;
        //int numberP = (int) (Math.random()*5);
        for(int p=0; p<cantEnemigos; p++){
            //nuevPosX = enemigos[p].positionX();
            //nuevPosY = enemigos[p].positionY();
            limpiarPos(enemigos[p].positionX(),enemigos[p].positionY());
            //mapa[nuevPosX][nuevPosY] = ' ';
            enemigos[p].mover();
            nuevPosX = enemigos[p].positionX();
            nuevPosY = enemigos[p].positionY();
            mapa[nuevPosX][nuevPosY] = 'E';
        }
    }
    public void choqueEnemigos(){
        int posEX;
        int posEY;
        int posJx = jugador.positionX();
        int posJy = jugador.positionY();
        
        for(int p=0; p<cantEnemigos; p++){
            posEX = enemigos[p].positionX();
            posEY = enemigos[p].positionY();
            
            if((posEX==posJx) && (posEY==posJy)) {
                mapa[posEX][posEY] = 'E';
                vida = false;
            }
        }
    }
}