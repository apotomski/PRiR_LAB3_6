package test;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

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
}
