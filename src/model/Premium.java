package model;

import java.util.Calendar;

import java.util.ArrayList;
//* */
public class Premium extends Consumer implements Editplaylist,CreatePlaylist{

    private ArrayList <Playlist> playlist;

    public Premium(String nickname, String id, Calendar bondingdate) {
        super(nickname, id, bondingdate);
        playlist= new ArrayList<Playlist>();

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
    public boolean addPlaylist(String name, int[][] matriz, String code,int option){
        boolean msg = true;
        Playlist objP = searchPlaylist(name);

        if (objP == null) {
            msg = false;
        } 
        else {
          playlist.add(new Playlist(name, matriz, code, option));       
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
    public String addAudioPlaylist(String namePlaylist,int typeAudio, Audio audio){
        String msg="";
         Playlist objP=searchPlaylist(namePlaylist);
         if(objP==null){
            msg="dont exist the playslist";
         }
         else{
            if(objP.typePlaylist()==1){
                if(objP.typePlaylist()==typeAudio){
                    boolean audiorepit=searchAudioPlaylist(audio, objP);
                    if(audiorepit){
                        objP.getAudios().add(audio);
                    }
                    else{
                        msg="the audio is repit";
                    }
                }
                else{
                    msg="you cant add this audio beacuse is difertent type of playlist";
                }
            }
            if(objP.typePlaylist()==2){
                if(objP.typePlaylist()==typeAudio){
                    boolean audiorepit=searchAudioPlaylist(audio, objP);
                    if(audiorepit){
                        objP.getAudios().add(audio);
                    }
                    else{
                        msg="the audio is repit";
                    }
                }
                else{
                    msg="you cant add this audio beacuse is difertent type of playlist";
                }
            }
            if(objP.typePlaylist()==3){
                
                    boolean audiorepit=searchAudioPlaylist(audio, objP);
                    if(audiorepit){
                        objP.getAudios().add(audio);
                    }
                    else{
                        msg="the audio is repit";
                    }
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

     

    
}