package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

import java.util.EventListener;

public class Cloud {

    Ellipse[] clist;

    int xradius;
    int yradius;
    int distance;


    int startx;
    int starty;

    public Cloud(){

        xradius = 40;
        yradius = 30;
        distance = 40;


        startx = 650;
        starty = 75;



        Color color = Color.GHOSTWHITE;



        clist = new Ellipse[7];

        Ellipse c1 = new Ellipse();
        c1.setCenterY(starty);
        c1.setCenterX(startx);
        c1.setRadiusX(xradius);
        c1.setRadiusY(yradius);
        c1.setFill(color);
        c1.setStyle("-fx-border-color: black");
        clist[0] = c1;



        Ellipse c2 = new Ellipse();
        c2.setCenterY(starty + distance);
        c2.setCenterX(startx + distance);
        c2.setRadiusX(xradius);
        c2.setRadiusY(yradius);
        c2.setFill(color);
        clist[1] = c2;

        Ellipse c3 = new Ellipse();
        c3.setCenterY(starty - distance);
        c3.setCenterX(startx + distance);
        c3.setRadiusX(xradius);
        c3.setRadiusY(yradius);
        c3.setFill(color);
        clist[2] = c3;

        Ellipse c4 = new Ellipse();
        c4.setCenterY(starty + distance);
        c4.setCenterX(startx + (distance * 2));
        c4.setRadiusX(xradius);
        c4.setRadiusY(yradius);
        c4.setFill(color);
        clist[3] = c4;

        Ellipse c5 = new Ellipse();
        c5.setCenterY(starty - distance);
        c5.setCenterX(startx + (distance * 2));
        c5.setRadiusX(xradius);
        c5.setRadiusY(yradius);
        c5.setFill(color);
        clist[4] = c5;


        Ellipse c6 = new Ellipse();
        c6.setCenterY(starty);
        c6.setCenterX(startx + (distance * 3));
        c6.setRadiusX(xradius);
        c6.setRadiusY(yradius);
        c6.setFill(color);
        clist[5] = c6;

        Ellipse c7 = new Ellipse();
        c7.setCenterY(starty);
        c7.setCenterX(startx + (distance * 1.5));
        c7.setRadiusX(xradius);
        c7.setRadiusY(yradius);
        c7.setFill(color);
        clist[6] = c7;

    }


    public void Move(){

        for(int i = 0; i < clist.length; i++){


            if(clist[i].getCenterX() > 650 || clist[i].getCenterX() < -40){
                clist[i].setVisible(false);
            } else {
                clist[i].setVisible(true);
            }



            clist[i].setCenterX(clist[i].getCenterX() - 3);



            if(clist[i].getCenterX() > 650 || clist[i].getCenterX() < -40){
                clist[i].setVisible(false);
            } else {
                clist[i].setVisible(true);
            }



        }

        if(clist[5].getCenterX() <= -100) {

            clist[0].setCenterX(startx);
            clist[1].setCenterX(startx + (distance));
            clist[2].setCenterX(startx + (distance));
            clist[3].setCenterX(startx + (distance * 2));
            clist[4].setCenterX(startx + (distance * 2));
            clist[5].setCenterX(startx + (distance * 3));
            clist[6].setCenterX(startx + (distance * 1.5));


        }



    }




    public Ellipse[] getList(){
        return clist;
    }








}
