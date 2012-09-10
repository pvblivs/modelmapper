# Configuration

ModelMapper uses a set of conventions and configuration to determine which source and destination properties match each other. Available configuration, along with default values, is described below:

Setting|Description|Default Value
-------|-----------|-------------
Access level|Determines which methods and fields are eligible for matching based on accessibility|public
Field matching|Indicates whether fields are eligible for matching|disabled
Naming convention|Determines which methods and fields are eligible for matching based on name|JavaBeans
Name transformer|Transforms eligible property and class names prior to matching|JavaBeans
Name tokenizer|Tokenizes source and destination property names prior to matching|Camel Case
[MatchingStrategies Matching strategy]|Determines how source and destination properties are matched|Standard

You can read about how this configuration is used during the [MatchingProcess matching process].

### Default Configuration

Default configuration uses the *Standard* matching strategy to match only *public* source and destination *methods* that are named according to the *JavaBeans* convention. 

### Configuration Examples

Adjusting configuration for certain matching requirements is simple. This example configures a `ModelMapper` to allow `protected` methods to be matched:

```
modelMapper.getConfiguration()
  .setMethodAccessLevel(AccessLevel.PROTECTED);
```

This example configures a `ModelMapper` to allow private fields to be matched:

```
modelMapper.getConfiguration()
  .enableFieldMatching(true)
  .setFieldAccessLevel(AccessLevel.PRIVATE);
```

This example configures a `ModelMapper` to use the `Loose` [MatchingStrategies matching strategy]:

```
modelMapper.getConfiguration()
  .setMatchingStrategy(MatchingStrategies.LOOSE);
```

This example configures a `ModelMapper` to allow any source and destination property names to be eligible for matching:

```
modelMapper.getConfiguration()
  .setSourceNamingConvention(NamingConventions.NONE);
  .setDestinationNamingConvention(NamingConventions.NONE);
```

This example configures a `ModelMapper` to use a `Pascal Case` name tokenizer for source and destination properties:

```
modelMapper.getConfiguration();
  .setSourceNameTokenizer(pascalCaseTokenizer);
  .setDestinationNameTokenizer(pascalCaseTokenizer);
```

### Available Conventions

ModelMapper includes several pre-defined conventions for handling different property matching requirements:

Convention|Description
----------|-----------
`NamingConventions.NONE`| Represents no naming convention, which applies to all property names
`NamingConventions.JAVABEANS_ACCESSOR`|Finds eligible accessors according to JavaBeans convention
`NamingConventions.JAVABEANS_MUTATOR`|Finds eligible mutators according to JavaBeans convention
`NameTransformers.JAVABEANS_ACCESSOR`|Transforms accessor names according to JavaBeans convention
`NameTransformers.JAVABEANS_MUTATOR`|Transforms mutators names according to JavaBeans convention
`NameTokenizers.CAMEL_CASE`|Tokenizes property and class names according to Camel Case convention
`MatchingStrategies.STANDARD`|Intelligently matches source and destination properties
`MatchingStrategies.LOOSE`|Loosely matches source and destination properties
`MatchingStrategies.STRICT`|Strictly matches source and destination properties

The [MatchingStrategies Matching Strategies] page provides more details about how each of the matching strategies work.