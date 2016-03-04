package question;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FiBQuestionTest {

	FiBQuestion test;
	
	@Before
	public void setUp() throws Exception {
		test = new FiBQuestion("If you enter 'true' into this blank,|, the answer will be correct.", "true");
	}

	@Test
	public void test() {
		assertTrue("If you enter 'true' into this blank,".equals(test.getPreText()));
		assertFalse("This is not the pretext".equals(test.getPreText()));
		assertFalse("If you enter 'true' into this blank,|, the answer will be correct.".equals(test.getPreText()));
		assertTrue("If you enter 'true' into this blank,|, the answer will be correct.".equals(test.getQuestion()));
		assertTrue(", the answer will be correct.".equals(test.getPostText()));
		assertFalse(", this is not the posttext".equals(test.getPostText()));
		
	}

}
