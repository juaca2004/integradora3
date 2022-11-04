package model;

import java.util.ArrayList;
//* */
public class Playlist{

    private String name;
    private ArrayList <Audio> audios;
    private int matriz[][];
    private String code;
    private TypePlaylist typePlaylist;


    public Playlist(String name, int[][] matriz, String code,int option) {
        this.name = name;
        this.matriz = matriz;
        this.code = code;
        audios= new ArrayList<Audio>();
        switch(option){
            case 1:
            typePlaylist=TypePlaylist.SONG ;
            break;
            case 2:
            typePlaylist=TypePlaylist.PODCAST ;
            break;
            case 3:
            typePlaylist=TypePlaylist.SONGPODCAST;
            break;
        }
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