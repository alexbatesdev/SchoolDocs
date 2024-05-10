using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CorpRest_DataIntegration.Restaurant_A
{
    public class Menu_A : AbstractMenu
    {
        private List<MenuItem_A> _itemList = new List<MenuItem_A>();

        public Menu_A() { 
            FakeDataLoad_A();
        }

        public override string GetDesc()
        {
            return "Restaurant A menu";
        }

        public override IIterator getIterator()
        {
            return new Menu_A_Iterator(this);
        }

        public override string GetName()
        {
            return "Menu A";
        }

        public override double GetPrice()
        {
            return -1;
        }

        private void FakeDataLoad_A()
        {
            MenuItem_A tempItem = null;
            tempItem = new MenuItem_A("2 Egg Breakfast", 15.25, MenuItem_A.MenuType.Breakfast);
            tempItem.AddSubItem(new MenuSubItem_A("Bacon", 5.05));
            tempItem.AddSubItem(new MenuSubItem_A("Toast", 4.55));

            _itemList.Add(tempItem);

            tempItem = new MenuItem_A("Grilled Cheese", 25.25, MenuItem_A.MenuType.Lunch);
            tempItem.AddSubItem(new MenuSubItem_A("Pickle", 5.05));
            tempItem.AddSubItem(new MenuSubItem_A("Cookie", 4.55));

            _itemList.Add(tempItem);

            tempItem = new MenuItem_A("Sirloin Steak", 55.25, MenuItem_A.MenuType.Dinner);
            tempItem.AddSubItem(new MenuSubItem_A("Pickle", 5.05));
            tempItem.AddSubItem(new MenuSubItem_A("Meat Juice", 4.55));

            _itemList.Add(tempItem);

            tempItem = new MenuItem_A("Mighty Steamed Meat and Greens", 95.25, MenuItem_A.MenuType.Dinner);
            tempItem.AddSubItem(new MenuSubItem_A("Hyrule Herb", 5.05));
            tempItem.AddSubItem(new MenuSubItem_A("Mighty Durians", 4.55));

            _itemList.Add(tempItem);
        }

        public List<MenuItem_A> MenuItems { get { return _itemList; } }
    }
}
