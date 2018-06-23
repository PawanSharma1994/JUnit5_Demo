package com.automation.junit5;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class StringTest {

	// Must be Static method
	// runs before all methods execution
	@BeforeAll
	static void before_all() {
		System.out.println("--Test Execution Started--");
	}

	// Must be Static method
	// runs after all methods got executed
	@AfterAll
	static void after_all() {
		System.out.println("--All Test Execution Completed--");
	}

	// Runs before each test method (@before for juit4)
	@BeforeEach
	void before_each(TestInfo info) {
		System.out.println("Initiallize the system for " + info.getDisplayName());
	}

	// Runs after each test method (@After for junit4)
	@AfterEach
	void after_each(TestInfo info) {
		System.out.println("Clean up test data " + info.getDisplayName());
	}

	// test can be declared with any access modifiers
	// only public allowed in junit4

	@Test
	@DisplayName("a simple test")
	void test() {
		int actualLength = "pawan".length();
		assertEquals(5, actualLength);
	}

	@Test
	@DisplayName("Test to convert string to uppercase")
	void toUpperCase_basic() {
		String str = "Abcd";
		assertNotNull(str);
		assertEquals("ABCD", str.toUpperCase());
	}

	@Test
	void boolean_basic() {
		boolean flag = "Abcdef".contains("k");
		assertFalse(flag); // condition should be false

	}

	@Test
	void split_Basic() {
		String[] actualStr = "abc def ghi".split(" ");
		// assertArrayEquals for array assertions
		assertArrayEquals(new String[] { "abc", "def", "ghi" }, actualStr);
	}

	// Exception handling is handled using assertThrows
	// ExcpectedExcpetion in junit4
	@Test
	void length_exception() {
		String str = null;
		assertThrows(NullPointerException.class, () -> {
			str.length();
		});
	}

	@ParameterizedTest // To declare the test as parameterized
	@ValueSource(strings = { "abcd", "defgh", "1234" }) // to provide data values : doubles ,longs , ints , strings
	void test_with_parameters(String str) {
		assertTrue(str.length() > 0);
	}

	@ParameterizedTest
	@CsvSource(value = { "abcd,AbcD", "1234dc,1234DC", "abcdefg,ABCDEFG","1,1" })
	void parameterized_test_lowercase(String expected, String actual) {
		assertEquals(expected, actual.toLowerCase());
	}

}
