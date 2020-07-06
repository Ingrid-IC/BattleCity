package battlecity;
import java.util.*;

public final class BattleCity {
	int dimX = Global.dimX;
	int dimY = Global.dimY;
	int cantEnemigos = Global.cantEnemigos;
	char muro = Global.muro;
	char bloque = Global.bloque;

	//private boolean vida = true;
	private char mapa[][] = new char[dimX][dimY];
	private Enemigos enemigos[] = new Enemigos[cantEnemigos];

	public BattleCity(){
		llenarMapa();
	}

	/*public boolean Vida(){
		return vida;
	}*/
	/*
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
	}*/

	public String mapa(){
		String nuevoMapa = "";
		for(int i=0;i<dimX;i++){
			for(int j=0;j<dimY;j++){
				nuevoMapa += mapa[i][j];
			}
		}
		return nuevoMapa;
	}
	
	private void llenarMapa(){
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

				if((i >= dimX/4) && (i <= 3*dimX/4) && (j == dimY/4)){
					mapa[i][j-2] = bloque;
					mapa[i][j-1] = bloque;
					mapa[i][j] = bloque;
				}
				if((i >= dimX/4) && (i <= 3*dimX/4) && (j == dimY/2)){
					mapa[i][j-2] = bloque;
					mapa[i][j-1] = bloque;
					mapa[i][j] = bloque;
				}
				if((i >= dimX/4) && (i <= 3*dimX/4) && (j == 3*dimY/4)){
					mapa[i][j-2] = bloque;
					mapa[i][j-1] = bloque;
					mapa[i][j] = bloque;
				} 
				if((i == dimX/2) && (j >= dimY/2) && (j <= 3*dimY/4)){
					mapa[i-1][j] = bloque;
					mapa[i][j] = bloque;
				}
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
	
	
	public void ubicarJugador(Jugador jugador){
		int posJX = jugador.positionX();
		int posJY = jugador.positionY();
		String direc = jugador.direccion();

		mapa[posJX-1][posJY-1] = 'o';	//izquierda superior
		mapa[posJX-1][posJY] =   'o';		//medio superior
		mapa[posJX-1][posJY+1] = 'o';	//derecha superior

		mapa[posJX][posJY-1] = 'o';		//izquierda
		mapa[posJX][posJY] =   jugador.id();		//medio
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

	private void ubicarEnemigo(Enemigos enemigo){
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

	private void limpiarPos(int posx, int posy){
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

	public void eliminarJugador(Jugador jugador){
		int posJX = jugador.positionX();
		int posJY = jugador.positionY();
		limpiarPos(posJX, posJY);
	}
	
	public void instruccion(Jugador jugador,String inst){
		//mapa[posX][posY] = ' ';
		limpiarPos(jugador.positionX(),jugador.positionY());
		if("arriba".equals(inst))    jugador.moverArriba();//posX = posX -1;
		if("abajo".equals(inst))     jugador.moverAbajo();//posX = posX +1;
		if("derecha".equals(inst))   jugador.moverDerecha();//posY = posY+1;
		if("izquierda".equals(inst)) jugador.moverIzquierda();//posY = posY -1;
		
		ubicarJugador(jugador);
	}

	private void enemigos(){
		int posEX;
		int posEY;

		for (int i=0; i<enemigos.length; i++){
			posEX = (int) (Math.random()*(dimX-6) +3);
			posEY = (int) (Math.random()*(dimY-8) +4);
			if(mapa[posEX][posEY] == muro || 
				mapa[posEX-1][posEY-1] == muro || 
				mapa[posEX-1][posEY+1] == muro ||
				mapa[posEX+1][posEY+1] == muro || 
				mapa[posEX+1][posEY-1] == muro) i--;
			else{
				enemigos[i] = new Enemigos(posEX,posEY);
				ubicarEnemigo(enemigos[i]);
			}
		}
	}
	
	public void moverEnemigos(){
		int nuevPosX;
		int nuevPosY;
		for(int p=0; p<cantEnemigos; p++){
			limpiarPos(enemigos[p].positionX(),enemigos[p].positionY());
			enemigos[p].mover();
			ubicarEnemigo(enemigos[p]);
		}
	}

	public void choqueEnemigos(Jugador jugador){
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

			 if(dist < 3) jugador.vida = false;
		}
	}
}