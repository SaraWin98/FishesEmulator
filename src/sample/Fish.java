package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Fish {
    int x,y, width, height;

    //The fish constructor
    public Fish(int x, int y, int width, int height) {
        this.x = x;
        this.y= y;
        this.width = width;
        this.height = height;
    }
    //We make getters and setters for the values that we will need to refer to in other classes
    void setX(int x) {
        this.x = x;
    }

    int getX() {
        return this.x;
    }

    void setY(int y) {
        this.y = y;
    }

    int getY() {
        return this.y;
    }

    int getWidth() { return this.width;}
    int getHeight() {return this.height;}

    public void draw(GraphicsContext graphicsContext) {
        //In order to make the moving object look like a fish, we use an image.
        //We here found an image online
        Image image = new Image("https://i.pinimg.com/originals/c7/67/e2/c767e2d122dc97c3c7058397bd467c4c.png");
        //command to draw the display the image in the canvas
        graphicsContext.drawImage(image,x,y,width,height);
    }
}

