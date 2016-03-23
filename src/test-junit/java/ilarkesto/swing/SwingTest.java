package ilarkesto.swing;

import static java.lang.System.setProperty;
import junit.framework.TestCase;

public class SwingTest extends TestCase {

        @Override
        protected void setUp() throws Exception {
                setProperty("java.awt.headless", "true");
        }

        public void testGetBestWindowPosition() {
	// Dimension window = new Dimension(100, 50);
	// Point parentPosition = new Point(10, 10);
	// Dimension parent = new Dimension(200, 100);
	// Point pos = Swing.getBestWindowPosition(window, parentPosition, parent);
	// assertEquals(60, pos.x);
	// assertEquals(35, pos.y);
        }

}
