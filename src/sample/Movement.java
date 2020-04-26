package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

    //movement class with the runnable interface
    public class Movement implements Runnable {
    //initializing new constructors for the Message and Fish a class
    Message message;
    Fish fish;
    //starting new constructors for the prebuilt java class Canvas and GraphicsContext
    GraphicsContext graphicsContext;
    Canvas canvas;

    //constructor for Movement class
    public Movement(Message message, Fish fish, GraphicsContext graphicsContext, Canvas canvas) {
        this.message = message;
        this.fish = fish;
        this.graphicsContext = graphicsContext;
        this.canvas = canvas;
    }

    //method to handle the commands the program receives
    public void movement(Message message, Fish fish, GraphicsContext graphicsContext, Canvas canvas) {
        //speed for the fish to move in the different directions
        int speed = 1;
        //initializing the commands
        String command = message.getCommand();
        //switching behaviour based on the received command
        switch (command) {
            //if the command is color, then we extract the second parameter
            //which changes the hue of the blue component of our RGB method
            case"color":
                String h = message.getParam();
                String numericPart =h.substring(h.indexOf(" ") + 1, h.length());
                int hue=Integer.parseInt(numericPart);
                graphicsContext.setFill(Color.rgb(0,0,hue));
                background(graphicsContext,canvas);
                break;
                //if the command is movedown, we add the speed to the y-value
                //but only if the fish not going over the boundaries of the canvas
                //the point (0,0) of the canvas is in the top left corner
                //therefore we need to add to the y-value in order to make the fish go down
            case "movedown":
                if (fish.getY() < canvas.getHeight() - fish.getHeight()) {
                    fish.setY(fish.getY() + speed);
                }
                background(graphicsContext,canvas);
                break;
                //if the command is moveup, substract the speed from the y-value
                //but only if the fish not going over the boundaries of the canvas
            case "moveup":
                if (fish.getY() > 0) {
                    fish.setY(fish.getY() - speed);
                }
                background(graphicsContext,canvas);
                break;
                //if command is moveleft, substract the speed from the x-value
                //but only if the fish not going over the boundaries of the canvas
            case "moveleft":
                if (fish.getX() > 0) {
                    fish.setX(fish.getX() - speed);
                }
                background(graphicsContext,canvas);
                break;
                //if command is moveright, add the speed to the x-value
                //but only if the fish not going over the boundaries of the canvas
            case "moveright":
                if (fish.getX() < canvas.getWidth() - fish.getWidth()) {
                    fish.setX(fish.getX() + speed);
                }
                background(graphicsContext,canvas);
                break;
                //if the command is center, set the fish in the middle of the canvas
            case "center":
                fish.setX((int) (canvas.getWidth()/2));
                fish.setY((int) (canvas.getHeight()/2));
                background(graphicsContext,canvas);
                break;
        }

    }

    //we need to clear the background in order to "reset" the canvas
    public void background(GraphicsContext graphicsContext, Canvas canvas){
        graphicsContext.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        fish.draw(graphicsContext);
    }

    //overriding the parent class to be able to start a new thread
    @Override
    //method which is a part of the runnable interface
    public void run() {
        movement(message, fish, graphicsContext, canvas);
    }
}

