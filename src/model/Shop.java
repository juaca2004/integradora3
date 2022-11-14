package model;
import java.util.Calendar;

public class Shop{

    private String nickname;
    private String nameSong;
    private Calendar dateOperation;

    public Shop(String nickname, String nameSong, Calendar dateOperation) {
        this.nickname = nickname;
        this.nameSong = nameSong;
        this.dateOperation = dateOperation;
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
    public String getNameSong() {
        return nameSong;
    }

    
    /** 
     * @param nameSong
     */
    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    
    /** 
     * @return Calendar
     */
    public Calendar getDateOperation() {
        return dateOperation;
    }

    
    /** 
     * @param dateOperation
     */
    public void setDateOperation(Calendar dateOperation) {
        this.dateOperation = dateOperation;
    }


    


    
}