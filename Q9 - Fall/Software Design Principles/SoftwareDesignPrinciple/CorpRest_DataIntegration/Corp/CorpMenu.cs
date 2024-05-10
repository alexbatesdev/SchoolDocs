using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CorpRest_DataIntegration.Corp
{
    public class CorpMenu : AbstractMenu
    {
        public override string GetDesc()
        {
            return "My Corporation - qwerty";
        }

        public override IIterator getIterator()
        {
            return new Corp_Menu_Iterator(this);
        }

        public override string GetName()
        {
            return "Corporations destroy lives";
        }

        public override double GetPrice()
        {
            return -1;
        }

        public List<AbstractMenu> MenuItems { get { return _menuList; } }
    }
}
