package censusanalyser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Map;

public class IndiaCensusAdapterTest {
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "/home/admin1/Downloads/CensusAnalyser/CensusAnalyser/src/test/resources/IndiaStateCensusData.csv";
    private static final String INDIA_STATE_CSV_FILE = "/home/admin1/Downloads/CensusAnalyser/CensusAnalyser/src/test/resources/IndiaStateCode.csv";
    private static final String WRONG_CENSUS_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData1.csv";
    private static final String INCORRECT_INDIA_CENSUS_CSV_FILE_TYPE_PATH = "./src/test/resources/IndiaStateCensusData.json";
    private static final String INCORRECT_INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData1.csv";
    private static final String WRONG_STATECODE_CSV_FILE_PATH = "/home/admin1/Downloads/CensusAnalyser/CensusAnalyser/src/test/resources/IndiaStateCode1.csv";
    private static final String INCORRECT_INDIA_STATECODE_CSV_FILE_TYPE_PATH = "/home/admin1/Downloads/CensusAnalyser/CensusAnalyser/src/test/resources/IndiaStateCode.json";
    private static final String INDIA_CENSUS_CSV_WITH_INCORRECT_DELIMITER_FILE_PATH="/home/admin1/Downloads/CensusAnalyser/CensusAnalyser/src/test/resources/IndiaStateCensusData_incorrect_delimiter.csv";
    private static final String INDIA_CENSUS_CSV_FILE_WITH_INCORRCT_HEADER_PATH="/home/admin1/Downloads/CensusAnalyser/CensusAnalyser/src/test/resources/IndiaStateCensusData_IncorrectHeaders.csv";

    @Test
    public void loadIndiaCensusData_WithCorrectFiles_ShouldReturnExactCount() {
        try {
            IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            Map<String, CensusDAO> indiaCensusDataMap = indiaCensusAdapter.loadCensusData(
                                                CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH,INDIA_STATE_CSV_FILE);
            Assert.assertEquals(29, indiaCensusDataMap.size());
        } catch (CensusAnalyserException e) {

        }
    }
    @Test
    public void loadIndiaCensusData_ButPassingOnlyOneFile_ShouldThrowException() {
        try {
            IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NOT_SUFFICIENT_FILES,e.type);
        }
    }
    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.INDIA,
                                            WRONG_CENSUS_CSV_FILE_PATH,INDIA_STATE_CSV_FILE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }
    @Test
    public void givenIndianCensusCSVFile_ButIncorrectFileType_ShouldThrowsCustomException() {
        try {
            IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.INDIA,
                                            INCORRECT_INDIA_CENSUS_CSV_FILE_TYPE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }
    @Test
    public void givenIndianCensusCSVFile_ButIncorrectFile_ShouldThrowsCustomException() {
        try {
            IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.INDIA,INCORRECT_INDIA_CENSUS_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }
    @Test
    public void givenIndianCensusCSVFile_ButIncorrectDelimiter_ShouldThrowsCustomException() {
        try {
            IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.INDIA,
                                            INDIA_CENSUS_CSV_WITH_INCORRECT_DELIMITER_FILE_PATH,INDIA_STATE_CSV_FILE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.PROBLEM_WITH_HEADER_FORMAT,e.type);
        }
    }
    @Test
    public void givenIndianCensusCSVFile_ButIncorrectHeaders_ShouldThrowsCustomException() {
        try {
            IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.INDIA,
                                            INDIA_CENSUS_CSV_FILE_WITH_INCORRCT_HEADER_PATH,INDIA_STATE_CSV_FILE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.PROBLEM_WITH_HEADER_FORMAT,e.type);
        }
    }
    @Test
    public void givenIndiaStateCodeData_WithWrongFile_ShouldThrowException() {
        try {
            IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.INDIA,
                                            INDIA_CENSUS_CSV_FILE_PATH,WRONG_STATECODE_CSV_FILE_PATH);
          } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }
    @Test
    public void givenIndianStateCodeCSVFile_ButIncorrectFileType_ShouldThrowsCustomException() {
        try {
            IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.INDIA,
                                            INDIA_CENSUS_CSV_FILE_PATH,INCORRECT_INDIA_STATECODE_CSV_FILE_TYPE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }
    @Test
    public void givenIndianStateCodeCSVFile_ButIncorrectDelimiter_ShouldThrowsCustomException() {
        try {
            IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.INDIA,
                                            INDIA_CENSUS_CSV_FILE_PATH,INDIA_STATE_CSV_FILE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.PROBLEM_WITH_HEADER_FORMAT,e.type);
        }
    }

}
