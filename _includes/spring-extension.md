# ModelMapper Spring Extension

The Spring extensions allows for the provisioning of destination objects to be delegated to a Spring BeanFactory during the mapping process. To use the extension, first obtain a Provider for a Spring BeanFactory:

```
Provider<?> springProvider = SpringIntegration.fromSpring(beanFactory);
```

Then configure the Provider for to be used globally for a ModelMapper:

```
modelMapper.getConfiguration().setProvider(springProvider);
```

Or set the Provider to be used for a specific TypeMap:

```
typeMap.setProvider(springProvider);
``` 

The provider can also be used for individual mappings:

```
withProvider(springProvider).map().someSetter(source.someGetter());
```