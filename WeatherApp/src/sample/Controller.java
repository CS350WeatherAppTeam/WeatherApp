package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;


public class Controller implements Initializable {



    Color darktext = Color.BLACK;
    Color lighttext = Color.SNOW;

    String darkback = "black";
    String lightback = "snow";

    @FXML
    private BorderPane mainpane;

    @FXML
    private TextField ziptextfield;

    @FXML
    private ToggleButton switchtime;

    @FXML
    private VBox weekbox;



    @FXML
    private Text sLocation;
    @FXML
    private Text sTemp;
    @FXML
    private Text sForecast;
    @FXML
    private ImageView sPic;
    @FXML
    private Text sDay;


    @FXML
    private Text temp1;
    @FXML
    private Text temp2;
    @FXML
    private Text temp3;
    @FXML
    private Text temp4;
    @FXML
    private Text temp5;
    @FXML
    private Text temp6;
    @FXML
    private Text temp7;


    @FXML
    private ImageView pic1;
    @FXML
    private ImageView pic2;
    @FXML
    private ImageView pic3;
    @FXML
    private ImageView pic4;
    @FXML
    private ImageView pic5;
    @FXML
    private ImageView pic6;
    @FXML
    private ImageView pic7;


    @FXML
    private Text day1;
    @FXML
    private Text day2;
    @FXML
    private Text day3;
    @FXML
    private Text day4;
    @FXML
    private Text day5;
    @FXML
    private Text day6;
    @FXML
    private Text day7;

    private String cast1;
    private String cast2;
    private String cast3;
    private String cast4;
    private String cast5;
    private String cast6;
    private String cast7;

    @FXML
    private Circle c1;



    @FXML
    private DialogPane dp;

    @FXML
    private Pane starpane;

    ArrayList<Circle> clist;

    @FXML
    private Text fdate;

    @FXML
    private Text enterziptext;

    String date;
    int time;









    Weather[] wList;
    Weather wSelected;


    Zipcode zip;
    ZipcodeList ziplist;

    String ID;
    String pointX;
    String pointY;









// Build zipcode list, and set default zip

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //      System.out.println("Building...");

        StarGenerator();



        // daylist = new Text[8];
        // daylist[1].setText("Check");

        wList = new Weather[15];

        ziplist = new ZipcodeList();
        try {
            ziplist.Build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ZiptoCast(new ActionEvent());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mainpane.widthProperty().addListener((obs, oldVal, newVal) -> {
            setVisibleCoords();
        });

        mainpane.heightProperty().addListener((obs, oldVal, newVal) -> {
            setVisibleCoords();
        });


        BackgroundAnimation();

    }


    // The full action helper method that uses the inputed zip code to convert to forecast

    @FXML
    public void ZiptoCast(ActionEvent actionEvent) throws Exception {

        // get the correct zip and its information
        String zipstring = ziptextfield.getText();

        String error = "none";

        try {
            zip = ziplist.Find(zipstring);

        } catch (Exception e) {
            error = "nonnumber";
        }
        if(zip == null){

            if(error.equals("nonnumber") == true){
                BadZip("nonnumber");
            } else {
                BadZip("null");
            }


        } else {


            // combine the zip's coords and get the geopoints
            String coords = zip.getLat() + "," + zip.getLog();
            CoordstoPoint(coords);

            // collect the weekly forecast
            getForcast();

            // set up the new selected and weekly forecasts to the fxmls
            if (time >= 18 || time < 6) {
                switchtime.setText("Set Daytime");
                setDark();
            } else {
                switchtime.setText("Set Nighttime");
                setLight();
            }


            switchtime.setSelected(false);
            sSetup();
            setup1(1);
            setup2(3);
            setup3(5);
            setup4(7);
            setup5(9);
            setup6(11);
            setup7(13);

        }


    }


