public class Duraklar implements Comparable<Duraklar> {
    public String durakAdi;
    public int bosPark;
    public int tandemBisiklet;
    public int normalBisiklet;

    public Duraklar(String durakAdi, int bosPark, int tandemBisiklet, int normalBisiklet){    // yapıcı metod
        this.durakAdi = durakAdi;
        this.bosPark = bosPark;
        this.tandemBisiklet = tandemBisiklet;
        this.normalBisiklet = normalBisiklet;
    }

    @Override
    public String toString(){
        return String.format("Durak Adı: %-20s  Boş Park Sayısı: %4d  "
                        + "Tandem Bisiklet Sayısı : %4d  Normal Bisiklet Sayısı : %4d",
                durakAdi, bosPark, tandemBisiklet, normalBisiklet);
    }

    @Override
    public int compareTo(Duraklar otherDurak) {
        if(normalBisiklet == otherDurak.normalBisiklet)
            return 0;
        else if (normalBisiklet < otherDurak.normalBisiklet)
            return -1;
        else
            return 1;
    }


}
