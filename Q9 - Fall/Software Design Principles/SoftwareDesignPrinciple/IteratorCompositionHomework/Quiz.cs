using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IteratorCompositionHomework
{
    public class Quiz : AbstractQuiz
    {
        public Quiz(string quizName)
        {
            _displayText = quizName;
        }

        public override IQuizIterator GetIterator()
        {
            return new QuizIterator(this);
        }
    }
}
