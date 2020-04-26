package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

public class Controller {
    //We start by making a Fish from our class fish.
    Fish fish = new Fish(200, 150, 25, 25);

    //Since we are working with a canvas we have to initialize the fxml canvas.
    @FXML
    public Canvas canvas;
    //In order to draw an object on the canvas we need to initialize GraphicsContext
    public GraphicsContext  graphicsContext;

    //Since we want to have the message output written in a list, we have to initialize a fxml listview,
    // where the input is the class Message
    @FXML
    private ListView<Message> tableInput;

    public void initialize() {
        //Here we make the canvas with the help of graphicsContext
        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.rgb(0, 0, 200));
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        //We use the draw method from fish to draw the fish
        fish.draw(graphicsContext);
        //we initialize the class UdpClient
        UdpClient client = new UdpClient(7000,this);
        // We make a new Thread which will allow us to run multiple threads at the same time.
        //This thread runs the UdpClient
        new Thread(client).start();
    }
    //method to receive the input from the UdpClient
    //uses this message to start a new thread for our Movement class
    public void messageReceived(Message message){
        //We add the message to the list which is displayed in our scene
        if(tableInput != null){
            tableInput.getItems().add(0,message);
        }
        //We make a new Thread that enables the fish to move.
        Thread move = new Thread(new Movement(message,fish, graphicsContext, canvas));
        move.start();
    }
}

