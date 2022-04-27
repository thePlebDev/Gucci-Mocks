## Example Usage

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
