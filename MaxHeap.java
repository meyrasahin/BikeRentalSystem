import java.util.Collections;
import java.util.PriorityQueue;

public class MaxHeap {
    PriorityQueue<TreeNode> pQueue;    // düğümleri tutmak için altyapıda öncelikli kuyruk kullanıldı

    public MaxHeap(){       // yapıcı metod
        pQueue = new PriorityQueue<TreeNode>(Collections.reverseOrder());
    }

    public void treeToMaxHeap(Tree agac){      // parametre olarak alınan ağaç yapısı max heap'e aktarılıyor
        TreeNode current = agac.getRoot();
        traverse(current);
    }

    private void traverse(TreeNode localRoot){    // ağacın kökü parametre olarak alındıktan sonra ağac dolaşılıyor ve öncelikli kuyruğa atılıyor
        if (localRoot != null){
            traverse(localRoot.leftChild);
            pQueue.add(localRoot);
            traverse(localRoot.rightChild);
        }
    }

    public void get3Durak(){         //en fazla normal bisiklet sayısına sahip üç durak yazdırılıyor
        System.out.println(pQueue.remove().data.toString());
        System.out.println(pQueue.remove().data.toString());
        System.out.println(pQueue.remove().data.toString());
        System.out.println();
    }

    public void show(){    // oluşturulan max heap yapısı yazdırılıyor
        while(!pQueue.isEmpty())
            System.out.println(pQueue.remove().data.toString());
        System.out.println();
    }
}
