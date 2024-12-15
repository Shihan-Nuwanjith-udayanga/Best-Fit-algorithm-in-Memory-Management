import java.util.Scanner;

public class BestFitMemoryAllocation {
    // Method to allocate memory to blocks as per Best fit algorithm
    static void bestFit(int blockSize[], int m, int processSize[], int n) {
        // Stores block id of the block allocated to a process
        int allocation[] = new int[n];

        // Initially no block is assigned to any process
        for (int i = 0; i < allocation.length; i++)
            allocation[i] = -1;

        // Pick each process and find suitable blocks according to its size and assign to it
        for (int i = 0; i < n; i++) {
            // Find the best fit block for current process
            int bestIdx = -1;
            for (int j = 0; j < m; j++) {
                if (blockSize[j] >= processSize[i]) {
                    if (bestIdx == -1 || blockSize[bestIdx] > blockSize[j]) {
                        bestIdx = j;
                    }
                }
            }

            // If we could find a block for the current process
            if (bestIdx != -1) {
                // Allocate block j to process i
                allocation[i] = bestIdx;

                // Reduce available memory in this block
                blockSize[bestIdx] -= processSize[i];
            }
        }

        System.out.println("\nProcess No.\tProcess Size\tBlock no.");
        for (int i = 0; i < n; i++) {
            System.out.print("   " + (i + 1) + "\t\t" + processSize[i] + "\t\t");
            if (allocation[i] != -1)
                System.out.print(allocation[i] + 1);
            else
                System.out.print("Not Allocated");
            System.out.println();
        }
    }

    // Driver Method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input block sizes
        System.out.print("Enter the number of memory blocks: ");
        int m = scanner.nextInt();
        int[] blockSize = new int[m];
        System.out.println("Enter the sizes of memory blocks:");
        for (int i = 0; i < m; i++) {
            System.out.print("Block " + (i + 1) + ": ");
            blockSize[i] = scanner.nextInt();
        }

        // Input process sizes
        System.out.print("\nEnter the number of processes: ");
        int n = scanner.nextInt();
        int[] processSize = new int[n];
        System.out.println("Enter the sizes of processes:");
        for (int i = 0; i < n; i++) {
            System.out.print("Process " + (i + 1) + ": ");
            processSize[i] = scanner.nextInt();
        }

        // Call bestFit method
        bestFit(blockSize, m, processSize, n);

        scanner.close();
    }
}
