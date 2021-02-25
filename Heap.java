class Node{
    private int iData;

    public Node(int key) {     // yapıcı metod
        iData = key;
    }
    public int getKey(){         // iData değişkenine dışardan erişmek için
        return iData;
    }
}

public class Heap {
    private Node[] heapArray;       // düğümleri tutacak olan dizi
    private int maxSize;            // dizinin boyutu
    private int currentSize;        // dizinin anlık boyutu

    public Heap(int maxSize){       // yapıcı metod
        this.maxSize = maxSize;
        currentSize = 0;
        heapArray = new Node[maxSize];
    }

    public Node remove(){
        Node root = heapArray[0];        // heap yapısındaki ilk eleman döndürülüyor
        heapArray[0] = heapArray[--currentSize];       // heapArray'deki son eleman yeni ilk eleman oluyor
        trickleDown(0);         // damlama işlemiyle heap yeniden şekil alıyor
        return root;
    }

    public void trickleDown(int index){
        int largerChild;
        Node top = heapArray[index];       // verilen indexteki node top olarak adlandırılıyor
        while(index < currentSize/2){
            int leftChild = 2*index +1;      // leftChild bulunuyor
            int rightChild = leftChild + 1;   // rightChild bulunuyor

            // eğer rightChild, leftChild'dan büyükse yeni largerChild rightChild olarak atanıyor
            if(rightChild < currentSize &&
                    heapArray[leftChild].getKey() < heapArray[rightChild].getKey()){
                largerChild = rightChild;
            }
            // eğer rightChild, leftChild'dan küçükse yeni largerChild leftChild olarak atanıyor
            else
                largerChild = leftChild;

            // eğer verilen indexteki değer bulduğumuz largerChildden büyükse döngü kırılıyor
            if(top.getKey() >= heapArray[largerChild].getKey())
                break;

            heapArray[index] = heapArray[largerChild];   // bulunan largerChild index ile her değişiyor
            index = largerChild;
        }
        heapArray[index] = top;

    }

    public void displayHeap(){   // heap düzeni yazdırılıyor
        int nBlanks = 32;
        int itemsPerRow = 1;
        int column = 0;
        int j = 0;
        String dots = "...............................";
        System.out.println(dots+dots);
        while(currentSize > 0) {
            if(column == 0)
                for(int k=0; k<nBlanks; k++)
                    System.out.print(" ");
            System.out.print(heapArray[j].getKey());
            if(++j == currentSize)
                break;
            if(++column==itemsPerRow){
                nBlanks /= 2;
                itemsPerRow *= 2;
                column = 0;
                System.out.println();
            }
            else
                for(int k=0; k<nBlanks*2-2; k++)
                    System.out.print(" ");
        }
        System.out.println("\n"+dots+dots);
    }

    public void displayArray(){     // array yapısı yazdırılıyor
        for (int j = 0; j<maxSize; j++)
            System.out.print(heapArray[j].getKey() + " ");
        System.out.println("");
    }

    public void insertAt(int index, Node newNode){   // parametre olarak ilgili index ve node alınıyor.indexe node atanıyor
        heapArray[index] = newNode;
    }

    public void incrementSize(){   // currentsize yeni ekleme işlemi yaptırıldığında artırılacak
        currentSize+=1;
    }
}

class heapSortApp{
    public static void main(String[] args){
        int size, j;
        size = 20;

        Heap theHeap = new Heap(size);    // heap nesnesi oluşturuluyor
        for (j = 0; j < size; j++) {
            int random = (int)(java.lang.Math.random()*100);
            Node newNode = new Node(random);
            theHeap.insertAt(j, newNode);     // random değerler heap yapısına ekleniyor
            theHeap.incrementSize();     // currentSize artırılıyor
        }
        System.out.println("Random: ");
        theHeap.displayArray();

        for(j=size/2 -1; j>=0; j--)     // random sayılar heap yapısına göre düzenleniyor
            theHeap.trickleDown(j);

        System.out.print("Heap: ");
        theHeap.displayArray();
        theHeap.displayHeap();

        for(j= size-1; j>=0; j--){
            Node biggestNode = theHeap.remove();     // heapArray[] artan sırayla güncelleniyor
            theHeap.insertAt(j, biggestNode);
        }
        System.out.println("Sorted: ");
        theHeap.displayArray();

    }
}

