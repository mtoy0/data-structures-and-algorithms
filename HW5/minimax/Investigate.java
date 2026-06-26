public class Investigate {
  public static void main(String[] args) {
    Minimax model = new Minimax(3);
    System.out.println(model.root);
    System.out.println();
    
    // Count the different terminal states
    int draws = countLeaves(model.root, 0);
    int maxWins = countLeaves(model.root, 1);
    int minWins = countLeaves(model.root, -1);
    
    System.out.println("Game Tree Analysis:");
    System.out.println("1) Leaves that result in a draw: " + draws);
    System.out.println("2) Leaves where max player (X) wins: " + maxWins);
    System.out.println("3) Leaves where min player (O) wins: " + minWins);
    System.out.println();
    System.out.println("Total leaves: " + (draws + maxWins + minWins));
  }
  
  public static int countLeaves(Vertex root, int targetUtility) {
    if (root.children.size() == 0) {
      return root.utility == targetUtility ? 1 : 0;
    }
    int count = 0;
    for (Vertex child : root.children) {
      count += countLeaves(child, targetUtility);
    }
    return count;
  }
}
