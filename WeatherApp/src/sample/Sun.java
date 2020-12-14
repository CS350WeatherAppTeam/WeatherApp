package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Sun {

    Circle sun;

    int size = 75;

    public Sun() {

        sun = new Circle();
        sun.setCenterX(size);
        sun.setCenterY(size);
        sun.setRadius(size);
        sun.setFill(Color.YELLOW);
    }



    public void setColor(double r, double g, double b){
        sun.setFill(Color.color(r,g,b));
    }

    public Circle getSun() {
        return sun;
    }

    public void setSun(Circle sun) {
        this.sun = sun;
    }
}
