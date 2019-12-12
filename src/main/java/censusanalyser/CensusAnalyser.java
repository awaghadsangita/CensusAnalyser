package censusanalyser;

import com.google.gson.Gson;
import csvbuilder.CSVBuilderException;
import csvbuilder.CSVBuilderFactory;
import csvbuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    Map<FieldName, Comparator<CensusDAO>> comparatorHashMap = null;
    Map<String, CensusDAO> censusStateMap=null;
    public CensusAnalyser() {

        this.comparatorHashMap = new HashMap<>();
        this.comparatorHashMap.put(FieldName.STATE, Comparator.comparing(census -> census.state));
        this.comparatorHashMap.put(FieldName.POPULATION, Comparator.comparing(census -> census.populationDensity, Comparator.reverseOrder()));
        this.comparatorHashMap.put(FieldName.DENSITY, Comparator.comparing(census -> census.densityPerSqKm, Comparator.reverseOrder()));
        this.comparatorHashMap.put(FieldName.AREA, Comparator.comparing(census -> census.totalArea, Comparator.reverseOrder()));
    }

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
         censusStateMap =new CensusLoader().loadCensusData(csvFilePath,IndiaCensusCSV.class);
         return censusStateMap.size();
    }

    public int loadIndiaStateCodeData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaStateCodeCSV> stateCSVIterator = csvBuilder.getCSVFileIterator(reader, IndiaStateCodeCSV.class);
            Iterable<IndiaStateCodeCSV> csvIterable = () -> stateCSVIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .filter(csvState->censusStateMap.get(csvState)!=null)
                    .forEach(csvState->censusStateMap.get(csvState).stateCode=csvState.stateCode);
            return censusStateMap.size();
        } catch (IOException | CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException("Error capturing CSV header!",
                    CensusAnalyserException.ExceptionType.PROBLEM_WITH_HEADER_FORMAT);
        }
    }

    public int loadUSCensusData(String csvFilePath) throws CensusAnalyserException {
        censusStateMap =new CensusLoader().loadCensusData(csvFilePath,USCensusCSV.class);
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
