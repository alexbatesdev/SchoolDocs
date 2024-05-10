using System;
using System.Collections.Generic;
using System.Linq;
using System.Resources;
using System.Text;
using System.Threading.Tasks;

namespace CorpRest_DataIntegration
{
    public abstract class AbstractMenu
    {
        protected List<AbstractMenu> _menuList = new List<AbstractMenu>();

        public void AddItem(AbstractMenu item)
        {
            _menuList.Add(item);
        }

        public void RemoveItem(AbstractMenu item)
        {
            _menuList.Remove(item);
        }

        public abstract string GetName();
        public abstract string GetDesc();
        public abstract double GetPrice();
        public abstract IIterator getIterator();

        public virtual void Print()
        {
            double dPrice = this.GetPrice();
            // Delineator -----------------------
            Console.WriteLine(" ******************************** ");
            Console.WriteLine(this.GetName());
            Console.WriteLine(" ================================ ");
            Console.WriteLine(this.GetDesc());
            if (dPrice > 0) Console.WriteLine("Price: " + dPrice.ToString());
            Console.WriteLine(" -------------------------------- ");
            IIterator iter = this.getIterator();
            while (iter != null && iter.hasNext())
            {
                AbstractMenu item = (AbstractMenu)iter.next();
                item.Print();
            }
        }
    }
}
