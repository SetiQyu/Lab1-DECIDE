import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class DecideTest {
    @Test void testGradleTesting() {
        Decide classUnderTest = new Decide();
        assertEquals(classUnderTest.writeOut(), "Test print", "Testing tests");
    }
}
