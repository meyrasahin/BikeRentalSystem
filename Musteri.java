public class Musteri {
    private int ID;
    private String time;

    public Musteri(int ID, String time ){
        this.ID = ID;
        this.time = time;
    }

    @Override
    public String toString(){
        return String.format("Müşteri ID :%4d  Kiralama saati : %5s", getID(), getTime());
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }
}
