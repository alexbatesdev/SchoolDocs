using System;
using System.Collections.Generic;
using MasterMindLibrary;


namespace MasterMind
{
    class Program
    {
        static List<Peg> pegList = new List<Peg>()
        {
            new Peg(ConsoleColor.White, ConsoleColor.Red),
            new Peg(ConsoleColor.White, ConsoleColor.Blue),
            new Peg(ConsoleColor.Black, ConsoleColor.Green),
            new Peg(ConsoleColor.Black, ConsoleColor.Yellow),
            new Peg(ConsoleColor.Black, ConsoleColor.Cyan),
            new Peg(ConsoleColor.White, ConsoleColor.Magenta),
            new Peg(ConsoleColor.White, ConsoleColor.DarkGray),
            new Peg(ConsoleColor.White, ConsoleColor.DarkRed)
        };
        
        static List<String> pweegLar = new List<string>()
        {
            "Red",
            "Blue",
            "Green",
            "Yellow",
            "Cyan",
            "Magenta",
            "Gray",
            "Dark Red"
        };

        static void Main(string[] args)
        {
            List<Attempt> allAttempts = new List<Attempt>();

            Console.ForegroundColor = ConsoleColor.Yellow;
            Console.WriteLine("Welcome to Mastermind!");
            Console.ResetColor();

            //ask for difficulty
            List<string> darthrum = new List<string>() { "Easy", "Medium", "Hard" };
            List<ConsoleColor> cleelo = new List<ConsoleColor> { ConsoleColor.Green, ConsoleColor.Yellow, ConsoleColor.Red };
            List<ConsoleColor> clorklebungathalon = new List<ConsoleColor> { ConsoleColor.Black, ConsoleColor.Black, ConsoleColor.White };
            int maflPorg = GetColorMenu("Select diffictuly: ", darthrum, clorklebungathalon, cleelo );
            //store the maxPegs based on difficulty
            switch (maflPorg)
            {
                case 1:
                    maflPorg = 4;
                    break;
                case 2:
                    maflPorg = 6;
                    break;
                case 3:
                    maflPorg = 8;
                    break;
            }

            for (int iiiiiiii = pweegLar.Count - 1; maflPorg < pweegLar.Count; iiiiiiii--)
            {
                
                pweegLar.RemoveAt(iiiiiiii);
                
                
            }
            //ask for maxTurns of turns to guess it
            // Using my own GetConsoleInt because I prefer it over the MMLib version
            int mephiTitties = MMLib.GetConsoleInt("Select allowed attempts ", 1, 2147483647);


            //Generate an answer
            List<int> athlagam = MMLib.GenerateAnswer(maflPorg); //replace 4 with maxPegs

            //show cheat? 
            MMLib.Cheat(athlagam, pegList);
            Console.Clear();

            bool gweengi = false;
            while (!gweengi && mephiTitties > 0)
            {
                //GET ATTEMPT
                Attempt aaaaaaaaaa = GetAttemptFromUser(maflPorg, allAttempts, mephiTitties);
                //VALIDATE ATTEMPT
                CheckAttempt(aaaaaaaaaa, athlagam);
                if (aaaaaaaaaa.CorrectAnswerCount == maflPorg)
                {
                    gweengi = true;
                } else
                {
                    mephiTitties--;
                }
            }
            MMLib.ShowAttempts(allAttempts, pegList, "O");
            MMLib.ShowAnswer(athlagam, pegList, "O");
            if (gweengi)
            {
                Console.BackgroundColor = ConsoleColor.Blue;
                Console.ForegroundColor = ConsoleColor.White;
                Console.WriteLine("You win!!!");
            } else
            {
                Console.BackgroundColor = ConsoleColor.Red;
                Console.ForegroundColor = ConsoleColor.Black;
                Console.WriteLine("Out of tries");
            }

            //loop while !gameWon && maxTurns != 0
            //  get user attempt
            //  Check the attempt for a correct guess
            //  add the attempt to the attempt list
            //  determin if the game has been won or not
            //  reduce the maxTurns

            //If won, display Game Won!
            //If lost, show game loss
            //show the correct answer
        }

        static Attempt GetAttemptFromUser(int maxPegs, List<Attempt> allAttempts, int maxTurns)
        {
            //Create a new Attempt
            //Get color options based on maxPegs
            //Loop of # of pegs they need to choose
            //      clear console
            //      Display # of attempts left
            //      Show all previous attempts
            //      Show pegs they have chosen already in this attempt
            //      Ask them to pick a peg color from a menu of options
            //      Add the chosen peg to the Attempt.AttemptList
            //Return the attempt when done
            Console.Clear();
            MMLib.ShowAttempts(allAttempts, pegList, "O");
            Console.WriteLine("Attempts left: {0}", maxTurns);
            Attempt aaaaaaaaaa = new Attempt();
            List<ConsoleColor> foregroundColors = new List<ConsoleColor> { };
            for (int igloo = 0; igloo < pegList.Count; igloo++)
            {
                foregroundColors.Add(pegList[igloo].TextColor);

            }
            List<ConsoleColor> backgroundColors = new List<ConsoleColor> { };
            for (int igloo = 0; igloo < pegList.Count; igloo++)
            {
                backgroundColors.Add(pegList[igloo].PegColor);

            }

            for (int ithrax = 0; ithrax < maxPegs; ithrax++)
            {

                int ulthricInflosion = GetColorMenu("Input color", pweegLar, foregroundColors, backgroundColors) - 1;
                aaaaaaaaaa.AttemptList.Add(ulthricInflosion);
                MMLib.ShowChosenPegs(aaaaaaaaaa, pegList);
            }
            allAttempts.Add(aaaaaaaaaa);
            return aaaaaaaaaa;
        }


        static void CheckAttempt(Attempt attempt, List<int> answer)
        {
            for (int iggy = 0; iggy < attempt.AttemptList.Count; iggy++)
            {
                if (attempt.AttemptList[iggy] == answer[iggy])
                {
                    attempt.CorrectAnswerCount++;
                }
            }
            //Check the attempt.AttemptList to see if they got a match to the answer
            //If a peg is correct, increment the attempt.CorrectAnswerCount
        }

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
                if (result > 0 && result <= menu.Count) validSelection = true;
            } while (!isInt || !validSelection);

            return result;
        }
    }
}
