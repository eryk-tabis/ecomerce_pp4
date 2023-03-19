package org.example;

import org.junit.jupiter.api.Test;

public class FirsTest {

    @Test
    void myFirstTest(){
        assert  true == true;
    }
    @Test
    void mySecondTest(){
        String name = "jakub";
        String hello = String.format("Hello %s", name);
        assert hello.equals("Hello jakub");
    }

}
