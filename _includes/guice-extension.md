# ModelMapper Guice Extension

The Guice extensions allows for the provisioning of destination objects to be delegated to a Guice Injector during the mapping process. To use the extension, first obtain a Provider for a Guice injector:

```
Provider<?> guiceProvider = GuiceIntegration.fromGuice(injector);
```

Then configure the Provider for to be used globally for a ModelMapper:

```
modelMapper.getConfiguration().setProvider(guiceProvider);
```

Or set the Provider to be used for a specific TypeMap:

```
typeMap.setProvider(guiceProvider);
``` 

The provider can also be used for individual mappings:

```
withProvider(guiceProvider).map().someSetter(source.someGetter());
```