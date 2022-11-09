package model;

import java.util.Calendar;
//* */
public abstract class Consumer extends User {

    public Consumer(String nickname, String id,Calendar bondingdate) {
        super(nickname, id, bondingdate);
    }

    public abstract boolean addPlaylist(String name, int[][] matriz);

    public abstract Playlist searchPlaylist(String name);

    public abstract  String addAudioPlaylist(String namePlaylist, Audio audio);

    public abstract  boolean searchAudioPlaylist(Audio audio,Playlist playlist);
    
    public abstract  String elimanteAudio(Audio auido, String namePlaylist );
    
    public abstract  String generateCode(int[][]matrizint,int typePlaylist);

    public abstract String sharePlaylist(String namePlaylist);


    
    
}
