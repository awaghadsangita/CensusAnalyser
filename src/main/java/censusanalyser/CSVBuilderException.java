package censusanalyser;

public class CSVBuilderException extends Exception {

    public CSVBuilderException(String message, ExceptionType unableToParse) {

    }

    enum ExceptionType {
        CENSUS_FILE_PROBLEM,UNABLE_TO_PARSE
    }
    CSVBuilderException.ExceptionType type;

    public CSVBuilderException(String message, String name) {
        super(message);
        this.type = ExceptionType.valueOf(name);
    }

//    public CSVBuilderException(String message, CSVBuilderException.ExceptionType type, Throwable cause) {
//        super(message, cause);
//        this.type = type;
//    }
}
