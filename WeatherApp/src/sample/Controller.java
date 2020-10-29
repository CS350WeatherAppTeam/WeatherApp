package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    private TextField ziptextfield;

    @FXML
    private ToggleButton switchtime;

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






    @FXML
    private Text fdate;

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

        System.out.println("Building...");

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


    }



    // The full action helper method that uses the inputed zip code to convert to forecast

        @FXML
         public void ZiptoCast(ActionEvent actionEvent) throws Exception {

        // get the correct zip and its information
        String zipstring = ziptextfield.getText();
        zip = ziplist.Find(zipstring);

        // combine the zip's coords and get the geopoints
        String coords = zip.getLat() + "," + zip.getLog();
        CoordstoPoint(coords);

        // collect the weekly forecast
        getForcast();

        // set up the new selected and weekly forecasts to the fxmls
            if(time >= 18 || time < 6) {
                switchtime.setText("Set Daytime");
            } else{
                switchtime.setText("Set Nighttime");
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


    public void sSetup(){

        sLocation.setText(zip.getCity() + ", " + zip.getState());

        sTemp.setText(wSelected.getTemp() + "F / " + ((Integer.parseInt(wSelected.getTemp()) - 32)) + "C");

        sForecast.setText(wSelected.getForcast());

        sPic.setImage(new Image(wSelected.getIconLink()));

        sDay.setText(wSelected.getDay());




    }

    public void setup1( int i){

        temp1.setText(wList[i].getTemp() + "F");
        day1.setText(wList[i].getDay());
        pic1.setImage((new Image(wList[i].getIconLink())));


    }

    public void setup2( int i){

        temp2.setText(wList[i].getTemp() + "F");
        day2.setText(wList[i].getDay());
        pic2.setImage((new Image(wList[i].getIconLink())));


    }

    public void setup3( int i){

        temp3.setText(wList[i].getTemp() + "F");
        day3.setText(wList[i].getDay());
        pic3.setImage((new Image(wList[i].getIconLink())));


    }

    public void setup4( int i){

        temp4.setText(wList[i].getTemp() + "F");
        day4.setText(wList[i].getDay());
        pic4.setImage((new Image(wList[i].getIconLink())));


    }

    public void setup5( int i){

        temp5.setText(wList[i].getTemp() + "F");
        day5.setText(wList[i].getDay());
        pic5.setImage((new Image(wList[i].getIconLink())));


    }

    public void setup6( int i){

        temp6.setText(wList[i].getTemp() + "F");
        day6.setText(wList[i].getDay());
        pic6.setImage((new Image(wList[i].getIconLink())));


    }

    public void setup7( int i) {

        temp7.setText(wList[i].getTemp() + "F");
        day7.setText(wList[i].getDay());
        pic7.setImage((new Image(wList[i].getIconLink())));

    }


    @FXML
    public void paneclick1(MouseEvent mouseEvent){

        sDay.setText(day1.getText());
        sTemp.setText(temp1.getText() + " / " + (Integer.parseInt(wList[1].getTemp()) - 32) + " C" );
        sForecast.setText((wList[1].getForcast()));
        sPic.setImage(pic1.getImage());

    }

    @FXML
    public void paneclick2(MouseEvent mouseEvent){

        sDay.setText(day2.getText());
        sTemp.setText(temp2.getText() + " / " + (Integer.parseInt(wList[3].getTemp()) - 32) + " C" );
        sForecast.setText((wList[3].getForcast()));
        sPic.setImage(pic2.getImage());

    }

    @FXML
    public void paneclick3(MouseEvent mouseEvent){

        sDay.setText(day3.getText());
        sTemp.setText(temp3.getText() + " / " + (Integer.parseInt(wList[5].getTemp()) - 32) + " C" );
        sForecast.setText((wList[5].getForcast()));
        sPic.setImage(pic3.getImage());

    }

    @FXML
    public void paneclick4(MouseEvent mouseEvent){

        sDay.setText(day4.getText());
        sTemp.setText(temp4.getText() + " / " + (Integer.parseInt(wList[7].getTemp()) - 32) + " C" );
        sForecast.setText((wList[7].getForcast()));
        sPic.setImage(pic4.getImage());

    }

    @FXML
    public void paneclick5(MouseEvent mouseEvent){

        sDay.setText(day5.getText());
        sTemp.setText(temp5.getText() + " / " + (Integer.parseInt(wList[9].getTemp()) - 32) + " C" );
        sForecast.setText((wList[9].getForcast()));
        sPic.setImage(pic5.getImage());

    }

    @FXML
    public void paneclick6(MouseEvent mouseEvent){

        sDay.setText(day6.getText());
        sTemp.setText(temp6.getText() + " / " + (Integer.parseInt(wList[11].getTemp()) - 32) + " C" );
        sForecast.setText((wList[11].getForcast()));
        sPic.setImage(pic6.getImage());

    }

    @FXML
    public void paneclick7(MouseEvent mouseEvent){

        sDay.setText(day7.getText());
        sTemp.setText(temp7.getText() + " / " + (Integer.parseInt(wList[13].getTemp()) - 32) + " C" );
        sForecast.setText((wList[13].getForcast()));
        sPic.setImage(pic7.getImage());

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
                 System.out.println(time);
                }


                ;
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


    @FXML
    private void TimeSwitch(){

        if(switchtime.isSelected() == true){

            if(switchtime.getText().equals("Set Nighttime") == true){

                switchtime.setText("Set Daytime");

            } else{

                switchtime.setText("Set Nighttime");

            }



            sTemp.setText(wList[wSelected.getNumber() + 1].getTemp() + " F / "+ (Integer.parseInt(wList[wSelected.getNumber() + 1].getTemp()) - 32) + " C");
            sDay.setText((wList[wSelected.getNumber() + 1].getDay()));
            sForecast.setText(wList[wSelected.getNumber() + 1].getForcast());
            sPic.setImage(new Image(wList[wSelected.getNumber() + 1].getIconLink()));

            setup1(2);
            setup2(4);
               setup3(6);
               setup4(8);
               setup5(10);
               setup6(12);
                setup7(14);


        } else{

            if(switchtime.getText().equals("Set Nighttime") == true){

                switchtime.setText("Set Daytime");

            } else{

                switchtime.setText("Set Nighttime");

            }

            sTemp.setText(wList[wSelected.getNumber()].getTemp() + " F / "+ (Integer.parseInt(wList[wSelected.getNumber() + 1].getTemp()) - 32) + " C");
            sDay.setText((wList[wSelected.getNumber()].getDay()));
            sForecast.setText(wList[wSelected.getNumber()].getForcast());
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
