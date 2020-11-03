package Engine;

public class Turn {
  
  private String description;
  private Player owner;

  public Turn (Player owner, String description) {
    this.owner = owner;
    this.description = owner.getName() + " " + description;
  }

  public void appendDescription (String info) {
    description.concat("\n"+ owner.getName() + " " + info);
    }
  
}
