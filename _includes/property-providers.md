# Providers

Providers allow allow you to provide your own instance of destination properties and types prior to mapping (see the general page on [Providers] for more info).

Consider this Provider which provides `Person` instances:

```
Provider<Person> personProvider = new AbstractProvider<Person>() {
  public Person get() {
    return new Person();
  }
}
```

Configuring `personProvider` to be used for a specific property mapping is simple:

```
withProvider(personProvider).map().setPerson(source.getPerson());
```

When mapping takes place, `personProvider` will be called to retrieve a Person instance, which will then be mapped to the destination object's `setPerson` method.

Providers can also be used with converters:

```
withProvider(personProvider)
    .using(personConverter)
    .map().setPerson(source.getPerson());
```