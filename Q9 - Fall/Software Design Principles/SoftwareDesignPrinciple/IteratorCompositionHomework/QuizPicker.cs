using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IteratorCompositionHomework
{
    public class QuizPicker : AbstractQuiz
    {
        public override IQuizIterator GetIterator()
        {
            return new QuizIterator(this);
        }
    }
}
