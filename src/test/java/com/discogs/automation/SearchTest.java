package com.discogs.automation;

import com.discogs.automation.pages.*;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

public class SearchTest extends BaseTest {

    protected HomePage homePage;
    protected Random rand = new Random();
    protected SearchResultPage searchResultPage;
    protected VinylCardPage vinylCardPage;
    protected Map<InputType, String> filtersApplied;

    @BeforeClass(description = "Initialization of Home page")
    public void initHomePage() {
        homePage = new HomePage(driver);
        homePage.acceptCookies();
    }

    @BeforeClass(description = "creation of prerequisites for tests")
    public void createPrerequisites() {
        filtersApplied = new HashMap<>();
        filtersApplied.put(InputType.STYLE, "disco");
        filtersApplied.put(InputType.YEAR, "1986");
    }

    @Test(description = "Verify that search result is correct")
    public void testSearchResultIsCorrect() {
        String textOfTitle = homePage.searchByText("jazz")
                .getVinylContainer().getVinylCardTitle(rand.nextInt(1, 50)).getText().toLowerCase();
        Assertions.assertThat(textOfTitle).contains("jazz")
                .withFailMessage("Search result doesn't match input search text");
        System.out.println(textOfTitle + " contains jazz");
    }

    @Test(description = "Verify that applied filters to advanced search are correct")
    public void testAdvancedSearchResultIsCorrect() {
        searchResultPage = homePage.advancedSearchByText().fillInSearchForm(filtersApplied);
        List<String> filtersNames = searchResultPage.getFiltersNames();

        vinylCardPage = searchResultPage.getVinylContainer().selectVinylCardByNumber(rand.nextInt(1, 50));

        SoftAssertions.assertSoftly(
                softly -> {
                    softly.assertThat(filtersNames).containsAll(filtersApplied.values())
                            .withFailMessage("Filters don't contain such filters names");
                    filtersApplied.keySet().stream().forEach(
                            x -> softly.assertThat(vinylCardPage.getTextOfInfoTable(x).equals(filtersApplied.get(x)))
                                    .withFailMessage("Info table '" + x.name().toLowerCase()
                                            + "' has value different from '" + filtersApplied.get(x) + "'"));
                }
        );
    }
}
