package model;
//* */

public class Audio{
    private String name;
    private String url;
    private int duration;
    private int view;


    public Audio(String name, String url, int duration) {
        this.name = name;
        this.url = url;
        this.duration = duration;
        view = 0;
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
     * @return String
     */
    public String getUrl() {
        return url;
    }


    
    /** 
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }


    
    /** 
     * @return String
     */
    public int getDuration() {
        return duration;
    }


    
    /** 
     * @param duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }


    
    /** 
     * @return int
     */
    public int getView() {
        return view;
    }


    
    /** 
     * @param view
     */
    public void setView(int view) {
        this.view = view;
    }

    



    
}