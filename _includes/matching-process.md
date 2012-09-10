# Matching Process

The matching process uses conventions configured in a `ModelMapper` or `TypeMap` to determine which source and destination properties match each other. The process works by identifying eligible properties, transforming and tokenizing their names, then using those tokens to determine source to destination matches.

### Eligibility

Property eligibility is determined in two steps. First, if field matching is enabled then fields that are accessible according to the configured **AccessLevel** and that are named according to the configured **NamingConvention** are eligible. Similarly, methods are eligible based on configured AccessLevels and NamingConventions. Eligible methods take precedence over fields with the same _transformed_ property name.

Additionally, only source methods with zero parameters and a non-`void` return type are eligible, and destination methods with one parameter and a `void` return type are eligible.

### Transformation

Prior to matching, **NameTransformers** are used to transform property names to their simple _property_ name, so that source and destination properties that are named according to different conventions can be matched. For example: Consider a source object with a `getPerson` method and a destination object with a `setPerson` method. In order for these to be matched, a `NameTransformer` is used to transform the method names to `person`.

Typically `NameTransformer` implementations will transform names according to the same convention as a `NamingConvention`, and are therefore usually configured together - such as with JavaBeans.

### Tokenization

After transformation, **NameTokenizers** are used to tokenize class and property names which are then used by the `MatchingStrategy` to determine matches.

### Matching

To determine whether a source and destination property hierarchy match, a **MatchingStrategy** is used, which analyzes the property and class name tokens from the source and destination sides to determine matches.

### Handling Ambiguity

For matching strategies that are inexact, it is possible that multiple source properties may match the same destination property. When this occurs, the matching engine attempts to resolve the ambiguity by finding the closest match among the duplicates. 

If the ambiguity cannot be resolved, a ConfigurationException is thrown, except when the `MatchingConfiguration` is set to `ignoreAmbiguity` in which case the destination property will simply be skipped during the mapping process.