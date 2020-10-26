package censusanalyser;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder<E>{
	public Iterator<E> getCSSVFileIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException;


}
