package censusanalyser;

import com.google.gson.Gson;

import java.util.*;

import static java.util.stream.Collectors.toCollection;

public class CensusAnalyser {
    public enum Country {INDIA, US}

    Map<FieldName, Comparator<CensusDAO>> comparatorHashMap = null;
    Map<String, CensusDAO> censusStateMap = null;
    private Country country;

    public CensusAnalyser(Country country) {
        this.country = country;
        this.comparatorHashMap = new HashMap<>();
        this.comparatorHashMap.put(FieldName.STATE, Comparator.comparing(census -> census.state));
        this.comparatorHashMap.put(FieldName.POPULATION, Comparator.comparing(census -> census.population, Comparator.reverseOrder()));
        this.comparatorHashMap.put(FieldName.DENSITY, Comparator.comparing(census -> census.populationDensity, Comparator.reverseOrder()));
        this.comparatorHashMap.put(FieldName.AREA, Comparator.comparing(census -> census.totalArea, Comparator.reverseOrder()));
        Comparator<CensusDAO> populationComparator = Comparator.comparing(census -> census.population,Comparator.reverseOrder());
        Comparator<CensusDAO> densityComparator = Comparator.comparing(census -> census.populationDensity,Comparator.reverseOrder());
        Comparator<CensusDAO> populationWithDensityComparator = populationComparator.thenComparing(densityComparator);
        this.comparatorHashMap.put(FieldName.POPULATIONWITHDENSITY, populationWithDensityComparator);
    }

    public int loadCensusData(String... csvFilePath) throws CensusAnalyserException {
        CensusAdapter censusAdapter = CensusAdapterFactory.getCensusData(country);
        censusStateMap = censusAdapter.loadCensusData(country, csvFilePath);
        return censusStateMap.size();
    }

    public String getSortedData(FieldName field) throws CensusAnalyserException {
        if (censusStateMap == null || censusStateMap.size() == 0) {
            throw new CensusAnalyserException("no census data",
                    CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        ArrayList censusDTOS = censusStateMap.values().stream()
                .sorted(this.comparatorHashMap.get(field))
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(toCollection(ArrayList::new));
        String sortedStateCensusJson = new Gson().toJson(censusDTOS);
        return sortedStateCensusJson;
    }
}
