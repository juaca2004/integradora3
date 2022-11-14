package model;

//* */
public class Podcast extends Audio{

    private String description;
    private Type_Podcast typePodcast;


    public Podcast(String name, String url, int duration,String description,int option) {
        super(name,url,duration);
        this.description = description;

        switch(option){
            case 1:
            typePodcast=Type_Podcast.POLITIC;
            case 2:
            typePodcast=Type_Podcast.ENTERTAIMENT;
            case 3:
            typePodcast=Type_Podcast.FASHION;
            case 4:
            typePodcast=Type_Podcast.VIDEOGAME;

        }
    }

    
    /** 
     * @return String
     */
    public String getDescription() {
        return description;
    }

    
    /** 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    
    /** 
     * @return Type_Podcast
     */
    public Type_Podcast getTypePodcast() {
        return typePodcast;
    }

    
    /** 
     * @param typePodcast
     */
    public void setTypePodcast(Type_Podcast typePodcast) {
        this.typePodcast = typePodcast;
    }

    
    /** 
    *typePodcast
    *the method return a number depend of the Podcast type
    *<b>pre:</b> the object is created.<br>
    *<b>post:</b> The method returns a int
     * @return int
     */
    public int typePodcast(){
        switch (typePodcast) {
            case POLITIC:
                return 1;
            case ENTERTAIMENT:
                return 2;
            case FASHION:
                return 3;
            case VIDEOGAME:
                return 4;
            default:
             return 0;
     }  

    }

    
    /** 
         *typeStringPodcast
    *the method return a String with the type of podcast
    *<b>pre:</b> the object is created.<br>
    *<b>post:</b> The method returns a String
     * @return String
     */
    public String typeStringPodcast(){
        String msj = ""; 
        switch(typePodcast){
        case POLITIC:
             msj = "politic";
            return msj;
        case ENTERTAIMENT:
             msj = "entertaiment";
            return msj;
        case FASHION:
             msj = "fashion";
            return msj;
        case VIDEOGAME:
             msj = "videogame";
            return msj;
        default:
            return null;  

        }
    }

    

    

    

    
}