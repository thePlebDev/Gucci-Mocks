## Documentation
The Gucci Mocks library currently only enables initialization

### [GucciExtension](https://github.com/thePlebDev/Gucci-Mocks/blob/master/src/main/java/com/Elliott/Engineering/app/GucciExtension.java)
- Junit5 extension used in combination with [@GucciInject](https://github.com/thePlebDev/Gucci-Mocks/blob/master/src/main/java/com/Elliott/Engineering/app/Annotations/GucciInject.java).
  Implementing the TestInstancePostProcessor interface allows this class to be called during the test instance postprocessing execution point.
- Once the test instance postprocessing execution point is reached, this class does three main things:

`1) Find the class loader :`
```Java
ClassLoader classLoader = o.getClass().getClassLoader();
        if(classLoader == null){
            classLoader = ClassLoader.getSystemClassLoader();
        }

```
- o represents the current instance of the test class. It is important that we
get this class loader inorder to work with classes in the same namespace.


`2) Get all fields in the class :`
```java
Field[] annotatedField = o.getClass().getDeclaredFields();
```
- Utilizing the Java reflections API we are able to access the method area
through the Java class object and access all the fields.

`3) Initialize all annotated fields :`

```java
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
```
- This code will loop through all the fields and if they are annotated with
[@GucciInject](https://github.com/thePlebDev/Gucci-Mocks/blob/master/src/main/java/com/Elliott/Engineering/app/Annotations/GucciInject.java)
try to initialize them.

### [@GucciInject](https://github.com/thePlebDev/Gucci-Mocks/blob/master/src/main/java/com/Elliott/Engineering/app/Annotations/GucciInject.java)
- A runtime annotation that must be used inside a Junit5 test class that has been
extended with the [GucciExtension](https://github.com/thePlebDev/Gucci-Mocks/blob/master/src/main/java/com/Elliott/Engineering/app/GucciExtension.java)
class.
- Only allowed on fields which signifies them to be instantiated. 
- A simple but useful annotation
```java
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GucciInject {
}
```
