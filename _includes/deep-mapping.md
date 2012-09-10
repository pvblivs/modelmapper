# Deep Mapping

This example maps the destination type's `setAge` method to the source type's `getCustomer().getAge()` method hierarchy, allowing deep mapping to occur between the source and destination methods: 

```
map().setAge(source.getCustomer().getAge());
```

This example maps the destination type's `getCustomer().setName()` method hierarchy to the source type's `getPerson().getFirstName()` method hierarchy: 

```
map().getCustomer().setName(source.getPerson().getFirstName());
```

**Note**: In order populate the destination object, deep mapping requires the `getCustomer` method to have a corresponding mutator, such as a `setCustomer` method or an accessible `customer` field.

Deep mapping can also be performed for source properties or values whose types do not match the destination property's type:

```
map(source.getPerson.getAge()).setAgeString(null);
```

**Note**: Since the `setAgeString` method requires a value we simply pass in `null` which is unused.