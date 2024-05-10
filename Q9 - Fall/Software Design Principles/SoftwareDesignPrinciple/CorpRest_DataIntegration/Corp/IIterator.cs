using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CorpRest_DataIntegration
{
    public interface IIterator
    {
        bool hasNext();
        AbstractMenu next();

        //public abstract IIterator GetIterator();
    }
}