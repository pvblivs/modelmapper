# Mapping Process

The process for mapping a source to a destination object is as follows:

 * If a `TypeMap` exists for the source and destination types, mapping will occur according to the `Mapping`s defined in the `TypeMap`.
 * Else if a `Converter` exists that is capable of converting the source object to the destination type, mapping will occur using the `Converter`.
 * Else a new `TypeMap` is created for the source and destination types, and mapping will occur according to the implicit `Mapping`s captured in the `TypeMap`.