package model;
//* */
public interface Editplaylist{

    public String addAudioPlaylist(String namePlaylist,int typeAudio, Audio audio);
    public boolean searchAudioPlaylist(Audio audio,Playlist playlist);
    public String elimanteAudio(Audio auido, String namePlaylist );
    
}