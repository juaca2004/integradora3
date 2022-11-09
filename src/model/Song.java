package model;

public class Song extends Audio{

    private String album;
    private double price;
    private int numberSales;
    private Types_Song typeSong;
  

    public Song(String name, String url, int duration,String album, double price, int option){
        super(name,url,duration);
        this.album = album;
        this.price = price;
        this.numberSales = 0;

        switch(option){
            case 1:
            typeSong=Types_Song.ROCK;
            case 2:
            typeSong=Types_Song.POP;
            case 3:
            typeSong=Types_Song.TRAP;
            case 4:
            typeSong=Types_Song.HOUSE;

        }
    }


    
    /** 
     * @return String
     */
    public String getAlbum() {
        return album;
    }


    
    /** 
     * @param album
     */
    public void setAlbum(String album) {
        this.album = album;
    }


    
    /** 
     * @return double
     */
    public double getPrice() {
        return price;
    }


    
    /** 
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }


    
    /** 
     * @return int
     */
    public int getNumberSales() {
        return numberSales;
    }


    
    /** 
     * @param numberSales
     */
    public void setNumberSales(int numberSales) {
        this.numberSales = numberSales;
    }


    
    /** 
     * @return Types_Song
     */
    public Types_Song getTypeSong() {
        return typeSong;
    }


    
    /** 
     * @param typeSong
     */
    public void setTypeSong(Types_Song typeSong) {
        this.typeSong = typeSong;
    }
    

    
}


