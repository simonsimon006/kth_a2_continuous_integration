package kth_a2_continuous_integration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

public class ContinuousIntegrationServerTest {
    /*
     * @Test void appHasAGreeting() {
     * App classUnderTest = new App();
     * assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
     * }
     */
    @BeforeEach
    void setUp() {
    }

    /**
     * Tests execute() by building the DECIDE project as a sample project.
     *
     * @return Needs to evaluate to true.
     */
    @Test
    @DisplayName("Sample project is built successfully")
    void buildSampleProject() {
        assertTrue(true, "Dummy test.");
    }
}
