import java.util.ArrayList;

public class MinerTest {

    public static void main(String[] args) {

        Util util = new Util();
        String sHash = util.generateHash("test0");
        System.out.println("Hash: "+sHash);

        //instantiate our list that will pass into the getMerkelRoot() function
        ArrayList<String> hashList = new ArrayList<>();
        hashList.add("test0");
        hashList.add("test1");
        hashList.add("test2");
        hashList.add("test3");

        String sMerkelRoot = util.getMerkelRoot(hashList);

        // Test print our merkle root value
        System.out.println("merkle root: " +sMerkelRoot);

        // Creating two threads
        Thread oThread1 = new Thread(new MerkleThread()); //pass an instance of the MerkleThread object
        oThread1.start();

        Thread oThread2 = new Thread(new MerkleThread()); //pass an instance of the MerkleThread object
        oThread2.start();

    }
}
