# Skipping Properties

While ModelMapper implicitly creates mappings from a source type to each property in the destination type, it may occasionally be desirable to skip the mapping of certain destination properties. 

This example specifies that the destination type's `setName` method should be skipped during the mapping process:

```
skip().setName(null);
```

**Note**: Since the `setName` method is skipped the `null` value is unused.