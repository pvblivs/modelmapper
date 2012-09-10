# API Overview 

The ModelMapper API consists of a few principal types:

  * *ModelMapper*
    * The class you instantiate to perform object mapping, configure matching, load PropertyMaps and register Mappers
    * Contains Configuration and TypeMaps

  * *PropertyMap*
    * The class you extend to define mappings between source and destination properties for a specific pair of types

  * *TypeMap*
    * The interface you use to perform configuration, introspection and mapping for a specific pair of types
    * Contains property mappings that are added from a PropertyMap
    * Created by a ModelMapper

  * *Converter*
    * The interface you implement to perform custom conversion between two types or property hierarchies
    * Added to a ModelMapper, set against a TypeMap, or used in a [PropertyMapping mapping].
    
  * *Provider*
    * The interface you implement to provide instances of destination types.
    * Set against a `TypeMap` or used in a [PropertyMapping mapping].

  * *Condition*
    * The interface you implement to conditionally create a mapping.
    * Used in a [PropertyMapping mapping].

Also see the [PropertyMapping Property Mapping] section of the User's Guide for an overview of the Mapping API.