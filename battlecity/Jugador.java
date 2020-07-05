package battlecity;

public class Jugador {
	private int posX = 3;
	private int posY = 4;
	private int id;
	private String direccion = "derecha";
	private int desplazamiento = 1;
	boolean vida = true;

	int dimX = Global.dimX;
	int dimY = Global.dimY;
	
	public Jugador(int id){
		this.id = id;
		//this.posX = posX;
		//this.posY = posY;
		//this.dimX = dimX;
		//this.dimY = dimY;
	}
	
	public int positionX(){
		return posX;
	}
	
	public int positionY(){
		return posY;
	}
	public String direccion(){
		return direccion;
	}
	public char id(){
		return (char)(id + '0');
	}
	
	public void actUbicacion(int nuevoX, int nuevoY){
		posX = nuevoX;
		posY = nuevoY;
	}
	
	public void moverArriba(){
		direccion = "arriba";
		posX = posX - desplazamiento;
		if(posX == 2) moverAbajo();
		if((posX == dimX/2 +1) && (posY >= dimY/2) && (posY <= 3*dimY/4)) moverAbajo();
		if((posX == 3*dimX/4 +1) && (posY >= 3*dimY/4 -2) && (posY <= 3*dimY/4)) moverAbajo();
		if((posX == 3*dimX/4 +1) && (posY >= dimY/2 -2) && (posY <= dimY/2)) moverAbajo();
		if((posX == 3*dimX/4 +1) && (posY >= dimY/4 -2) && (posY <= dimY/4)) moverAbajo();
	}

	public void moverAbajo(){
		direccion = "abajo";
		posX = posX + desplazamiento;
		if(posX == dimX-3) moverAbajo();
		if((posX == dimX/2 -2) && (posY >= dimY/2) && (posY <= 3*dimY/4)) moverArriba();
		if((posX == dimX/4 -1) && (posY >= 3*dimY/4 -2) && (posY <= 3*dimY/4)) moverArriba();
		if((posX == dimX/4 -1) && (posY >= dimY/2 -2) && (posY <= dimY/2)) moverArriba();
		if((posX == dimX/4 -1) && (posY >= dimY/4 -2) && (posY <= dimY/4)) moverArriba();
	}

	public void moverDerecha(){
		direccion = "derecha";
		posY = posY + desplazamiento;
		if(posY == dimY-4) moverIzquierda();
		if((posX >= dimX/4) && (posX <= 3*dimX/4) && (posY == dimY/4 -3)) moverIzquierda();
		if((posX >= dimX/4) && (posX <= 3*dimX/4) && (posY == dimY/2 -3)) moverIzquierda();
		if((posX >= dimX/4) && (posX <= 3*dimX/4) && (posY == 3*dimY/4 -3)) moverIzquierda();
	}

	public void moverIzquierda(){
		direccion = "izquierda";
		posY = posY - desplazamiento;
		if(posY == 3) moverDerecha();
		if((posX >= dimX/4) && (posX <= 3*dimX/4) && (posY == dimY/4 +1)) moverDerecha();
		if((posX >= dimX/4) && (posX <= 3*dimX/4) && (posY == dimY/2 +1)) moverDerecha();
		if((posX >= dimX/4) && (posX <= 3*dimX/4) && (posY == 3*dimY/4 +1)) moverDerecha();
	}
}