    public void BadZip(String reason){


        if(reason.equals("null") == true){
            reason = "Zipcode does not exist";
        } else {
            reason = "Zipcode must be 5 digit sequence";
        }


        Alert alert = new Alert(Alert.AlertType.ERROR, reason );
        alert.setHeaderText(null);
        alert.setGraphic(null);
        alert.showAndWait();

    }


    // sets specific variables to matching weather variables
    public void sSetup(){
        sLocation.setText(zip.getCity() + ", " + zip.getState());
        Double c = ((Integer.parseInt(wSelected.getTemp()) - 32)) * .55;
        long ce = Math.round(c);
        sTemp.setText(wSelected.getTemp() + "F / " + ce + "C");
        sForecast.setText(wSelected.getFullForecast());
        sPic.setImage(new Image(wSelected.getIconLink()));
        sDay.setText(wSelected.getDay());
    }

    public void setup1( int i){
        temp1.setText(wList[i].getTemp() + "F");
        day1.setText(wList[i].getDay());
        pic1.setImage((new Image(wList[i].getIconLink())));
        cast1 = wList[i].getFullForecast();
    }

    public void setup2( int i){
        temp2.setText(wList[i].getTemp() + "F");
        day2.setText(wList[i].getDay());
        pic2.setImage((new Image(wList[i].getIconLink())));
        cast2 = wList[i].getFullForecast();
    }

    public void setup3( int i){
        temp3.setText(wList[i].getTemp() + "F");
        day3.setText(wList[i].getDay());
        pic3.setImage((new Image(wList[i].getIconLink())));
        cast3 = wList[i].getFullForecast();
    }


    public void setup4( int i){
        temp4.setText(wList[i].getTemp() + "F");
        day4.setText(wList[i].getDay());
        pic4.setImage((new Image(wList[i].getIconLink())));
        cast4 = wList[i].getFullForecast();
    }

    public void setup5( int i){
        temp5.setText(wList[i].getTemp() + "F");
        day5.setText(wList[i].getDay());
        pic5.setImage((new Image(wList[i].getIconLink())));
        cast5 = wList[i].getFullForecast();
    }

    public void setup6( int i){
        temp6.setText(wList[i].getTemp() + "F");
        day6.setText(wList[i].getDay());
        pic6.setImage((new Image(wList[i].getIconLink())));
        cast6 = wList[i].getFullForecast();
    }

    public void setup7( int i) {
        temp7.setText(wList[i].getTemp() + "F");
        day7.setText(wList[i].getDay());
        pic7.setImage((new Image(wList[i].getIconLink())));
        cast7 = wList[i].getFullForecast();
    }


    // mouse click functionality that swaps the main panes variables with the selected panes variables
    @FXML
    public void paneclick1(MouseEvent mouseEvent){
        sDay.setText(day1.getText());
        sTemp.setText(temp1.getText() + " / " + FConvert(temp1) + "C" );
        sForecast.setText(cast1);
        sPic.setImage(pic1.getImage());
        borderSelected(0);
    }

    @FXML
    public void paneclick2(MouseEvent mouseEvent){
        sDay.setText(day2.getText());
        sTemp.setText(temp2.getText() + " / " + FConvert(temp2) + "C" );
        sForecast.setText(cast2);
        sPic.setImage(pic2.getImage());
        borderSelected(1);
    }

    @FXML
    public void paneclick3(MouseEvent mouseEvent){
        sDay.setText(day3.getText());
        sTemp.setText(temp3.getText() + " / " + FConvert(temp3) + "C" );
        sForecast.setText(cast3);
        sPic.setImage(pic3.getImage());
        borderSelected(2);
    }

    @FXML
    public void paneclick4(MouseEvent mouseEvent){
        sDay.setText(day4.getText());
        sTemp.setText(temp4.getText() + " / " + FConvert(temp4) + "C" );
        sForecast.setText(cast4);
        sPic.setImage(pic4.getImage());
        borderSelected(3);
    }

