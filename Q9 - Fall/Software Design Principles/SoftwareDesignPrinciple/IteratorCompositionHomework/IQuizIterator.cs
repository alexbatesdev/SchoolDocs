using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IteratorCompositionHomework
{
    public interface IQuizIterator
    {
        bool HasNext();
        AbstractQuiz Next();
    }
}
