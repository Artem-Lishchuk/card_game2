package com.company;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.BiConsumer;


public class Main {

    public static void main(String[] args) {
        Gamer Artem = new Gamer();
        Gamer en = new Gamer();
        en.main_player = en.player_generator();
        Scanner scan = new Scanner(System.in);
        Random random = new Random();
        ArrayList<Player> players = new ArrayList<>();
        players.add(Artem.player_generator());
        players.add(Artem.player_generator());
        players.add(Artem.player_generator());
        players.forEach(player -> System.out.println(player.name));


        int ads = scan.nextInt();
        Artem.main_player = players.get(ads);
        Artem.players.add(players.get(ads));
        players.removeAll(players);

        players.add(en.main_player);
        players.add(Artem.main_player);

        Artem.main_player.position = 0;
        en.main_player.position = 7;


        while (players.size() > 1) {
            Artem.main_player.move(Artem.main_player.choose_direction() , Artem);
            en.main_player.move(random.nextInt(3 - 1) + 1 , en);
            player_event(Artem);
            minievent(en);
            System.out.printf("%s %d %n" ,"Your position:" ,  Artem.main_player.position);
            System.out.printf("Enemy position: %d%n" , en.main_player.position);
            if (Artem.main_player.position == en.main_player.position) {
                System.out.println("Battle!!!");
//                System.out.println("Enemy has " + enemy.collection.size() + "cards");
                System.out.printf("Enemy has %d cards %n" , en.main_player.collection.size());
                if (battle(Artem.main_player, en.main_player) == true) {
                    players.remove(en.main_player);
                    Artem.coins += 20;
                    System.out.printf("You earned %d coins%n" , Artem.coins);
                }
                else {
                    players.remove(Artem.main_player);
                    Artem.coins += 5;
                    System.out.printf("You earned %d coins%n" ,Artem.coins);
                }
            }
            System.out.printf("Now you have %d coins ,you can use them to buy or change your hero%n" , Artem.coins);

        }
    }





    static boolean battle(Player a , Player b){
        a.main_action.accept(a);
        b.main_action.accept(b);
        a.cards = a.collection;
        b.cards = b.collection;
        boolean gg = true;
        Random random = new Random();
        while (b.cards.size()  > 0 || a.cards.size() > 0){
            if (b.cards.size()  > 0){
                b.play_card(random.nextInt(b.cards.size()),a,b);
            }
            else {
                a.play_card(a.choose(), a,b);
            }
        }

        if (a.score > b.score){
            gg = true;
            System.out.println("You win");
            System.out.printf("my score = %d%n", a.score);
            System.out.printf("enemy score = %d%n" , b.score);
        }
        else {
            gg = false;
            System.out.println("You lose");
            System.out.printf("my score = %d%n" , a.score);
            System.out.printf("enemy score = %d%n" , b.score);
        }
        a.score =0;
        b.score = 0;
        return gg;
    }
    static void player_event(Gamer me){
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        int choice = rand.nextInt(9-1)+1;
        if (choice == 1){
            int a= rand.nextInt(me.main_player.collection.size()-1)+1;
            System.out.printf("Was deleted card: %s%n" , me.main_player.collection.get(a).name );
            me.main_player.collection.remove(a);
        }
        if ( choice==2){
            me.main_player.collection.add(me.card_generator());
            System.out.printf("Was added card %s%n" , me.main_player.collection.get(me.main_player.collection.size()-1).name);
        }
        if (choice == 3){
            me.coins +=10;
            System.out.println("+ 10 coins");
        }

        if (choice == 4){
            System.out.println("- 5 coins");
            me.coins -=5;
        }
        if (choice == 5){
            int a = rand.nextInt(200-1)+1;
            int b = rand.nextInt(200-1)+1;
            System.out.printf("%d + %d = ?" , a,b);
            if (scan.nextInt() == a+b){
                me.coins += 15;
                System.out.println(":) , you're right + 15 coins");
            }
            else {
                System.out.println("You're incorrect");
            }
        }
        if (choice == 6){
            int a = rand.nextInt(200-1)+1;
            int b = rand.nextInt(200-1)+1;
            System.out.printf("%d - %d = ? " , a , b);
            if (scan.nextInt() == a - b){
                me.coins += 15;
                System.out.println(":) , you're right + 15 coins");
            }
            else {
                System.out.println(":( , you're incorrect");
            }
        }
        if (choice == 7) {
            int a = rand.nextInt(20 - 1) + 1;
            int b = rand.nextInt(20 - 1) + 1;
            System.out.printf("%d * %d = ? ", a, b);
            if (a * b == scan.nextInt()) {
                me.coins += 15;
                System.out.println(":) , you are right");

            } else {
                System.out.println("incorect");
            }
        }
            if (choice == 8) {
                int a = rand.nextInt(50 - 1) + 1;
                int b = rand.nextInt(10 - 1) + 1;
                System.out.printf("%d %s %d = ? ", a, "%", b);
                if (scan.nextInt() == a % b) {
                    me.coins += 15;
                    System.out.println(":) , you're right");
                } else {
                    System.out.println(":) , you're incorrect ");
                }
            }
    }





