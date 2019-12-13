package censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String INDIA_STATE_CSV_FILE = "/home/admin1/Downloads/CensusAnalyser/CensusAnalyser/src/test/resources/IndiaStateCode.csv";
    private static final String US_CENSUS_CSV_FILE_PATH = "/home/admin1/Downloads/CensusAnalyser/CensusAnalyser/src/test/resources/USCensusData.csv";

    @Test
    public void givenIndianCensusCSVFile_ReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            int numOfRecords = censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CSV_FILE);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void giveIndianCensusData_WhenSortedOnState_ShouldReturnStateStartWithLowestAlphabet() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CSV_FILE);
            String sortedCensusData = censusAnalyser.getSortedData(FieldName.STATE);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void giveIndianCensusData_WhenSortedOnState_ShouldReturnStateWithHighestAlphabet() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CSV_FILE);
            String sortedCensusData = censusAnalyser.getSortedData(FieldName.STATE);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("West Bengal", censusCSV[censusCSV.length - 1].state);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void giveIndianCensusData_WhenSortedOnPopulation_ShouldReturnStateWithHighestPopulation() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CSV_FILE);
            String sortedCensusData = censusAnalyser.getSortedData(FieldName.POPULATION);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Uttar Pradesh", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void giveIndianCensusData_WhenSortedOnPopulation_ShouldReturnStateWithSmallestPopulation() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CSV_FILE);
            String sortedCensusData = censusAnalyser.getSortedData(FieldName.POPULATION);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Sikkim", censusCSV[censusCSV.length - 1].state);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void giveIndianCensusData_WhenSortedOnDensity_ShouldReturnStateWithHighestDensity() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CSV_FILE);
            String sortedCensusData = censusAnalyser.getSortedData(FieldName.DENSITY);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Bihar", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void giveIndianCensusData_WhenSortedOnDensity_ShouldReturnStateWithLowestDensity() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CSV_FILE);
            String sortedCensusData = censusAnalyser.getSortedData(FieldName.DENSITY);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Arunachal Pradesh", censusCSV[censusCSV.length - 1].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void giveIndianCensusData_WhenSortedOnArea_ShouldReturnStateWithLargestArea() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CSV_FILE);
            String sortedCensusData = censusAnalyser.getSortedData(FieldName.AREA);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Rajasthan", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void giveIndianCensusData_WhenSortedOnArea_ShouldReturnStateWithSmallestArea() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CSV_FILE);
            String sortedCensusData = censusAnalyser.getSortedData(FieldName.AREA);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Goa", censusCSV[censusCSV.length - 1].state);
        } catch (CensusAnalyserException e) {
        }
    }
    @Test
    public void giveIndianCensusData_WhenSortedOnState_ButPassOnlyOneFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedData(FieldName.STATE);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NOT_SUFFICIENT_FILES,e.type);
        } }
    @Test
    public void givenUsCensusData_WithCorrectFile_ShouldReturnCorrectRecords() {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
        try {
            int numOfRecords = censusAnalyser.loadCensusData(US_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(51, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenUsCensusData_WithSortedOnState_ShouldReturnStateStartWithLowestAlphabet() {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
        try {
            censusAnalyser.loadCensusData(US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedData(FieldName.STATE);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Alabama", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenUsCensusData_WithSortedOnState_ShouldReturnStateStartWithHighestAlphabet() {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
        try {
            censusAnalyser.loadCensusData(US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedData(FieldName.STATE);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Wyoming", censusCSV[censusCSV.length - 1].state);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenUsCensusData_WithSortedOnPopulation_ShouldReturnStateWithHighestPopulationState() {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
        try {
            censusAnalyser.loadCensusData(US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedData(FieldName.POPULATION);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("District of Columbia", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenUsCensusData_WithSortedOnPopulation_ShouldReturnStateWithLowestPopulationState() {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
        try {
            censusAnalyser.loadCensusData(US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedData(FieldName.POPULATION);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Alaska", censusCSV[censusCSV.length - 1].state);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenUsCensusData_WithSortedOnArea_ShouldReturnStateWithHighestArea() {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
        try {
            censusAnalyser.loadCensusData(US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedData(FieldName.AREA);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Alaska", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenUsCensusData_WithSortedOnArea_ShouldReturnStateWithLowestArea() {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
        try {
            censusAnalyser.loadCensusData(US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedData(FieldName.AREA);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("District of Columbia", censusCSV[censusCSV.length - 1].state);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenUsCensusData_WithSortedOnDensity_ShouldReturnStateWithHighestDensityState() {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
        try {
            censusAnalyser.loadCensusData(US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedData(FieldName.DENSITY);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("North Carolina", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenUsCensusData_WithSortedOnDensity_ShouldReturnStateWithLowestDensityState() {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
        try {
            censusAnalyser.loadCensusData(US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedData(FieldName.DENSITY);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Ohio", censusCSV[censusCSV.length - 1].state);
        } catch (CensusAnalyserException e) {
        }
    }
}
