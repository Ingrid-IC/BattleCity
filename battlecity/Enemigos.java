package battlecity;

public class Enemigos {
	int posX;
	int posY;
	int dimX;
	int dimY;
	String direccion = "arriba";
	
	public Enemigos(int posX, int posY, int dimX, int dimY){
		this.posX = posX;
		this.posY = posY;
		this.dimX = dimX;
		this.dimY = dimY;
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
	
	public void mover(){
		int direc = (int) (Math.random()*4 +1);
		switch (direc) {
			case 1:
				moverArriba();
				break;
			case 2:
				moverAbajo();
				break;
			case 3:
				moverDerecha();
				break;
			case 4:
				moverIzquierda();
				break;
			default:
				break;
		}
	}
	public void moverArriba(){
		posX = posX -1;
		if(posX == 2) posX = posX +1;
		direccion = "arriba";
	}
	public void moverAbajo(){
		posX = posX +1;
		if(posX == dimX-3) posX = posX-1;
		direccion = "abajo";
	}
	public void moverDerecha(){
		posY = posY+1;
		if(posY == dimY-4) posY = posY-1;
		direccion = "derecha";
	}
	public void moverIzquierda(){
		posY = posY -1;
		if(posY == 3) posY = posY+1;
		direccion = "izquierda";
	}
}
