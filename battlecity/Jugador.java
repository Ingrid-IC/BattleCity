package battlecity;

public class Jugador {
	int id;
	int posX = 3;
	int posY = 4;
	int dimX = 35;
	int dimY = 80;
	String direccion = "derecha";
	
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
	
	public void actUbicacion(int nuevoX, int nuevoY){
		posX = nuevoX;
		posY = nuevoY;
	}
	
	public void moverArriba(){
		posX = posX-1;
		if(posX == 2) posX++;
		direccion = "arriba";
	}
	public void moverAbajo(){
		posX = posX+1;
		if(posX == dimX-3) posX--;
		direccion = "abajo";
	}
	public void moverDerecha(){
		posY = posY+1;
		if(posY == dimY-4) posY--;
		direccion = "derecha";
	}
	public void moverIzquierda(){
		posY = posY-1;
		if(posY == 3) posY++;
		direccion = "izquierda";
	}
}
