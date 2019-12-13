package censusanalyser;

import java.util.Map;

public class CensusAdapterFactory {
    public static CensusAdapter getCensusData(CensusAnalyser.Country country)
            throws CensusAnalyserException {
        if (country.equals(CensusAnalyser.Country.INDIA)) {
            return new IndiaCensusAdapter();
        }
        if (country.equals(CensusAnalyser.Country.US)) {
            return new USCensusAdapter();
        }
        throw new CensusAnalyserException("Unkonwn country", CensusAnalyserException.ExceptionType.INCORRECT_COUNTRY);
    }
}
