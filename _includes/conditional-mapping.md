# Conditional Mapping


Mapping for a destination property can be made conditional by supplying a `Condition` along with the mapping.

Consider this condition, which applies if the source type is not `null`:

```
Condition notNull = new Condition() {
  public boolean applies(MappingContext<?, ?> context) {
    return context.getSource() != null;
  }
};
```

We can use the `notNull` Condition to specify that mapping only take place for a property if the source is not null:

```
when(notNull).map().setName(source.getName());
```

In this example, mapping to `setName` will be _skipped_ if the source is not null, else mapping will proceed from the source object's `getName` method:

```
when(notNull).skip().setName(source.getName());
```

Conditions can also be used with Providers and Converters:

```
when(someCondition)
    .withProvider(personProvider)
    .using(personConverter)
    .map().setPerson(source.getPerson());
```

### Combining Conditions

Conditions can be combined using boolean operators with the help of the `Conditions` class:

```
when(Conditions.or(isNull, isEmpty)).skip().setName(source.getName());

when(Conditions.and(txIsActive, inScope)).map().setRequest(source.getRequest());
```

Alternatively, `Condition` implementations can extend `AbstractCondition` which has built in support for combining conditions:

```
Condition isNull = new AbstractCondition() {
  public boolean applies(MappingContext<?, ?> context) {
    return context.getSource() == null;
  }
};

when(isNull.or(isEmpty)).skip().setName(source.getName());
```