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
        populationDensity= indiaCensusCSV.densityPerSqKm;
        population = indiaCensusCSV.population;
    }

    public CensusDAO(USCensusCSV censusCSV) {
        state=censusCSV.state;
        stateCode=censusCSV.stateId;
        population=censusCSV.population;
        populationDensity=censusCSV.populationDensity;
        totalArea=censusCSV.totalArea;
    }

    public Object getCensusDTO(CensusAnalyser.Country country) {
        if(country.equals(CensusAnalyser.Country.US))
            return new USCensusCSV(state,stateCode,population,populationDensity,totalArea);
        return new IndiaCensusCSV(state,(int)population,(int)populationDensity,(int)totalArea);
    }
}
