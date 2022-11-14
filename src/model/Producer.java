package model;

import java.util.Calendar;

public abstract class Producer extends User{

    private String url;
    private String name;
    private int totalViews;
    private int totalPLayedTime;

    
    public Producer(String nickname, String id, Calendar bondingdate, String url, String name) {
        super(nickname, id, bondingdate);
        this.url = url;
        this.name = name;
        totalViews =0;
        totalPLayedTime =0;
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
     * @return int
     */
    public int getTotalViews() {
        return totalViews;
    }


    
    /** 
     * @param totalViews
     */
    public void setTotalViews(int totalViews) {
        this.totalViews = totalViews;
    }


    
    /** 
     * @return int
     */
    public int getTotalPLayedTime() {
        return totalPLayedTime;
    }


    
    /** 
     * @param totalPLayedTime
     */
    public void setTotalPLayedTime(int totalPLayedTime) {
        this.totalPLayedTime = totalPLayedTime;
    }



    

    
}