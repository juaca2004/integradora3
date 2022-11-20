package model;

import java.util.ArrayList;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
//* */

public class MusicApp {

    private ArrayList <User> users;
    private ArrayList <Audio> audios;
    private ArrayList<Shop> shops;

    public MusicApp() {
        users= new ArrayList<User>();
        audios= new ArrayList<Audio>();
        shops = new ArrayList<Shop>(); 
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

    
    /** 
     * @param minutes
     * @param second
     * @return int
     */
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
            int [][]matriz=generateMatriz();
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
        
         Random r= new Random();
       
         int value = r.nextInt(8 + 1) + 1;
       
        return value;
       
       }

    
    /** 
    *generateMatriz
    *the method generate a matriz
     * @return int[][]
     */
    public int[][] generateMatriz(){
      int matriz[][]=new int[6][6];

      for(int i=0;i<6;i++){
        for(int j=0;j<6;j++){
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
    


    
    /** 
    *shareplaylis
    *the method share the playslist
    *<b>pre:</b> the paramatrer was pass.<br>
    *<b>post:</b> The method returns a string is was exit
     * @param nickname
     * @param namePlaylist
     * @return String
     */
    public String shareplaylis(String nickname,String namePlaylist){
      String code=" ";
      User objU = searchUser(nickname);

      if (objU == null) {
          code = "the user doesn't exist";
      } else {
        if(objU instanceof Standard){
            Standard obj = (Standard) objU; 
             code=obj.sharePlaylist(namePlaylist)+"\n"+obj.sharePlaylistMatriz(namePlaylist);
        }
        else if(objU instanceof Premium){
            Premium obj = ( Premium) objU; 
            code=obj.sharePlaylist(namePlaylist)+"\n"+obj.sharePlaylistMatriz(namePlaylist);

        }
        else{
            code="you must enter a user type consumer";
        }
      }

      return code;
    }


  
  /** 
    *playAudio
    *the method simulate a playback
    *<b>pre:</b> the paramatrer was pass.<br>
    *<b>post:</b> The method returns a string is was exit
   * @param nickname
   * @param audio
   * @return String
   */
  public String playAudio(String nickname, String audio){
   String msg="";
   User objU = searchUser(nickname);

      if (objU == null) {
          msg = "the user doesn't exist";
      } else {
        Audio objA= searchAudio(audio);
        if(objA== null){
            msg="the audio doesn't exist";
        }

        else{
            if(objU instanceof Standard){
                Standard obj = (Standard) objU; 
                 msg=obj.play(objA);
                 updateState(objA);
            }
            else if(objU instanceof Premium){
                Premium obj = ( Premium) objU; 
                msg=obj.play(objA);
                updateState(objA);
    
            }
            else{
                msg="you must enter a user type consumer";
            }

      }
  }
  return msg;
 } 

 
 /**
  *updateState
  *the method update the states of diferent class
  *<b>pre:</b> the paramatrer was pass.<br> 
  * @param audio
  */
 public void updateState(Audio audio){
    if(audio instanceof Song){
        Song objA = (Song) audio;
        boolean val=false;
        for(int i=0;i<users.size() && !val;i++){
            if(users.get(i) instanceof Artist){
                Artist obj = (Artist) users.get(i);
                if(obj.searchAudioAutor(objA)){
                    obj.setTotalViews(obj.getTotalPLayedTime()+1);
                    obj.setTotalPLayedTime(objA.getDuration()+obj.getTotalPLayedTime());
                    objA.setView(objA.getView()+1);
                    val=true;
                }
            }
        }

  }
  else if(audio instanceof Podcast){
    Podcast objA = ( Podcast) audio;
        boolean val=false;
        for(int i=0;i<users.size() && !val;i++){
            if(users.get(i) instanceof Creator){
                Creator obj = (Creator) users.get(i);
                if(obj.searchAudioAutor(objA)){
                    obj.setTotalViews(obj.getTotalPLayedTime()+1);
                    obj.setTotalPLayedTime(objA.getDuration()+obj.getTotalPLayedTime());
                    objA.setView(objA.getView()+1);
                    val=true;
                }
            }
        }

  }
 }


 
 /** 
  *buySong
  *the method buy a song 
  *<b>pre:</b> the paramatrer was pass.<br>
  *<b>post:</b> The method returns a string is was exit
  * @param nickname
  * @param nameSong
  * @return String
  */
 public String buySong(String nickname, String nameSong){

    String msj = ""; 
    User user = searchUser(nickname);

    if(user == null){
        msj = "this user doesnt exist";
    }
    else{
        Audio newAudio = searchAudio(nameSong);
        if(newAudio == null){
            msj = "the audio doesnt exist";
        }

        else{
            if(newAudio instanceof Song){
                Song newSong = ( (Song)(newAudio) );
                if(user instanceof Standard){
                    int numBuys = countBuysForUser(nickname);
                    if(numBuys<100){
                        Shop newShop = new Shop( nickname, nameSong,dateActual()); 
                        shops.add(newShop); 
                        newSong.setNumberSales(newSong.getNumberSales()+1);

                    }else{
                        msj = "the purchasing limit has been reached "; 
                    }

                } else if( user instanceof Premium){
                    Shop newShop = new Shop( nickname, nameSong,dateActual()); 
                    shops.add(newShop); 
                    newSong.setNumberSales(newSong.getNumberSales()+1);

                }
            }
            else if(newAudio instanceof Podcast){
                msj = "is not posible buy a podcast";
                
            }
            else{
                msj = "must enter a user type consumer"; 
            }
        } 

    }
    return msj; 

}



/** 
 *countBuysForUser
 *the method the times a user bought a song
 *<b>pre:</b> the paramatrer was pass.<br>
 *<b>post:</b> The method returns a string is was exit
 * @param nickname
 * @return int
 */
public int countBuysForUser(String nickname){
    int count =0; 
    if(shops.size() != 0){

        for(int i = 0; i <shops.size(); i++ ){
            shops.get(i).getNickname().equalsIgnoreCase(nickname);
            count++;
        }

    }
    return count; 

}



/** 
  *infoTotalViews
 *the method shaw the total views in all plataform
 *<b>pre:</b> the musicApp was exist.<br>
 *<b>post:</b> The method returns a int is was exit
 * @return int
 */
public int infoTotalViews(){
    int totalViews=0;
    if(audios.size()!=0){
        for(int i=0;i<audios.size();i++){
          totalViews+=audios.get(i).getView();
        }
    }
    return totalViews;
}



/** 
 *infoMostViewSong
 *the method show the song most view for a user and all plataform 
 *<b>pre:</b> the musicApp was exist .<br>
 *<b>post:</b> The method returns a String is was exit
 * @param nickname
 * @return String
 */
public String infoMostViewSong(String nickname){
    String msj = ""; 
    User user = searchUser(nickname);

    if(user == null){
        msj = "this user doesnt exist";
    }
    else{

        if(user instanceof Standard){
            Standard obj = (Standard) user; 
             msj=mostSongViews() +"\n"+ obj.mostSongViews();
        }
        else if(user instanceof Premium){
            Premium obj = ( Premium) user; 
            msj=mostSongViews() +"\n"+ obj.mostSongViews();

        }
        else{
            msj="you must enter a user type consumer";
        }
    }
    return msj;
}


/** 
 *infoMostViewSong
 *the method show the podcast most view for a user and all plataform 
 *<b>pre:</b> the musicApp was exist.<br>
 *<b>post:</b> The method returns a String is was exit
 * @param nickname
 * @return String
 */
public String infoMostViewPodcast(String nickname){
    String msj = ""; 
    User user = searchUser(nickname);

    if(user == null){
        msj = "this user doesnt exist";
    }
    else{

        if(user instanceof Standard){
            Standard obj = (Standard) user; 
             msj=mostPodcastViews() + obj.mostPodcastViews();
        }
        else if(user instanceof Premium){
            Premium obj = ( Premium) user; 
            msj=mostPodcastViews() + obj.mostPodcastViews();

        }
        else{
            msj="you must enter a user type consumer";
        }
    }
    return msj;
}


/** 
 *mostSongViews
 *the method show the genre song most views
 *<b>pre:</b> the musicApp was exist.<br>
 *<b>post:</b> The method returns a String is was exit
 * @return String
 */
public String mostSongViews(){
    String msg="";
    int [] geners= {0,0,0,0};
    int position=0;
     if(audios.size()!=0){
       for(int i=0; i<audios.size();i++){
         if(audios.get(i) instanceof Song){
           Song song = ( (Song)(audios.get(i)) );
           switch(song.typeSong()){
             case 1:
              geners[0]++;
              break;
             case 2:
             geners[1]++;
              break;
             case 3:
             geners[2]++;
              break;
             case 4:
             geners[3]++;
              break;
             default:
              break;
           }
         }
       }
       int mayor=0;
        for(int i=0; i<4;i++){
         if(geners[i]>mayor){
           position=i;
         }
        }
       switch(position){
         case 0:
         msg="\n the most listened to genre  rock \n"+"views: "+geners[position];
         break;
         case 1:
         msg="\n the most listened to genre: pop \n"+"views: "+geners[position];
         break;
         case 2:
         msg="\n the most listened to genre : trap \n"+"views: "+geners[position];
         break;
         case 3:
         msg="\n the most listened to genre: house \n"+"views: "+geners[position];
         break;
         case 4:
         msg="the dont exist song";
         break;
       }
       
     }
     else{
       msg="the plataform dont have audios";
     }
    return msg;
   }

   
   /** 
 * mostPodcastViews
 *the method show the genre podcast most views
 *<b>pre:</b> the musicApp was exist.<br>
 *<b>post:</b> The method returns a String is was exit
    * @return String
    */
   public String mostPodcastViews(){
    String msg="";
    int [] geners= {0,0,0,0};
    int position=4;
     if(audios.size()!=0){
       for(int i=0; i<audios.size();i++){
         if(audios.get(i) instanceof Podcast){
          Podcast podcast = ( (Podcast)(audios.get(i)) );
           switch(podcast.typePodcast()){
             case 1:
              geners[0]++;
              break;
             case 2:
             geners[1]++;
              break;
             case 3:
             geners[2]++;
              break;
             case 4:
             geners[3]++;
              break;
             default:
              break;
           }
         }
       }
       int mayor=0;
        for(int i=0; i<4;i++){
         if(geners[i]>mayor){
           position=i;
         }
        }
       switch(position){
         case 0:
         msg="the most listened to genre: Politic \n"+"views: "+geners[position];
         break;
         case 1:
         msg="the most listened to genre: Entertaiment \n"+"views: "+geners[position];
         break;
         case 2:
         msg="the most listened to genre : Fashion \n"+"views: "+geners[position];
         break;
         case 3:
         msg="the most listened to genre: Videogame \n"+"views: "+geners[position];
         break;
         case 4:
         msg="the dont exist podcast";
         break;
       }
       
     }
     else{
       msg="the plataform dont have audios";
     }
    return msg;
   }


   
   /** 
    * topArtist
 *the method show the top of Artist
 *<b>pre:</b> the musicApp was exist.<br>
 *<b>post:</b> The method returns a String is was exit
    * @return String
    */
   public String topArtist(){
    String massage="";
    int top1 = 0;
		int top2 = 0;
		int top3 = 0;
		int top4 = 0;
		int top5 = 0;
		String name1 = " ";
		String name2 = " ";
		String name3 = " ";
		String name4 = " ";
		String name5 = " "; 
       if(users.size()!=0){

		for(int i = 0; i<users.size(); i++){
           if(users.get(i) instanceof Artist){
            Artist obj = (Artist) users.get(i); 
			if( obj.getTotalViews() > top1){
				
				top5 = top4;
				top4= top3;
				top3= top2;
				top2 = top1;
				top1 = obj.getTotalViews();
				name5 = name4;
				name4 = name3;
				name3 = name2;
				name2= name1; 
				name1 =obj.getName(); 
				
			} else if(obj.getTotalViews() > top2){
				
				top5 = top4;
				top4= top3;
				top3= top2;
				top2 = obj.getTotalViews(); 
				name5 = name4;
				name4 = name3;
				name3 = name2;
				name2= obj.getName();

			}else if( obj.getTotalViews() > top3){

				top5 = top4;
				top4= top3;
				top3= obj.getTotalViews(); 
				name5 = name4;
				name4 = name3;
				name3 = obj.getName();

			} else if( obj.getTotalViews() > top4){

				top5 = top4;
				top4= obj.getTotalViews(); 
				name5 = name4;
				name4 = obj.getName();

			}else if( obj.getTotalViews() > top5){

				top5 = obj.getTotalViews();
				name5 =  obj.getName(); 
		
			}
        }

      if(top1==0){
        massage="there are no registered Artist";
      }
      else{
        massage = "top 5 artist \n" +
        "1."+ name1 + ": " + top1 + "\n"+
        "2."+ name2 + ": " + top2 + "\n"+
        "3."+ name3 + ": " + top3 + "\n"+
        "4."+ name4 + ": " + top4 + "\n"+
        "5."+ name5 + ": " + top5 + "\n"; 
      }

		}
       }
    else{
        massage="there are no registered users";
    }
    return massage;
   }


   
   /** 
       * topCreator
 *the method show the top of Creator
 *<b>pre:</b> the musicApp was exist.<br>
 *<b>post:</b> The method returns a String is was exit
    * @return String
    */
   public String topCreator(){
    String massage="";
    int top1 = 0;
		int top2 = 0;
		int top3 = 0;
		int top4 = 0;
		int top5 = 0;
		String name1 = " ";
		String name2 = " ";
		String name3 = " ";
		String name4 = " ";
		String name5 = " "; 
       if(users.size()!=0){

		for(int i = 0; i<users.size(); i++){
           if(users.get(i) instanceof Creator){
            Creator obj = (Creator) users.get(i); 
			if( obj.getTotalViews() > top1){
				
				top5 = top4;
				top4= top3;
				top3= top2;
				top2 = top1;
				top1 = obj.getTotalViews();
				name5 = name4;
				name4 = name3;
				name3 = name2;
				name2= name1; 
				name1 =obj.getName(); 
				
			} else if(obj.getTotalViews() > top2){
				
				top5 = top4;
				top4= top3;
				top3= top2;
				top2 = obj.getTotalViews(); 
				name5 = name4;
				name4 = name3;
				name3 = name2;
				name2= obj.getName();

			}else if( obj.getTotalViews() > top3){

				top5 = top4;
				top4= top3;
				top3= obj.getTotalViews(); 
				name5 = name4;
				name4 = name3;
				name3 = obj.getName();

			} else if( obj.getTotalViews() > top4){

				top5 = top4;
				top4= obj.getTotalViews(); 
				name5 = name4;
				name4 = obj.getName();

			}else if( obj.getTotalViews() > top5){

				top5 = obj.getTotalViews();
				name5 =  obj.getName(); 
		
			}
        }

      if(top1==0){
        massage="there are no registered Creator";
      }
      else{
        massage = "top 5 Creator \n" +
        "1."+ name1 + ": " + top1 + "\n"+
        "2."+ name2 + ": " + top2 + "\n"+
        "3."+ name3 + ": " + top3 + "\n"+
        "4."+ name4 + ": " + top4 + "\n"+
        "5."+ name5 + ": " + top5 + "\n"; 
      }

		}
       }
    else{
        massage="there are no registered users";
    }
    return massage;
   }


   
   /** 
           *  topSong
 *the method show the top of Song
 *<b>pre:</b> the musicApp was exist.<br>
 *<b>post:</b> The method returns a String is was exit
    * @return String
    */
   public String topSong(){
    String msj = ""; 
    int top1 = 0;
    int top2 = 0;
    int top3 = 0;
    int top4 = 0;
    int top5 = 0;
    int top6 = 0;
    int top7 = 0;
    int top8 = 0;
    int top9 = 0;
    int top10 = 0;
    String type1 = "";
    String type2 = "";
    String type3 = "";
    String type4 = "";
    String type5 = "";
    String type6 = "";
    String type7 = "";
    String type8 = "";
    String type9 = "";
    String type10 = "";
    String name1 = "";
    String name2 = "";
    String name3 = "";
    String name4 = "";
    String name5 = "";
    String name6 = "";
    String name7 = "";
    String name8 = "";
    String name9 = "";
    String name10 = ""; 

    if(audios.size()!=0){
        for(int i = 0; i<audios.size(); i++){
           if(audios.get(i) instanceof Song){
            Song obj = (Song) audios.get(i); 
            if( obj.getView() > top1){
                
                top10=top9; 
                top9 = top8;
                top8= top7;
                top7= top6;
                top6 = top5;
                top5 = top4;
                top4= top3;
                top3= top2;
                top2 = top1;
                top1 = obj.getView();
                name10= name9;
                name9= name8;
                name8= name7;
                name7= name6;
                name6= name5;
                name5 = name4;
                name4 = name3;
                name3 = name2;
                name2= name1; 
                name1 =obj.getName(); 
                type10 = type9;
                type9 = type8;
                type8= type7;
                type7 = type6;
                type6 = type5;
                type5 = type4;
                type4 = type3;
                type3 = type2;
                type2 = type1;
                type1 = obj.typeStringSong(); 
                
            } else if(obj.getView() > top2){
                
                top10=top9; 
                top9 = top8;
                top8= top7;
                top7= top6;
                top6 = top5;
                top5 = top4;
                top4= top3;
                top3= top2;
                top2 = obj.getView(); 
                name10= name9;
                name9= name8;
                name8= name7;
                name7= name6;
                name6= name5;
                name5 = name4;
                name4 = name3;
                name3 = name2;
                name2= obj.getName();
                type10 = type9;
                type9 = type8;
                type8= type7;
                type7 = type6;
                type6 = type5;
                type5 = type4;
                type4 = type3;
                type3 = type2;
                type2 = obj.typeStringSong();

            }else if( obj.getView() > top3){

                top10=top9; 
                top9 = top8;
                top8= top7;
                top7= top6;
                top6 = top5;
                top5 = top4;
                top4= top3;
                top3= obj.getView(); 
                name10= name9;
                name9= name8;
                name8= name7;
                name7= name6;
                name6= name5;
                name5 = name4;
                name4 = name3;
                name3 = obj.getName();
                type10 = type9;
                type9 = type8;
                type8= type7;
                type7 = type6;
                type6 = type5;
                type5 = type4;
                type4 = type3;
                type3 = obj.typeStringSong(); 


            } else if( obj.getView() > top4){

                top10=top9; 
                top9 = top8;
                top8= top7;
                top7= top6;
                top6 = top5;
                top5 = top4;
                top4= obj.getView(); 
                name10= name9;
                name9= name8;
                name8= name7;
                name7= name6;
                name6= name5;
                name5 = name4;
                name4 = obj.getName();
                type10 = type9;
                type9 = type8;
                type8= type7;
                type7 = type6;
                type6 = type5;
                type5 = type4;
                type4 = obj.typeStringSong();


            }else if( obj.getView() > top5){

                top10=top9; 
                top9 = top8;
                top8= top7;
                top7= top6;
                top6 = top5;
                top5 = obj.getView();
                name10= name9;
                name9= name8;
                name8= name7;
                name7= name6;
                name6= name5;
                name5 =  obj.getName(); 
                type10 = type9;
                type9 = type8;
                type8= type7;
                type7 = type6;
                type6 = type5;
                type5 = obj.typeStringSong();
        
            } else if(obj.getView() > top6){
                top10=top9; 
                top9 = top8;
                top8= top7;
                top7= top6;
                top6 = obj.getView();
                name10= name9;
                name9= name8;
                name8= name7;
                name7= name6;
                name6= obj.getName();
                type10 = type9;
                type9 = type8;
                type8= type7;
                type7 = type6;
                type6 = obj.typeStringSong();

            } else if( obj.getView()>top7){
                top10=top9; 
                top9 = top8;
                top8= top7;
                top7= obj.getView();
                name10= name9;
                name9= name8;
                name8= name7;
                name7= obj.getName();
                type10 = type9;
                type9 = type8;
                type8= type7;
                type7 = obj.typeStringSong();
            } else if(obj.getView()> top8){
                top10=top9; 
                top9 = top8;
                top8= obj.getView();
                name10= name9;
                name9= name8;
                name8= obj.getName();
                type10 = type9;
                type9 = type8;
                type8= obj.typeStringSong(); 
            } else if(obj.getView()> top9){
                top10=top9; 
                top9 = obj.getView();
                name10= name9;
                name9= obj.getName();
                type10 = type9;
                type9 = obj.typeStringSong();
            } else if(obj.getView()>10){
                top10 = obj.getView();
                name10 = obj.getName();
                type10 = obj.typeStringSong(); 
            }
        }

      if(top1==0){
        msj="there are not registered a song :( ";
      }
      else{
        msj = "top 10 songs \n" +
        "1."+ name1 + ": views " + top1 + " genre : "+ type1 + "\n"+
        "2."+ name2 + ": views " + top2 + " genre : "+ type2 +"\n"+ 
        "3."+ name3 + ": views " + top3 + " genre : "+ type3 +"\n"+
        "4."+ name4 + ": views " + top4 + " genre : "+ type4 +"\n"+
        "5."+ name5 + ": views " + top5 + " genre : "+ type5 +"\n" +
        "6."+ name6 + ": views " + top6 + " genre : "+ type6 +"\n" +
        "7."+ name7 + ": views " + top7 + " genre : "+ type7 +"\n" +
        "8."+ name8 + ": views " + top8 + " genre : "+ type8 +"\n" +
        "9."+ name9 + ": views " + top9 + " genre : "+ type9 +"\n" +
        "10."+ name10 + ": views " + top10 + " genre : "+ type10 +"\n";

      }

        }
       }
    else{
        msj="there are no registered songs";
    }
    return msj;

   }

   
   /** 
               *  topPodcast
 *the method show the top of Podcast
 *<b>pre:</b> the musicApp was exist.<br>
 *<b>post:</b> The method returns a String is was exit
    * @return String
    */
   public String topPodcast(){
    String msj = ""; 
    int top1 = 0;
    int top2 = 0;
    int top3 = 0;
    int top4 = 0;
    int top5 = 0;
    int top6 = 0;
    int top7 = 0;
    int top8 = 0;
    int top9 = 0;
    int top10 = 0;
    String type1 = "";
    String type2 = "";
    String type3 = "";
    String type4 = "";
    String type5 = "";
    String type6 = "";
    String type7 = "";
    String type8 = "";
    String type9 = "";
    String type10 = "";
    String name1 = "";
    String name2 = "";
    String name3 = "";
    String name4 = "";
    String name5 = "";
    String name6 = "";
    String name7 = "";
    String name8 = "";
    String name9 = "";
    String name10 = ""; 

    if(audios.size()!=0){
        for(int i = 0; i<audios.size(); i++){
           if(audios.get(i) instanceof Podcast){
            Podcast obj = (Podcast) audios.get(i); 
            if( obj.getView() > top1){
                
                top10=top9; 
                top9 = top8;
                top8= top7;
                top7= top6;
                top6 = top5;
                top5 = top4;
                top4= top3;
                top3= top2;
                top2 = top1;
                top1 = obj.getView();
                name10= name9;
                name9= name8;
                name8= name7;
                name7= name6;
                name6= name5;
                name5 = name4;
                name4 = name3;
                name3 = name2;
                name2= name1; 
                name1 =obj.getName(); 
                type10 = type9;
                type9 = type8;
                type8= type7;
                type7 = type6;
                type6 = type5;
                type5 = type4;
                type4 = type3;
                type3 = type2;
                type2 = type1;
                type1 = obj.typeStringPodcast(); 
                
            } else if(obj.getView() > top2){
                
                top10=top9; 
                top9 = top8;
                top8= top7;
                top7= top6;
                top6 = top5;
                top5 = top4;
                top4= top3;
                top3= top2;
                top2 = obj.getView(); 
                name10= name9;
                name9= name8;
                name8= name7;
                name7= name6;
                name6= name5;
                name5 = name4;
                name4 = name3;
                name3 = name2;
                name2= obj.getName();
                type10 = type9;
                type9 = type8;
                type8= type7;
                type7 = type6;
                type6 = type5;
                type5 = type4;
                type4 = type3;
                type3 = type2;
                type2 = obj.typeStringPodcast();

            }else if( obj.getView() > top3){

                top10=top9; 
                top9 = top8;
                top8= top7;
                top7= top6;
                top6 = top5;
                top5 = top4;
                top4= top3;
                top3= obj.getView(); 
                name10= name9;
                name9= name8;
                name8= name7;
                name7= name6;
                name6= name5;
                name5 = name4;
                name4 = name3;
                name3 = obj.getName();
                type10 = type9;
                type9 = type8;
                type8= type7;
                type7 = type6;
                type6 = type5;
                type5 = type4;
                type4 = type3;
                type3 = obj.typeStringPodcast(); 


            } else if( obj.getView() > top4){

                top10=top9; 
                top9 = top8;
                top8= top7;
                top7= top6;
                top6 = top5;
                top5 = top4;
                top4= obj.getView(); 
                name10= name9;
                name9= name8;
                name8= name7;
                name7= name6;
                name6= name5;
                name5 = name4;
                name4 = obj.getName();
                type10 = type9;
                type9 = type8;
                type8= type7;
                type7 = type6;
                type6 = type5;
                type5 = type4;
                type4 = obj.typeStringPodcast();


            }else if( obj.getView() > top5){

                top10=top9; 
                top9 = top8;
                top8= top7;
                top7= top6;
                top6 = top5;
                top5 = obj.getView();
                name10= name9;
                name9= name8;
                name8= name7;
                name7= name6;
                name6= name5;
                name5 =  obj.getName(); 
                type10 = type9;
                type9 = type8;
                type8= type7;
                type7 = type6;
                type6 = type5;
                type5 = obj.typeStringPodcast();
        
            } else if(obj.getView() > top6){
                top10=top9; 
                top9 = top8;
                top8= top7;
                top7= top6;
                top6 = obj.getView();
                name10= name9;
                name9= name8;
                name8= name7;
                name7= name6;
                name6= obj.getName();
                type10 = type9;
                type9 = type8;
                type8= type7;
                type7 = type6;
                type6 = obj.typeStringPodcast();

            } else if( obj.getView()>top7){
                top10=top9; 
                top9 = top8;
                top8= top7;
                top7= obj.getView();
                name10= name9;
                name9= name8;
                name8= name7;
                name7= obj.getName();
                type10 = type9;
                type9 = type8;
                type8= type7;
                type7 = obj.typeStringPodcast();
            } else if(obj.getView()> top8){
                top10=top9; 
                top9 = top8;
                top8= obj.getView();
                name10= name9;
                name9= name8;
                name8= obj.getName();
                type10 = type9;
                type9 = type8;
                type8= obj.typeStringPodcast(); 
            } else if(obj.getView()> top9){
                top10=top9; 
                top9 = obj.getView();
                name10= name9;
                name9= obj.getName();
                type10 = type9;
                type9 = obj.typeStringPodcast();
            } else if(obj.getView()>10){
                top10 = obj.getView();
                name10 = obj.getName();
                type10 = obj.typeStringPodcast(); 
            }
        }

      if(top1==0){
        msj="there are not registered a podcast :( ";
      }
      else{
        msj = "top 10 podcasts \n" +
        "1."+ name1 + ": views " + top1 + " genre : "+ type1 + "\n"+
        "2."+ name2 + ": views " + top2 + " genre : "+ type2 +"\n"+ 
        "3."+ name3 + ": views " + top3 + " genre : "+ type3 +"\n"+
        "4."+ name4 + ": views " + top4 + " genre : "+ type4 +"\n"+
        "5."+ name5 + ": views " + top5 + " genre : "+ type5 +"\n" +
        "6."+ name6 + ": views " + top6 + " genre : "+ type6 +"\n" +
        "7."+ name7 + ": views " + top7 + " genre : "+ type7 +"\n" +
        "8."+ name8 + ": views " + top8 + " genre : "+ type8 +"\n" +
        "9."+ name9 + ": views " + top9 + " genre : "+ type9 +"\n" +
        "10."+ name10 + ": views " + top10 + " genre : "+ type10 +"\n";

      }

        }
       }
    else{
        msj="there are no registered podcasts ";
    }
    return msj;


   }
   
   /** 
                   *  infoSongsSold
 *the method show the song most sold
 *<b>pre:</b> the musicApp was exist.<br>
 *<b>post:</b> The method returns a String is was exit
    * @return String
    */
   public String infoSongsSold(){
    String msj = ""; 
    int countRock = 0;
    int countPop =0;
    int countTrap =0;
    int countHouse =0; 
    if(audios.size()!=0){
            for(int i =0; i< audios.size(); i++){
                if(audios.get(i) instanceof Song){
                    Song song = ( (Song)(audios.get(i)) );
                    switch(song.typeSong()){
                     case 1:
                      countRock += song.getNumberSales(); 
                      break;
                     case 2:
                     countPop += song.getNumberSales(); 
                      break;
                     case 3:
                     countTrap += song.getNumberSales(); 
                      break;
                     case 4:
                     countHouse += song.getNumberSales(); 
                      break;

                     default:
                      break;
                   }

                }
            }
        }else{
             msj="the plataform dont have audios";
        }

        msj = "the number sales for genre is: \n" +
            "Rock: " + countRock + "\n"+
            "Pop: " +  countPop + "\n"+
            "Trap: " + countTrap +"\n"+
            "House: "+ countHouse + "\n"; 
        return msj; 

   }

   
   /** 
                       *  totalSalesSongs
 *the method show the total of sales
 *<b>pre:</b> the musicApp was exist.<br>
 *<b>post:</b> The method returns a String is was exit
    * @return String
    */
   public String totalSalesSongs(){
    String msj = ""; 
    double totalSales = 0.0; 
    if(audios.size()!=0){
            for(int i =0; i< audios.size(); i++){
                if(audios.get(i) instanceof Song){
                    Song song = ( (Song)(audios.get(i)) );
                    totalSales += song.getNumberSales() *  song.getPrice(); 
                }
            }
        }else{
            msj="the plataform dont have audios";
        }
        msj = " the total sales value of the songs is... " + totalSales; 
        return msj; 
   }
   
   /** 
                           *  mostSoldSong
 *the method show the of song most sold, show the number of sales and the price
 *<b>pre:</b> the musicApp was exist.<br>
 *<b>post:</b> The method returns a String is was exit
    * @return String
    */
   public String mostSoldSong(){
    String msj = ""; 
    int solds =0;
    String name = "";
    double totalSales =0.0; 

    if(audios.size() !=0){
        for(int i =0; i< audios.size(); i++){
            if(audios.get(i) instanceof Song){
                Song song = ( (Song)(audios.get(i)) );
                if(song.getNumberSales() > solds){
                    solds = song.getNumberSales();
                    name = song.getName(); 
                    totalSales = song.getNumberSales() * song.getPrice(); 
                }
            }
        }

    } else{
        msj = "the plataform dont have audios"; 
      }

      msj = "the most selling song is  " +  name + " to these sales " + solds + " and the total sales value " + totalSales; 
      return msj; 
   }




}