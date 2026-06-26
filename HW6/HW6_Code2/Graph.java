import java.nio.file.*;

public class Graph {
    
    // Main method
    public static void main(String[] args) throws Exception {
        int[][] w = weights("dijkstra1.txt");
        Vertex[] G = new Vertex[w.length];
        dijkstra(G, w, 0);
        
        // Print results
       System.out.println("Dijkstra results:");
        for (int i = 0; i < G.length; i++) {
            System.out.printf("%c: %.0f via %c%n", G[i].label(), G[i].d, G[i].pi);
        }
        BellmanFordprint ("bellmanford1.txt");
    }
public static void BellmanFordprint(String path) throws Exception  {
        System.out.println("Bellman-Ford:");
        int[][] w2 = weights(path);
        Vertex[] G2 = new Vertex[w2.length];
        boolean ok = bellmanFord(G2, w2, 0);
        if (!ok) {
            System.out.println("Negative-weight cycle detected");
        }
        for (Vertex v : G2) {
           System.out.printf("%c: %.0f via %c%n", v.label(), v.d, v.pi);
        }
    }
    // Dijkstra's algorithm implementation
    public static void dijkstra(Vertex[] G, int[][] w, int si) {
        initalizeSingleSource(G, si);
        Vertex u;
        
        while ((u = dequeue(G)) != null) {
            for (int j = 0; j < w[u.i].length; j++) {
                if (w[u.i][j] > 0) {  // Only relax if edge exists
                    relax(u, G[j], w);
                }
            }
        }
        
        return;
    }

    // Initialize all vertices with default values
    public static void initalizeSingleSource(Vertex[] G, int si) {
        for (int i = 0; i < G.length; i++) {
            G[i] = new Vertex(i);
        }
        G[si].d = 0;
    }

    // Relax edge from u to v
    public static void relax(Vertex u, Vertex v, int[][] w) {
        if (v.d > u.d + w[u.i][v.i]) {
            v.d = u.d + w[u.i][v.i];
            v.pi = u.label();
        }
    }

    // Dequeue vertex with minimum distance that hasn't been visited
    public static Vertex dequeue(Vertex[] G) {
        Vertex ret = null;
        
        for (Vertex v : G) {
            if (v.visited) {
                continue;
            } else if (ret == null || ret.d > v.d) {
                ret = v;
            }
        }
        
        if (ret != null) {
            ret.visited = true;
        }
        
        return ret;
    }

    // Read weights from file and parse into 2D array
    public static int[][] weights(String path) throws Exception {
        String contents = new String(Files.readAllBytes(Paths.get(path)));
        String[] rows = contents.trim().split("\n");
        int[][] ret = new int[rows.length][rows[0].split(",").length];
        String[] row = null;
        
        for (int i = 0; i < rows.length; i++) {
            row = rows[i].split(",");
            for (int j = 0; j < ret[i].length; j++) {
                ret[i][j] = Integer.parseInt(row[j].trim());
            }
        }
        
        return ret;
    }

    // Inner Vertex class
    static class Vertex implements Comparable<Vertex> {
        public boolean visited = false;
        public int i = -1;
        public double d = Double.POSITIVE_INFINITY;
        public char pi = 0;

        public Vertex(int i) {
            this.i = i;
            this.pi = this.label();
        }

        public int compareTo(Vertex other) {
            return Double.compare(this.d, other.d);
        }

        public char label() {
            return (char) (this.i + 'a');
        }
    }
    public static boolean bellmanFord(Vertex[] G, int[][] w, int si) {
        initalizeSingleSource(G, si);
        int V = G.length;

        // Relax all edges |V| - 1 times
        for (int k = 0; k < V - 1; k++) {
            for (int u = 0; u < V; u++) {
                for (int v = 0; v < V; v++) {
                    if (w[u][v] != 0) {   // there is an edge u -> v
                        relax(G[u], G[v], w);
                    }
                }
            }
        }

        // Check for negative-weight cycles
        for (int u = 0; u < V; u++) {
            for (int v = 0; v < V; v++) {
                if (w[u][v] != 0 && G[v].d > G[u].d + w[u][v]) {
                    return false; // negative cycle found
                }
            }
        }
        return true;
    }



}



