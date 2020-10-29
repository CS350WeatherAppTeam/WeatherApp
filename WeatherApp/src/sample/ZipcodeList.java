package sample;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ZipcodeList {


    ArrayList<Zipcode>[] zipcodeList = new ArrayList[10];



    public void Build() throws Exception {

        for (int i = 0; i < 10; i++) {

            zipcodeList[i] = new ArrayList<Zipcode>();

        }

        Path noPath = Paths.get("us-zip-code-latitude-longitude.txt");
        File simpleData = noPath.toFile();

        FileReader fr = null;
        BufferedReader br = null;

        try {
            //This can throw file not found exception
            fr = new FileReader(simpleData);
            br = new BufferedReader(fr);
            //Null when we're done
            String lineOData = br.readLine();

            //System.out.println("The line"+lineOData);

            String parse = new String();
            String[] split;


            lineOData = br.readLine();

            while (lineOData != null) {

                Zipcode zip = new Zipcode();

                split = lineOData.split(";");


                //      for(int i = 0; i < split.length; i++){
                //         System.out.println(split[i]);
                //    }

                zip.setZip(split[0]);
                zip.setCity(split[1]);
                zip.setState(split[2]);
                zip.setLat(split[3]);
                zip.setLog(split[4]);


                //zipcode is finished, now to sort

                //convert zip to int;

                int intzip = Integer.parseInt(zip.getZip());

                int key = 0;

                if (intzip < 10000) {

                    key = 0;

                } else {
                    key = intzip / 10000;
                }

                zipcodeList[key].add(zip);

                lineOData = br.readLine();

            }


        } catch (IOException ex) {
            //Print to the error stream
            //IOException ex will contain attempted file name
            System.err.println("ERROR accessing :" + ex.getMessage());
        } finally {
            //Ok, we're done let's close this down.
            try {
                //br may have failed to init, check before closing
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                //We couldn't close the file?
                //Ok, we're screwed bail.
                ex.printStackTrace();
                //Non-zero means we failed
                System.exit(-1);
            }
        }

        for (int i = 0; i < 10; i++) {

            int heapsize = zipcodeList[i].size();
            BuildMaxHeap(i, heapsize);

            for(int j = zipcodeList[i].size() - 1; j > 0; j--){

                Zipcode temp = zipcodeList[i].get(0);

                zipcodeList[i].set(0, zipcodeList[i].get(j));
                zipcodeList[i].set(j, temp);
                heapsize--;
                MaxHeapify(i, 1 , heapsize);




            }






        }
    }

    public void BuildMaxHeap(int i, int heapsize) {

        heapsize = zipcodeList[i].size();
        for(int j = zipcodeList[i].size()/2; j > 0; j--){

            MaxHeapify(i, j, heapsize);

        }
    }


    public void MaxHeapify(int key, int i, int heapsize){

        int l = i * 2;
        int r = i * 2 + 1;

        int largest = i;

        if( l < heapsize && Integer.parseInt(zipcodeList[key].get(l).getZip()) > Integer.parseInt(zipcodeList[key].get(i).getZip())){

            largest = l;

        } else {

            largest = i;

        }

        if( r < heapsize && Integer.parseInt(zipcodeList[key].get(r).getZip()) > Integer.parseInt(zipcodeList[key].get(largest).getZip())) {

            largest = r;

        }

        if(largest != i){

            Zipcode temp = zipcodeList[key].get(i);

            zipcodeList[key].set(i, zipcodeList[key].get(largest));
            zipcodeList[key].set(largest, temp);
            MaxHeapify(key, largest, heapsize);

        }


    }





    public Zipcode Find(String v){

        int searchzip = Integer.parseInt(v);

        int key = 0;

        if(searchzip < 10000){
            key = 0;
        } else {
            key = searchzip / 10000;
        }

        int count = 0;
        Zipcode hunt = zipcodeList[key].get(count);

        while( count < zipcodeList[key].size() ) {
            hunt = zipcodeList[key].get(count);

            if(hunt.getZip().equals(v) == false){

                count++;


            } else {
                return hunt;
            }

        }

        return null;




    }

    public String toString(){

        String s = new String();

        for(int i = 0; i < zipcodeList.length; i++){
            for(int j = 0; j < zipcodeList[i].size(); j++){
                s = s + zipcodeList[i].get(j).getZip() + " ";
            }
        }

        return s;
    }






}
