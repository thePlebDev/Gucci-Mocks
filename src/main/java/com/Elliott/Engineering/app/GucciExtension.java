/*
 * Copyright (c) 2007 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package com.Elliott.Engineering.app;

import com.Elliott.Engineering.app.Annotations.GucciInject;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
/**
 *Junit5 extension used in combination with {@link GucciInject}.
 *   Implementing the TestInstancePostProcessor interface allows this class to be called during the
 * test instance postprocessing execution point. Usage Example:
 * <pre>
 *    {@code
 *
 *  @ExtendWith(GucciExtension.class)
 *    public class ExampleTest {
 *
 *        @GucciInject
 *        private ClassToBeInjected injectedClass;
 *
 *        @Test
 *        public void shouldDoSomething() {
 *            injectedClass.add(100);
 *        }
 *    }
 *    }
 *   </pre>
 * **/
public class GucciExtension implements TestInstancePostProcessor {
    @Override
    public void postProcessTestInstance(Object o, ExtensionContext extensionContext) throws Exception {

        ClassLoader classLoader = o.getClass().getClassLoader();
        if(classLoader == null){
            classLoader = ClassLoader.getSystemClassLoader();
        }

        Field[] annotatedField = o.getClass().getDeclaredFields();
        for (Field field : annotatedField) {
            if (field.isAnnotationPresent(GucciInject.class)) {
                field.setAccessible(true);
                try{
                    String binaryName = field.getGenericType().getTypeName();
                    field.set(o,dynamicInjection(classLoader,binaryName) );
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }

            };
        }




    }

    public Object dynamicInjection(ClassLoader classLoader, String binaryName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        System.out.println(binaryName);
        return classLoader.loadClass(binaryName).getDeclaredConstructor().newInstance();
    }
}
