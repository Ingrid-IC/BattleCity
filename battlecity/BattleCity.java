package battlecity;
import java.util.*;

public final class BattleCity {
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_RESET = "\u001B[0m";
	boolean vida = true;
	int nrclient;
	int dimX = 35;
	int dimY = 80;

	
	char mapa[][] = new char[dimX][dimY];
	
	//int posX = 3;
	//int posY = 4;
	char muro = (char)177;
	
	int cantEnemigos = 8;
	Enemigos enemigos[] = new Enemigos[cantEnemigos];
	//Jugador jugador;
	//Jugador jugadores[];

	public BattleCity(){
		//this.nrclient = nrclient;
		llenarMapa();
	}
	public boolean Vida(){
		return vida;
	}
	
	public void run(){
		while(vida){
			System.out.print("\033[H\033[2J");
			System.out.flush();
			mostrarMapa();
			moverEnemigos();
			//choqueEnemigos();
			try{
				Thread.sleep(1000);
			} 
			catch(InterruptedException e){
				 // this part is executed when an exception (in this example InterruptedException) occurs
			}
		}
		System.out.println("=======Perdiste=======");
	}

	public String mapa(){
		String nuevoMapa = "";
		for(int i=0;i<dimX;i++){
			for(int j=0;j<dimY;j++){
				nuevoMapa += mapa[i][j];
			}
		}
		return nuevoMapa;
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
				//if(mapa[i][j] == muro) System.out.print(ANSI_RED+mapa[i][j]+ANSI_RESET);
				//else  if((mapa[i][j] == 'o') || (mapa[i][j] == '+')) System.out.print(ANSI_WHITE+mapa[i][j]+ANSI_RESET);
				//else  if((mapa[i][j] == 'O') || (mapa[i][j] == '*')) System.out.print(ANSI_BLUE+mapa[i][j]+ANSI_RESET);
				//else 
				System.out.print(mapa[i][j]);
			System.out.print("\n");
		}
	}
	
	/*public void nuevoJugador(int jugadorId){
		//jugadores[jugadorId] = new Jugador(jugadorId);
		//jugador = new Jugador(jugadorId,posX,posY,dimX,dimY);
		ubicarJugador(jugadores[jugadorId]);
	}*/
	
	public void ubicarJugador(Jugador jugador){
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

	public void ubicarEnemigo(Enemigos enemigo){
		int posEX = enemigo.positionX();
		int posEY = enemigo.positionY();
		String direc = enemigo.direccion();

		mapa[posEX-1][posEY-1] = 'O';
		mapa[posEX-1][posEY] =   'O';
		mapa[posEX-1][posEY+1] = 'O';

		mapa[posEX][posEY-1] = 'O';
		mapa[posEX][posEY] =   '*';
		mapa[posEX][posEY+1] = 'O';

		mapa[posEX+1][posEY-1] = 'O';
		mapa[posEX+1][posEY] =   'O';
		mapa[posEX+1][posEY+1] = 'O';

		if(direc == "derecha")   mapa[posEX][posEY+1] = '*';		//derecha
		if(direc == "arriba")    mapa[posEX-1][posEY] = '*';		//medio superior
		if(direc == "izquierda") mapa[posEX][posEY-1] = '*';		//izquierda
		if(direc == "abajo")     mapa[posEX+1][posEY] = '*';		//medio inferior
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
	
	public void instruccion(Jugador jugador,String inst){
		//mapa[posX][posY] = ' ';
		limpiarPos(jugador.positionX(),jugador.positionY());
		if("arriba".equals(inst))    jugador.moverArriba();//posX = posX -1;
		if("abajo".equals(inst))     jugador.moverAbajo();//posX = posX +1;
		if("derecha".equals(inst))   jugador.moverDerecha();//posY = posY+1;
		if("izquierda".equals(inst)) jugador.moverIzquierda();//posY = posY -1;
		
		ubicarJugador(jugador);
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
			ubicarEnemigo(enemigos[i]);
		}
	}
	
	public void moverEnemigos(){
		int nuevPosX;
		int nuevPosY;
		for(int p=0; p<cantEnemigos; p++){
			limpiarPos(enemigos[p].positionX(),enemigos[p].positionY());
			enemigos[p].mover();
			//nuevPosX = enemigos[p].positionX();
			//nuevPosY = enemigos[p].positionY();
			ubicarEnemigo(enemigos[p]);
		}
	}

/*
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
	}*/
}