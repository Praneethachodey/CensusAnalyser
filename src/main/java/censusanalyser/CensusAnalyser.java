package censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
			ICSVBuilder csvBuilder = new CSVBuilderFactory().createCSVBuilder();
			Iterator<IndiaCensusCSV> censusCSVIterator = csvBuilder.getCSSVFileIterator(reader, IndiaCensusCSV.class);
			return getCount(censusCSVIterator);
		} catch (CSVBuilderException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		} catch (IOException |RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}
	}

	public int loadStateCodeData(String StateCodeCsvPath) throws CensusAnalyserException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(StateCodeCsvPath));
			ICSVBuilder csvBuilder = new CSVBuilderFactory().createCSVBuilder();
			Iterator<IndiaStateCode> censusCSVIterator = csvBuilder.getCSSVFileIterator(reader,IndiaStateCode.class);
			return getCount(censusCSVIterator);
		} catch (CSVBuilderException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		} catch (IOException |RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.STATE_FILE_PROBLM);
		} 

	}

	
	private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> iterable = () -> iterator;
        int namOfEateries = (int) StreamSupport.stream(iterable.spliterator(), false).count();
        return namOfEateries;
    }
}
