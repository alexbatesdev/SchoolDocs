using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CorpRest_DataIntegration.Corp
{
    internal class Corp_Menu_Iterator : IIterator
    {
        private int _position = 0;
        private int _menuItemCount = 0;
        private CorpMenu _menu = null;

        public Corp_Menu_Iterator(CorpMenu menu)
        {
            _menu = menu;
            _menuItemCount = _menu.MenuItems.Count;
        }

        public bool hasNext()
        {
            return ( _position < _menuItemCount );
        }

        public AbstractMenu next()
        {
            AbstractMenu tempItem = null;
            
            if (hasNext())
            {
                tempItem = _menu.MenuItems.ElementAt( _position );
                _position++;
            }

            return tempItem;
        }
    }
}
