package censusanalyser;

public class CensusDAO {
    public String state;
    public String stateCode;
    public int population;
    public double totalArea;
    public double densityPerSqKm;
    public double populationDensity;

    public CensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state=indiaCensusCSV.state;
        totalArea = indiaCensusCSV.areaInSqKm;
        densityPerSqKm= indiaCensusCSV.densityPerSqKm;
        populationDensity = indiaCensusCSV.population;
    }

    public CensusDAO(USCensusCSV censusCSV) {
        state=censusCSV.state;
        stateCode=censusCSV.stateId;
        population=censusCSV.population;
        populationDensity=censusCSV.populationDensity;
        totalArea=censusCSV.totalArea;
    }
}
