namespace ConsoleLibrary
{
    public class IO
    {
        public static void Print(string prompt)
        {
            Console.WriteLine(prompt);
        }

        public static void Ticket(string prompt, string delineator = "-----------------------")
        {
            Console.WriteLine(delineator);
            Console.WriteLine(prompt);
            Console.WriteLine(delineator);
        }

        public static int GetConsoleInt(string prompt, int min, int max)
        {
            bool validSelection = false;
            int result = 0;
            do
            {
                Console.WriteLine(prompt + $" (Min: {min} - Max: {max})");
                bool validInt = int.TryParse(Console.ReadLine(), out result);
                if (validInt)
                {
                    result = Math.Clamp(result, min, max);
                    validSelection = true;
                }
            } while (!validSelection);
            return result;
        }

        //Get console float
        public static float GetConsoleFloat(string prompt, float min, float max)
        {
            bool validSelection = false;
            float result = 0;
            do
            {
                Console.WriteLine(prompt + $" (Min: {min} - Max: {max})");
                bool validFloat = float.TryParse(Console.ReadLine(), out result);
                if (validFloat)
                {
                    result = Math.Clamp(result, min, max);
                    validSelection = true;
                }

            } while (!validSelection);
            return result;
        }
        //Get console bool
        public static bool GetConsoleBool(string prompt)
        {
            bool validSelection = false;
            bool result = false;
            do
            {
                Console.WriteLine(prompt + " (Y/N)");
                string input = Console.ReadLine().ToLower();
                if (input == "y")
                {
                    result = true;
                    validSelection = true;
                }
                else if (input == "n")
                {
                    result = false;
                    validSelection = true;
                }
            } while (!validSelection);
            return result;
        }
        //get console string
        public static string GetConsoleString(string prompt)
        {
            Console.WriteLine(prompt);
            return Console.ReadLine();
        }
        //get console menu
        public static int GetConsoleMenu(List<String> menu, string prompt)
        {
            bool validSelection = false;
            int result;
            bool isInt;
            Console.WriteLine(prompt);
            for (int index = 0; index < menu.Count; index++)
            {
                Console.WriteLine($"{index + 1} - {menu[index]}");
            }
            do
            {
                Console.Write("> ");
                isInt = int.TryParse(Console.ReadLine(), out result);
                if (result > 0 && result < menu.Count) validSelection = true;
            } while (!isInt && !validSelection);

            return result;
        }

        //Get console menu, but with fun colors
        public static int GetColorMenu(string prompt, List<String> menu, List<ConsoleColor> textColors, List<ConsoleColor> backgroundColors)
        {
            bool validSelection = false;
            int result;
            bool isInt;
            Console.WriteLine(prompt);
            for (int index = 0; index < menu.Count; index++)
            {
                Console.Write($"{index + 1} - ");
                Console.BackgroundColor = backgroundColors[index];
                Console.ForegroundColor = textColors[index];
                Console.Write(menu[index]);
                Console.ResetColor();
                Console.WriteLine();
            }
            do
            {
                Console.ResetColor();
                Console.Write("> ");
                isInt = int.TryParse(Console.ReadLine(), out result);
                if (result > 0 && result < menu.Count) validSelection = true;
            } while (!isInt && !validSelection);

            return result;
        }
    }
}