using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Factory_Pattern_Homework.RealFactories
{
    public class LanguageFactoryFactory
    {
        public LanguageFactoryFactory() { }

        public LanguageFactoryAbstract getFactory(Language language)
        {
            switch (language)
            {
                case Language.html:
                    return new HTMLLanguageFactory();
                case Language.xml:
                    return new XMLLanguageFactory();
                default:
                    return null;
            }
        }
    }
    public enum Language { html, xml }
}
