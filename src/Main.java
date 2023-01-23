package src;

import src.entities.Mailer;

public class Main {
    public static void main(String[] args) {
        Mailer mailer = new Mailer();

        mailer.addUser("joshuahawattaazevedo@gmail.com");
        mailer.addUser("emilianoazevedo72@gmail.com");
        mailer.sendEmail("Bello!!!", "Mais um e-mail em Java (e dessa vez o topzera)!", "Joshua Hawatta");
    }
}
