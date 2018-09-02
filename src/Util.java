import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;

public class Util {


    public String getMerkelRoot(ArrayList<String> listName){

        // Instantiate your nodes of the Merkel Tree
        MerkleNode oNode0 = new MerkleNode();
        MerkleNode oNode1 = new MerkleNode();
        MerkleNode oNode2 = new MerkleNode();
        MerkleNode oNode3 = new MerkleNode();
        MerkleNode oNode4 = new MerkleNode();
        MerkleNode oNode5 = new MerkleNode();
        MerkleNode oNode6 = new MerkleNode();

        // Create leaves of tree with hashes of inputs
        oNode0.sHash = generateHash(listName.get(0));
        oNode1.sHash = generateHash(listName.get(1));
        oNode2.sHash = generateHash(listName.get(2));
        oNode3.sHash = generateHash(listName.get(3));

        // Begin creating upper levels of tree
        populateMerkleNode(oNode4,oNode0,oNode1);
        populateMerkleNode(oNode5,oNode2,oNode3);
        populateMerkleNode(oNode6,oNode4,oNode5);

        return oNode6.sHash; //this is the merkel root

    }

    // private so that only used by this class
    private void populateMerkleNode(MerkleNode oNode, MerkleNode oLeftNode, MerkleNode oRightNode){
        oNode.oLeft = oLeftNode;
        oNode.oRight = oRightNode;
        oNode.sHash = generateHash(oLeftNode.sHash +oRightNode.sHash); //create our node for tree
    }
    /* Algorithm to generate has using SHA-256*/
    public synchronized String generateHash(String sOriginal){

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] btEncodedhash = digest.digest(
                    sOriginal.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < btEncodedhash.length; i++) {
                //0xff is telling it's going to be a hex number
                sb.append(Integer.toString((btEncodedhash[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        }
        catch(Exception ex){

            System.out.println("Error generating hash: " + ex.getMessage());
            return null;
        }
    }

    public void sleep(int iSeconds){

        try {
            Thread.sleep(iSeconds * 1000);
        }
        catch (Exception ex) {
            //noting
        }

    }
}
