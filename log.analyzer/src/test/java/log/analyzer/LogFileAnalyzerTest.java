package log.analyzer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class LogFileAnalyzerTest {

	@Mock
	private InputStream mockStream;

	@InjectMocks
	private LogFileAnalyzer analyzer;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSuccessfulParseLogCounts() throws IOException {
		String data = "Info: Start\n" + "Warning: Low Memory\n" + "Error: DB Failed\n" + "Info: Finished";

		InputStream stream = new ByteArrayInputStream(data.getBytes());

		Map<String, Integer> result = analyzer.parseLogCounts(stream);

		assertEquals(2, result.get("INFO"));
		assertEquals(1, result.get("WARNING"));
		assertEquals(1, result.get("ERROR"));
	}

	@Test
	void testParseLogCountsWhenInputStreamIsNull() {
		assertThrows(NullPointerException.class, () -> analyzer.parseLogCounts(null));
	}

	@Test
	void testParseLogCountsWhenEmptyFile() throws IOException {
		InputStream stream = new ByteArrayInputStream("".getBytes());
		Map<String, Integer> result = analyzer.parseLogCounts(stream);
		assertEquals(0, result.size());
	}

	@Test
	void testSuccessfulProcessLine() {
		Map<String, Integer> counts = new java.util.HashMap<>();
		analyzer.processLine("Info: Hello", counts);
		assertEquals(1, counts.get("INFO"));
	}

	@Test
	void testSuccesfulProcessLineWithMultipleSameKeys() {
		Map<String, Integer> counts = new java.util.HashMap<>();

		analyzer.processLine("Warning: A", counts);
		analyzer.processLine("Warning: B", counts);
		analyzer.processLine("Warning: C", counts);

		assertEquals(3, counts.get("WARNING"));
	}

	@Test
	void testGetFileFromResourcesWhenFileNotFound() {
		assertThrows(FileNotFoundException.class, () -> analyzer.getFileFromResources("log.txt"));
	}
}