    static void minievent(Gamer me){
        Random rand = new Random();
        int choice = rand.nextInt(4-1)+1;
        if (choice == 1){
            int a= rand.nextInt(me.main_player.collection.size()-1)+1;
            System.out.printf("In enemy collection was deleted card: %s%n" , me.main_player.collection.get(a).name );
            me.main_player.collection.remove(a);
        }
        if ( choice==2 ){
            me.main_player.collection.add(me.card_generator());
            System.out.printf("To enemy collection was added card %s%n", me.main_player.collection.get(me.main_player.collection.size()-1).name);
        }
    }
}

class Player{
    int score = 0;
    int position ;
    String name = "";
    Consumer<Player> main_action ;
    ArrayList<Card> cards = new ArrayList<Card>();
    ArrayList<Card> collection =  new ArrayList<Card>();
    int choose_direction(){
        Scanner scan = new Scanner(System.in);
        if (this.position == 0){
            System.out.println("Tap 1 to move right");
        }
        else if (this.position == 7){
            System.out.println("Tap 2 to move left");
        }
        else{
            System.out.println("Tap 1 to move right");
            System.out.println("Tap 2 to move left");
            System.out.println("Tap 3 to buy hero");
            System.out.println("Tap 4 to change hero");
        }
        int number = scan.nextInt();
        return number;
    }

    void move(int number , Gamer a ){
        //Scanner scan = new Scanner(System.in) ;
        boolean g = true;
        if (this.position == 0 && number == 2){
            g = false;
        } else if (this.position == 7 && number == 1) {
            g = false;
        }
        else {
            g = true;
        }
        if (number == 1 && g == true){
            position +=1;
        }
        if (number == 2 && g == true){
            position -=1;
        }
        if (number == 3){
            System.out.println("Buy hero");
            a.buy_hero();
        }
        if (number == 4){
            System.out.println("Change hero");
            a.change_hero(a.choose_hero());
        }
    }
    void play_card(int num,Player me,Player enemy) {
        this.cards.get(num).action.accept(me,enemy);
        this.score += this.cards.get(num).value;
        this.cards.remove(num);
    }
    int choose(){
        for (int i = 0; i < this.cards.size(); i++){
            System.out.println(i);
            System.out.println(this.cards.get(i).name);
        }
        Scanner my_scanner = new Scanner(System.in);
        int num = my_scanner.nextInt();

        return num;
    }


}

