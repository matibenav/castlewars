package Engine;

public enum Settings {

	PLAYERS (2,4,"Players per game"),
	STARTING_CARDS (5, 10, "Initial amount of cards per Player"),
	CARDS (10, 20, "Amount of Cards per type in deck");
	
	private int max;
	private int min;
	private String description;
	
	
  public int getMax() {
    return max;
  }

  public int getMin() {
    return min;
  }

  public String getDescription() {
    return description;
  }

  private Settings(int min, int max, String description) {
    this.min = min;
    this.max = max;
		this.description = description;
	}

}
