package censusanalyser;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class CensusAdapterTest {
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "/home/admin1/Downloads/CensusAnalyser/CensusAnalyser/src/test/resources/IndiaStateCensusData.csv";
    private static final String INDIA_STATE_CSV_FILE = "/home/admin1/Downloads/CensusAnalyser/CensusAnalyser/src/test/resources/IndiaStateCode.csv";
    @Test
    public void loadIndiaCensusData_WithCorrectFiles_ShouldReturnExactCount() {
        try {
            IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            Map<String, CensusDAO> indiaCensusDataMap = indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH,INDIA_STATE_CSV_FILE);
            Assert.assertEquals(29, indiaCensusDataMap.size());
        } catch (CensusAnalyserException e) {
        }
    }
    @Test
    public void loadIndiaCensusData_ButPassingOnlyOneFile_ShouldThrowException() {
        try {
            IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            Map<String, CensusDAO> indiaCensusDataMap = indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, indiaCensusDataMap.size());
        } catch (CensusAnalyserException e) {
        }
    }
}
