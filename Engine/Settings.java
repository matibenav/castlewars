package Engine;

public enum Settings {

  PLAYERS(2, 4, "Amount of players"), 
  STARTING_CARDS(5, 10, "Initial amount of cards per player"),
  CARDS(15, 40, "Amount of cards per type in deck");

  private int max;
  private int min;
  private String description;

  /**
   * @return maximum admitted value for selected setting
   */
  public int getMax() {
    return max;
  }

  /**
   * @return minumum admitted value for selected setting
   */
  public int getMin() {
    return min;
  }

  /**
   * @return setting description
   */
  public String getDescription() {
    return description;
  }

  private Settings(int min, int max, String description) {
    this.min = min;
    this.max = max;
    this.description = description;
  }

}
