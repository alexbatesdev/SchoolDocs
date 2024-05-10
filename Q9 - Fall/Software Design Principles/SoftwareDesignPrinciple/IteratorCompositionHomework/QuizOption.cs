using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IteratorCompositionHomework
{
    public class QuizOption : AbstractQuiz
    {
        private bool _isCorrect = false;

        public QuizOption(string displayText) {
            _displayText = displayText;
        }

        public QuizOption(string displayText, bool isCorrect)
        {
            _displayText = displayText;
            _isCorrect = isCorrect;
        }

        public bool getIsCorrect()
        {
            return _isCorrect;
        }

        public override IQuizIterator GetIterator()
        {
            return null;
        }
    }
}