    @FXML
    public void paneclick5(MouseEvent mouseEvent){
        sDay.setText(day5.getText());
        sTemp.setText(temp5.getText() + " / " + FConvert(temp5) + "C" );
        sForecast.setText(cast5);
        sPic.setImage(pic5.getImage());
        borderSelected(4);
    }

    @FXML
    public void paneclick6(MouseEvent mouseEvent){
        sDay.setText(day6.getText());
        sTemp.setText(temp6.getText() + " / " + FConvert(temp6) + "C" );
        sForecast.setText(cast6);
        sPic.setImage(pic6.getImage());
        borderSelected(5);
    }

    @FXML
    public void paneclick7(MouseEvent mouseEvent){
        sDay.setText(day7.getText());
        sTemp.setText(temp7.getText() + " / " + FConvert(temp1) + "C" );
        sForecast.setText(cast7);
        sPic.setImage(pic7.getImage());
        borderSelected(6);
    }

    // method that converts the Fahrenheit number to a celcius number
    public String FConvert(Text temp){
        Double celsius  = (Double.parseDouble(temp.getText().substring(0,temp.getText().length()-1)) - 32) * .55;
        Long ce = Math.round(celsius);
        String c = "" + ce;
        return c;
    }





    // turning the gained long and lat into grid points for forecast
    public void CoordstoPoint(String coords) throws Exception{

        // the specific spots where the important info is in the file
        final int IDpoint = 58;
        final int Xpoint = 59;
        final int Ypoint = 60;

        //Connecting to API
        String pointlink = "https://api.weather.gov/points/" + coords;
        URL pointUrl = new URL(pointlink);
        HttpURLConnection pointHTTP = (HttpURLConnection) pointUrl.openConnection();
        InputStream pointStream = pointHTTP.getInputStream();

        //set up reader
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(pointStream, "UTF-8"));
            String lineOData = br.readLine();

            //putting each read line into a string list to easily pull it
            String splitline[] = new String[94];
            int count = 0;
            while (lineOData != null) {

                splitline[count] = lineOData;

                // uncomment to check what is being read and where it is in the file
                //   System.out.println(lineOData);


                count++;
                lineOData = br.readLine();

            }

            // setting ID, X and Y points
            setID(splitline[IDpoint].substring(19,22));
            //  System.out.println(getID());

            setPointX(splitline[Xpoint].substring(17, splitline[Xpoint].indexOf(",")));
            //   System.out.println(getPointX());

            setPointY(splitline[Ypoint].substring(17, splitline[Ypoint].indexOf(",")));
            //  System.out.println(getPointY());


