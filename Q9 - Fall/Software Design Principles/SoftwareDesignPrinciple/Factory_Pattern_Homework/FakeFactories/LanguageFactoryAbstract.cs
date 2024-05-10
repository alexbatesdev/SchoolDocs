using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Factory_Pattern_Homework
{
    public abstract class LanguageFactoryAbstract
    {
        public LanguageFactoryAbstract() { }

        public abstract string Create(AbstractComponent component);
    }
}
