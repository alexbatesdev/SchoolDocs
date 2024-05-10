using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using CorpRest_DataIntegration.Corp;

namespace CorpRest_DataIntegration.Restaurant_A
{
    public class Menu_A_Iterator : IIterator
    {
        private Menu_A _menu = null;
        private int _position = 0;
        private int _menuItemCount = 0;
        
        public Menu_A_Iterator(Menu_A menu)
        {
            _menu = menu;
            _menuItemCount = _menu.MenuItems.Count;
        }

        public bool hasNext()
        {
            return (_position < _menuItemCount);
        }

        public AbstractMenu next()
        {
            AbstractMenu commonItem = null;
            //CommonItem commonItem = null; //For us, it's the same
            MenuItem_A tempItem = null;
            double tempPrice = 0;
            StringBuilder tempDescription = new StringBuilder();
            CommonItem.MenuTime commonTime = CommonItem.MenuTime.Day;
            // ////////////////////////////////////// End of setting default values
            if (hasNext())
            {
                tempItem = _menu.MenuItems.ElementAt(_position);
                tempPrice = tempItem.GetCost();
                // Flatten multiple lists to a string
                foreach(MenuSubItem_A menuSubItem_A in tempItem.SubItems)
                {
                    tempPrice += menuSubItem_A.GetCost();
                    tempDescription.Append(menuSubItem_A.GetTitle() + " - " + menuSubItem_A.GetCost() + "\n");
                }

                switch (tempItem.ItemMenuType) 
                {
                    case MenuItem_A.MenuType.Breakfast:
                        commonTime = CommonItem.MenuTime.Morning;
                        break;
                    case MenuItem_A.MenuType.Lunch:
                        commonTime = CommonItem.MenuTime.Lunch;
                        break;
                    case MenuItem_A.MenuType.Dinner:
                        commonTime = CommonItem.MenuTime.Evening;
                        break;
                    case MenuItem_A.MenuType.Common:
                        commonTime = CommonItem.MenuTime.Day;
                        break;
                    default:
                        commonTime = CommonItem.MenuTime.Day;
                        break;
                }

                commonItem = new CommonItem(tempItem.GetTitle(), tempDescription.ToString(), tempPrice, commonTime);
                _position++;
            }
            return commonItem;
        }
    }
}