            // Closing file if something goes wrong
        } catch (IOException ex){
            //Print to the error stream
            //IOException ex will contain attempted file name
            System.err.println("ERROR accessing :"+ex.getMessage());
        } finally {
            //Ok, we're done let's close this down.
            try {
                //br may have failed to init, check before closing
                if(br != null){
                    br.close();
                }
            } catch (IOException ex){
                //We couldn't close the file?
                //Ok, we're screwed bail.
                ex.printStackTrace();
                //Non-zero means we failed
                System.exit(-1);
            }
        }


    }



    // Taking the coords we gained and getting the forecast from it,
    // We then set up the Weather list to easily pull this information later
    public void getForcast() throws Exception{
        // connecting to API
        String forecastLink = "https://api.weather.gov/gridpoints/" + getID() + "/" + getPointX() + "," + getPointY() + "/forecast";
        URL forecastUrl = new URL(forecastLink);
        HttpURLConnection forecastHTTP = (HttpURLConnection) forecastUrl.openConnection();
        InputStream forecastStream = forecastHTTP.getInputStream();

        // setting up reader
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(forecastStream, "UTF-8"));
            String lineOData = br.readLine();

            // getting rid of extraneous data up to the point where the forecast is being told to us
            while(lineOData.contains("periods") == false){

                lineOData = br.readLine();

            }

            // geting rid of Periods line
            lineOData = br.readLine();
            String[] wData = new String[15];



            //When the periods line is gone, each day's forecast neatly separates into 15 lines (including brackets)
            //Each day is chopped into it's own section and labeled into a Weather class that is put into the list

            //If you want to add more data, uncomment the printline to find the info within 15 lines and set it into a matching variable
            for(int i = 1; i < wList.length; i++){
                Weather wHold = new Weather();
                for(int j = 0; j < 15; j++) {
                    wData[j] = lineOData;
                    System.out.println(lineOData);
                    lineOData = br.readLine();
                }

                //saving data

                wHold.setDay(wData[2].substring(25,wData[2].indexOf(",") - 1));
                //printline checks it's saving it correctly
                //   System.out.println(wHold.getDay());

                wHold.setTemp(wData[6].substring(31, wData[6].indexOf(",")));
                //    System.out.println(wHold.getTemp());

                wHold.setForcast(wData[12].substring(34, wData[12].indexOf(",") - 1));
                //    System.out.println(wHold.getForcast());

                wHold.setWindmph(wData[9].substring(30, wData[9].indexOf(",") - 1));
                //    System.out.println(wHold.getWindmph());

                wHold.setWinddir(wData[10].substring(34, wData[10].indexOf(",") - 1));
                //     System.out.println(wHold.getWinddir());

                wHold.setIconLink(wData[11].substring(25, wData[11].indexOf("\",")));
                //         System.out.println(wHold.getIconLink());


                wHold.setFullForecast(wData[13].substring(37, wData[13].length() - 1));
                //      System.out.println(wHold.getFullForecast());

                wHold.setNumber((Integer.parseInt(wData[1].substring(26, wData[1].indexOf(",")))));
                //     System.out.println(wHold.getNumber());

                if(i == 1){
                    date = wData[3].substring(30, wData[3].indexOf("\",") - 15);
                    // System.out.println(date);
                    date = date.substring(5, date.length()) + "-" + date.substring(0,4);
                    fdate.setText(date);
                    time = Integer.parseInt(wData[3].substring(41, 43));
                    //     System.out.println(time);
                }
                wList[i] = wHold;
            }

            wSelected = wList[1];


            // closing file if something goes wrong
        } catch (IOException ex){
            //Print to the error stream
            //IOException ex will contain attempted file name
            System.err.println("ERROR accessing :"+ex.getMessage());
        } finally {
            //Ok, we're done let's close this down.
            try {
                //br may have failed to init, check before closing
                if(br != null){
                    br.close();
                }
            } catch (IOException ex){
                //We couldn't close the file?
                //Ok, we're screwed bail.
                ex.printStackTrace();
                //Non-zero means we failed
                System.exit(-1);
            }
        }
    }










    // Time button funcitonality
    @FXML
    private void TimeSwitch(){

        // checking which time it currently is
        if(switchtime.isSelected() == true){

            if(switchtime.getText().equals("Set Nighttime") == true){
                switchtime.setText("Set Daytime");
                setDark();
            } else{
                switchtime.setText("Set Nighttime");
                setLight();
            }


            Double c = ((Integer.parseInt(wList[wSelected.getNumber() + 1].getTemp()) - 32)) * .55;
            long ce = Math.round(c);

            // setting main pane
            sTemp.setText(wList[wSelected.getNumber() + 1].getTemp() + "F / " + ce + "C");
            sDay.setText((wList[wSelected.getNumber() + 1].getDay()));
            sForecast.setText(wList[wSelected.getNumber() + 1].getFullForecast());
            sPic.setImage(new Image(wList[wSelected.getNumber() + 1].getIconLink()));
            //  sTemp.set

            // setting other panes
            setup1(2);
            setup2(4);
            setup3(6);
            setup4(8);
            setup5(10);
            setup6(12);
            setup7(14);


        } else{

            // if opposite is default
            if(switchtime.getText().equals("Set Nighttime") == true){
                switchtime.setText("Set Daytime");
                setDark();
            } else{
                switchtime.setText("Set Nighttime");
                setLight();
            }


            Double c = ((Integer.parseInt(wList[wSelected.getNumber()].getTemp()) - 32)) * .55;
            long ce = Math.round(c);

            sTemp.setText(wList[wSelected.getNumber()].getTemp() + "F / " + ce + "C");
            sDay.setText((wList[wSelected.getNumber()].getDay()));
            sForecast.setText(wList[wSelected.getNumber()].getFullForecast());
            sPic.setImage(new Image(wList[wSelected.getNumber()].getIconLink()));

            setup1(1);


            setup2(3);
            setup3(5);
            setup4(7);
            setup5(9);
            setup6(11);
            setup7(13);


        }


    }

    public void setDark(){
        mainpane.setStyle("-fx-background-color:" + darkback);
        setAllBorder(lightback);

        sLocation.setFill(lighttext);
        sForecast.setFill(lighttext);
        sDay.setFill(lighttext);
        sTemp.setFill(lighttext);
        fdate.setFill(lighttext);

        enterziptext.setFill(lighttext);


        day1.setFill(lighttext);
        day2.setFill(lighttext);
        day3.setFill(lighttext);
        day4.setFill(lighttext);
        day5.setFill(lighttext);
        day6.setFill(lighttext);
        day7.setFill(lighttext);

        temp1.setFill(lighttext);
        temp2.setFill(lighttext);
        temp3.setFill(lighttext);
        temp4.setFill(lighttext);
        temp5.setFill(lighttext);
        temp6.setFill(lighttext);
        temp7.setFill(lighttext);

        enterziptext.setFill(lighttext);
    }

    public void setLight(){
        mainpane.setStyle("-fx-background-color:" + lightback);
        setAllBorder(darkback);

        sLocation.setFill(darktext);
        sForecast.setFill(darktext);
        sDay.setFill(darktext);
        sTemp.setFill(darktext);

        day1.setFill(darktext);
        day2.setFill(darktext);
        day3.setFill(darktext);
        day4.setFill(darktext);
        day5.setFill(darktext);
        day6.setFill(darktext);
        day7.setFill(darktext);

        temp1.setFill(darktext);
        temp2.setFill(darktext);
        temp3.setFill(darktext);
        temp4.setFill(darktext);
        temp5.setFill(darktext);
        temp6.setFill(darktext);
        temp7.setFill(darktext);

        fdate.setFill(darktext);
        enterziptext.setFill(darktext);
    }




    public void BackgroundAnimation(){

        Runnable r = new Runnable() {
            @Override
            public void run() {

                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Twinkle();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        };

        Thread t = new Thread(r);

        t.setDaemon(true);

        t.start();

    }



    public void Twinkle() throws InterruptedException {

        Random r = new Random();

        int i = r.nextInt(clist.size());

        int time = r.nextInt(1500) + 500;

        Thread.sleep(time);

        clist.get(i).setRadius(2);

        Thread.sleep(100);

        clist.get(i).setRadius(1);

    }

    public void StarGenerator(){

        Random rand = new Random();

        clist = new ArrayList<>();

        int x = 10;
        int y = 10;

        int MaxY = 490;

        starpane.toBack();

        while(x <= 645) {

            Circle c = new Circle(0, 0, 1);
            c.setFill(lighttext);


            y = rand.nextInt(MaxY - 10) + 10;

            boolean visible = true;

            if (x >= 50 && x <= 595) {
                if (y >= 290) {
                    visible = false;
                }
            }
            if (x >= 70 && x <= 225) {
                if (y >= 215 && y <= 240) {
                    visible = false;
                }
            }

            if (x >= 230 && x <= 415) {
                if (y >= 60 && y <= 90) {
                    visible = false;
                }
            }

            if (x >= 425 && x <= 515) {
                if (y >= 215 && y <= 240) {
                    visible = false;
                }
            }

            if(visible == true) {
                c.setCenterX(x);
                c.setCenterY(y);
                clist.add(c);
                starpane.getChildren().add(c);
            }



            x++;
        }

    }



    @FXML
    private void Mousecoords(MouseEvent mouseEvent){
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();

        System.out.println("moose");
        System.out.println(x + "," + y);
    }

    public void setAllBorder(String color){

        mainpane.getCenter().setStyle("-fx-border-color:" + color);
        mainpane.getTop().setStyle("-fx-border-color:" + color);
        weekbox.setStyle("-fx-border-color:" + color);

        for(int i = 0; i < 7; i++){

            weekbox.getChildren().get(i).setStyle("-fx-border-color:" + color);

        }
    }

    @FXML
    private void setVisibleCoords(){



        double x = mainpane.getWidth();
        double y = mainpane.getHeight();

        //   mainpane.getCenter().setStyle("-fx-border-style: none none none none");
        //  sForecast.setVisible(false);
        // weekbox.setVisible(false);
        //  ziptextfield.setVisible(false);
        //  enterziptext.setVisible(false);

        starpane.setOpacity((x/1600) + (y/970));
        //   System.out.println(starpane.getOpacity());


        if (x <= 690 || y <= 470){
            mainpane.getCenter().setStyle("-fx-border-style: none none none none");
            weekbox.setVisible(false);


        } else {
            mainpane.getCenter().setStyle("-fx-border-style: solid solid solid solid");
            weekbox.setVisible(true);

        }

        if (x <= 690) {
            ziptextfield.setVisible(false);
            enterziptext.setVisible(false);
            switchtime.setVisible(false);
        } else{
            ziptextfield.setVisible(true);
            enterziptext.setVisible(true);
            switchtime.setVisible(true);
        }

        if(x <= 685 || y <= 450) {
            sForecast.setVisible(false);
        } else{
            sForecast.setVisible(true);
        }

        if(x <= 225){
            fdate.setVisible(false);
        } else {
            fdate.setVisible(true);
        }

        if(x <= 610) {
            sTemp.setVisible(false);
            sDay.setVisible(false);
        } else {
            sTemp.setVisible(true);
            sDay.setVisible(true);
        }

        if(y <= 250){
            sLocation.setVisible(false);
            mainpane.getTop().setVisible(false);
            mainpane.getTop().setDisable(true);
        } else{
            sLocation.setVisible(true);
            mainpane.getTop().setVisible(true);
            mainpane.getTop().setDisable(false);
        }


        System.out.println(x + "," + y);


    }

    public void borderSelected(int paneNum) {
        // For reseting the border whenever it is clicked so that there are no duplicate selected panel
        for(int i = 0; i < 7; i++){
            // This will check the condition whether the border should stay white or black
            // depending on the status of switchtime
            if(switchtime.getText().equals("Set Nighttime") == true){
                weekbox.getChildren().get(paneNum).setStyle("-fx-border-width: 1");
                weekbox.getChildren().get(i).setStyle("-fx-border-color: black");
            } else{
                weekbox.getChildren().get(paneNum).setStyle("-fx-border-width: 1");
                weekbox.getChildren().get(i).setStyle("-fx-border-color: white");
            }
        }
        weekbox.getChildren().get(paneNum).setStyle("-fx-border-color: red; -fx-border-width:3");
    }



    public Weather[] getwList() {
        return wList;
    }

    public void setwList(Weather[] wList) {
        this.wList = wList;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPointX() {
        return pointX;
    }

    public void setPointX(String pointX) {
        this.pointX = pointX;
    }

    public String getPointY() {
        return pointY;
    }

    public void setPointY(String pointY) {
        this.pointY = pointY;
    }

    public Weather getwSelected() {
        return wSelected;
    }

    public void setwSelected(Weather wSelected) {
        this.wSelected = wSelected;
    }






}