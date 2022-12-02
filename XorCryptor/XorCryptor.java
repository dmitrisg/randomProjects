//core:
//input of key and data
//ascii decoding mechanism
//output of decrypted data
//recognize spaces

//extra:
// read key from file w history?
//include a logger
//handle non ascii bit strings
//validate input(make sure its a bit string or ignore parts that arent 1 0 or space)
//do this is in a different language, have a gui, use csharp?
//export in a more compact format thats still unreadable.
//encode first or decode?
//make a loop
//write in assembly
//words to sentences
//pad with zeros, otherwise may not decrypt properly.
//output letters instead as an option
//add byte validation to limit size
//if key is a int, not sure what xor value returns
//the reverse of an xor is an xor
//(a XOR b)XOR b = a
//11111111 not ok as input
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class XorCryptor {
    Scanner in;
    public XorCryptor(){
        in = new Scanner(System.in);
    }
    public static void main(String[] args){
        XorCryptor xorCryptor = new XorCryptor();
       xorCryptor.go();
    }

    private void go(){
        String message = inputString("Enter message: ");
        byte key = inputByte("Enter key:");
        if (encryptPrompt()){
            print(bytesToBinaryString(encrypt(message, key)));
        }
        else print(decrypt(message, key));
    }

    private String decrypt(String message, byte key){
        String[] byteStrings = message.split(" ");
        ArrayList<Object> byteStringList = new ArrayList<>(); //bad naming
        Collections.addAll(byteStringList, byteStrings);
        byteStringList.replaceAll(x -> (Byte.parseByte((String) x,2)^key)); //make sure theres always a string there
        String decryptedMessage = "";
        for (Object b : byteStringList){ //has right ints
            decryptedMessage += Character.toString((int) b); //figure out type conversion
        }
        return decryptedMessage;
    }
    private byte[] encrypt(String message, byte key){
        byte[] messageBytes = message.getBytes();
        ArrayList<Byte> messageBytesList = byteArrayToArrayList(messageBytes);
        messageBytesList.replaceAll(x -> (byte)(x^key)); //slay
        return byteArrayListToArray(messageBytesList);
    }

    private boolean encryptPrompt(){
        print("e for encrypt, d for decrypt");
        String response = in.next();
        return response.equals("e");
    }

    private ArrayList<Byte> byteArrayToArrayList(byte[] bytes){
        ArrayList<Byte> byteList = new ArrayList<>();
        for (byte b : bytes){
            byteList.add(b);
        }
        return byteList;
    }

    private byte[] byteArrayListToArray(ArrayList<Byte> byteList){
        byte[] bytes = new byte[byteList.size()];
        int byteIndex = 0;
        for (byte b : byteList){
            bytes[byteIndex] = b;
            byteIndex++;
        }
        return bytes;
    }

    private String inputString(String prompt){
        print(prompt);
        return in.nextLine();
    }

    private byte inputByte(String prompt){
        print(prompt);
        return in.nextByte(2);
    }
    private String bytesToBinaryString(byte[] bytes){
        String binaryString = "";
        for (byte b : bytes){
            binaryString += Integer.toBinaryString(b) + " ";
        }
        return binaryString.trim();
    }

    private void print(String message){
        System.out.println(message);
    }
}
