using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CorpRest_DataIntegration.Corp
{
    internal class CommonItem : AbstractMenu
    {
        private string _name;
        private string _desc;
        private double _price = 0;
        private MenuTime _time = MenuTime.Day;


        public CommonItem(string name, string description, double price, MenuTime time)
        { 
            _name = name;
            _desc = description;
            _price = price;
            _time = time;
        }


        public override string GetDesc()
        {
            return _desc;
        }
        public override IIterator getIterator()
        {
            return null;
        }
        public override string GetName()
        {
            return _name;
        }
        public override double GetPrice()
        {
            return _price;
        }
        public List<AbstractMenu> MenuItems { get { return _menuList; } }

        public enum MenuTime { Day, Morning, Lunch, Evening, Night }
    }
}
