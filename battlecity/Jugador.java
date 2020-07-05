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
		posX = posX - desplazamiento;
		if(posX == 2) moverAbajo();
		direccion = "arriba";
	}
	public void moverAbajo(){
		posX = posX + desplazamiento;
		if(posX == dimX-3) moverAbajo();
		direccion = "abajo";
	}
	public void moverDerecha(){
		posY = posY + desplazamiento;
		if(posY == dimY-4) moverIzquierda();
		direccion = "derecha";
	}
	public void moverIzquierda(){
		posY = posY - desplazamiento;
		if(posY == 3) moverDerecha();
		direccion = "izquierda";
	}
}
