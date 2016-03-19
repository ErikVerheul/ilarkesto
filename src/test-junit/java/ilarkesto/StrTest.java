package ilarkesto;

import ilarkesto.base.StrExtend;
import junit.framework.TestCase;

public class StrTest extends TestCase {

	public void testRemovePostfix() {
		assertEquals("my", StrExtend.removeSuffix("myTest", "Test"));
		assertEquals("myTest", StrExtend.removeSuffix("myTest", "Tes"));
		assertEquals("myTest2", StrExtend.removeSuffix("myTest2", "Test"));
	}

	public void testGetTokenAfter() {
		assertEquals("result", StrExtend.getTokenAfter("ein test, um 'result' zu finden", " ,'", "test", 1));
	}

	// --- dependencies ---

}
