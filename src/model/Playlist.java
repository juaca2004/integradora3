package model;

import java.util.ArrayList;
//* */
public class Playlist{

    private String name;
    private ArrayList <Audio> audios;
    private int matriz[][];
    private String code;
    private TypePlaylist typePlaylist;


    public Playlist(String name, int[][] matriz) {
        this.name = name;
        this.matriz = matriz;
        typePlaylist=null;
        code=null;
        audios= new ArrayList<Audio>();
    }


    
    /** 
     * @return String
     */
    public String getName() {
        return name;
    }


    
    /** 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    
    /** 
     * @return ArrayList<Audio>
     */
    public ArrayList<Audio> getAudios() {
        return audios;
    }


    
    /** 
     * @param audios
     */
    public void setAudios(ArrayList<Audio> audios) {
        this.audios = audios;
    }


    
    /** 
     * @return int[][]
     */
    public int[][] getMatriz() {
        return matriz;
    }


    
    /** 
     * @param matriz
     */
    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }


    
    /** 
     * @return String
     */
    public String getCode() {
        return code;
    }


    
    /** 
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }




   
     
     /** 
      * @return TypePlaylist
      */
     public TypePlaylist getTypePlaylist() {
        return typePlaylist;
    }



    
    /** 
     * @param typePlaylist
     */
    public void setTypePlaylist(TypePlaylist typePlaylist) {
        this.typePlaylist = typePlaylist;
    }
    

     public char analyticsPlaylist(){
        char val= 'n';
        TypePlaylist route;
        int song=0;
        int podcast=0;
        if(audios.size()==0){
            val= 'n';
        }
        else{
            for(int i=0; i<audios.size();i++){
                if(audios .get(i) instanceof Song){
                 song++;
               }
               else{
                  podcast++;
               }
              }
      
              if(song==0 && podcast!=0){
                 route=TypePlaylist.PODCAST;
               } 
               else if(song!=0 && podcast==0){
                route=TypePlaylist  .SONG;
               }
                else{
                route=TypePlaylist  .SONGPODCAST;
                }

            if(route!=typePlaylist){
                val='f';
            }
            else if(route==typePlaylist){
                val='t';
            }
        }
        return val;
     }

     public void changeTypePlaylist(){
        int song=0;
        int podcast=0;
        for(int i=0; i<audios.size();i++){
            if(audios .get(i) instanceof Song){
             song++;
           }
           else{
              podcast++;
           }
          }
  
          if(song==0 && podcast!=0){
             typePlaylist=TypePlaylist.PODCAST;
           } 
           else if(song!=0 && podcast==0){
            typePlaylist=TypePlaylist  .SONG;
           }
            else{
                typePlaylist=TypePlaylist  .SONGPODCAST;
            }
    
     }


         /** 
    *typePlaylist
    *the method return a number depend of the playlist type
    *<b>pre:</b> the object is created.<br>
    *<b>post:</b> The method returns a int
     * @return int
     */
    public int typePlaylist(){
        switch (typePlaylist) {
            case SONG:
                return 1;
            case PODCAST:
                return 2;
            case SONGPODCAST:
                return 3;
            default:
             return 0;
     }  

    }

}

