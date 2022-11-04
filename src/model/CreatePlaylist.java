package model;
//* */
public interface CreatePlaylist{

    public boolean addPlaylist(String name, int[][] matriz, String code,int option);
    public Playlist searchPlaylist(String name);

    
}