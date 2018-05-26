package tests;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import models.User;

public class UserNameTest {

	@BeforeClass
	public static void startTest() {
		System.out.println("[TEST] Checking name (String) accordance..");
	}
	
	@Test
	public void shouldGetName() throws Exception {
		assertThat(User.withName("Jan").getFirst_name())
			.isEqualTo("Jan");
	}
	
	@Test
	public void shouldBeEqualWhenNameIsEqual() throws Exception {
		assertThat(User.withName("Jan"))
			.isEqualTo(User.withName("Jan"))
			.isNotEqualTo(User.withName("Something else"))
			.isNotEqualTo(User.withName(null))
			.isNotEqualTo(new Object())
			.isNotEqualTo(null);
		
		assertThat(User.withName(null))
			.isEqualTo(User.withName(null))
			.isNotEqualTo(User.withName("Jan"));
	}
	
	@Test
	public void shouldDetermineHashCodeOnName() throws Exception {
		assertThat(User.withName("Jan").hashCode()).as("hashCode")
			.isEqualTo(User.withName("Jan").hashCode())
			.isNotEqualTo(User.withName("Something else").hashCode())
			.isNotEqualTo(User.withName(null).hashCode());
	}
	
	@AfterClass
	public static void stopTest() {
		System.out.println("[TEST] Finished.");
	}
}

