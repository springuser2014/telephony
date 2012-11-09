package telephony;


import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertTrue;

@RunWith(JukitoRunner.class)
public class RandomTest {                       // (1)


    @Test
    public void testSimple() {                                              // (3)
        assertTrue(true);
    }
}
