using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace CorpRest_DataIntegration.Restaurant_B
{
    
    public class Menu_B : AbstractMenu
    {
        private int _MAXSIZE = 100;
        private int _itemCount = 0;
        private MenuItem_B[] _items;
        // //////////////////////////////////////////
        public Menu_B()
        {
            _items = new MenuItem_B[_MAXSIZE];

            // ---------------
            // Add records
            FakeDataLoad_B();
        }
        // //////////////////////////////////////////
        public void AddItem(MenuItem_B item)
        {
            _items[_itemCount] = item;
            _itemCount++;
        }
        // //////////////////////////////////////////
        private void FakeDataLoad_B()
        {
            // -------------------------------------
            // Add Fake Data
            AddItem(new MenuItem_B("Pancakes", "4 Pancakes, Juice, your choice of syrup, and sausage", 4.50, MenuItem_B.ServiceTime.Morning));
            AddItem(new MenuItem_B("Ham Sandwich", "4 Pancakes, Juice, your choice of syrup, and sausage", 4.75, MenuItem_B.ServiceTime.Noon));
            AddItem(new MenuItem_B("Steak", "4 Pancakes, Juice, your choice of syrup, and sausage", 5.00, MenuItem_B.ServiceTime.Late));
            AddItem(new MenuItem_B("Eggs", "4 Pancakes, Juice, your choice of syrup, and sausage", 4.50, MenuItem_B.ServiceTime.Morning));
            AddItem(new MenuItem_B("Bacon", "4 Pancakes, Juice, your choice of syrup, and sausage", 4.75, MenuItem_B.ServiceTime.Noon));
            AddItem(new MenuItem_B("Sausage", "4 Pancakes, Juice, your choice of syrup, and sausage", 5.00, MenuItem_B.ServiceTime.Late));
        }
        // //////////////////////////////////////////
        public MenuItem_B getMenuItem_B(int index)
        {
            MenuItem_B itemReturn = null;
            // --------------------------------------
            try
            {
                itemReturn = _items[index];
            }
            catch(Exception ex)
            {
                int x = 1;
            }

            return itemReturn;
        }
        // //////////////////////////////////
        public override IIterator getIterator()
        {
            return new Menu_B_Iterator(this);
        }

        public override string GetName()
        {
            return "Restaurant B";
        }

        public override string GetDesc()
        {
            return "Aquired restauant";
        }

        public override double GetPrice()
        {
            return 0;
        }

        public MenuItem_B[] Items { get { return _items; } }
        // //////////////////////////////////////////
        public int ItemCount { get { return _itemCount; } }
        // //////////////////////////////////////////
    }
}
