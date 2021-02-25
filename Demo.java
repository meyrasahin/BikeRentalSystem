import java.util.Scanner;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Demo {
    public static void main(String[] args) {


        Tree agac = new Tree();
        Hashtable<Integer, Duraklar> hashtable = new Hashtable<>();

        String[] duraklar = {"Inciraltı, 28, 2, 10", "Sahilevleri, 8, 1, 11", "Doğal Yaşam Parkı, 17, 1, 6",
                "Bostanlı İskele, 7, 0, 5", "Pasaport İskele, 9, 3, 11", "Bornova Metro, 10, 1, 8", "Karşıyaka iskele, 13, 1, 6",
                "Konak Metro, 11, 0, 9", "Hasanaga Parkı, 10, 2, 7" };

        createTree(agac, duraklar);    // ağaç oluşturuluyor


        // yapılacak işlemlerin seçimi için menü yapısı kullanıldı
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nAkıllı Bisiklet Kiralama Sistemine Hoşgeldiniz!\n\nİşlem Menüsü\n_____________");
        System.out.println("1. Ağacın Derinliği\n2. Ağacı Listele\n3. Müşteri Hareketleri\n4. Bisiklet Kiralama\n5. HashTable Yapısını Listele\n6. HashTable Güncelleme\n7. Max Heap Yapısını Listele\n8. Bisiklet Sayısı En Fazla Olan Üç Durağı Listele\n0. Çıkış");
        System.out.print("\nSeçiminizi Giriniz:");
        int secim = scanner.nextInt();           // menü yazdırıldıktan sonra kullanıcından secim isteniyor

        while(secim > 0){
            if (secim == 1){       // ağacın derinliği isteniyorsa
                int maxDepth = agac.maxDepth(agac.getRoot());    // maxDepth() metodu yardımıyla hesaplanıyor
                System.out.println("\nAğacın Derinliği : " + maxDepth);
                System.out.println();
            }
            if (secim == 2){     // ağacın dolaşılması isteniyorsa
                System.out.println("\nAğaç Dolaşılıyor.\n__________________");
                agac.inOrder(agac.getRoot());
            }
            if (secim == 3){      // müşteri ID'sine göre müşteri hareketleri isteniyorsa
                System.out.print("Müşteri ID Bilgisini Giriniz : ");
                int value = scanner.nextInt();       // kullanıcıdan ID bilgisi alıyoruz

                List bulunan = agac.findID(value);    // findID() metodu yardımıyla müşterinin görüldüğü duraklar listeye atılıyor

                System.out.println("\nMüşteri ID: " + value + " Müşteri Hareketleri \n__________________________________");     // yazdırma işlemi yapılıyor
                for (int i = 0; i < bulunan.size()-1; i += 2)
                    System.out.format("%-20s  -->  %40s\n", bulunan.get(i),bulunan.get(i+1).toString() );

                System.out.println();
            }
            if (secim == 4){     // bisiklet kiralanmak isteniyorsa
                System.out.println("Hangi duraktan bisiklet kiralamak istiyorsunuz?");
                String durak = scanner.next();            // kullanıcıdan durak bilgisi alınıyor

                Musteri musteri = createMusteri();       // random bir müşteri oluşturuluyor

                TreeNode root = agac.getRoot();
                agac.rent(root, durak, musteri);     // agacın kökü, kullanıcıdan alınan durak adı ve oluşturulan müşteri

            }
            if (secim == 5){                 // hashtable yapısına dönüştürülmek istenirse
                if(hashtable.isEmpty())    // eğer daha önce hashTable doldurulmamışsa
                    agac.intoHashTable(agac.getRoot(), hashtable);       // intoHashTable() metodu yardımıyla dönüşüm yapılır

                System.out.println("\nHashTable Yapısından Yazdırılıyor.\n_________________________________");
                for (Map.Entry<Integer, Duraklar> e : hashtable.entrySet())
                    System.out.format("%-20s -->  %s\n", e.getValue().durakAdi, e.getValue().toString());
                System.out.println();
            }
            if (secim == 6){       // boş park sayısına göre hashtable güncellenmek isteniyorsa
                for (Map.Entry<Integer, Duraklar> e : hashtable.entrySet()){      // ilgili güncellemeler yapılıyor
                    if (e.getValue().bosPark > 5){
                        e.getValue().normalBisiklet += 5;
                        e.getValue().bosPark -= 5;
                    }
                }
                System.out.println("\nHashTable Güncellendi. Tekrar Yazdırılıyor.\n___________________________________________");
                for (Map.Entry<Integer, Duraklar> e : hashtable.entrySet())     // hashtable yazdırılıyor
                    System.out.format("%-20s -->  %s\n", e.getValue().durakAdi, e.getValue().toString());
                System.out.println();
            }
            if (secim == 7){       // max heap yapısından listelenmesi isteniyorsa
                MaxHeap maxHeap = new MaxHeap();    // max heap sınıfından nesne oluşturuluyor
                maxHeap.treeToMaxHeap(agac);          // oluşturulan nesne üzerinden treeToMaxHeap() metodu yardımıyla ağaç max heap yapısına aktarılıyor
                System.out.println("\nAğaç Üzerindeki Bilgiler Max Heap Yapısına Aktarıldı. Max Heap'ten Yazdırılıyor.\n________________________________________________________________________________");
                maxHeap.show();           // max heap yapısı yazdırılıyor
            }
            if (secim == 8){                // o anda bisiklet sayısı en fazla olan üç durak isteniyorsa
                MaxHeap maxHeap = new MaxHeap();          // max heap sınıfından nesne oluşturuluyor
                maxHeap.treeToMaxHeap(agac);           // ağaç, max heap yapısına aktarılıyor
                System.out.println("\nNormal Bisiklet Sayısı En Fazla Olan 3 Durak Yazdırılıyor.\n__________________________________________________________");
                maxHeap.get3Durak();          // get3Durak() metodu yardımıyla üç durak nesnesi getiriliyor
            }

            System.out.println("1. Ağacın Derinliği\n2. Ağacı Listele\n3. Müşteri Hareketleri\n4. Bisiklet Kiralama\n5. HashTable Yapısını Listele\n6. HashTable Güncelleme\n7. Max Heap Yapısını Listele\n8. Bisiklet Sayısı En Fazla Olan Üç Durağı Listele\n0. Çıkış");
            System.out.print("\nYeni seçiminizi giriniz:");
            secim = scanner.nextInt();
        }

    }

    static void createTree(Tree agac, String[] duraklar){
        List<Musteri> musteriler;

        for (String item : duraklar){     // duraklar dizisi dolaşılıyor
            String[] values = item.split(", ");
            String durakAdi = values[0];
            int bosPark = Integer.parseInt(values[1]);
            int tandemBisiklet = Integer.parseInt(values[2]);
            int normalBisiklet = Integer.parseInt(values[3]);

            Duraklar newDurak = new Duraklar(durakAdi, bosPark, tandemBisiklet, normalBisiklet);       // durak nesnesi oluşturuluyor

            musteriler = new ArrayList<>();     // her bir durak için musterileri tutacak list tanımlanıyor

            Random random = new Random();
            int musteriSayisi = 10 - random.nextInt(10);    // 1-10 arasında random müşteri oluşturuluyor
            int sayac = 0;
            for(int i =0; i<musteriSayisi; i++){
                Musteri newMusteri = createMusteri();       // createMusteri() metodu yardımıyla müşteri oluşturuluyor
                sayac ++;
                if(sayac < newDurak.normalBisiklet){
                    newDurak.normalBisiklet --;         // her müşteride normal bisiklet sayısı azaltılıyor
                    newDurak.bosPark ++;                // boş park sayısı artırılıyor
                    musteriler.add(newMusteri);         // oluşturulan müşteri listeye ekleniyor
                }
                else
                    break;
            }
            agac.insert(newDurak, musteriler);      // oluşturulan durak nesnesi vve müşteriler listesi ağaca ekleniyor
        }

    }


    static Musteri createMusteri(){
        Random random = new Random();
        int musteriID = 20 - random.nextInt(20);     // 1-20 arasında random ID oluşturuluyor

        String time = "";
        int hour = random.nextInt(24);       // 0-24 arasında random saat oluşturuluyor
        int second = random.nextInt(60);        // 0-60 arasında random dakika oluşturuluyor

        if(hour < 10 && second < 10 )
            time = "0" + hour + ".0" + second;
        else if(hour < 10)
            time = "0" + hour + "." + second;
        else if(second < 10)
            time = "" + hour + ".0" + second;
        else
            time = "" + hour + "." + second;

        Musteri newMusteri = new Musteri(musteriID, time);        // yeni müşteri oluşturuluyor ve döndürülüyor
        return newMusteri;
    }
}
