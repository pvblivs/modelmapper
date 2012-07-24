package org.modelmapper.internal;

import static java.util.Arrays.asList;
import static org.modelmapper.internal.PropertiesMatcher.MatchStrength.EXACT;
import static org.modelmapper.internal.PropertiesMatcher.MatchStrength.IGNORING_CASE;
import static org.modelmapper.internal.PropertiesMatcher.MatchStrength.IGNORING_CASE_AND_TOKEN_POSITION;
import static org.modelmapper.internal.PropertiesMatcher.MatchStrength.IGNORING_TOKEN_POSITION;
import static org.modelmapper.internal.PropertiesMatcher.MatchStrength.NONE;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.config.Configuration;
import org.modelmapper.spi.NameTokenizer;
import org.modelmapper.spi.NameableType;
import org.modelmapper.spi.PropertyInfo;

class PropertiesMatcher {
  public enum MatchStrength {
    EXACT(0), IGNORING_CASE(1), IGNORING_TOKEN_POSITION(2), IGNORING_CASE_AND_TOKEN_POSITION(3), NONE(
        100);

    private int priority;

    private MatchStrength(final int priority) {
      this.priority = priority;
    }

    public int getPriority() {
      return priority;
    }
  }

  private final Configuration configuration;

  PropertiesMatcher(final Configuration configuration) {
    this.configuration = configuration;
  }

  List<MatchStrength> match(final List<? extends PropertyInfo> sourceProperties,
      final List<? extends PropertyInfo> destProperties) {

    List<String> sourceTokens = getAllTokens(sourceProperties,
        configuration.getSourceNameTokenizer());
    List<String> destinationTokens = getAllTokens(destProperties,
        configuration.getDestinationNameTokenizer());

    return matchTokens(sourceTokens, destinationTokens);
  }

  private List<String> getAllTokens(final List<? extends PropertyInfo> properties,
      final NameTokenizer nameTokenizer) {
    List<String> toReturn = new ArrayList<String>();

    for (PropertyInfo property : properties) {
      NameableType nameableType = NameableType.forPropertyType(property.getPropertyType());
      List<String> tokens = asList(nameTokenizer.tokenize(property.getName(), nameableType));
      toReturn.addAll(tokens);
    }

    return toReturn;
  }

  private static List<MatchStrength> matchTokens(List<String> sourceTokens, List<String> destTokens) {
    List<MatchStrength> strengths = new ArrayList<MatchStrength>(sourceTokens.size());

    for (int index = 0; index < sourceTokens.size(); index++) {
      String sourceToken = sourceTokens.get(index);

      // Handle positional matches
      if (destTokens.size() > index) {
        String destToken = destTokens.get(index);

        if (destToken != null) {
          MatchStrength positionalMatch = null;
          if (destToken.equals(sourceToken))
            positionalMatch = EXACT;
          else if (destToken.equalsIgnoreCase(sourceToken))
            positionalMatch = IGNORING_CASE;

          if (positionalMatch != null) {
            strengths.add(positionalMatch);
            destTokens.set(index, null);
            continue;
          }
        }
      }

      // Handle non-positional matches
      strengths.add(matchToken(sourceToken, destTokens));
    }

    for (String destToken : destTokens)
      if (destToken != null)
        strengths.add(NONE);

    return strengths;
  }

  private static MatchStrength matchToken(final String sourceToken, List<String> destTokens) {
    for (int i = 0; i < destTokens.size(); i++) {
      String destToken = destTokens.get(i);
      if (destToken != null) {
        if (sourceToken.equals(destToken)) {
          destTokens.set(i, null);
          return IGNORING_TOKEN_POSITION;
        } else if (sourceToken.equalsIgnoreCase(destToken)) {
          destTokens.set(i, null);
          return IGNORING_CASE_AND_TOKEN_POSITION;
        }
      }
    }

    return NONE;
  }
}
