using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IteratorCompositionHomework
{
    public abstract class AbstractQuiz
    {
        public List<AbstractQuiz> _quizList = new List<AbstractQuiz> { };
        public string _displayText = "";
        public abstract IQuizIterator GetIterator();

        public virtual string GetDisplayText()
        {
            return _displayText;
        }

        public virtual void addChild(AbstractQuiz option) { 
            _quizList.Add(option);
        }

        public void Print()
        {
            Console.WriteLine("=====================");
            Console.WriteLine(_displayText);
            if (_quizList.Count > 0)
            {
                Console.WriteLine(_quizList[0] != null ? _quizList[0] : null);
                Console.WriteLine(_quizList[1] != null ? _quizList[1] : null);
                Console.WriteLine(_quizList[2] != null ? _quizList[2] : null);
            }
            Console.WriteLine("=====================");
        }
    }
}
