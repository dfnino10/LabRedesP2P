package common;

import java.util.NoSuchElementException;

public final class Optional<T> {

  private static final Optional<?> EMPTY = new Optional();

  private final T value;

  public Optional(T value) {
    this.value = value;
  }

  private Optional() {
    this.value = null;
  }

  @SuppressWarnings("unchecked")
  public static <T> Optional<T> of(T value) {
    return value == null ? (Optional<T>) EMPTY : new Optional<T>(value);
  }

  public T get() throws NoSuchElementException {
    if (value == null) {
      throw new NoSuchElementException("No value present");
    }
    return value;
  }

  public boolean isPresent() {
    return value != null;
  }


  public T orElse(T defaultValue) {
    return value != null ? value : defaultValue;
  }

}