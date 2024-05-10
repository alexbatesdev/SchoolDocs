using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace IteratorCompositionHomework
{
    public partial class Form1 : Form
    {
        IQuizIterator quizIterator;
        AbstractQuiz currentQuiz;

        public Form1()
        {
            InitializeComponent();

            //The QuizPicker is just an AbstractQuiz, with no differences at all
            AbstractQuiz quizPicker = new QuizPicker();

            quizPicker.addChild(genQuizA());
            quizPicker.addChild(genQuizB());
            quizPicker.addChild(genQuizC());


            quizIterator = quizPicker.GetIterator();
            currentQuiz = quizIterator.Next();

            displayLabel.Text = currentQuiz.GetDisplayText();




            List<Button> buttons = new List<Button>() { buttonA, buttonB, buttonC };

            for (int i = 0; i < 3; i++)
            {
                string text = ((QuizOption)currentQuiz._quizList[i]).GetDisplayText();
                Console.WriteLine(text);
                buttons.ElementAt(i).Text = text;
                buttons.ElementAt(i).Click += checkCorrect;
            }

        }

        public int getOptionIndex(object sender)
        {
            int optionIndex = -1;
            switch (((Button)sender).Name)
            {
                case "buttonA":
                    optionIndex = 0;
                    break;
                case "buttonB":
                    optionIndex = 1;
                    break;
                case "buttonC":
                    optionIndex = 2;
                    break;
                default:
                    optionIndex = -1;
                    break;
            }
            return optionIndex;
        }

        private void nextButton_Click(object sender, EventArgs e)
        {
            currentQuiz = quizIterator.Next();

            switch (currentQuiz)
            {
                case QuizPicker quizPicker:
                    buttonA.Text = "Quiz";
                    displayLabel.Text = "Quiz Complete!";
                    return;
            }
                
            displayLabel.Text = currentQuiz.GetDisplayText();

            buttonA.BackColor = Color.Transparent;
            buttonB.BackColor = Color.Transparent;    
            buttonC.BackColor = Color.Transparent;


            switch (currentQuiz)
            {
                case Quiz quiz:
                    buttonA.Text = "Section";
                    buttonB.Text = "Complete!";
                    buttonC.Text = "Congrats!";
                    return;
            }


            buttonA.Text = currentQuiz._quizList.ElementAt(0).GetDisplayText();
            buttonB.Text = currentQuiz._quizList.ElementAt(1).GetDisplayText();
            buttonC.Text = currentQuiz._quizList.ElementAt(2).GetDisplayText();
                
        }

        private void checkCorrect(object sender, EventArgs yarrgs)
        {
            switch (currentQuiz)
            {
                case Quiz quiz:
                    return;
                case QuizPicker quizPicker:
                    return;
            }
            if (currentQuiz != null)
            {
                int optionIndex = -1;
                switch (((Button)sender).Name)
                {
                    case "buttonA":
                        optionIndex = 0;
                        break;
                    case "buttonB":
                        optionIndex = 1;
                        break;
                    case "buttonC":
                        optionIndex = 2;
                        break;
                    default:
                        optionIndex = -1;
                        break;
                }
                if (((QuizQuestion)currentQuiz).isCorrect(optionIndex))
                {
                    ((Button)sender).Text = "Correct!";
                    ((Button)sender).BackColor = Color.Green;
                } else
                {
                    ((Button)sender).Text = "Incorrect!";
                    ((Button)sender).BackColor = Color.Red;
                }
            }
        }

        private AbstractQuiz genQuizA()
        {
            AbstractQuiz quiz = new Quiz("Quiz A - By Me!!");

            quiz.addChild(
                new QuizQuestion(
                    "A.1.Welcome to the quiz, are you ready?",
                    new List<AbstractQuiz>
                    {
                        new QuizOption("Aye Aye Captain!!!", true),
                        new QuizOption("No"),
                        new QuizOption("Yeah!!!", true)
                    }
                )
            );

            quiz.addChild(
                new QuizQuestion(
                    "A.2.What is the best color?",
                    new List<AbstractQuiz>
                    {
                        new QuizOption("Purple", true),
                        new QuizOption("Red"),
                        new QuizOption("White")
                    }
                )
            );

            quiz.addChild(
                new QuizQuestion(
                    "A.3.What is the capital of New York?",
                    new List<AbstractQuiz> {
                        new QuizOption("Abraham"),
                        new QuizOption("Kentucky"),
                        new QuizOption("Albany", true)
                    }
                )
            );

            quiz.addChild(
                new QuizQuestion(
                    "A.4.What is the airspeed velocity of an unladen swallow?",
                    new List<AbstractQuiz> {
                        new QuizOption("Green"),
                        new QuizOption("20.11 mph", true),
                        new QuizOption("Sir GoldenLoin of Camelot"),
                    }
                )
            );


            quiz.addChild(
                new QuizQuestion(
                    "A.5.Why is the sky so blue?",
                    new List<AbstractQuiz>
                    {
                        new QuizOption("Light Refractions"),
                        new QuizOption("Larry", true),
                        new QuizOption("Lord Zaxby from Kolob")
                    }
                )
            );

            quiz.addChild(
                new QuizQuestion(
                    "A.6.What is x in (5x+9)/3 = 0?",
                    new List<AbstractQuiz>
                    {
                        new QuizOption("-9/5", true),
                        new QuizOption("Red"),
                        new QuizOption("0")
                    }
                )
            );
            return quiz;
        }

        // Assistant generated code for genQuizB starts here
        private AbstractQuiz genQuizB()
        {
            AbstractQuiz quiz = new Quiz("Quiz B - By GPT!!");

            quiz.addChild(
                new QuizQuestion(
                    "B.1.What is the largest planet in our Solar System?",
                    new List<AbstractQuiz>
                    {
                new QuizOption("Earth"),
                new QuizOption("Jupiter", true),
                new QuizOption("Mars")
                    }
                )
            );

            quiz.addChild(
                new QuizQuestion(
                    "B.2.What is 2 + 2?",
                    new List<AbstractQuiz>
                    {
                new QuizOption("3"),
                new QuizOption("4", true),
                new QuizOption("5")
                    }
                )
            );

            quiz.addChild(
                new QuizQuestion(
                    "B.3.Who wrote the play 'Romeo and Juliet'?",
                    new List<AbstractQuiz> {
                new QuizOption("Mark Twain"),
                new QuizOption("William Shakespeare", true),
                new QuizOption("Charles Dickens")
                    }
                )
            );

            quiz.addChild(
                new QuizQuestion(
                    "B.4.What is the square root of 64?",
                    new List<AbstractQuiz> {
                new QuizOption("6"),
                new QuizOption("8", true),
                new QuizOption("10"),
                    }
                )
            );

            quiz.addChild(
                new QuizQuestion(
                    "B.5.Which of the following is NOT a prime number?",
                    new List<AbstractQuiz>
                    {
                new QuizOption("2"),
                new QuizOption("9", true),
                new QuizOption("13")
                    }
                )
            );

            quiz.addChild(
                new QuizQuestion(
                    "B.6.Who painted the Mona Lisa?",
                    new List<AbstractQuiz>
                    {
                new QuizOption("Leonardo da Vinci", true),
                new QuizOption("Michelangelo"),
                new QuizOption("Vincent van Gogh")
                    }
                )
            );
            return quiz;
        }
        // Assistant generated code for genQuizB ends here

        // Assistant generated code for genQuizC starts here
        private AbstractQuiz genQuizC()
        {
            AbstractQuiz quiz = new Quiz("Quiz C - By GPT!!");

            quiz.addChild(
                new QuizQuestion(
                    "C.1.What is the atomic number of hydrogen?",
                    new List<AbstractQuiz>
                    {
                new QuizOption("0"),
                new QuizOption("1", true),
                new QuizOption("2")
                    }
                )
            );

            quiz.addChild(
                new QuizQuestion(
                    "C.2.How many states are there in the USA?",
                    new List<AbstractQuiz>
                    {
                new QuizOption("50", true),
                new QuizOption("49"),
                new QuizOption("51")
                    }
                )
            );

            quiz.addChild(
                new QuizQuestion(
                    "C.3.What is the powerhouse of the cell?",
                    new List<AbstractQuiz> {
                new QuizOption("Nucleus"),
                new QuizOption("Mitochondria", true),
                new QuizOption("Endoplasmic reticulum")
                    }
                )
            );

            quiz.addChild(
                new QuizQuestion(
                    "C.4.What is the symbol for gold in the periodic table?",
                    new List<AbstractQuiz> {
                new QuizOption("Gd"),
                new QuizOption("Au", true),
                new QuizOption("Ag"),
                    }
                )
            );

            quiz.addChild(
                new QuizQuestion(
                    "C.5.What is the freezing point of water in Fahrenheit?",
                    new List<AbstractQuiz>
                    {
                new QuizOption("0"),
                new QuizOption("32", true),
                new QuizOption("100")
                    }
                )
            );

            quiz.addChild(
                new QuizQuestion(
                    "C.6.Who is known as the Father of Modern Physics?",
                    new List<AbstractQuiz>
                    {
                new QuizOption("Albert Einstein"),
                new QuizOption("Isaac Newton", true),
                new QuizOption("Galileo Galilei")
                    }
                )
            );
            return quiz;
        }
        // Assistant generated code for genQuizC ends here

    }
}
