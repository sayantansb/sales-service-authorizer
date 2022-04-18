package api.sales.service.authorizers.util;

import api.sales.service.authorizers.App;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { App.class})
public class AuthUtilTest extends TestCase {

    @Autowired
    private AuthUtil authUtil;

    @Test
    public void testGetSub() {
        assertTrue(authUtil.getSub("sales","dev","SGVsbG8gV29ybGQK"));
    }
}