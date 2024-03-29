package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class USCensusCSV {
    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "State Id", required = true)
    public String stateId;

    @CsvBindByName(column = "Population", required = true)
    public int population;

    @CsvBindByName(column = "Total area", required = true)
    public double totalArea;

    @CsvBindByName(column = "Population Density", required = true)
    public double populationDensity;

    public USCensusCSV(String state, String stateCode, int population, double populationDensity, double totalArea) {
        this.state=state;
        this.stateId=stateCode;
        this.population=population;
        this.populationDensity=populationDensity;
        this.totalArea=totalArea;
    }

    public USCensusCSV() {

    }
}
