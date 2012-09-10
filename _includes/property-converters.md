# Converters

Converters allow custom conversion to take place when mapping a source to a destination property. 

Consider this converter, which extends AbstractConverter and converts a String to an uppercase String:

```
Converter<String, String> toUppercase = new AbstractConverter<String, String>() {
  protected String convert(String source) {
    return source == null ? null : source.toUppercase();
  }
};
```

Using the `toUppercase` Converter to map from a source property to a destination property is simple:

```
using(toUppercase).map().setName(source.getName());
```

Alternatively, instead of using a converter to map a source property we can create a Converter intended to map from a source _object_ to a destination property:

```
Converter<Person, String> toUppercase = new AbstractConverter<Person, String>() {
  protected String convert(Person person) {
    return person == null ? null : person.getFirstName();
  }
};
```

When defining a mapping to use this converter, we simply pass the source _object_, which is of type `Person`, to the `map` method:

```
using(personToNameConverter).map(source).setName(null);
```

**Note**: Since a source object is given, the `null` value passed to `setName` is unused.