public class Minimax {
  public static void main(String[] args) {
    Minimax test = new Minimax(3);
    System.out.println(test.root);
  }

  public Vertex root;
  public String message;

  public Minimax(int size) {
    this.message = "Tic Tac Toe";
    this.root = new Vertex(size);
    growTree(this.root, 1, 0);

  }

  private int growTree(Vertex root, int turn, int utility) {
   utility = root.terminal();
    if (utility != Vertex.CONT) {
      root.utility = (byte) utility;
      return utility;
    }
    int place = root.board.length;
    for (int i = 0; i < place; i++) {
      root.grow(i, turn);
     }

     for (Vertex child : root.children){
      child.utility = (byte) growTree(child, -turn, utility);
     }
    return root.minimax(turn);
  }

  public void play(int place) {
    // implement me
   //this.root.board[place] = 1; // Player move
   //Vertex match = null;
   boolean validMove = false;
    for (Vertex child : this.root.children) {
      if(child.board[place] == 1){
        this.root = child;
        validMove = true;
        break;
      }
    }
      if(validMove && this.root.children.size()>0){
        this.root = this.root.min();
      }
      this.message = evalMessage();
      }
 

  public int gameOverState() {
    if (this.root.children.size() > 0)
      return Vertex.CONT;
    return this.root.terminal();
  }

  private String evalMessage() {
    if (this.root.children.size() > 0)
      return this.message;
    else if (this.root.terminal() == 0)
      return "Draw! (right click to reset).";
    else
      return "Peasant! (right click to reset).";
  }
}