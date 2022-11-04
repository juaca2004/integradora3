package model;

import java.util.Calendar;
//* */
public abstract class User {

    private String nickname;
    private String id;
    private Calendar bondingdate;


    public User(String nickname, String id,Calendar bondingdate) {
        this.nickname = nickname;
        this.id = id;
        this.bondingdate = bondingdate;
    }


    
    /** 
     * @return String
     */
    public String getNickname() {
        return nickname;
    }


    
    /** 
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    
    /** 
     * @return String
     */
    public String getId() {
        return id;
    }


    
    /** 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }


    
    /** 
     * @return Calendar
     */
    public Calendar getBondingdate() {
        return bondingdate;
    }


    
    /** 
     * @param bondingdate
     */
    public void setBondingdate(Calendar bondingdate) {
        this.bondingdate = bondingdate;
    }



     
    
}