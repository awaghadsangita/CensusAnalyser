package censusanalyser;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Preconditions;
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

    Map<String,IndiaCensusDAO> censusStateMap=null;

    public CensusAnalyser() {
        this.censusStateMap = new HashMap<>();
    }

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaCensusCSV> csvFileIterator = csvBuilder.getCSVFileIterator(reader, IndiaCensusCSV.class);
            Iterable<IndiaCensusCSV> csvIterable = () -> csvFileIterator;
            StreamSupport.stream(csvIterable.spliterator(), false).
                            forEach(censusCSV->censusStateMap.put(censusCSV.state,new IndiaCensusDAO(censusCSV)));
            return this.censusStateMap.size();
        } catch (IOException | CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),
                                                CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException("Error capturing CSV header!",
                                                CensusAnalyserException.ExceptionType.PROBLEM_WITH_HEADER_FORMAT);
        }
    }

    public int loadIndiaStateCodeData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaStateCodeCSV> stateCSVIterator = csvBuilder.getCSVFileIterator(reader, IndiaStateCodeCSV.class);
            int count=0;
           while(stateCSVIterator.hasNext()){
               count++;
               IndiaStateCodeCSV stateCSV=stateCSVIterator.next();
               IndiaCensusDAO censusDAO=censusStateMap.get(stateCSV.state);
               if(censusDAO==null)continue;
               censusDAO.stateCode=stateCSV.stateCode;
           }
            return count;
        } catch (IOException | CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    private <E> int getCount(Iterator<E> CSVIterator) {
        Iterable<E> csvIterable = () -> CSVIterator;
        int namOfEateries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        return namOfEateries;
    }

    public String getStateWiseSortedCensusData(String field) throws CensusAnalyserException {
        if (censusStateMap == null || censusStateMap.size() == 0) {
            throw new CensusAnalyserException("no census data",
                    CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        List<IndiaCensusDAO> censusDAOS=censusStateMap.values().stream().collect(Collectors.toList());
        this.sort(censusDAOS,this.getComparator(field));
        String sortedStateCensusJson = new Gson().toJson(censusDAOS);
        return sortedStateCensusJson;
    }

    public Comparator<IndiaCensusDAO> getComparator(String field){
        Comparator<IndiaCensusDAO> censusComparator ;
        switch (field.toLowerCase()) {
            case "state":
                censusComparator= Comparator.comparing(census -> census.state);
                break;
            case "population":
                censusComparator= Comparator.comparing(census -> census.population);
                break;
            case "area":
                censusComparator= Comparator.comparing(census -> census.areaInSqKm);
                break;
            case "density":
                censusComparator= Comparator.comparing(census -> census.densityPerSqKm);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + field.toLowerCase());
        }
        return censusComparator;
    }

    private void sort(List<IndiaCensusDAO> censusDTOS, Comparator<IndiaCensusDAO> censusComparator) {
        for (int i = 0; i < censusDTOS.size() - 1; i++) {
            for (int j = 0; j < censusDTOS.size() - i - 1; j++) {
                IndiaCensusDAO census1 = censusDTOS.get(j);
                IndiaCensusDAO census2 = censusDTOS.get(j + 1);
                if (censusComparator.compare(census1, census2) > 0) {
                    censusDTOS.set(j, census2);
                    censusDTOS.set(j + 1, census1);
                }
            }
        }
    }
}
