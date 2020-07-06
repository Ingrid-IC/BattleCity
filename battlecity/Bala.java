/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battlecity;

/**
 *
 * @author Usuario
 */
public class Bala {
    int posX;
    int posY;
    int vX;
    int vY;
    boolean isEnemy;

    public Bala(int x , int y ,int vx,int vy,boolean isEnemy) {
        this.posX = x;
        this.posY = y;
        this.vX = vx;
        this.vY = vy;
        this.isEnemy = isEnemy;
    }
    
    
    
}
