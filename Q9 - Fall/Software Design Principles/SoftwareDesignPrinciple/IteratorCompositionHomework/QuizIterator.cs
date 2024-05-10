using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IteratorCompositionHomework
{
    internal class QuizIterator : IQuizIterator
    {
        public int _position = 0;

        public int _count = 0;

        private AbstractQuiz _quiz;

        public IQuizIterator _quizIterator = null;

        public QuizIterator(AbstractQuiz quiz) {
            switch (quiz)
            {
                case Quiz question:
                    _position = -1;
                    break;
            }

            _count = quiz._quizList.Count;
            _quiz = quiz;
            if (quiz._quizList.Count > 0)
                _quizIterator = quiz._quizList[0].GetIterator();
            else
                _quizIterator = null;

        }

        public AbstractQuiz Current()
        {
            return _quiz._quizList[_position];
        }

        public bool HasNext()
        {
            //If I don't have an iterator I don't have a next
            if (_quizIterator == null)
            {
                return false;
            }

            //If my child has a next, I have a next
            if (_quizIterator.HasNext() == true)
            {
                return true;
            }

            //If my position is less than count, I have a next
            if (_position < _count) //maybe off by one
            {
                return true;
            }

            //Otherwise there is no next
            return false;
        }

        public AbstractQuiz Next()
        {
            // If my child has a child
            if (_quizIterator != null && _quizIterator.HasNext())
            {
                // Enter my child
                return _quizIterator.Next();
            }

            _position++;
            //If I have next
            if (HasNext()) 
            {
                //Enter child
                _quizIterator = _quiz._quizList[_position].GetIterator();
                return _quizIterator.Next();
            }

            //Return myself if I and my child don't have next
            return _quiz;

        }
    }
}
