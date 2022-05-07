import exceptions.DuplicatedElementOnListException;
import exceptions.InvalidStringContainerPatternException;
import exceptions.InvalidStringContainerValueException;
import org.assertj.core.api.SoftAssertions;
import org.junit.*;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class StringContainerTest {
    private StringContainer sc;

    @Before
    public void init() {
        sc = new StringContainer("\\d{2}[-]\\d{3}");
    }

    @Test
    public void shouldReturnTrueWhenAddedOneElement() {
        boolean flag = sc.add("00-000");
        assertTrue(flag);
    }

    @Test
    public void shouldReturnCorrectSize() {
        sc.add("00-000");
        sc.add("00-000");
        sc.add("22-000");
        assertEquals(3, sc.size());
    }

    @Test(expected = DuplicatedElementOnListException.class)
    public void shouldThrowDuplicatedElementOnListExceptionWhenAddingDuplicatedElementInNotAllowed() {
        sc = new StringContainer("\\d{2}[-]\\d{3}", true);
        sc.add("00-000");
        sc.add("00-000");
    }

    @Test
    public void shouldReturnExpectedElementWhenGet() {
        sc.add("00-000");
        sc.add("11-000");
        sc.add("22-000");
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(sc.get(0)).isEqualTo("00-000");
        softAssertions.assertThat(sc.get(2)).isEqualTo("22-000");

        softAssertions.assertAll();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowIndexOutOfBoundsExceptionWhenGetNegativeIndex() {
        sc.add("00-000");
        sc.add("11-000");

        sc.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowIndexOutOfBoundsExceptionWhenGetWrongIndex() {
        sc.add("00-000");
        sc.add("11-000");

        sc.get(2);
    }

    @Test
    public void shouldReturnTrueWhenStringContainerContainString() {
        sc.add("00-000");
        sc.add("11-000");
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(sc.containString("00-000")).isEqualTo(true);
        softAssertions.assertThat(sc.containString("11-000")).isEqualTo(true);

        softAssertions.assertAll();
    }

    @Test
    public void shouldReturnFalseWhenStringContainerNotContainString() {
        sc.add("00-000");
        sc.add("11-000");

        assertFalse(sc.containString("22-000"));
    }

    @Test
    public void shouldRemoveElementFromFirstIndex() {
        sc.add("00-000");
        sc.add("11-000");
        sc.add("22-000");
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(sc.remove(0)).isEqualTo(true);
        softAssertions.assertThat(sc.size()).isEqualTo(2);
        softAssertions.assertThat(sc.containString("00-000")).isEqualTo(false);
        softAssertions.assertThat(sc.containString("11-000")).isEqualTo(true);
        softAssertions.assertThat(sc.containString("22-000")).isEqualTo(true);

        softAssertions.assertAll();
    }

    @Test
    public void shouldRemoveElementFromMiddleIndex() {
        sc.add("00-000");
        sc.add("11-000");
        sc.add("22-000");
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(sc.remove(1)).isEqualTo(true);
        softAssertions.assertThat(sc.size()).isEqualTo(2);
        softAssertions.assertThat(sc.containString("00-000")).isEqualTo(true);
        softAssertions.assertThat(sc.containString("11-000")).isEqualTo(false);
        softAssertions.assertThat(sc.containString("22-000")).isEqualTo(true);

        softAssertions.assertAll();
    }

    @Test
    public void shouldRemoveElementFromLastIndex() {
        sc.add("00-000");
        sc.add("11-000");
        sc.add("22-000");
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(sc.remove(2)).isEqualTo(true);
        softAssertions.assertThat(sc.size()).isEqualTo(2);
        softAssertions.assertThat(sc.containString("00-000")).isEqualTo(true);
        softAssertions.assertThat(sc.containString("11-000")).isEqualTo(true);
        softAssertions.assertThat(sc.containString("22-000")).isEqualTo(false);

        softAssertions.assertAll();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowIndexOutOfBoundsExceptionWhenRemoveWrongIndex() {
        sc.add("00-000");
        sc.add("11-000");

        sc.remove(2);
    }

    @Test
    public void shouldRemoveFirstElement() {
        sc.add("00-000");
        sc.add("11-000");
        sc.add("22-000");
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(sc.remove("00-000")).isEqualTo(true);
        softAssertions.assertThat(sc.size()).isEqualTo(2);
        softAssertions.assertThat(sc.containString("00-000")).isEqualTo(false);
        softAssertions.assertThat(sc.containString("11-000")).isEqualTo(true);
        softAssertions.assertThat(sc.containString("22-000")).isEqualTo(true);

        softAssertions.assertAll();
    }

    @Test
    public void shouldRemoveMiddleElement() {
        sc.add("00-000");
        sc.add("11-000");
        sc.add("22-000");
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(sc.remove("11-000")).isEqualTo(true);
        softAssertions.assertThat(sc.size()).isEqualTo(2);
        softAssertions.assertThat(sc.containString("00-000")).isEqualTo(true);
        softAssertions.assertThat(sc.containString("11-000")).isEqualTo(false);
        softAssertions.assertThat(sc.containString("22-000")).isEqualTo(true);

        softAssertions.assertAll();
    }

    @Test
    public void shouldRemoveLastElement() {
        sc.add("00-000");
        sc.add("11-000");
        sc.add("22-000");
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(sc.remove("22-000")).isEqualTo(true);
        softAssertions.assertThat(sc.size()).isEqualTo(2);
        softAssertions.assertThat(sc.containString("00-000")).isEqualTo(true);
        softAssertions.assertThat(sc.containString("11-000")).isEqualTo(true);
        softAssertions.assertThat(sc.containString("22-000")).isEqualTo(false);

        softAssertions.assertAll();
    }

    @Test
    public void shouldNotRemoveAnyElement() {
        sc.add("00-000");
        sc.add("11-000");
        sc.add("22-000");
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(sc.remove("44-444")).isEqualTo(false);
        softAssertions.assertThat(sc.size()).isEqualTo(3);
        softAssertions.assertThat(sc.containString("00-000")).isEqualTo(true);
        softAssertions.assertThat(sc.containString("11-000")).isEqualTo(true);
        softAssertions.assertThat(sc.containString("22-000")).isEqualTo(true);

        softAssertions.assertAll();
    }

    @Test(expected = InvalidStringContainerPatternException.class)
    public void shouldThrowInvalidStringContainerPatternExceptionWhenGivenStringIsNotCompilable() {
        String incorrectPattern = "***";
        sc.stringToPattern(incorrectPattern);
    }

    @Test(expected = InvalidStringContainerPatternException.class)
    public void shouldThrowInvalidStringContainerPatternExceptionWhenInitializeStringContainerWithIncorrectPattern() {
        String incorrectPattern = "\\";
        sc = new StringContainer(incorrectPattern);
    }

    @Test
    public void shouldReturnTrueWhenGivenStringIsCompilableToRegex() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(sc.stringToPattern("\\d+")).isInstanceOf(Pattern.class);
        softAssertions.assertThat(sc.stringToPattern("^([a-z0-9]+[\\.\\_\\-]?[\\w]+)+@([a-z0-9]+\\.)+[a-z]{2,4}$"))
                .isInstanceOf(Pattern.class);
        softAssertions.assertThat(sc.stringToPattern("[A-Z][a-z]{2,}")).isInstanceOf(Pattern.class);
        softAssertions.assertAll();
    }

    @Test
    public void shouldInitializeStringContainerWhenGivenStringIsCompilableToRegex() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(sc = new StringContainer("\\d+")).isInstanceOf(StringContainer.class);
        softAssertions.assertThat(sc).isNotNull();
        softAssertions.assertThat(sc
                        = new StringContainer("^([a-z0-9]+[\\.\\_\\-]?[\\w]+)+@([a-z0-9]+\\.)+[a-z]{2,4}$"))
                .isInstanceOf(StringContainer.class);
        softAssertions.assertThat(sc).isNotNull();
        softAssertions.assertThat(sc = new StringContainer("[A-Z][a-z]{2,}"))
                .isInstanceOf(StringContainer.class);
        softAssertions.assertThat(sc).isNotNull();

        softAssertions.assertAll();
    }

    @Test
    public void shouldReturnTrueWhenStringMatchToPattern() {
        assertTrue(sc.isCorrectValueForPattern("55-555"));
    }

    @Test(expected = InvalidStringContainerValueException.class)
    public void shouldThrowInvalidStringContainerValueExceptionWhenStringNotMatchToPattern() {
        sc.add("00-0001");
    }
}