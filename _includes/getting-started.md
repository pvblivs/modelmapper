# Getting Started

This section will guide you through the setup and basic usage of ModelMapper.

### Setting Up

If you're a Maven user just add the `modelmapper` library as a dependency:

{:.prettyprint .lang-xml}
	<dependency>
	  <groupId>org.modelmapper</groupId>
	  <artifactId>modelmapper</artifactId>
	  <version>0.5.0</version>
	</dependency>

Otherwise you can [download](https://github.com/jhalterman/modelmapper/downloads) the latest ModelMapper jar and add it to your classpath.

### Mapping

To perform an object mapping, simply instantiate the `ModelMapper` class and call the `map` method, passing in a <i>source</i> object to map from along with the <i>destination</i> type you want to map to:

{:.prettyprint .lang-java}
    ModelMapper modelMapper = new ModelMapper();
    PersonDTO personDTO = modelMapper.map(person, PersonDTO.class);

You can also map a source to an existing destination object:

{:.prettyprint .lang-java}
    modelMapper.map(person, existingPersonDTO);

### How It Works

When the `map` method is called, the source and destination types are analyzed to determine which properties match each other based on current [configuration](/user-manual/configuration). Data is then mapped according to these matches. For more details on how matching and mapping works, check out the [related section](/user-manual/how-it-works/) in the user manual.

### Handling Mismatches

For properties that cannot be matched automatically:

 * The [Property Mapping](/user-manual/property-mapping) API can be used to define explicit mappings
 * [Configuration](/user-manual/configuration) can be adjusted to allow different properties to match each other based on naming, accessibility and other conventions

[Validation](/user-manual/validation) can be performed to ensure that all destination properties are mapped as expected.