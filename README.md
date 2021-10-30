# PRiR_LAB3_6
PRiR lab 3 projekt 6. Program jest połączeniem wszystkich 3 programów dotyczących filozofów z wykładu. Na początku program pyta o liczbę filozofów między 2 a 100 i o metodę którą ma wykonać.
Następnie tworzy podaną liczbę obiektów dla danej metody i uruchamia ich pracę na wątkach.

Opis kodu

Main
Pobiera numer metody i liczbę filozofów a następnie tworzy obiekty danej klasy i wywołuje ich pracę na wątkach

  Scanner skaner = new Scanner(System.in);
          int liczba = 0;

          while(liczba < 2 || liczba > 100) {
              System.out.println("Podaj liczbe filozofów(minimalna liczba:2 | maxymalna liczba 100)");
              liczba = skaner.nextInt();
          }

          System.out.println("Wybierz sposób rozwiązania \n 1.Pierwszy sposób \n 2.Drugi sposób \n 3.Trzeci sposób");
          int wybor = skaner.nextInt();

          switch(wybor) {
              case 1: {
                  for ( int i = 0; i < liczba; i++)
                      new Wariant1(i,liczba).start();
              } break;
              case 2: {
                  for ( int i = 0; i < liczba; i++)
                      new Wariant2(i, liczba).start();
              } break;
              case 3: {
                  for ( int i = 0; i < liczba; i++)
                      new Wariant3(i, liczba).start();
              } break;
          }

      }
      
 Klasa Wariant1
 konstruktor przypisuje wartości
 
     public Wariant1 (int nr, int max) {
            this.nr = nr;
            this.MAX = max;
            this.widelec = new Semaphore[MAX] ;
            for ( int i = 0; i < this.MAX; i++) {
                this.widelec[i] = new Semaphore( 1 );
            }
        }
        
   metoda run wywołuje komunikaty o myśleniu jedzeniu i braniu i odkładaniu widelca a także usypia na określony czas filozofa na podstawie podanejna wykładzie metody
   
        public void run () {
            while (true) {
                System.out.println("Mysle nr:" + this.nr);
                try {
                    Thread.sleep((long) (7000 * Math.random()));
                } catch (InterruptedException e) {

                }

                this.widelec[this.nr].acquireUninterruptibly();
                this.widelec[(this.nr + 1) % this.MAX].acquireUninterruptibly();
                System.out.println("Zaczyna jesc nr:" + this.nr);

                try {
                    Thread.sleep((long) (5000 * Math.random()));
                } catch (InterruptedException e) {

                }

                System.out.println("Konczy jesc nr:" + this.nr);
                this.widelec[this.nr].release();
                this.widelec[(this.nr + 1) % this.MAX].release();
            }}
            
    Klasa Wariant2
    Konstruktor przypisuje wartości
    
        public Wariant2 (int nr, int max) {
            this.nr = nr;
            this.MAX = max;
            this.widelec = new Semaphore[this.MAX];
            for ( int i = 0; i < this.MAX; i++) {
                this.widelec[i] = new Semaphore( 1 );
            }
    }
 konstruktor przypisuje wartości
 
 
metoda run wywołuje komunikaty o myśleniu jedzeniu i braniu i odkładaniu widelca a także usypia na określony czas filozofa na podstawie podanej na wykładzie metody
  
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
    
  Klasa Wariant3
 konstruktor przypisuje wartości
 
    public Wariant3(int nr, int max) {
        this.nr = nr ;
        this.losuj = new Random(this.nr) ;
        this.MAX = max;
        widelec = new Semaphore[this.MAX] ;
        for ( int i = 0; i < this.MAX; i++) {
            this.widelec[i] = new Semaphore( 1 ) ;
        }
    }
    
 metoda run wywołuje komunikaty o myśleniu jedzeniu i braniu i odkładaniu widelca a także usypia na określony czas filozofa na podstawie podanej na wykładzie metody
 
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
    
  
