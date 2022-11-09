package model;

import java.util.ArrayList;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
//* */

public class MusicApp {

    private ArrayList <User> users;
    private ArrayList <Audio> audios;

    public MusicApp() {
        users= new ArrayList<User>();
        audios= new ArrayList<Audio>();
    }


    
    /**
     * 
     *registerUserConsumer
     *the register consumer method
     *<b>pre:</b> nickname had to be entered and nickname not repeated.<br>
     *<b>post:</b> a id,nickname,option had to be entered 
     * @param nickname
     * @param id
     * @param option
     * @return String
     */
    public String  registerUserConsumer(String nickname , String id ,int option){
        String msg = "user created";
        User objU = searchUser(nickname);

        if (objU != null) {
            msg = "the user exist";
        } else {
            Calendar bondingdate=dateActual();
            if(option==1){
                users.add(new Standard(nickname,id,bondingdate));
            }
            else{
                users.add(new Premium(nickname,id,bondingdate));
            }
        
        }
        return msg;
    }



    
    /** 
     *registerUserProducer
     *the register producer method
     *<b>pre:</b> nickname had to be entered and nickname not repeated.<br>
     *<b>post:</b> a id,nickname,option,url,name had to be entered 
     * @param nickname
     * @param id
     * @param url
     * @param name
     * @param option
     * @return String
     */
    public String  registerUserProducer(String nickname , String id,String url, String name,int option){
        String msg = "user created";
        User objU = searchUser(nickname);

        if (objU != null) {
            msg = "the user exist";
        } else {
            Calendar bondingdate=dateActual();
            if(option==1){
                users.add(new Creator(nickname,id,bondingdate,url,name));
            }
            else{
                users.add(new Artist(nickname,id,bondingdate,url,name));
            }
            
        }
        return msg;
    }

    


    
    
    /** 
     *registerAudio
     *the register song method
     *<b>pre:</b> nickname had to be entered and nickname not repeated.<br>
     *<b>post:</b> name,nickname,option,url,duration,album,price,option had to be entered 
     * @param nickname
     * @param name
     * @param url
     * @param duration
     * @param album
     * @param price
     * @param option
     * @return String
     */
    public String  registerAudio(String nickname,String name, String url, int minutes,int second,String album,double price,int option){
        String msg = "song created";
        User objU = searchUser(nickname);

        if (objU == null) {
            msg = "the user doesn't exist";
        } else {
             if(objU instanceof Artist){
               Audio objA= searchAudio(name);
                 if(objA!= null){
                     msg="the song exist";
                 }

                 else{
                    audios.add(new Song(name, url, totaltime(minutes, second), album, price, option));
                    Artist objArtist = (Artist) objU;
                    objArtist.getSongs().add((Song) audios.get(audios.size()-1));
                 }
             }
             else{
                msg="the user is not a Artist";
             }
        }
        return msg;
    }

    
    /** 
     *registerAudio
     *the register podcast method
     *<b>pre:</b> nickname had to be entered and nickname not repeated.<br>
     *<b>post:</b> name,nickname,option,url,duration,description had to be entered 
     * @param nickname
     * @param name
     * @param url
     * @param duration
     * @param option
     * @param description
     * @return String
     */
    public String  registerAudio(String nickname,String name, String url, int minutes,int second,int option,String description){
        String msg = "podcast created";
        User objU = searchUser(nickname);

        if (objU == null) {
            msg = "the user doesn't exist";
        } else {
             if(objU instanceof Creator){
               Audio objA= searchAudio(name);
                 if(objA!= null){
                     msg="the podcast exist";
                 }

                 else{
                    audios.add(new Podcast(name, url, totaltime(minutes, second), description, option));
                    Creator objcCreator = (Creator) objU;
                    objcCreator.getPodcasts().add((Podcast) audios.get(audios.size()-1));
                 }
             }
             else{
                msg="the user is not a creator";
             }
        }
        return msg;
    }

