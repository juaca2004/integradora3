package model;

import java.util.Calendar;
import java.util.ArrayList;
//* */
public class Creator extends Producer{
    private ArrayList <Podcast> podcasts;

    public Creator(String nickname, String id, Calendar bondingdate, String url, String name) {
        super(nickname, id, bondingdate, url, name);
        podcasts= new ArrayList<Podcast>();
    }

    
    /** 
     * @return ArrayList<Podcast>
     */
    public ArrayList<Podcast> getPodcasts() {
        return podcasts;
    }

    
    /** 
     * @param podcasts
     */
    public void setPodcasts(ArrayList<Podcast> podcasts) {
        this.podcasts = podcasts;
    }


    

    


    
}