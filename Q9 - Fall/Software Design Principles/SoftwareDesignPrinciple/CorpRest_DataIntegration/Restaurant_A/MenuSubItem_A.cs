using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CorpRest_DataIntegration.Restaurant_A
{
    public class MenuSubItem_A
    {
        private string _title = null;

        private double _cost = 0.0;

        public MenuSubItem_A(string title, double cost)
        {
            _title = title;
            _cost = cost;
        }

        public string GetTitle()
        {
            return _title;
        }

        public double GetCost()
        {
            return _cost;
        }
    }
}
