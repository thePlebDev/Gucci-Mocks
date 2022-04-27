package com.Elliott.Engineering.app;

import com.Elliott.Engineering.app.Annotations.GucciInject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(GucciExtension.class)
public class ExtensionTest {

    @GucciInject
    ClassToBeInjected underTest;


    @Test
    public void testingMore(){
        //GIVEN
        int EXPECTED_VALUE = 1;

        //WHEN
        int returnedValue = underTest.returnInt();

        //THEN
        assertThat(returnedValue).isEqualTo(EXPECTED_VALUE);
    }
}
