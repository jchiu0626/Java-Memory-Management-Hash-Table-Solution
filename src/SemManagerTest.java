import java.io.FileNotFoundException;
import org.junit.Test;
import student.TestCase;

//-------------------------------------------------------------------------
/**
*  Test the SemManager class
*
*  @author Yung-Lin Chiu
*  @version September 2023
*/
public class SemManagerTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */    
    public void setUp() {
        // Nothing here
    }


    /**
     * Get code coverage of the class declaration.
     * @throws FileNotFoundException 
     */
    @Test
    public void testMInitx() throws FileNotFoundException
    {
        SemManager sem = new SemManager();
        assertNotNull(sem);
        SemManager.main(null);
    }
    
    /**
     * Get code coverage of the class declaration.
     * @throws FileNotFoundException 
     */    
    @Test
    public void testArgsLength() throws FileNotFoundException
    {
        SemManager sem = new SemManager();
        assertNotNull(sem);
        String[] args = {"abc"};
        SemManager.main(args);
    }
    
    /**
     * Get code coverage of the class declaration.
     * @throws FileNotFoundException 
     */    
    @Test
    public void testArgsType() 
            throws java.lang.NumberFormatException, FileNotFoundException
    {
        SemManager sem = new SemManager();
        assertNotNull(sem);
        String[] args = {"abc", "def", "ghi"};
        SemManager.main(args);
    }
}

