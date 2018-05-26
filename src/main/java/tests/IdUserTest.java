package tests;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import models.User;

public class IdUserTest {
	
	@BeforeClass
	public static void startTest() {
		System.out.println("[TEST] Checking ID (int) accordance..");
	}
	
	@Test
	public void shouldGetId() throws Exception {
		assertThat(User.withId(24).getId())
			.isEqualTo(24);
	}
	
	@Test
	public void shouldBeEqualWhenIdIsEqual() throws Exception {
		assertThat(User.withId(24))
			.isEqualTo(User.withId(24))
			.isNotEqualTo(User.withId(5))
			.isNotEqualTo(User.withId(0))
			.isNotEqualTo(new Object())
			.isNotEqualTo(0);
		
		assertThat(User.withId(0))
			.isEqualTo(User.withId(0))
			.isNotEqualTo(User.withId(5));
	}
	
	@Test
	public void shouldDetermineHashCodeOnId() throws Exception {
		assertThat(User.withId(24).hashCode()).as("hashCode")
			.isEqualTo(User.withId(24).hashCode())
			.isNotEqualTo(User.withId(5).hashCode())
			.isNotEqualTo(User.withId(0).hashCode());
	}
	
	@AfterClass
	public static void stopTest() {
		System.out.println("[TEST] Finished.");
	}
}
