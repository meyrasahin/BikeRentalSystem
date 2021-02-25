import java.util.List;
import java.util.ArrayList;
import java.util.Hashtable;

class TreeNode implements Comparable<TreeNode>{
    public List<Musteri> musteriler;
    public Duraklar data;
    public TreeNode leftChild;
    public TreeNode rightChild;

    public void displayNode() {
        System.out.println(".....................................................................................................................");
        System.out.println(data.toString());
        System.out.println();
        for (Musteri item : musteriler)
            System.out.println(item.toString());

        System.out.println(".....................................................................................................................\n");
    }

    @Override
    public int compareTo(TreeNode otherNode) { // max heap yapısında karşılaştırma yapılabilmesi için TreeNode'ların comparable olması gerekir.
        return data.compareTo(otherNode.data);
    }

}


public class Tree {
    private TreeNode root;

    public Tree() {     // yapıcı metod
        root = null;
    }

    public TreeNode getRoot(){    // root private olduğundan, dışardan erişmek için getRoot() metodu
        return root;
    }

    // Agacın preOrder Dolasılması
    public void preOrder(TreeNode localRoot){
        if (localRoot != null){
            localRoot.displayNode();
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }

    // Agacın inOrder Dolasılması
    public void inOrder(TreeNode localRoot){
        if (localRoot != null){
            inOrder(localRoot.leftChild);
            localRoot.displayNode();
            inOrder(localRoot.rightChild);
        }
    }

    // Agacın postOrder Dolasılması
    public void postOrder(TreeNode localRoot){
        if (localRoot != null){
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            localRoot.displayNode();
        }
    }

    int i =0;
    public void intoHashTable(TreeNode root, Hashtable hashtable){   // ağacın kökü ve hashtable yapısı alınıyor
        i += 1;
        if(root != null){       // ağaç dolaşılırken hashtable yapısına durak adı, durak nesne olarak aktarılıyor
            intoHashTable(root.leftChild, hashtable);
            hashtable.put(i , root.data);
            intoHashTable(root.rightChild, hashtable);
        }
    }

    // Agaca bir dügüm eklemeyi saglayan metot
    public void insert(Duraklar newdata, List<Musteri> musteriler){   // yeni bir durak nesnesi ve musteri listesi alınıyor
        TreeNode newNode = new TreeNode();    // yeni node oluşturuluyor ve atamalar gerçekleşiyor
        newNode.data = newdata;
        newNode.musteriler = musteriler;
        if (root == null)
            root = newNode;
        else{
            TreeNode current = root;
            TreeNode parent;
            while (true){
                parent = current;     // yeni durağın adına göre karşılaştırmalar yapılarak eklenecek düğüm bulunuyor ve ekleniyor
                if (newdata.durakAdi.compareTo(current.data.durakAdi) < 0){
                    current = current.leftChild;
                    if (current == null){
                        parent.leftChild = newNode;
                        return;
                    }
                }
                else{
                    current = current.rightChild;
                    if (current == null){
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }


    public void rent(TreeNode root, String durak, Musteri musteri){   // kiralama işlemi için kök, durak ve müşteri parametreleri alınıyor
        while(!root.data.durakAdi.equals(durak)){        // ilgili durak adını bulana kadar dönecek
            if(durak.compareToIgnoreCase(root.data.durakAdi) < 0){    // durak adı bulunan düğümden küçükse sol ağaca gidecek
                if(root.leftChild != null)
                    root = root.leftChild;
                else
                    break;
            }
            else{               // durak adı bulunan düğümden büyükse sağ aağaca gidecek
                if(root.rightChild != null)
                    root = root.rightChild;
                else
                    break;
            }
        }

        root.musteriler.add(musteri);        // ilgili durağa müşteri eklenecek
        root.data.normalBisiklet -= 1;       // normal bisiklet sayısı azalacak
        root.data.bosPark +=1;               // boş park sayısı artacak

        System.out.println("Kiralama İşlemi Tamamlandı. Yeni Ağaç Dolaşılıyor.");
        inOrder(getRoot());       // agaç tekrar dolaşılıyor
    }


    public List findID(int ID){     // ID'si verilen müşterinin hareketlerini görüntülemek için kullanılıyor
        List liste = new ArrayList();   // bulunan duraklar listeye eklenecek
        TreeNode current = root;
        traverse(current, ID, liste);       // traverse() metodu yardımıyla müşteri bulunuyor ve listeye ekleniyor
        return liste;
    }


    private void traverse(TreeNode current, int ID, List liste){     // ağaç dolaşılarak müşteri ID'si verilen değere eşit olan müşteriler ve durak bilgileri listeye ekleniyor
        if (current != null){
            traverse(current.leftChild, ID,liste);
            for(Musteri musteri : current.musteriler){
                if(musteri.getID() == ID){
                    liste.add(current.data.durakAdi);
                    liste.add(musteri);
                }
            }
            traverse(current.rightChild, ID,liste);
        }
    }

    public int maxDepth(TreeNode root){    // ağacın maksimum derinliği döndürülüyor
        if (root == null)
            return 0;
        int leftDepth = maxDepth(root.leftChild);
        int rightDepth = maxDepth(root.rightChild);
        if (leftDepth > rightDepth)
            return (leftDepth + 1 );
        else
            return (rightDepth + 1);
    }


}
