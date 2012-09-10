# Getting Started

This section will guide you through the setup and basic usage of ModelMapper.

## Setting up Maven

Add the `modelmapper` library as a dependency:

{:.prettyprint .lang-xml}
	<dependency>
	  <groupId>org.modelmapper</groupId>
	  <artifactId>modelmapper</artifactId>
	  <version>0.4.0</version>
	</dependency>	

## Mapping

To perform an object mapping, simply instantiate the `ModelMapper` class and call the `map` method, passing in a <i>source</i> object to map from along with the <i>destination</i> type you want to map to:

{:.prettyprint .lang-java}
    ModelMapper modelMapper = new ModelMapper();
    PersonDTO personDTO = modelMapper.map(person, PersonDTO.class);

You can also map a source to an existing destination object:

{:.prettyprint .lang-java}
    modelMapper.map(person, existingPersonDTO);

## How it Works

When the `map` method is called, the source and destination types are analyzed to determine which properties match each other based on current [Configuration configuration]. Data is then mapped according to these matches. 

## Handling Mismatched Models

For properties that cannot be matched automatically:

 * The [PropertyMapping Property Mapping] API can be used to define explicit mappings
 * [Configuration] can be adjusted to allow different properties to match each other based on naming, accessibility and other conventions

[Validation] can be performed to ensure that all destination properties are mapped as expected.