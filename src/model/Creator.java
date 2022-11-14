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


    

    
    /** 
    *searchAudioAutor
    *this method sees if an audio is from a creator
    *<b>pre:</b> data must be entered.<br>
    *<b>post:</b> The method returns a boolean
     * @param podcast
     * @return boolean
     */
    public boolean searchAudioAutor(Podcast podcast){
        boolean isFound= false;
        for(int i=0;i<podcasts.size() && !isFound ;i++){
            if( podcasts.get(i) == podcast){
                isFound= true;
            }
         }
    
        return isFound;
        
    }


    
}