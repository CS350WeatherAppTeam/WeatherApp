package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    // random changee

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("weather.fxml"));
        primaryStage.setTitle("Weather App");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }


    public static void main(String[] args)  {

      //  ZipcodeList z = new ZipcodeList();
    //    z.Build();




       // Controller c = new Controller();

       // Zipcode zip = z.Find("76712");
     //  String coords = c.ZiptoCoords("75090");

      //  "33.604148,-96.550263"

      //  String coords = zip.getLat() + "," + zip.getLog();

    //    c.CoordstoPoint(coords);

  //      c.getForcast();

    //    String ziplink = "https://www.zipcodeapi.com/rest/j0OBr5GWb5guxkTB8hUPEynhnS1TGfLRBeAtMfsf6KpDRzfPQOtyb5XtxiCKuzo3/info.json/75090/degrees";

     //   String pointlink = "https://api.weather.gov/points/{latitude},{longitude}";

    //    String link = "https://api.weather.gov/gridpoints/FWD/133,140/forecast";
     //   URL githubUrl = new URL(link);
     //   HttpURLConnection githubHTTP = (HttpURLConnection) githubUrl.openConnection();
     //   Map<String, List<String>> headerMap = githubHTTP.getHeaderFields();

     //   InputStream dataStream = githubHTTP.getInputStream();

     //   BufferedReader br = null;

     //   try {
            //This can throw file not found exception

       //     br = new BufferedReader(new InputStreamReader(dataStream, "UTF-8"));
            ;
            //Get rid of header line
        //    br.readLine();
            //Null when we're done
       //     String lineOData = br.readLine();

        //    while (lineOData != null) {
        //        lineOData = br.readLine();

         //       System.out.println(lineOData);
        //    }
       // } catch (IOException ex){
            //Print to the error stream
            //IOException ex will contain attempted file name
     //       System.err.println("ERROR accessing :"+ex.getMessage());
     //   } finally {
            //Ok, we're done let's close this down.
      //      try {
                //br may have failed to init, check before closing
       //         if(br != null){
       //             br.close();
       //         }
      //      } catch (IOException ex){
                //We couldn't close the file?
                //Ok, we're screwed bail.
       //         ex.printStackTrace();
                //Non-zero means we failed
         //       System.exit(-1);
    //        }
     //   }

     //   System.out.println("End");
        launch(args);
    }
}
