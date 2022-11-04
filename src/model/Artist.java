package model;

import java.util.Calendar;
import java.util.ArrayList;
//* */

public class Artist extends Producer{
    private ArrayList <Song> songs;

public Artist(String nickname, String id, Calendar bondingdate, String url, String name) {
        super(nickname, id, bondingdate, url, name);
        songs= new ArrayList<Song>();
    }


/** 
 * @return ArrayList<Song>
 * 
 */
public ArrayList<Song> getSongs() {
    return songs;
}


/** 
 * @param songs
 */
public void setSongs(ArrayList<Song> songs) {
    this.songs = songs;
}

    

    
}