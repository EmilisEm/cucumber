package hellocucumber;

import io.cucumber.java.ParameterType;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CustomParameterTypes {
  @ParameterType("h5|div|span|button|a")
  public HtmlElement htmlElement(String text) {
    return Arrays.stream(HtmlElement.values())
        .filter(htmlElement -> htmlElement.getValue().equals(text))
        .findFirst()
        .orElseThrow(
            () ->
                new IllegalArgumentException(
                    "Unable to convert input '%s' to html element. Allowed elements: %s"
                        .formatted(
                            text,
                            Arrays.stream(HtmlElement.values())
                                .map(HtmlElement::getValue)
                                .collect(Collectors.joining(", ")))));
  }
}
