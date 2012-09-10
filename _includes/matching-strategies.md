# Matching Strategies

Matching strategies are used during the [MatchingProcess matching process] to match source and destination properties to each other. Below is a description of each strategy.

### Standard

The Standard matching strategy allows for source properties to be intelligently matched to destination properties, requiring that _all_ destination properties be matched and all source property names have at least one token matched. The following rules apply: 

  * Tokens can be matched in _any_ order 
  * _All_ destination property name tokens must be matched 
  * _All_ source property names must have at least _one_ token matched

The standard matching strategy is configured by default, and while it is not exact, it is ideal to use in most scenarios.

### Loose

The Loose matching strategy allows for source properties to be loosely matched to destination properties by requiring that _only_ the last destination property in a hierarchy be matched. The following rules apply: 

  * Tokens can be matched in _any_ order 
  * The last destination property name must have _all_ tokens matched 
  * The last source property name must have at least _one_ token matched

The loose matching strategy is ideal to use for source and destination object models with property hierarchies that are very dissimilar. It may result in a higher level of ambiguous matches being detected, but for well-known object models it can be a quick alternative to defining [PropertyMapping mappings].

### Strict

The strict matching strategy allows for source properties to be strictly matched to destination properties. This strategy allows for complete matching accuracy, ensuring that no mismatches or ambiguity occurs. But it requires that property name tokens on the source and destination side match each other precisely. The following rules apply: 

  * Tokens are matched in _strict_ order 
  * _All_ destination property name tokens must be matched 
  * _All_ source property names must have _all_ tokens matched 

The strict matching strategy is ideal to use when you want to ensure that no ambiguity or unexpected mapping occurs without having to inspect a `TypeMap`. The drawback is that the strictness may result in some destination properties remaining unmatched.