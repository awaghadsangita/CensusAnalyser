package csvbuilder;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class OpenCSVBuilder<E> implements ICSVBuilder {
    @Override
    public Iterator getCSVFileIterator(Reader reader, Class csvClass) throws CSVBuilderException {
        return this.getCSVBuilder(reader, csvClass).iterator();
    }

    @Override
    public List getCSVFileList(Reader reader, Class csvClass) throws CSVBuilderException {
        return this.getCSVBuilder(reader, csvClass).parse();

    }

    private CsvToBean<E> getCSVBuilder(Reader reader, Class csvClass) {
        CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
        csvToBeanBuilder.withType(csvClass);
        csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
        CsvToBean<E> csvToBean = csvToBeanBuilder.build();
        return csvToBean;
    }
}
