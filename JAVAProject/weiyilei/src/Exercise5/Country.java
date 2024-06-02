package Exercise5;

public class Country {
    String name;
    long population;
    long area;
    double dense;

    public Country(String name,long population,long area,double dense){
        this.name = name;
        this.population = population;
        this.area = area;
        this.dense = dense;
    }

    public String getName(){
        return this.name;
    }

    public long getPopulation(){
        return this.population;
    }

    public long getArea(){
        return this.area;
    }

    public double getDense(){
        return this.dense;
    }
}
