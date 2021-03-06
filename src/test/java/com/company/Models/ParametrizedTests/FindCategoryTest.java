package com.company.Models.parametrizedTests;

import com.company.Models.Category;
import com.company.SearchAlgorithms.DataExtractor;
import com.company.SearchAlgorithms.Extractor;
import com.company.utils.Utils;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FindCategoryTest {

    private final Category outPutCategory;
    private final String input;
    private Extractor extractor;
    private DataExtractor dataExtractor;
    private Utils utils;

    public FindCategoryTest(Category outPutCategory, String input) {
        this.outPutCategory = outPutCategory;
        this.input = input;
        utils= new Utils();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        Object[][] data = new Object[][]{{Category.BOOKS, "http://localhost:8888/sample_site_to_crawl/details.php?id=102"},
                {Category.MOVIE, "http://localhost:8888/sample_site_to_crawl/details.php?id=202"},
                {Category.MUSIC, "http://localhost:8888/sample_site_to_crawl/details.php?id=301"}};
        return Arrays.asList(data);
    }

    @Before
    public void setup() throws MalformedURLException {
        dataExtractor = new DataExtractor();
        extractor = new Extractor("http://localhost:8888",dataExtractor,utils);
    }

    @Test
    public void shouldPassisFindingCategory() throws ClassNotFoundException {
      //  String actual = new Gson().toJson(extractor.findCategory(input));
      //  String expected = new Gson().toJson(outPutCategory);
      //  //Assert
      //  Assert.assertEquals(expected, actual);
    }
    @Test
    public void shouldFailisFindingCategory() throws ClassNotFoundException {
       /// String actual = new Gson().toJson(extractor.findCategory(input));
       /// //Assert
       /// Assert.assertNotEquals(null, actual);
    }


}
