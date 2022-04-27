## This is the example usage
- To initialize the Gucci Mocks library we can annotate any JUnit5
test class with `@ExtendWith(MockitoExtension.class)`. Doing so will
activate the `@GucciInject` annotation. Before any test method
the `@GucciInject` annotation will attempt to initialize any field 
annotated with `@GucciInject`.


```Java
@ExtendWith(GucciExtension.class)
public class ExampleTest {
    @GucciInject
    private ClassToBeInjected injectedClass;
    
    @Test
    public void shouldDoSomething() {
        injectedClass.add(100);
    
    }
}
  
        
        
```
