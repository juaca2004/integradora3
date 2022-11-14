package model;

import java.util.Calendar;

import java.util.ArrayList;
//* */
public class Premium extends Consumer implements Playing {

    private ArrayList <Playlist> playlist;
    private ArrayList <Audio> audios;

    public Premium(String nickname, String id, Calendar bondingdate) {
        super(nickname, id, bondingdate);
        playlist= new ArrayList<Playlist>();
        audios= new ArrayList<Audio>();

    }


    
    /** 
    *addPlaylist
    *register de playlist
    *<b>pre:</b> the name of the playlist cant repit.<br>
    *<b>post:</b> returns a boolean wiht is created or not 
     * @param name
     * @param matriz
     * @param code
     * @param option
     * @return boolean
     */
    @Override
    public boolean addPlaylist(String name, int[][] matriz){
        boolean msg = true;
        Playlist objP = searchPlaylist(name);

        if (objP == null) {
            msg = false;
        } 
        else {
          playlist.add(new Playlist(name, matriz));       
             }

        return msg;
    }

    
    
    /** 
    *searchPlaylist
    *the method looks for a user with the name passed by parameter
    *<b>pre:</b> a string data must be entered.<br>
    *<b>post:</b> The method returns whether or not there is a user with that nicknmae
     * @param name
     * @return Playlist
     */
    @Override
    public Playlist searchPlaylist(String name){
        Playlist obj=null;
        boolean val= false;
         for(int i=0;i<playlist.size() && !val ;i++){
            if( playlist.get(i).getName().equalsIgnoreCase(name)){
                obj=playlist.get(i);
                val= true;
            }
         }
    
        return obj;
    }

    
    /** 
    *addAudioPlaylist
    *subroutine add aduio in playlist
    *<b>pre:</b>the values had to be entered to add audio in playlist.<br>
    *<b>post:</b> pass the parameters to addTreasure which is in level
     * @param namePlaylist
     * @param typeAudio
     * @param audio
     * @return String
     */
    @Override
    public String addAudioPlaylist(String namePlaylist, Audio audio){
        String msg="";
         Playlist objP=searchPlaylist(namePlaylist);
         if(objP==null){
            msg="dont exist the playslist";
         }
         else{
            boolean audiorepit=searchAudioPlaylist(audio, objP);
                    if(audiorepit){
                        objP.getAudios().add(audio);
                        msg="the audio added with exit";
                    }
                    else{
                        msg="the audio is repit";
                    }
                }
    
        return msg;
     }

     
     /** 
    *searchAudioPlaylist
    *the method looks exist a audio in a playlist
    *<b>pre:</b> data must be entered.<br>
    *<b>post:</b> The method returns a boolean 
      * @param audio
      * @param playlist
      * @return boolean
      */

