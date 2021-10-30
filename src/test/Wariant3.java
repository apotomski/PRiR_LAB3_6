package test;
import java.util.Random;
import java.util.concurrent.Semaphore ;
public class Wariant3 extends Thread {
    int MAX;
    static Semaphore [] widelec;
    int nr;
    Random losuj ;
    public Wariant3(int nr, int max) {
        this.nr = nr ;
        this.losuj = new Random(this.nr) ;
        this.MAX = max;
        widelec = new Semaphore[this.MAX] ;
        for ( int i = 0; i < this.MAX; i++) {
            this.widelec[i] = new Semaphore( 1 ) ;
        }
    }
    public void run () {
        while (true) {
            System.out.println ( "Mysle nr:" + this.nr) ;
            try {
                Thread.sleep ( ( long ) (5000 * Math.random( ) ) ) ;
            } catch ( InterruptedException e ) {
            }
            int strona = this.losuj.nextInt( 2 ) ;
            boolean podnioslDwaWidelce = false ;
            do {
                if (strona == 0) {
                    this.widelec[this.nr].acquireUninterruptibly ();
                    if(!( widelec[(this.nr +1 ) % this.MAX].tryAcquire( ))) {
                        this.widelec[this.nr].release();
                    } else {
                        podnioslDwaWidelce = true ;
                    }
                } else {
                    this.widelec[(this.nr + 1) % this.MAX].acquireUninterruptibly();
                    if (!(this.widelec[this.nr].tryAcquire())) {
                        this.widelec[(this.nr + 1) % this.MAX].release();
                    } else {
                        podnioslDwaWidelce = true;
                    }
                }
            } while ( podnioslDwaWidelce == false);
            System.out.println ( "Zaczyna jesc nr:" + this.nr) ;
            try {
                Thread.sleep((long)(3000 * Math.random()));
            } catch (InterruptedException e) {
            }
            System.out.println("Konczy jesc nr:" + this.nr) ;
            this.widelec[this.nr].release();
            this.widelec[(this.nr + 1) % this.MAX].release();
        }
    }
}
