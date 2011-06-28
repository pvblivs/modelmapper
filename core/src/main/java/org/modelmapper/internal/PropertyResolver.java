/**
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.modelmapper.internal;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.modelmapper.config.Configuration;
import org.modelmapper.spi.PropertyInfo;

/**
 * Resolves properties.
 * 
 * @param <M> property type
 * @param <PI> property info type
 * 
 * @author Jonathan Halterman
 */
interface PropertyResolver<M extends Member, PI extends PropertyInfo> {
  PropertyResolver<Field, Mutator> FIELDS = new DefaultPropertyResolver<Field, Mutator>() {
    @Override
    public Mutator propertyInfoFor(Class<?> initialType, Field field, Configuration configuration,
        String name) {
      return PropertyInfoRegistry.fieldPropertyFor(initialType, field, configuration, name);
    }

    @Override
    public Field[] membersFor(Class<?> type) {
      return type.getDeclaredFields();
    }
  };

  PropertyResolver<Method, Accessor> ACCESSORS = new DefaultPropertyResolver<Method, Accessor>() {
    @Override
    public boolean isValid(Method method) {
      return super.isValid(method) && method.getParameterTypes().length == 0
          && !method.getReturnType().equals(void.class);
    }

    @Override
    public Accessor propertyInfoFor(Class<?> initialType, Method method,
        Configuration configuration, String name) {
      return PropertyInfoRegistry.accessorFor(initialType, method, configuration, name);
    }

    @Override
    public Method[] membersFor(Class<?> type) {
      return type.getDeclaredMethods();
    }
  };

  PropertyResolver<Method, Mutator> MUTATORS = new DefaultPropertyResolver<Method, Mutator>() {
    @Override
    public boolean isValid(Method method) {
      return super.isValid(method) && method.getParameterTypes().length == 1
          && method.getReturnType().equals(void.class);
    }

    @Override
    public Mutator propertyInfoFor(Class<?> initialType, Method method,
        Configuration configuration, String name) {
      return PropertyInfoRegistry.mutatorFor(initialType, method, configuration, name);
    }

    @Override
    public Method[] membersFor(Class<?> type) {
      return type.getDeclaredMethods();
    }
  };

  static abstract class DefaultPropertyResolver<M extends Member, PI extends PropertyInfo>
      implements PropertyResolver<M, PI> {
    @Override
    public boolean isValid(M member) {
      return !Modifier.isStatic(member.getModifiers());
    }
  }

  boolean isValid(M member);

  PI propertyInfoFor(Class<?> initialType, M member, Configuration configuration, String name);

  M[] membersFor(Class<?> type);
}