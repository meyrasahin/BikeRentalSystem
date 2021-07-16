# BikeRentalSystem

Ege University - Computer Engineering Department
Data Structures Project 3

ARAMA AĞAÇLARI, YIĞINLAR ve HASH TABLOSU: BİSİKLET KİRALAMA SİSTEMİ  
Duraklar Nesnesi (Durak Adı, Boş Park, Tandem Bisiklet, Normal Bisiklet)

String[] duraklar = { “İnciraltı, 28, 2, 10”, “Sahilevleri, 8, 1, 11“, “Doğal Yaşam Parkı, 17, 1, 6”, “Bostanlı İskele, 7, 0, 5“ };
	
1)	a) duraklar dizisine 5 tane daha istasyonun bilgilerini ekleyiniz (bisim.com.tr gibi bisiklet kiralama sistemlerini araştırarak tahmini değerler yazabilirsiniz), 9’a tamamlayınız. duraklar dizisindeki string’leri sahalarına ayrıştırarak Durak Nesnelerini oluşturup, Durak Adı’na göre DurakAğacı adlı ikili arama ağacına yerleştiren kodu yazınız. Her bir Durak nesnesini ağaca eklerken, düğüme List tipinde bir veri yapısı içinde 1 ile 10 adet arasında random sayıda rastgele Müşteri (Müşteri ID, kiralama saati) ekleyiniz (Sistemde toplam 20 müşterinin kayıtlı olduğunu varsayınız ID:1 – ID:20). Boş park ve bisiklet sayılarını güncelleyiniz. Hazır ağaç kodlarından yararlanabilirsiniz. Sayısal elemanlar için uygun veri tiplerini belirleyiniz. 

b) Ağacın derinliğini bulduran metodu yazınız. Ağaçtaki tüm bilgileri (Listeye göre kaç müşterinin kiralama yaptığı bilgisi ve Liste içindeki bilgiler dahil) ekrana listeleyen metodu yazınız. 

c) Klavyeden verilen bir Müşteri ID’si için ağacın tüm düğümlerini dolaşarak içlerindeki listelerdeki bilgilere bakarak hangi istasyonlardan saat kaçta bisiklet kiraladığını ekrana listeleyen metodu yazınız.

d) Kiralama işlemi: Adı verilen bir istasyondan, Müşteri Numarası (ID) verilen kişinin normal bir bisiklet kiralama işlemini yapıp, Listeye ilgili bilgiyi (ID + random saat) ekleyen metodu yazınız. BP sayısı da 1 artacak. 

2) a) 1. Soruda belirtilen Durak Bilgilerini Durak Adı’na göre bir Hash Table’a yerleştiren kodu yazınız. [duraklar dizisini kullanabilirsiniz. Bu soruda müşteri eklemenize gerek yok]

b) Boş Park Yeri sayısı 5’ten fazla olan tüm istasyonlara 5’er tane normal bisiklet ekleyerek Hash Tablosunda güncelleyen kodu yazınız. 

3) a) C# / Java ile bir Heap Veri Yapısı (sınıfı) tasarlayınız. Altyapıda elemanlarını tutmak için dizi veya List / Vector kullanabilirsiniz. Kodlayıp çalıştırınız.  

b) Sadece Normal Bisiklet sayılarına göre düğümleri bir Max. Yığın’a yani Max. Heap’e (Java’daki PriorityQueue Heap düzenindedir) yerleştiren kodu yazınız. 

c) O anda en fazla Normal Bisiklet olan üç İstasyonu Heap’ten çekerek ilgili durak / istasyon bilgilerini listeleyen kodu yazınız. 

