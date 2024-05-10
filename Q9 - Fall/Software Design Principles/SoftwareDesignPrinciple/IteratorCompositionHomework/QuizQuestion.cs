using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IteratorCompositionHomework
{
    public class QuizQuestion : AbstractQuiz
    {
        //public List<QuizOption> _quizList = new List<QuizOption> { };

        public QuizQuestion(string question, List<AbstractQuiz> quizOptions)
        {
            _displayText = question;
            _quizList = quizOptions;
        }


        public override IQuizIterator GetIterator()
        {
            return new QuizIterator(this);
        }
        
        
        public bool isCorrect(int index)
        {
            return ((QuizOption)_quizList.ElementAt(index)).getIsCorrect();
        }
    }
}
