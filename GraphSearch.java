/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tugas3_alifiany044469803;

/**
 *
 * @author u
 */
import java.util.*;
public class GraphSearch {
    //struktur untuk menyimpan graph
    private final Map<String, List<String>> adj;
    
    //struktur untuk menyimpan nilai tiap node
    private final Map<String, Integer> nodeValues;
    
    public GraphSearch(){
        adj = new HashMap<>();
        
        //inisialisasi Map nilai node
        nodeValues = new HashMap<>();
    }
    
    //metode untuk mengatur nilai node
    public void setNodeValue(String node, int value){
        nodeValues.put(node, value);
    }
    
    //metode untuk menambahkan edge ke graph
    public void addEdge(String u, String v){
        //tambahkan v sebagai tetangga u
        adj.computeIfAbsent(u, k -> new LinkedList<>()).add(v);
        //tambahkan u sebagai tetangga v (karena tak berarah)
        adj.computeIfAbsent(v, k -> new LinkedList<>()).add(u); 
    }
    
    //algoritma DFS
    public void DFS(String startNode, int targetValue){
        //set untuk melacak node yang telah dikunjungi
        Set<String> visited = new HashSet<>();
        
        //stack untuk menyimpan node yang akan dikunjungi
        Stack<String> Stack = new Stack<>();
        
        System.out.println("\n--- Tahap demi tahap pencarian DFS ---");
        
        //mulai dari node awal
        Stack.push(startNode);
        visited.add(startNode);
        boolean found = false;
        
        while(!Stack.isEmpty()){
            String currentNode = Stack.pop(); //ambil node dari atas stack
            int currentValue = nodeValues.getOrDefault(currentNode, targetValue);
            System.out.println("Mengunjungi Node : " + currentNode + " (Nilai : " + currentValue +")");
            
            if(currentValue == targetValue){
                System.out.println("\n**Nilai target " + targetValue + " Ditemukan pada Node " + currentNode + "!**");
                found = true;
                break;
            }
            
            //dapatkan neighbor (tetangga) dari node saat ini
            List<String> neighbors = adj.getOrDefault(currentNode, Collections.emptyList());
            
            //masukan neighbor yang belum dikunjungi kedalam stack
            for(int i=neighbors.size()-1; i>=0; i--){
                String neighbor = neighbors.get(i);
                if(!visited.contains(neighbor)){
                    visited.add(neighbor);
                    Stack.push(neighbor);
                }
            }
        }
        if(!found){
            System.out.println("\n**Node target " + targetValue + "Tidak ditemukan!**");
        }
    }
    
    //algoritma BFS
    public void BFS(String startNode, int targetValue){
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>(); //BFS menggunakan queue
        System.out.println("\n--- Tahap demi tahap pencarian BFS ---");
        
        queue.offer(startNode);
        visited.add(startNode);
        boolean found = false;
        
        while(!queue.isEmpty()){
            String currentNode = queue.poll(); //ambil dari depan queue
            int currentValue = nodeValues.getOrDefault(currentNode, targetValue);
            System.out.println("Mengunjungi Node : " + currentNode + "(Nilai : " + currentValue + ")");
            
            //perbandingan antar dua int primitif
            if(currentValue == targetValue){
                System.out.println("\n**Nillai target " + targetValue + " Ditemukan pada Node " + currentNode + "!**");
                
                found = true;
                break;
            }
            List<String> neighbors = adj.getOrDefault(currentNode, Collections.emptyList());
            
            //BFS : masukkan tetangga ke queue (FIFO)
            for(String neighbor : neighbors){
                if(!visited.contains(neighbor)){
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        if(!found){
            System.out.println("**Nilai target " + targetValue + " Tidak ditemukan!**");
        }
    }

    /**
     * @param args the command line arguments
     */
    //metode utama
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GraphSearch graph = new GraphSearch();
        
        //definisikan nilai untuk tiap node
        System.out.println("1. Mendefinisikan Nilai tiap Node");
        graph.setNodeValue("a1", 21);
        graph.setNodeValue("a2", 31);
        graph.setNodeValue("a3", 46);
        graph.setNodeValue("a4", 14);
        graph.setNodeValue("a5", 44);
        graph.setNodeValue("a6", 100);
        graph.setNodeValue("a7", 62);
        graph.setNodeValue("a8", 20);
        
        //definisikan koneksi graph
        System.out.println("2. Mendefinisikan koneksi graph (a1 - a8)");
        graph.addEdge("a1", "a2");
        graph.addEdge("a1", "a3");
        graph.addEdge("a2", "a4");
        graph.addEdge("a2", "a5");
        graph.addEdge("a3", "a6");
        graph.addEdge("a4", "a7");
        graph.addEdge("a5", "a7");
        graph.addEdge("a6", "a8");
        
        System.out.println("---Graph telah selesai dibuat---");
        
        //input nilai yang akan dicari
        System.out.println("\n--------------------------------------------------");
        System.out.print("Masukkan nilai n yang akan dicari : ");
        
        //membaca input integer dari console
        int targetValue = scanner.nextInt();
        System.out.println("Nilai yang di cari (n) adalah : " + targetValue);
        
        //eksekusi DFS
        System.out.println("\n*** Mulai DFS dari a1 mencari nilai : " + targetValue + " ***");
        graph.DFS("a1", targetValue);
        
        //eksekusi BFS
        System.out.println("\n==================================================");
        System.out.println("\n***Mulai BFS dari a1 mencari nilai : " + targetValue + " ***");
        graph.BFS("a1", targetValue);
        System.out.println("====================================================");
    }
    
}
