package exercise_one.model.color;

import main.model.color.RGB;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestColormodelRGB
{
    @Test
    public void shouldCreateInstanceOfRGB()
    {
        assertNotNull(new RGB());
    }
    
    @Test
    public void shouldInitializeWithBlack()
    {
        RGB rgb = new RGB();
        assertTrue(rgb.getRed() == 0 && rgb.getGreen() == 0 && rgb.getBlue() == 0);
    }
    
    @Test
    public void shouldInitializeWithValues()
    {
        RGB rgb = new RGB(1,2,3);
        assertTrue(rgb.getRed() == 1 && rgb.getGreen() == 2 && rgb.getBlue() == 3);
    }
    
    @Test
    public void shouldReturnNonEmptyString()
    {
        RGB rgb = new RGB();
        assertTrue(rgb.toString() instanceof String && rgb.toString() != "");
    }
}
