package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Controller {

    Weather[] wList;
    Weather wSelected;


    Zipcode zip;


    String ID;
    String pointX;
    String pointY;

    public Controller(){

        wList = new Weather[14];

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
            Weather wHold = new Weather();


            //When the periods line is gone, each day's forecast neatly separates into 15 lines (including brackets)
            //Each day is chopped into it's own section and labeled into a Weather class that is put into the list

            //If you want to add more data, uncomment the printline to find the info within 15 lines and set it into a matching variable
            for(int i = 0; i < wList.length; i++){

                for(int j = 0; j < 15; j++) {
                    wData[j] = lineOData;
               //     System.out.println(lineOData);
                    lineOData = br.readLine();
                }

                //saving data
        
                wHold.setDay(wData[2].substring(25,wData[2].indexOf(",") - 1));
                //printline checks it's saving it correctly
                  System.out.println(wHold.getDay());

                wHold.setTemp(wData[6].substring(31, wData[6].indexOf(",")));
                 System.out.println(wHold.getTemp());

                wHold.setForcast(wData[13].substring(37, wData[13].indexOf(",")));
                 System.out.println(wHold.getForcast());

                wHold.setWindmph(wData[9].substring(30, wData[9].indexOf(",") - 1));
                 System.out.println(wHold.getWindmph());

                wHold.setWinddir(wData[10].substring(34, wData[10].indexOf(",") - 1));
                 System.out.println(wHold.getWinddir());

                wList[i] = wHold;


            }


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
