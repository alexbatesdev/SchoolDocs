using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IteratorCompositionHomework
{
    public class OptionsIterator : IQuizIterator
    {
        public int _position = 0;

        public int _count = 0;

        private AbstractQuiz _quiz;

        public OptionsIterator(AbstractQuiz quiz)
        {
            _count = quiz._quizList.Count;
            _position = 0;
            _quiz = quiz;
        }

        public AbstractQuiz Current()
        {
            return _quiz._quizList[_position];
        }

        public bool HasNext()
        {
            return _position < _count;
        }

        public AbstractQuiz Next()
        {
            if (HasNext())
            {
                AbstractQuiz nextQuiz = _quiz._quizList.ElementAt(_position);
                _position++;
                return nextQuiz;
            }
            return null;
        }
    }
}