using System.Diagnostics;
using System.Reflection.Metadata.Ecma335;
using System.Runtime.CompilerServices;

namespace BigOTires;

internal class Program
{
    static void Main(string[] args)
    {
        ProblemOne();
        ProblemTwo();
        ProblemThree();
    }

    private static void ProblemOne()
    {
        Stopwatch stopwatch = new Stopwatch();

        int[] array = GetArrayOfRandomInts(1000, true); // new int[] { 0, 1, 5, 20, 90, 2, 10, 100 };
        stopwatch.Start();
        int largest = FindLargestNumber(array);
        Console.WriteLine(largest);

        stopwatch.Stop();

        printExecutionTime(stopwatch);
    }

    private static int FindLargestNumber(int[] numbers)
    {
        int largest = numbers[0];
        int count = 0;
        for (int i = 1; i < numbers.Length; i++)
        {
            count++;
            if (numbers[i] > largest)
            {
                largest = numbers[i];
            }
        }
        Console.WriteLine($"Count: {count}");
        return largest;
    }

    private static void ProblemTwo()
    {
        Stopwatch stopwatch = new Stopwatch();

        //int[] sorted1 = { 2, 5, 5, 5 };
        //int[] sorted2 = { 2, 2, 3, 5, 5, 7 };

        int[] sorted1 = GetArrayOfRandomInts(50000, true);
        int[] sorted2 = GetArrayOfRandomInts(50000, true);

        stopwatch.Start();
        int[] result = FindCommonValues(sorted1, sorted2);
        stopwatch.Stop();
        //for (int i = 0; i < result.Length; i++)
        //{
        //    Console.Write(result[i] + " ");
        //}
        //Console.WriteLine();

        printExecutionTime(stopwatch);
    }

    private static int[] FindCommonValues(int[] array1, int[] array2)
    {
        //My pseudo code
        //Iterate through list grab unique numbers and counts
        //uniqueNums = [(2, 1), (5, 3)] //Do a dictionary instead of a list of pairs
        //Do so for second list as well
        //uniqueNums2 = [(2, 2), (3, 1), (5, 2), (7, 1)] //Do a dictionary instead of a list of pairs

        // Iterate through both lists to find what numbers match
        //uniqueNumsInCommon = [2, 5]

        // Print matching numbers, making sure to get the right amounts
        // Example: 2 is in array 1 once, and array 2 twice; only print 2 once
        int comparisons = 0;
        int operations = 0;

        Dictionary<int, int> Array1NumsAndCounts = new Dictionary<int, int>();
        Dictionary<int, int> Array2NumsAndCounts = new Dictionary<int, int>();

        List<int> output = new List<int>();

        //Iterates through the first list and compresses duplicates into their values and counts
        for (int i = 0; i < array1.Length; i++)
        {
            // if a number is not in the dictionary add it to the dictionary as a key and set the value to 1
            if (!Array1NumsAndCounts.ContainsKey(array1[i]))
            {
                Array1NumsAndCounts[array1[i]] = 1;
            }
            else
            // If a number is already in the dictionary then increase the count at the key for that number by 1
            {
                Array1NumsAndCounts[array1[i]]++;
            }
        }
        //Iterates through the second list and compresses duplicates into their values and counts
        for (int i = 0; i < array2.Length; i++)
        {
            // if a number is not in the dictionary add it to the dictionary as a key and set the value to 1
            if (!Array2NumsAndCounts.ContainsKey(array2[i]))
            {
                Array2NumsAndCounts[array2[i]] = 1;
            }
            else
            // If a number is already in the dictionary then increase the count at the key for that number by 1
            {
                Array2NumsAndCounts[array2[i]]++;
            }
        }

        // Iterate through the first list
        for (int i = 0; i < Array1NumsAndCounts.Count; i++)
        {
            //Checking if the keys in the first list are in the second list
            //THIS IS THE PART WITH THE DOMINANT INSTRUCTIONS
            operations++;
            if (Array2NumsAndCounts.ContainsKey(Array1NumsAndCounts.ElementAt(i).Key))
            {
                // The key is the number that we know is in both arrays at least once
                // I'm not counting this as an operation due to the fact that I could have just used Array1NumsAndCounts.ElementAt(i).Key where I used the variable key. It's not actually storing anything, it's just to make writing the code easier
                int key = Array1NumsAndCounts.ElementAt(i).Key;
                // We only want to append the the amount of the key that they have in common
                // This means we want whichever count is fewer
                // This if appends the key to the output as many times as it appears in whatever list it appears in least
                if (Array1NumsAndCounts[key] <= Array2NumsAndCounts[key])
                {
                    for (int j = 0; j < Array1NumsAndCounts[key]; j++)
                    {
                        output.Add(key);
                    }
                }
                else
                {
                    for (int j = 0; j < Array2NumsAndCounts[key]; j++)
                    {
                        output.Add(key);
                    }
                }
            }
        }
        Console.WriteLine("Operations: " + operations);
        return output.ToArray();
    }

    private static void ProblemThree()
    {
        Stopwatch stopwatch = new Stopwatch();
        string word = "racecar";
        stopwatch.Start();
        bool isWordAPalendrome = word.isPalendromeemordnelaPsi();
        stopwatch.Stop();
        Console.WriteLine($"It is {isWordAPalendrome} to say that {word} is a palindrome");

        printExecutionTime(stopwatch);
    }

    private static void printExecutionTime(Stopwatch watch)
    {
        Console.WriteLine($"Execution Time (s): {watch.Elapsed.TotalSeconds}");
        Console.WriteLine($"Execution Time (ms): {watch.Elapsed.TotalMilliseconds}");
        Console.WriteLine($"Execution Time (ns): {watch.Elapsed.TotalMilliseconds * 1000000}");
        Console.WriteLine("--------------------------------------------------------------------");
    }

    private static int[] GetArrayOfRandomInts(int count, bool sorted)
    {
        Random random = new Random();
        int[] ints = new int[count];

        for (int i = 0; i < count; i++)
        {
            ints[i] = random.Next(0, 1000);
        }

        if (sorted)
        {
            Array.Sort(ints);
        }

        return ints;
    }
}

static class ExtensionMethods
{
    public static bool isPalendromeemordnelaPsi(this String word)
    {
        if (word == null) return false;
        if (word.Length == 0) return false;
        if (word.ToLower() == word.ToLower().esrever()) return true;
        return false;
    }

    // not going to lie, I don't get why using the built in reverse is such a big deal. Reversing a string is so simple
    public static String esrever(this String word)
    {
        int wordLength = word.Length;
        string outputWord = "";
        for (int i = wordLength - 1; i >= 0; i--)
        {
            outputWord += word[i];
        }
        return outputWord;
    }
}