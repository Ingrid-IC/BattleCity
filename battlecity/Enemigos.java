package battlecity;

public class Enemigos {
    boolean vida = false;
    int posX;
    int posY;
    int dimX = Global.dimX;
    int dimY = Global.dimY;
    boolean disparo = false;
    Bala bala;
    String direccion = "arriba";
    private int desplazamiento = 1;

    public Enemigos(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.vida = true;
        bala = new Bala(posX, posY, 0, 0, false);
           
		//this.dimX = dimX;
        //this.dimY = dimY;
    }

    public int positionX() {
        return posX;
    }

    public int positionY() {
        return posY;
    }

    public String direccion() {
        return direccion;
    }

    public void mover() {
        int direc = (int) (Math.random() * 4 + 1);
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

    public void moverArriba() {
        direccion = "arriba";
        posX = posX - desplazamiento;
        if (posX == 2) {
            moverAbajo();
        }
        if ((posX == dimX / 2 + 1) && (posY >= dimY / 2) && (posY <= 3 * dimY / 4)) {
            moverAbajo();
        }
        if ((posX == 3 * dimX / 4 + 1) && (posY <= 3 * dimY / 4)) {
            moverAbajo();
        }
        if ((posX == 3 * dimX / 4 + 1) && (posY <= dimY / 2)) {
            moverAbajo();
        }
        if ((posX == 3 * dimX / 4 + 1) && (posY <= dimY / 4)) {
            moverAbajo();
        }
    }

    public void moverAbajo() {
        direccion = "abajo";
        posX = posX + desplazamiento;
        if (posX == dimX - 3) {
            moverArriba();
        }
        if ((posX == dimX / 2 - 2) && (posY >= dimY / 2) && (posY <= 3 * dimY / 4)) {
            moverArriba();
        }
        if ((posX == dimX / 4 - 1) && (posY <= 3 * dimY / 4)) {
            moverArriba();
        }
        if ((posX == dimX / 4 - 1) && (posY <= dimY / 2)) {
            moverArriba();
        }
        if ((posX == dimX / 4 - 1) && (posY <= dimY / 4)) {
            moverArriba();
        }
    }

    public void moverDerecha() {
        direccion = "derecha";
        posY = posY + desplazamiento;
        if (posY == dimY - 4) {
            moverIzquierda();
        }
        if ((posX >= dimX / 4) && (posX <= 3 * dimX / 4) && (posY == dimY / 4 - 3)) {
            moverIzquierda();
        }
        if ((posX >= dimX / 4) && (posX <= 3 * dimX / 4) && (posY == dimY / 2 - 3)) {
            moverIzquierda();
        }
        if ((posX >= dimX / 4) && (posX <= 3 * dimX / 4) && (posY == 3 * dimY / 4 - 3)) {
            moverIzquierda();
        }
    }

    public void moverIzquierda() {
        direccion = "izquierda";
        posY = posY - desplazamiento;
        if (posY == 3) {
            moverDerecha();
        }
        if ((posX >= dimX / 4) && (posX <= 3 * dimX / 4) && (posY == dimY / 4 + 1)) {
            moverDerecha();
        }
        if ((posX >= dimX / 4) && (posX <= 3 * dimX / 4) && (posY == dimY / 2 + 1)) {
            moverDerecha();
        }
        if ((posX >= dimX / 4) && (posX <= 3 * dimX / 4) && (posY == 3 * dimY / 4 + 1)) {
            moverDerecha();
        }
    }

    public void disparar() {
        if (this.disparo == true) {

        } else {
            this.disparo = true;
            if (direccion == "arriba") {
                this.bala = new Bala(this.posX, this.posY, 0, 1, true);
            } else if (direccion == "abajo") {
                this.bala = new Bala(this.posX, this.posY, 0, -1, true);
            } else if (direccion == "izquierda") {
                this.bala = new Bala(this.posX, this.posY, -1, 0, true);
            } else if (direccion == "derecha") {
                this.bala = new Bala(this.posX, this.posY, 1, 0, true);
            }

        }

    }

    public void moverDisparo() {
        if (this.disparo == true) {
            if (this.bala.vX == 0 && this.bala.vY == 1 && this.bala.posY < dimY - 4) {//DERECHA
                this.bala.posY = this.bala.posY + 1;
            } else if (this.bala.vX == 0 && this.bala.vY == -1 && this.bala.posY > 3) {//IZQUIERDA
                this.bala.posY = this.bala.posY - 1;
            } else if (this.bala.vX == -1 && this.bala.vY == 0 && this.bala.posX > 2) {//ARRIBA
                this.bala.posX = this.bala.posX - 1;
            } else if (this.bala.vX == 1 && this.bala.vY == 0 && this.bala.posX < dimX - 3) {//ABAJO
                this.bala.posX = this.bala.posX + 1;
            }
            
            if (this.bala.posY == dimY - 4 || this.bala.posY ==  3) {
                this.disparo = false;
            }
            if (this.bala.posX == 2 || this.bala.posX == dimX - 3) {
                this.disparo = false;
            }
        }
    }
    public void eliminarEnemigo(){
       this.vida = false;
    
    }
}