class Card{
    String name;
        int value ;
        BiConsumer<Player,Player> action;
    }



    class  Gamer{

        int coins = 0 , level = 0 , position;
        ArrayList<Player> players =  new ArrayList<Player>();
        Player main_player;
        static Card card_generator(){
            Random random = new Random();
            BiConsumer<Player,Player> action;
            int num_of_action = random.nextInt(5-0)+0, value = random.nextInt(30-1)+1;
            Card card = new Card();
            card.value = value;
            //if (value = random.nextInt(10-1)+1=small card);
            if(value >= 1 && value < 10){
                card.name = "small ";
                card.name += value;
            }
        if(value >=10  && value <= 20){
            card.name = "middle ";
            card.name += value;
        }
        if(value >= 20 && value <= 30){
            card.name = "large "  ;
            card.name += value;
        }
        if(num_of_action == 0){
            card.name += " buff card if last left";
            card.action = (Player me, Player enemy) -> {
                if (me.cards.size() == 1){
                    card.value +=15;
                }
            };
        }
        if(num_of_action == 1) {
            card.name += " de buff on 10 if enemy score > 50 ";
            card.action = (Player me, Player enemy) -> {
                if(enemy.score>= 50){
                    card.value -= 10;
                }
            };
        }
        if(num_of_action == 2) {
            card.name += " Buff on 15 if your score < 10";
            card.action = (Player me, Player enemy) -> {
                if (me.score <=10){
                    card.value += 15;
                }
            };
        }
        if(num_of_action == 3) {
            int a = random.nextInt(20-1)+1;
            card.action = (Player me, Player enemy) -> {
                if (enemy.cards.size() == 1){
                    card.value -= a;
                }
            };
            card.name += "de buff on "+ a + "if enemy has 1 card" ;
        }
        if(num_of_action == 4) {

            int a = random.nextInt(20-1)+1;
            card.action = (Player me, Player enemy) -> {
                if (me.score <=10){
                    card.value += a;
                }
            };
            card.name += " Buff on" + a + "if your score < 10";
        }
        return card;
    }
    static Player player_generator(){
        Random random = new Random();
        Player player= new Player();
        int num_of_action = random.nextInt(5-1)+1;
        if (num_of_action == 1){
            player.main_action = (Player me ) ->{
                System.out.println("Big buff");
                for (int i =0; i < me.cards.size(); i++){
                    me.cards.get(i).value += 5;
                }
            };
            player.name += "Paladin";
        }
        if (num_of_action == 2){
            player.main_action = (Player me ) ->{
                System.out.println("+ cards");
                for (int i =0; i < 1; i++){
                    me.cards.add(card_generator());
                }
            };
            player.name += "Droid";
        }
        for (int i =0; i < 5 ; i++){
            player.collection.add(card_generator());
        }
        return player;
    }
    int choose_hero(){
        String s="";
        for (int i = 0; i < this.players.size(); i++){
            System.out.printf("tap %d to choose hero %s%n" , i ,this.players.get(i).name);
        }
        if (main_player.collection.size() > 5){
            for (int i =0; i < main_player.collection.size(); i++){
                for(int d = 0; d < this.players.size(); d++){
                    if ( d == this.players.size()-1) {
                        System.out.print(i + "  ");
                        System.out.printf("%-50s%n" ,this.players.get(d).collection.get(i).name);
                    }
                    else{
                        System.out.print(i);
                        System.out.printf("%-50s" ,this.players.get(d).collection.get(i).name);
                    }
                }
            }
        }
        else{
            for (int i =0; i < 5; i ++){
                for(int d = 0; d < this.players.size(); d++){
                    if ( d == this.players.size()-1) {
                        System.out.print(i + "  ");
                        System.out.printf("%-70s%n" ,this.players.get(d).collection.get(i).name);
                    }

                    else{
                        System.out.print(i);
                        System.out.printf("%-70s" ,this.players.get(d).collection.get(i).name);
                    }
                    }
                }
            }

        Scanner my_scanner = new Scanner(System.in);
        int num = my_scanner.nextInt();

        return num;
    }
    void change_hero(int num){
        int pos = main_player.position;
        main_player = this.players.get(num);
        main_player.position = pos;

    }
    void buy_hero(){
        Scanner scan = new Scanner(System.in);
        if (this.coins >= 50){
            ArrayList<Player> players1 =  new ArrayList<Player>();
            players1.add(player_generator());
            players1.add(player_generator());
            players1.add(player_generator());
            players1.forEach(player -> System.out.println(player.name));
            int ads = scan.nextInt();
            this.players.add(players1.get(ads));
            players1.removeAll(players1);
            System.out.printf("You bought hero %s%n" , this.players.get(this.players.size()-1).name);
            this.coins -= 50;
            }
        }
    }