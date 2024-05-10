using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CorpRest_DataIntegration.Restaurant_B
{
    public class MenuItem_B
    {
        private string _itemName = null;
        private string _itemDesc = null;
        private double _itemCost = 0;
        private ServiceTime _serviceTime;
        // //////////////////////////////////////
        public MenuItem_B(string itemName, string itemDesc, double itemCost, ServiceTime serviceTime)
        {
            _itemName = itemName;
            _itemDesc = itemDesc;
            _itemCost = itemCost;
            _serviceTime = serviceTime;

        }
        // //////////////////////////////////////
        public string ItemName { get { return _itemName; } }
        public string ItemDesc { get { return _itemDesc; } }
        public double ItemCost { get { return _itemCost; } }
        public ServiceTime ServTime{ get { return _serviceTime; } }

        // //////////////////////////////////////
        public enum ServiceTime { Morning, Noon, Late }

    }
}
