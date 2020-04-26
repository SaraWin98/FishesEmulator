package sample;

import java.io.IOException;
import java.net.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpClient implements Runnable {
    //in order to start this class as a new thread, it needs to implement the runnable interface
    //the run() method is a part of the interface
    //run() will automatically called in when executing a new thread on this class

    private final int port;
    //starting a new instance of controller, to be able to refer to its methods
    private Controller handle;
    //initializing a new socket to send/receive packets
    private DatagramSocket socket = null;
    private boolean receiveMessages = true;

    //constructor for the UdpClient class
    public UdpClient(int port, Controller controller) {
        this.port = port;
        this.handle = controller;
    }
    //method to set up the socket
    private void setupSocket(){
        //trying to start a new socket in the port we initialized in the controller class
        try {
            socket = new DatagramSocket(port);

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    //method to receive the packet send to our socket
    private Message receivePacket(){
        //initializing an empty array to store the packet
        // the packet is sent as bytes so why we need a byte array
        //255 is the maximum size of what we can receive in this array
        //so if the message is bigger than 256 bytes, then it will cut of the rest
        byte[] inBuf = new byte[255];
        //preparing to receive a new packet
        DatagramPacket packet = new DatagramPacket(inBuf, inBuf.length);
        Message message = null;
        try {
            //receive the new packet
            socket.receive(packet);
            //send the packet to the Message class to turn it into a string
            message = new Message(packet.getData(), packet.getLength());
            String m = message.toString();
            //if message is empty return a message
            if (m == "") {
                System.out.println("no message");
                //else sent it to the controller class
            } else {
                handle.messageReceived(message);
            }
            packet.getSocketAddress();
            //error that occurs if no packet was sent/can be received
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }

    //overriding the parent class to be able to start a new thread
    @Override
    //method which is a part of the runnable interface
    public void run() {
        //if there is no socket, then start one
        if (socket == null){
            setupSocket();
        }
        //if we receive a message, start the receivePacket() method
        while (receiveMessages){
            receivePacket();
        }
    }

}