    public int totaltime(int minutes,int second){
        int time;
        return time=(minutes*60)+second;
    }

    
    /** 
     *registerPlaylist
     *the register playlist method
     *<b>pre:</b> nickname had to be entered and nickname not repeated.<br>
     *<b>post:</b> name,nickname,option had to be entered 
     * @param nickname
     * @param name
     * @param option
     * @return String
     */
    public String registerPlaylist(String nickname,String name){
        String msg = "Playslist created";
        User objU = searchUser(nickname);

        if (objU == null) {
            msg = "the user doesn't exist";
        } else {
            if(objU instanceof Standard){
                int [][]matriz=generateMatriz();
                Standard objStandard = (Standard) objU;
                boolean val=objStandard.addPlaylist(name, matriz);
                if(val==false){
                  msg="the playlist exist";
                }
          }
          else if(objU instanceof Premium){
            int [][]matriz=generateMatriz();;
            Premium objPremium = ( Premium) objU;
            boolean val=objPremium.addPlaylist(name, matriz);
            if(val==false){
              msg="the playlist exist";
            }
          }
          else{
            msg="this user is not standard or premium";
          }
        
       }
       return msg;
    }


    
    /** 
    *searchPlayer
    *the method looks for a user with the nickname passed by parameter
    *<b>pre:</b> a string data must be entered.<br>
    *<b>post:</b> The method returns whether or not there is a user with that nicknmae
     * @param nickname
     * @return User
     */
    public User searchUser(String nickname){
        User obj=null;
        boolean val= false;
         for(int i=0;i<users.size() && !val ;i++){
            if( users.get(i).getNickname().equalsIgnoreCase(nickname)){
                obj=users.get(i);
                val= true;
            }
         }
    
        return obj;
        
    
    }

    
    /** 
    *searchAudio
    *the method looks for a user with the name passed by parameter
    *<b>pre:</b> a string data must be entered.<br>
    *<b>post:</b> The method returns whether or not there is a user with that name
     * @param name
     * @return Audio
     */
    public Audio searchAudio(String name){
        Audio obj=null;
        boolean val= false;
         for(int i=0;i<audios.size() && !val ;i++){
            if( audios.get(i).getName().equalsIgnoreCase(name)){
                obj=audios.get(i);
                val= true;
            }
         }
    
        return obj;
    }

    
    /** 
    *dateActual
    *the method return the date of app
     * @return Calendar
     */
    public Calendar dateActual(){
        
        Calendar calendar=new GregorianCalendar(2022,Calendar.NOVEMBER,8);

        return calendar ;
         
    }

    
    /**
    *generateNumber
    *the method generate a random number
     * @return int
     */
    public int generateNumber(){
        int number=0;
         Random r= new Random();
       
         number= (int) (r.nextInt()* 9); 
       
        return number;
       
       }

    
    /** 
    *generateMatriz
    *the method generate a matriz
     * @return int[][]
     */
    public int[][] generateMatriz(){
      int matriz[][]=new int[6][6];

      for(int i=0;i<6;i++){
        for(int j=0;i<6;i++){
            matriz[i][j]=generateNumber();
        }
      }
      return matriz;

    }


    


    
    /**
    *editAudioPlaylist
    *the method edit the playslist
    *<b>pre:</b> the paramatrer was pass.<br>
    *<b>post:</b> The method returns a string is was exit
     * @param option
     * @param nickname
     * @param namePlaylist
     * @param audio
     * @return String
     */
    public String editAudioPlaylist(int option,String nickname,String namePlaylist, String audio){
        String msg="";
        Audio objA= searchAudio(audio);
 
        if(objA==null){
         msg="the song dont exist";
        }
        else{
         User objU=searchUser(nickname);
         if(objU==null){
             msg="the user dont exist";
         }
         else{
            if(option==1){
              if(objU instanceof Standard){
                 Standard obj = (Standard) objU; 
                 msg=obj.addAudioPlaylist(namePlaylist, objA);
              }
              else if(objU instanceof Premium){
                 Premium obj = (Premium) objU; 
                 msg=obj.addAudioPlaylist(namePlaylist, objA);
              }
              else {
                msg="this user is not premiun or standard";
              }
            }
            if(option==2){
                            
               if(objU instanceof Standard){
                Standard obj = (Standard) objU; 
                 msg=obj.elimanteAudio(objA, namePlaylist);
              }
             else if(objU instanceof Premium){
                Premium obj = (Premium) objU; 
                msg=obj.elimanteAudio(objA, namePlaylist);
             }
              else {
               msg="this user is not premiun or standard";
             }
            }
         }
         
        }
 
          
        return msg;
    }
    


    public String shareplaylis(String nickname,String namePlaylist){
      String code=" ";
      User objU = searchUser(nickname);

      if (objU == null) {
          code = "the user doesn't exist";
      } else {
        if(objU instanceof Standard){
            Standard obj = (Standard) objU; 
             code=obj.sharePlaylist(namePlaylist);
        }
        else if(objU instanceof Premium){
            Premium obj = ( Premium) objU; 
            obj.sharePlaylist(namePlaylist);
        }
        else{
            code="you must enter a user type consumer";
        }
      }

      return code;
    }


   





}