      @Override
     public boolean searchAudioPlaylist(Audio audio,Playlist playlist){
       boolean val=false;
       if(playlist!=null){
        val=true;
       }
       else{
        boolean vali=false;
        for(int i=0;i<playlist.getAudios().size()&&!vali;i++){
            if(playlist.getAudios().get(i)==audio){
                vali=true;
            }
            if(i==playlist.getAudios().size()-1){
                val=true;
            }
        }
       }
        
       return val;
     }

     
     /** 
    *elimanteAudio
    *eliminate a audio in a playlist
    *<b>pre:</b> the playlist was created.<br>
    *<b>post:</b> returns a String is the was exit
      * @param auido
      * @param namePlaylist
      * @return String
      */
      @Override
     public String elimanteAudio(Audio auido, String namePlaylist ){
       String msg="";
       Playlist objP=searchPlaylist(namePlaylist);
       if(objP==null){
          msg="dont exist the playslist";
       }
       else{
          boolean audiorepit=searchAudioPlaylist(auido, objP);
          if(audiorepit==false){
            objP.getAudios().remove(auido);
            msg="has been removed successfully";
          }
          else{
            msg="no such audio found";
          }

          
       }
       return msg;
     }

    

    
    /** 
    *generateCode
    *this methot generate code depending of matriz and type of playlist
    *<b>pre:</b> the paramatrers was enter with exit.<br>
    *<b>post:</b> returns a String is the was exit
     * @param int[][]matriz
     * @param typePlaylist
     * @return String
     */
    @Override
    public String generateCode(int[][]matriz,int typePlaylist){
        String code="";
         switch(typePlaylist){
          case 1:
          for (int i = matriz.length; i > 0; i--) { // Gets the values of the first column of the matrix
            code += matriz[i - 1][0];
          }
              for (int i = 1, j = 1; i < matriz.length -1; i++, j++) { // Gets the values of the diagonal of the matrix           
                code+= matriz[i][j];      	
              }
          for (int i = matriz.length; i > 0; i--) { // Gets the values of the last column of the matrix
            code += matriz[i - 1][matriz[0].length - 1];
          }
           break;
  
          case 2:
          for (int j = 0; j < matriz.length -4; j++) { // Gets the values of the first row, since column zero until column two
            code+= matriz[0][j];
          }
          for (int i = 0; i < matriz.length; i++) { // Gets the values of the column two
            code += matriz[i][2];
          }
          for (int i = matriz.length; i > 0; i--) { // Gets the values of the column three
            code += matriz[i - 1][3];
          }
          for (int j = matriz.length -2; j < matriz.length; j++ ) { // Gets the values of the first row, since column four until column five
            code += matriz[0][j];
          }
  
           break;
             
           case 3:
           for (int i=5;i>=0;i--){
              for(int j=5;j>=0;j--){
                  int sum = i+j;
                  if (sum%2!=0){
                      if(sum!=1){
                          code+=matriz[i][j]+" ";
                      }
                  }
  
              }
          }
           break;
  
  
         }
  
        return code;
      }


    
    /** 
    *sharePlaylistMatriz
    *this methot show the matrix
    *<b>pre:</b> the paramatrers was enter with exit.<br>
    *<b>post:</b> returns a String is the was exit
     * @param namePlaylist
     * @return String
     */
    @Override
    public String sharePlaylistMatriz(String namePlaylist){
      String msg=" ";
      Playlist objP=searchPlaylist(namePlaylist);
      if(objP==null){
         msg=" ";
      }
      else{
        msg= printMatrix(objP.getMatriz());
      }

      return msg;
    }

    
    /** 
         *printMatrix
    *this methot print the matrix
    *<b>pre:</b> the paramatrers was enter with exit.<br>
    *<b>post:</b> returns a String is the was exit
     * @param matrix
     * @return String
     */
    @Override
    public String printMatrix(int[][] matrix) {
      String print = "";
      for (int i = 0; i < matrix.length; i++) { // filas numbers.length
        for (int j = 0; j < matrix[0].length; j++) { // columnas numbers[0].length
          print += matrix[i][j] + " ";
        }
        print += "\n";
      }
  
      return print;
    }

    
    /** 
              *sharePlaylist
    *this methot share the playlist
    *<b>pre:</b> the paramatrers was enter with exit.<br>
    *<b>post:</b> returns a String is the was exit
     * @param namePlaylist
     * @return String
     */
    @Override
    public String sharePlaylist(String namePlaylist) {
        String msg="";
        Playlist objP=searchPlaylist(namePlaylist);
        if(objP==null){
           msg="dont exist the playslist";
        }
        else{
         char val=objP.analyticsPlaylist();
          if(val=='n'){
           msg="there are no songs in the playlist so it cannot generate a code";
          }
          else if(val=='t'){
           msg=objP.getCode();
          }
          else if(val=='f'){
           objP.changeTypePlaylist();
          objP.setCode(generateCode(objP.getMatriz(),objP.typePlaylist() ));
           msg=objP.getCode();
          }

        }
       return msg;
    }
    
    /** 
    *sharePlaylist
    *this methot add a reproduction 
    *<b>pre:</b> the paramatrers was enter with exit.<br>
    *<b>post:</b> returns a String is the was exit
     * @param audio
     * @return String
     */
    @Override
    public String play(Audio audio){
      String msg="."+"\n"+"."+"\n"+"."+"\n"+"the audio is playing"+"\n";
      audios.add(audio);
      return msg;
    }

    
    /** 
         *mostSongViews
    *this methot show the most song view in this user 
    *<b>pre:</b> the paramatrers was enter with exit.<br>
    *<b>post:</b> returns a String is the was exit
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
          msg="the most listened to genre for this user: rock \n"+"views: "+geners[position];
          break;
          case 1:
          msg="the most listened to genre for this user: pop \n"+"views: "+geners[position];
          break;
          case 2:
          msg="the most listened to genre for this user: trap \n"+"views: "+geners[position];
          break;
          case 3:
          msg="the most listened to genre for this user: house \n"+"views: "+geners[position];
          break;
          case 4:
          msg="the dont exist song";
          break;
        }
        
      }
      else{
        msg="the user dont have reproduction";
      }
     return msg;
    }

    
    /** 
              *mostPodcastViews
    *this methot show the most song view in this user 
    *<b>pre:</b> the paramatrers was enter with exit.<br>
    *<b>post:</b> returns a String is the was exit
     * @return String
     */
    public String mostPodcastViews(){
      String msg="";
      int [] geners= {0,0,0,0};
      int position=0;
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
           msg="the most listened to genre for this user: Politic \n"+"views: "+geners[position];
           break;
           case 1:
           msg="the most listened to genre for this user: Entertaiment \n"+"views: "+geners[position];
           break;
           case 2:
           msg="the most listened to genre for this user: Fashion \n"+"views: "+geners[position];
           break;
           case 3:
           msg="the most listened to genre for this user: Videogame \n"+"views: "+geners[position];
           break;
           case 4:
           msg="the dont exist podcast";
           break;
         }
         
       }
       else{
         msg="the user dont have reproduction";
       }
      return msg;
     }

      


  
     

    
}