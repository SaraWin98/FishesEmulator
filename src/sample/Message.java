package sample;

public class Message {
    String message;
    String command;
    String param;

    //constructor to turn the received message in a string
    public Message (byte[] message, int length){
        this(new String(message, 0, length));
    }
    //constructor to separate the string
    //when we receive a message from the potentiometer
    //it wil first state the command "color" followed by the value for the hue
    //so we need to separate these to in order to use them as command and input
    public Message(String message) {
        this.message = message;
        String[] messageArray = message.split(" ");
        //make the command always the first element in the array
        //so that we also can use that if we receive fx. "moveleft"
        command = messageArray[0];
        //if there are more than one element, save the second element as param
        if (messageArray.length > 1) {
            param = messageArray[1];
        }
    }

    //getters for the command and the param
    public String getCommand(){
        return command;
    }
    public String getParam(){
        return param;
    }
    //method to return the message after it was transformed to a string
    public String toString() {
        return message;
    }
}
