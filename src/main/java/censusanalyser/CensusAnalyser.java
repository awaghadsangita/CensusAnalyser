package censusanalyser;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser {
    public enum Country {INDIA, US}

    Map<FieldName, Comparator<CensusDAO>> comparatorHashMap = null;
    Map<String, CensusDAO> censusStateMap = null;

    public CensusAnalyser() {
        this.comparatorHashMap = new HashMap<>();
        this.comparatorHashMap.put(FieldName.STATE, Comparator.comparing(census -> census.state));
        this.comparatorHashMap.put(FieldName.POPULATION, Comparator.comparing(census -> census.populationDensity, Comparator.reverseOrder()));
        this.comparatorHashMap.put(FieldName.DENSITY, Comparator.comparing(census -> census.densityPerSqKm, Comparator.reverseOrder()));
        this.comparatorHashMap.put(FieldName.AREA, Comparator.comparing(census -> census.totalArea, Comparator.reverseOrder()));
    }

    public int loadCensusData(Country country, String... csvFilePath) throws CensusAnalyserException {
        CensusAdapter censusAdapter = CensusAdapterFactory.getCensusData(country);
        censusStateMap = censusAdapter.loadCensusData(country, csvFilePath);
        return censusStateMap.size();
    }

    public String getSortedData(FieldName field) throws CensusAnalyserException {
        if (censusStateMap == null || censusStateMap.size() == 0) {
            throw new CensusAnalyserException("no census data",
                    CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        List<CensusDAO> censusDAOS = censusStateMap.values().stream().collect(Collectors.toList());
        this.sort(censusDAOS, this.comparatorHashMap.get(field));
        String sortedStateCensusJson = new Gson().toJson(censusDAOS);
        return sortedStateCensusJson;
    }

    private void sort(List<CensusDAO> censusDTOS, Comparator<CensusDAO> censusComparator) {
        for (int i = 0; i < censusDTOS.size() - 1; i++) {
            for (int j = 0; j < censusDTOS.size() - i - 1; j++) {
                CensusDAO census1 = censusDTOS.get(j);
                CensusDAO census2 = censusDTOS.get(j + 1);
                if (censusComparator.compare(census1, census2) > 0) {
                    censusDTOS.set(j, census2);
                    censusDTOS.set(j + 1, census1);
                }
            }
        }
    }
}
