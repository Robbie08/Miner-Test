public class MerkleThread implements Runnable {


    @Override
    public void run() {

        while(true){

            Util util = new Util();
            util.sleep(3);
            System.out.println("still running.");
        }
    }
}
