package test;
import java.util.concurrent.Semaphore;
public class Wariant2 extends Thread {
    int MAX;
    static Semaphore [] widelec;
    int nr;
    public Wariant2 (int nr, int max) {
        this.nr = nr;
        this.MAX = max;
        this.widelec = new Semaphore[this.MAX];
        for ( int i = 0; i < this.MAX; i++) {
            this.widelec[i] = new Semaphore( 1 );
        }
    }
    public void run () {
        while (true) {
            System.out.println ( "Mysle  nr:" + this.nr);
            try {
                Thread.sleep ((long)(5000 * Math.random()));
            } catch (InterruptedException e) {
            }
            if (this.nr == 0) {
                this.widelec[ (this.nr + 1) % this.MAX].acquireUninterruptibly();
                this.widelec[this.nr].acquireUninterruptibly();
            } else {
                this.widelec[this.nr].acquireUninterruptibly();
                this.widelec[ (this.nr + 1) % this.MAX].acquireUninterruptibly();
            }
            System.out.println("Zaczyna jesc  nr:" + this.nr);
            try {
                Thread.sleep((long)(3000 * Math.random()));
            } catch ( InterruptedException e ) {
            }
            System.out.println ( "Konczy jesc  nr:" + this.nr);
            this.widelec[this.nr].release();
            this.widelec[(this.nr + 1) % MAX].release();
        }
    }

}
