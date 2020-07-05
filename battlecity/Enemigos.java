package battlecity;

public class Enemigos {
	int posX;
	int posY;
	int dimX = Global.dimX;
	int dimY = Global.dimY;
	String direccion = "arriba";
	private int desplazamiento = 1;
	
	public Enemigos(int posX, int posY){
		this.posX = posX;
		this.posY = posY;
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
		posX = posX - desplazamiento;
		if(posX == 2) moverAbajo();
		direccion = "arriba";
	}
	public void moverAbajo(){
		posX = posX + desplazamiento;
		if(posX == dimX-3) moverArriba();
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
