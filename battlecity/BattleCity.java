package battlecity;
import java.util.*;

public final class BattleCity {
	boolean vida = true;
	int nrclient;
	int dimX = 35;
	int dimY = 80;
	
	char mapa[][] = new char[dimX][dimY];
	
	int posX = 3;
	int posY = 4;
	char muro = (char)177;
	
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
				System.out.print("\033[H\033[2J");
				System.out.flush();
				mostrarMapa();
				moverEnemigos();
				choqueEnemigos();
				try{
					Thread.sleep(1000);
				} 
				catch(InterruptedException e){
					 // this part is executed when an exception (in this example InterruptedException) occurs
				}
			//}else System.out.println("=======Perdiste=======");
		}
		System.out.println("=======Perdiste=======");
	}
	
	public void llenarMapa(){
		for(int i=0;i<dimX;i++){
			for(int j=0;j<dimY;j++){
				mapa[i][j] = ' ';
				
				mapa[0][j] = muro;
				mapa[1][j] = muro;
				
				mapa[i][0] = muro;
				mapa[i][1] = muro;
				mapa[i][2] = muro;
				
				mapa[i][dimY-1] = muro;
				mapa[i][dimY-2] = muro;
				mapa[i][dimY-3] = muro;

				mapa[dimX-1][j] = muro;
				mapa[dimX-2][j] = muro;
				
				mapa[dimX-1][dimY-1] = muro;
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
		ubicarJugador();
	}
	
	public void ubicarJugador(){
		int posJX = jugador.positionX();
		int posJY = jugador.positionY();
		String direc = jugador.direccion();

		mapa[posJX-1][posJY-1] = 'o';	//izquierda superior
		mapa[posJX-1][posJY] =   'o';		//medio superior
		mapa[posJX-1][posJY+1] = 'o';	//derecha superior

		mapa[posJX][posJY-1] = 'o';		//izquierda
		mapa[posJX][posJY] =   '+';		//medio
		mapa[posJX][posJY+1] = 'o';		//derecha

		mapa[posJX+1][posJY-1] = 'o';	//izquierda inferior
		mapa[posJX+1][posJY] =   'o';		//medio inferior
		mapa[posJX+1][posJY+1] = 'o';	//derecha inferior
		
		if(direc == "derecha")   mapa[posJX][posJY+1] = '+';		//derecha
		if(direc == "arriba")    mapa[posJX-1][posJY] = '+';		//medio superior
		if(direc == "izquierda") mapa[posJX][posJY-1] = '+';		//izquierda
		if(direc == "abajo")     mapa[posJX+1][posJY] = '+';		//medio inferior
		//mapa[posJX][posJY] = 'x';
	}
	public void ubicarEnemigo(int posEX,int posEY){
		mapa[posEX-1][posEY-1] = '*';
		mapa[posEX-1][posEY] = '*';
		mapa[posEX-1][posEY+1] = '*';

		mapa[posEX][posEY-1] = '*';
		mapa[posEX][posEY] = '+';
		mapa[posEX][posEY+1] = '+';

		mapa[posEX+1][posEY-1] = '*';
		mapa[posEX+1][posEY] = '*';
		mapa[posEX+1][posEY+1] = '*';
	}

	public void limpiarPos(int posx, int posy){
		//mapa[posx][posy] = ' ';
		mapa[posx-1][posy-1] = ' ';
		mapa[posx-1][posy] 	 = ' ';
		mapa[posx-1][posy+1] = ' ';

		mapa[posx][posy-1] = ' ';
		mapa[posx][posy]   = ' ';
		mapa[posx][posy+1] = ' ';

		mapa[posx+1][posy-1] = ' ';
		mapa[posx+1][posy]   = ' ';
		mapa[posx+1][posy+1] = ' ';
	}
	
	public void instruccion(String inst){
		//mapa[posX][posY] = ' ';
		limpiarPos(jugador.positionX(),jugador.positionY());
		if("arriba".equals(inst))    jugador.moverArriba();//posX = posX -1;
		if("abajo".equals(inst))     jugador.moverAbajo();//posX = posX +1;
		if("derecha".equals(inst))   jugador.moverDerecha();//posY = posY+1;
		if("izquierda".equals(inst)) jugador.moverIzquierda();//posY = posY -1;
		
		ubicarJugador();
		//if(mapa[jugador.positionX()][jugador.positionY()] != 'E') ubicarJugador();
		//else vida = false;//System.out.println("=======Perdiste=======");
	}

	public void enemigos(){
		int posEX;
		int posEY;
		for (int i=0; i<enemigos.length; i++){
			posEX = (int) (Math.random()*(dimX-6) +3);
			posEY = (int) (Math.random()*(dimY-8) +4);
			enemigos[i] = new Enemigos(posEX,posEY,dimX,dimY);
			//mapa[posEX][posEY] = 'E';
			ubicarEnemigo(posEX,posEY);
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
			//mapa[nuevPosX][nuevPosY] = 'E';
			ubicarEnemigo(nuevPosX,nuevPosY);
		}
	}
	public void choqueEnemigos(){
		int posEX;
		int posEY;
		int posJX = jugador.positionX();
		int posJY = jugador.positionY();
		double dist;
		
		for(int p=0; p<cantEnemigos; p++){
			posEX = enemigos[p].positionX();
			posEY = enemigos[p].positionY();
			
			 dist = Math.pow(posJX-posEX,2)+Math.pow(posJY-posEY,2);
			 dist = Math.sqrt(dist);

			 if(dist < 3) vida = false;

			//if((posEX==posJx) && (posEY==posJy)) {
			//	mapa[posEX][posEY] = 'E';
			//	vida = false;
			//}
		}
	}
}