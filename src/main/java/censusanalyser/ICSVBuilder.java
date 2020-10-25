package censusanalyser;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder{
	public Iterator getCSSVFileIterator(Reader reader, Class csvClass) throws CensusAnalyserException;


}
