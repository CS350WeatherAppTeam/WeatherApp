package sample;

public class Weather {

    String day;
    String temp;
    String windmph;
    String winddir;
    String Forecast;
    String fullForecast;
    String iconLink;
    int number;

    public String getForecast() {
        return Forecast;
    }

    public void setForecast(String forecast) {
        Forecast = forecast;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getIconLink() {
        return iconLink;
    }

    public void setIconLink(String iconLink) {
        this.iconLink = iconLink;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWindmph() {
        return windmph;
    }

    public void setWindmph(String windmph) {
        this.windmph = windmph;
    }

    public String getWinddir() {
        return winddir;
    }

    public void setWinddir(String winddir) {
        this.winddir = winddir;
    }

    public String getForcast() {
        return Forecast;
    }

    public void setForcast(String forcast) {
        Forecast = forcast;
    }

    public String getFullForecast() {
        return fullForecast;
    }

    public void setFullForecast(String fullForecast) {
        this.fullForecast = fullForecast;
    }
}
