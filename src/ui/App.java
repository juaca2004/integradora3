package ui;

import model.MusicApp;

import java.util.Scanner;





public class App{

    public static Scanner lector = new Scanner(System.in);
   

    private MusicApp musicApp;

    public App() {
        createMusicApp();
    }

    




    
    /** 
     * @param args
     */
    public static void main(String[] args){
        App objMain=new App();
        boolean val = false;

        while (!val){
            System.out.println("1. register a consumer user.\n" + "2.register a producer user \n" + "3. register song or Podcast\n" + "4.register playlist\n"+"5.edit playlist\n"+"6.out ");
            int option = lector.nextInt();
            lector.nextLine();
            switch (option){
                case 1:
                    objMain.registerUserConsumer();
                    break;
                case 2:
                    objMain.registerUserProductor();
                    break;
                case 3:
                    objMain.registerAudio();
                    break;
                case 4:
                     objMain.registerPlaylist();
                     break;
                case 5:
                     objMain.editPlaylist();
                     break;
                case 6:
                     val = true;
                     break;
                default:
                    System.out.println("you must enter an available option");
                    break;
            }
        }
    }

    public void createMusicApp(){
        musicApp= new MusicApp();
        
    }
     /**
    * registerUserConsumer
    *the game is created
    *<b>pre:</b> the program has been started.<br>
    *<b>post:</b> game is created

   */
    public void registerUserConsumer(){
        System.out.println("enter the user's nickname");
        String nickname=lector.nextLine();
        System.out.println("enter your id");
        String id= lector.nextLine();
        System.out.println("Enter what type of consumer user is going to enter, 1. standard.2. premium");
        int option= lector.nextInt();
         if(option>2||option<1){
            System.out.println("you must enter an available option");
         }
         else{
            System.out.println(musicApp.registerUserConsumer(nickname, id,option));
         }

    }
     /**
    * registerUserProductor
    *the game is created
    *<b>pre:</b> the program has been started.<br>
    *<b>post:</b> game is created

   */
    public void registerUserProductor(){
        System.out.println("enter the user's nickname");
        String nickname=lector.next();
        System.out.println("enter your id");
        String id= lector.next();
        System.out.println("enter the url of the profile picture");
        String url= lector.next();
        System.out.println("enter tour name");
        String name= lector.next();
        System.out.println("Enter what type of producer user is going to enter, 1. creator content.2. Artist");
        int option= lector.nextInt();
        if(option>2||option<1){
            System.out.println("you must enter an available option");
         }
         else{
            System.out.println(musicApp.registerUserProducer(nickname, id, url, name, option));
         }
        }

    
     /**
    *registerAudio
    *the game is created
    *<b>pre:</b> the program has been started.<br>
    *<b>post:</b> game is created

   */
    public void registerAudio(){
        System.out.println("enter the nickname of artist or content creator");
        String nickname=lector.next();
        System.out.println("enter the name of audio");
        String name= lector.next();
        System.out.println("enter the url with a picture");
        String url= lector.next();
        System.out.println("enter of duration of audio");
        String duration=lector.next();
        System.out.println("Enter what type of audio is going to enter, 1. Song.2. podcast");
        int option= lector.nextInt();
        switch(option){
            case 1:
            System.out.println("enter the name of album");
            String album=lector.next();
            System.out.println("enter the price of song");
            double price=lector.nextDouble();
            System.out.println("enter the type of song.1.rock,2.pop,3.trap,4.house");
             int typeSong=lector.nextInt();
             if(typeSong>4||typeSong<1){
                System.out.println("you must enter an available option");
             }
             else{
                System.out.println(musicApp.registerAudio(nickname, name, url, duration, album, price, typeSong));
             }

             break;
            case 2:
             System.out.println("enter the description of podcast");
              String description=lector.next();
              System.out.println("enter the type of podcast.1.Politic,2.Entertaiment,3.Fashion,4.Videogame");
             int typePodcast=lector.nextInt();
             System.out.println(musicApp.registerAudio(nickname, name, url, duration, typePodcast, description));
             break;
            default:
            System.out.println("you must enter an available option");
             break;
            }
        
    }
     /**
    *registerPlaylist
    *the game is created
    *<b>pre:</b> the program has been started.<br>
    *<b>post:</b> game is created

   */
    public void registerPlaylist(){
        System.out.println("enter the nickname of user standard or premium");
        String nickname=lector.next();
        System.out.println("enter the name of playlist");
        String name= lector.next();
        System.out.println("Enter the type of playlist you want,1.only songs,2.only podcasts or 3. podcast and songs");
        int option= lector.nextInt();
        if(option>4|| option<1){
            System.out.println("you must enter an available option");
        }
        else{
            System.out.println(musicApp.registerPlaylist(nickname, name, option));
        }

    }
     /**
    * editPlaylist
    *the game is created
    *<b>pre:</b> the program has been started.<br>
    *<b>post:</b> game is created

   */
    public void editPlaylist(){
        System.out.println("enter the nickname of user standard or premium");
        String nickname=lector.next();
        System.out.println("enter the option you want,1.add audio in playlis,2.eliminate audio in playlist");
        int option= lector.nextInt();
         if(option==1 || option==2){
            System.out.println("enter the name of the playlist");
            String namePlaylist=lector.next();
            System.out.println("enter the name of audio");
            String audio=lector.next();

            System.out.println(musicApp.editAudioPlaylist(option, nickname, namePlaylist, audio));
             
         }

         else{
            System.out.println("you must enter an available option");
         }
    }



}