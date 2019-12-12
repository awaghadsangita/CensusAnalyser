package censusanalyser;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class USCensusAdapterTest {
    private String US_CENSUS_CSV_FILE_PATH="/home/admin1/Downloads/CensusAnalyser/CensusAnalyser/src/test/resources/USCensusData.csv";
    private String WRONG_US_CENSUS_CSV_FILE_PATH="/home/admin1/Downloads/CensusAnalyser/CensusAnalyser/src/test/resources/USCensusData1.csv";
    private String INCORRECT_US_CENSUS_CSV_FILE_TYPE_PATH="/home/admin1/Downloads/CensusAnalyser/CensusAnalyser/src/test/resources/USCensusData1.json";


    @Test
    public void loadingUsCensusData_ShouldReturnCorrectRecords() {
        USCensusAdapter usCensusAdapter = new USCensusAdapter();
        try {
            Map<String, CensusDAO> usCensusDataMap = usCensusAdapter.loadCensusData(CensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(51,usCensusDataMap.size());
        } catch (CensusAnalyserException e) {
        }
    }
    @Test
    public void givenUSCensusData_WithWrongFile_ShouldThrowException() {
        try {
            USCensusAdapter usCensusAdapter = new USCensusAdapter();
            usCensusAdapter.loadCensusData(CensusAnalyser.Country.US,WRONG_US_CENSUS_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }
    @Test
    public void givenUSCensusCSVFile_ButIncorrectFileType_ShouldThrowsCustomException() {
        try {
            USCensusAdapter usCensusAdapter = new USCensusAdapter();
            usCensusAdapter.loadCensusData(CensusAnalyser.Country.US,INCORRECT_US_CENSUS_CSV_FILE_TYPE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }
    @Test
    public void givenUSCensusCSVFile_ButIncorrectDelimiter_ShouldThrowsCustomException() {
        try {
            USCensusAdapter usCensusAdapter = new USCensusAdapter();
            usCensusAdapter.loadCensusData(CensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.PROBLEM_WITH_HEADER_FORMAT,e.type);
        }
    }
    @Test
    public void givenIndianCensusCSVFile_ButIncorrectHeaders_ShouldThrowsCustomException() {
        try {
            USCensusAdapter usCensusAdapter = new USCensusAdapter();
            usCensusAdapter.loadCensusData(CensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.PROBLEM_WITH_HEADER_FORMAT,e.type);
        }
    }
}