/*public class Graph {
    
    // Main method
    public static void main(String[] args) {
        int[][] w = weights("dijkstra1.txt");
        Vertex[] G = new Vertex[w.length];
        dijkstra(G, w, 0);
        
        for (int i = 0; i < w.length; i++) {
            for (int j = 0; j < w[i].length; j++) {
                System.out.printf("%3d", w[i][j]);
            }
            System.out.println();
        }
    }

    // Dijkstra's algorithm implementation
    public static void dijkstra(Vertex[] G, int[][] w, int si) {
        initalizeSingleSource(G, si);
        Vertex u;
        
        while ((u = dequeue(G)) != null) {
            for (int j = 0; j < w[u.i].length; j++) {
                relax(u, G[j], w);
            }
        }
        
        return;
    }

    // Initialize all vertices with default values
    public static void initalizeSingleSource(Vertex[] G, int si) {
        for (int i = 0; i < G.length; i++) {
            G[i] = new Vertex(i);
        }
        G[si].d = 0;
    }

    // Relax edge from u to v
    public static void relax(Vertex u, Vertex v, int[][] w) {
        if (v.d > u.d + w[u.i][v.i]) {
            v.d = u.d + w[u.i][v.i];
            v.pi = u.label();
        }
    }

    // Dequeue vertex with minimum distance that hasn't been visited
    public static Vertex dequeue(Vertex[] G) {
        Vertex ret = null;
        
        for (Vertex v : G) {
            if (v.visited) {
                continue;
            } else if (ret == null || ret.d > v.d) {
                ret = v;
            }
        }
        
        if (ret != null) {
            ret.visited = true;
        }
        
        return ret;
    }

    // Read weights from file and parse into 2D array
    public static int[][] weights(String path) {
        String contents = Frequency.read(path);
        String[] rows = contents.split("\n");
        int[][] ret = new int[rows.length][rows[0].split(",").length];
        String[] row = null;
        
        for (int i = 0; i < rows.length; i++) {
            row = rows[i].split(",");
            for (int j = 0; j < ret[i].length; j++) {
                ret[i][j] = Integer.parseInt(row[j]);
            }
        }
        
        return ret;
    }

    // Inner Vertex class
    static class Vertex implements Comparable<Vertex> {
        public boolean visited = false;
        public int i = -1;
        public double d = Double.POSITIVE_INFINITY;
        public char pi = 0;

        public Vertex(int i) {
            this.i = i;
            this.pi = this.label();
        }

        public int compareTo(Vertex other) {
            return Double.compare(this.d, other.d);
        }

        public char label() {
            return (char) (this.i + 'a');
        }
    }
}
/*public class Graph {
    public static void main(String[] args) {
        int[][] w = weights("dijkstra1.txt");
        Vertex[] G = new Vertex[w.length];
        dijkstra(G, w, 0);
        for (int i = 0; i < w.length; i++) {
            for (int j = 0; j < w[i].length; j++) {
                System.out.printf("%3d", w[i][j]);
            }
            System.out.println();
        }
    }

    public static void initalizeSingleSource(Vertex[] G, int si) {
        for (int i = 0; i < G.length; i++) {
            G[i] = new Vertex(i);
        }
        G[si].d = 0;
    }
        
    

    public static int[][] weights(String path) {
        String contents = Frequency.read(path);
        String[] rows = contents.split("\n");
        int[][] ret = new int[rows.length][rows[0].split(",").length];
        String[] row = null;
        for (int i = 0; i < rows.length; i++) {
            row = rows[i].split(",");
            for (int j = 0; j < ret[i].length; j++) {
                ret[i][j] = Integer.parseInt(row[j]);
            }
        }
        return ret;
    }
    public static void dijkstra(Vertex[] G, int[][] w, int si){
        initalizeSingleSource(G, si);
        Vertex u;
        while (( u = dequeue(G)) != null) {
            for (int j = 0; j < w[j].length; j++) 
                 relax(u, G[j], w);
                
            }
        
        return;
    }
    public static void relax(Vertex u, Vertex v, int[][] w){
        if (v.d > u.d + w[u.i][v.i]){
            v.d = u.d + w[u.i][v.i];
            v.pi = u.label();
        }
    }
    

    public static Vertex dequeue(Vertex[] G){
         Vertex ret = null;
        for(Vertex v : G){
        if (v.visited){
        continue;
        }
        else if (ret == null || ret.d > v.d){
        ret = v;
        }
    }
        if(ret != null){
            ret.visited = true;
        }
           return ret;
        }
    





    class Vertex implements Comparable<Vertex>{
    public boolean visited = false;
    public int i = -1;
    public double d = Double.POSITIVE_INFINITY;
    public char pi = 0;

    public Vertex(int i) {
            this.i = i;
            this.pi = this.label();
        }

    public int compareTo(Vertex other) {
        return Double.compare(this.d, other.d);
    }

    public char label() {
        return (char) (this.i + 'a');
    }
}
}
*/
