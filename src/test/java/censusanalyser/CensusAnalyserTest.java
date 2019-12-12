package censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String INDIA_STATE_CSV_FILE = "/home/admin1/Downloads/CensusAnalyser/CensusAnalyser/src/test/resources/IndiaStateCode.csv";
    private static final String WRONG_CENSUS_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData1.csv";
    private static final String INCORRECT_INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData1.csv";
    private static final String INCORRECT_INDIA_CENSUS_CSV_FILE_TYPE_PATH = "./src/test/resources/IndiaStateCensusData.json";
    private static final String WRONG_STATECODE_CSV_FILE_PATH = "/home/admin1/Downloads/CensusAnalyser/CensusAnalyser/src/test/resources/IndiaStateCode1.csv";
    private static final String INCORRECT_INDIA_STATECODE_CSV_FILE_TYPE_PATH = "/home/admin1/Downloads/CensusAnalyser/CensusAnalyser/src/test/resources/IndiaStateCode.json";
    private static final String US_CENSUS_CSV_FILE_PATH = "/home/admin1/Downloads/CensusAnalyser/CensusAnalyser/src/test/resources/USCensusData.csv";
    @Test
    public void givenIndianCensusCSVFile_ReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH,INDIA_STATE_CSV_FILE);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }
    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(CensusAnalyser.Country.INDIA,WRONG_CENSUS_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }
    @Test
    public void giveIndianCensusData_WhenSortedOnState_ShouldReturnSortedResult() {
         try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedData(FieldName.STATE);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
        } catch (CensusAnalyserException e) {}
    }
    @Test
    public void givenIndianCensusCSVFile_ButIncorrectFileType_ShouldThrowsCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(CensusAnalyser.Country.INDIA,INCORRECT_INDIA_CENSUS_CSV_FILE_TYPE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }
    @Test
    public void givenIndianCensusCSVFile_ButIncorrectFile_ShouldThrowsCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(CensusAnalyser.Country.INDIA,INCORRECT_INDIA_CENSUS_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }
    @Test
    public void givenIndianCensusCSVFile_ButIncorrectDelimiter_ShouldThrowsCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.PROBLEM_WITH_HEADER_FORMAT,e.type);
        }
    }
    @Test
    public void givenIndianCensusCSVFile_ButIncorrectHeaders_ShouldThrowsCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.PROBLEM_WITH_HEADER_FORMAT,e.type);
        }
    }
    @Test
    public void givenIndiaStateCodeData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(CensusAnalyser.Country.INDIA,WRONG_STATECODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }
    @Test
    public void givenIndianStateCodeCSVFile_ButIncorrectFileType_ShouldThrowsCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(CensusAnalyser.Country.INDIA,INCORRECT_INDIA_STATECODE_CSV_FILE_TYPE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }
    @Test
    public void givenIndianStateCodeCSVFile_ButIncorrectDelimiter_ShouldThrowsCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(CensusAnalyser.Country.INDIA,INDIA_STATE_CSV_FILE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.PROBLEM_WITH_HEADER_FORMAT,e.type);
        }
    }
    @Test
    public void giveIndianCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedData(FieldName.POPULATION);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Uttar Pradesh", censusCSV[0].state);
        } catch (CensusAnalyserException e) {}
    }
    @Test
    public void giveIndianCensusData_WhenSortedOnDensity_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedData(FieldName.DENSITY);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Bihar", censusCSV[0].state);
        } catch (CensusAnalyserException e) {}
    }
    @Test
    public void giveIndianCensusData_WhenSortedOnArea_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedData(FieldName.AREA);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Rajasthan", censusCSV[0].state);
        } catch (CensusAnalyserException e) {}
    }
    @Test
    public void givenUsCensusData_ShouldReturnCorrectRecords() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        try {
            int numOfRecords = censusAnalyser.loadIndiaCensusData(CensusAnalyser.Country.US,US_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(51,numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }
}
