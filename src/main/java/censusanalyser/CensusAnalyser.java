package censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import censusanalyser.CensusAnalyserException.ExceptionType;

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
			CsvToBeanBuilder<IndiaCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(IndiaCensusCSV.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<IndiaCensusCSV> csvToBean = csvToBeanBuilder.build();
			Iterator<IndiaCensusCSV> censusCSVIterator = csvToBean.iterator();
			;
			Iterable<IndiaCensusCSV> csvIterable = () -> censusCSVIterator;
			int namOfEateries = 0;
			namOfEateries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
			return namOfEateries;
		} catch (IllegalStateException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		} catch (IOException | RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}
	}

	public int loadStateCodeData(String StateCodeCsvPath) throws CensusAnalyserException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(StateCodeCsvPath));
			CsvToBeanBuilder<IndiaStateCode> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(IndiaStateCode.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<IndiaStateCode> csvToBean = csvToBeanBuilder.build();
			Iterator<IndiaStateCode> censusCSVIterator = csvToBean.iterator();
			Iterable<IndiaStateCode> csvIterable = () -> censusCSVIterator;
			int namOfEateries = 0;
			namOfEateries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
			return namOfEateries;
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (IllegalStateException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		}

	}
}
