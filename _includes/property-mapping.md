# Property Mapping

For most object models, ModelMapper does a good job of intelligently mapping source and destination properties. But for certain models where property and class names are very dissimilar, a PropertyMap can be created to define **explicit** mappings between source and destination properties.

### Creating a PropertyMap

To start, extend PropertyMap, supplying type arguments to represent the source type `<S>` and destination type `<D>`, then override the `configure` method:

{:.prettyprint .lang-java}
	public class PersonMap extends PropertyMap<Person, PersonDTO>() {
	  @Override
	  protected void configure() {
	    map().setName(source.getFirstName());
	  }
	};

### Defining Mappings

A PropertyMap allows you to define source to destination property and value mappings using actual code. These definitions are placed in the PropertyMap's `configure` method where they are captured and evaluated later.

This example maps the destination type's `setName` method to the source type's `getFirstName` method:

{:.prettyprint .lang-java}
	map().setName(source.getFirstName());

This example maps the destination type's `setEmployer` method to the constant value `"Initech"`:

{:.prettyprint .lang-java}
	map().setEmployer("Initech");

Map statements can also be written to map properties whose types do not match:

{:.prettyprint .lang-java}
	map(source.getAge()).setAgeString(null);

Similar for constant values:

{:.prettyprint .lang-java}
	map(21).setAgeString(null);

**Note**: Since the `setAgeString` method requires a value we simply pass in `null` which is unused.

### Using a PropertyMap

Once a PropertyMap is defined, it is used to add mappings to a ModelMapper:

{:.prettyprint .lang-java}
	modelMapper.addMappings(new PersonMap());

Multiple PropertyMaps may be added for the same source and destination types, so long as only one mapping is defined for each destination property. 

**Explicit** mappings defined in a PropertyMap will override any **implicit** mappings for the same destination properties.

# More

Read on to learn about [DeepPropertyMapping deep mapping], [SkippingProperties skipping properties], [PropertyConverters custom converters], [PropertyProviders providers] and [ConditionalMapping conditional mapping].