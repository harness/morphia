package dev.morphia.mapping;


import dev.morphia.annotations.Entity;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import dev.morphia.TestBase;
import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Id;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


/**
 * @author Uwe Schaefer, (us@thomas-daily.de)
 */
public class LocaleMappingTest extends TestBase {

    @Test
    public void testLocaleMapping() {
        E e = new E();
        e.l1 = Locale.CANADA_FRENCH;
        e.l2 = Arrays.asList(Locale.GERMANY, Locale.TRADITIONAL_CHINESE);
        e.l3 = new Locale[]{Locale.TRADITIONAL_CHINESE, Locale.FRENCH};

        getDs().save(e);
        e = getDs().get(e);

        Assert.assertEquals(Locale.CANADA_FRENCH, e.l1);

        Assert.assertEquals(2, e.l2.size());
        Assert.assertEquals(Locale.GERMANY, e.l2.get(0));
        Assert.assertEquals(Locale.TRADITIONAL_CHINESE, e.l2.get(1));

        Assert.assertEquals(2, e.l3.length);
        Assert.assertEquals(Locale.TRADITIONAL_CHINESE, e.l3[0]);
        Assert.assertEquals(Locale.FRENCH, e.l3[1]);

    }

    @Entity
    public static class E {
        @Id
        private ObjectId id;
        private Locale l1;

        private List<Locale> l2 = new ArrayList<Locale>();

        private Locale[] l3;
    }
}
