using CorpRest_DataIntegration.Corp;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CorpRest_DataIntegration.Restaurant_B
{
    internal class Menu_B_Iterator : IIterator
    {
        private Menu_B _menu = null;
        private int _position = 0;
        private int _menuItemCount = 0;

        public Menu_B_Iterator(Menu_B menu)
        {
            _menu = menu;
            _menuItemCount = _menu.ItemCount;
        }


        public bool hasNext()
        {
            return (_position < _menuItemCount);
        }

        public AbstractMenu next()
        {
            AbstractMenu commonItem = null;

            MenuItem_B tempItem = null;
            double tempPrice = 0;
            string tempDescription;
            CommonItem.MenuTime commonTime = CommonItem.MenuTime.Day;

            if (hasNext())
            {
                tempItem = _menu.Items[_position ];

                switch (tempItem.ServTime)
                {
                    case MenuItem_B.ServiceTime.Morning:
                        commonTime = CommonItem.MenuTime.Morning;
                        break;
                    case MenuItem_B.ServiceTime.Noon:
                        commonTime = CommonItem.MenuTime.Lunch; 
                        break;
                    case MenuItem_B.ServiceTime.Late:
                        commonTime = CommonItem.MenuTime.Evening;
                        break;
                    default:
                        commonTime = CommonItem.MenuTime.Day;
                        break;
                }

                commonItem = new CommonItem(
                        tempItem.ItemName,
                        tempItem.ItemDesc,
                        tempItem.ItemCost,
                        commonTime
                );
                _position++;
            }
            return commonItem;
        }
    }
}
